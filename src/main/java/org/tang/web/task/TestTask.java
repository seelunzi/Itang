package org.tang.web.task;

import org.tang.framework.annotation.CronTask;
import org.tang.framework.annotation.InitBean;
import org.tang.framework.util.DateUtils;

/**
 * @author tang
 */
@InitBean
public class TestTask {

    @CronTask("0/5 * * * * ? ")
    public void test() {
        System.out.println("定时任务执行中:" + DateUtils.getDateTimeString());
    }

}
