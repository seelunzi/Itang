package org.tang.framework.task;

import com.google.appengine.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author tang
 */
public class TaskThreadPool {

    public static final ScheduledThreadPoolExecutor taskPool = new ScheduledThreadPoolExecutor(30);

    /***
     * 带名字的线程池
     * */
    public void ceshi() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();
    }

}
