package org.tang.framework.aspect;

import org.tang.framework.annotation.Around;
import org.tang.framework.annotation.InitBean;
import org.tang.framework.annotation.LogHead;
import org.tang.framework.point.AspectPoint;
import org.tang.framework.util.AspectUtil;
import org.tang.framework.util.PropertUtil;
import org.tang.framework.util.StringUtil;

import java.lang.reflect.Method;

@InitBean
public class LoggerAspect {


    /**
     * 日志标头设置
     *
     * @param wrapper
     * @return
     * @throws Throwable
     */
    @Around(annotationClass = LogHead.class)
    public Object transacted(AspectPoint wrapper) throws Throwable {
        try {
            // AOP获取方法执行信息
            Method method = wrapper.getMethod();
            Class<?> clazz = PropertUtil.getClass(method);
            String module = AspectUtil.getCurrLog();
            if (!StringUtil.isNullOrEmpty(module)) {
                module += "_";
            }
            String classLog = AspectUtil.getClassLog(clazz);
            if (!StringUtil.isNullOrEmpty(classLog)) {
                module += classLog;
            }
            if (!StringUtil.isNullOrEmpty(module)) {
                module += ".";
            }
            String methodLog = AspectUtil.getMethodLog(method);
            if (!StringUtil.isNullOrEmpty(methodLog)) {
                module += methodLog;
            } else {
                module += method.getName();
            }
            AspectUtil.writeLog(module);
            return wrapper.invoke();
        } finally {
            AspectUtil.minusLog();
        }
    }
}
