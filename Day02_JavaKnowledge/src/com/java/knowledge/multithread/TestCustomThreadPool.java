package com.java.knowledge.multithread;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCustomThreadPool {
    public static void main(String[] args) {
        int corePoolSize = 2; //核心线程数
        int maximumPoolSize = 4;//最大线程数
        long keepAliveTime = 10;//线程空闲时间
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3);
        ThreadFactory threadFactory = new CustomThreadFactory();
        RejectedExecutionHandler rejectedExecutionHandler = new IgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                unit, workQueue, threadFactory, rejectedExecutionHandler);

        executor.prestartAllCoreThreads(); //预先启动核心线程
        for (int i = 1; i <= 10; i++) {
            MyTask myTask = new MyTask(String.valueOf(i));
            executor.execute(myTask);
        }
        executor.shutdown();




    }



    static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "Thead-pool-" + threadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    static class IgnorePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.err.println(r.toString() + " rejected" + " remove the runnable :" + executor.remove(r));
        }
    }

    static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is Running");
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [Thread-pool-" + name + "]";
        }
    }
}
