package org.tang.framework.task;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TaskThreadPool {

    public static final ScheduledThreadPoolExecutor taskPool = new ScheduledThreadPoolExecutor(30);


}
