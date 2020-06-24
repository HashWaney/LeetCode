package com.java.knowledge.multithread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
    public static ThreadPoolExecutor pl = null;

    public static void main(String[] args) {
        pl = new ThreadPoolExecutor(4, 7, 60, TimeUnit.MILLISECONDS, new
                LinkedBlockingDeque<>());

        for (int i = 0; i < 10; i++) {
            pl.execute(new MyRunnable());
        }
        pl.shutdown();
    }

    static class MyRunnable implements Runnable {


        private int count = 0;

        private String getCount() {
            return pl.getCorePoolSize() + "-" + pl.getActiveCount() + "-" + pl.getMaximumPoolSize();
        }

        @Override
        public void run() {
            count++;
            System.err.println(
                  "Thread name:" + Thread.currentThread().getName() + " start " + getCount() +" count :"+count
            );
            try {
                Thread.sleep(3000L);
                System.err.println("Thread name:" + Thread.currentThread().getName() + " end " + getCount() +" count :"+count);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
