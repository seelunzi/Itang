package org.tang.framework.task;

import org.apache.log4j.Logger;
import org.tang.framework.annotation.CronTask;
import org.tang.framework.point.AspectPoint;
import org.tang.framework.util.DateUtils;
import org.tang.framework.util.StringUtil;

import java.lang.reflect.Method;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/***
 * @author tang
 * */
public class TaskTrigger {

    static Logger logger = Logger.getLogger(TaskTrigger.class);

    public static Method getTriggerMethod() {
        Method[] methods = TaskTrigger.class.getDeclaredMethods();
        if (StringUtil.isNullOrEmpty(methods)) {
            return null;
        }
        for (Method method : methods) {
            if (method.getName().equals("taskTrigger")) {
                return method;
            }
        }
        return null;
    }

    private static Map<Method, ZonedDateTime> cronExpressionMap = new ConcurrentHashMap<Method, ZonedDateTime>();


    public static void nextRun(Object bean, Method method, String cron, ZonedDateTime zonedDateTime) {
        //获取下次执行时间
        CronExpression express = new CronExpression(cron);
        if (zonedDateTime == null) {
            zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        }
        zonedDateTime = express.nextTimeAfter(zonedDateTime);
        cronExpressionMap.put(method, zonedDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.DATETIME_PATTERN, Locale.CHINA);
        Date nextRunDate = DateUtils.toDate(zonedDateTime.toLocalDateTime().format(formatter));
        logger.debug(bean.getClass().getName() + ":" + method.getName() + " will run on " + DateUtils.toString(nextRunDate));
        long timeRage = nextRunDate.getTime() - System.currentTimeMillis();
        TaskThreadPool.taskPool.schedule(() -> {
            Object[] params = {};
            try {
                method.invoke(bean, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, timeRage, TimeUnit.MILLISECONDS);
    }

    /**
     * 定时任务管理
     *
     * @param aspect
     * @return
     * @throws Throwable
     */
    public static Object taskTrigger(AspectPoint aspect) throws Throwable {
        Method method = aspect.getMethod();
        CronTask cronTask = method.getAnnotation(CronTask.class);
        Object bean = aspect.getBean();
        String cron = cronTask.value();
        try {
            return aspect.invoke();
        } finally {
            ZonedDateTime zonedDateTime = cronExpressionMap.get(method);
            nextRun(bean, method, cron, zonedDateTime);
        }
    }
}
