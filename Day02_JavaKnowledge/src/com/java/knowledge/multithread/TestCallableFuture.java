package com.java.knowledge.multithread;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.*;

public class TestCallableFuture {
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            System.err.println("计算结果中....");
            Thread.sleep(1000);
            for (int i = 0; i < 1000; i++) {
                sum += i;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future result = executor.submit(task);
        executor.shutdown();

        try {

            Thread.sleep(1000);
        } catch (Exception e
        ) {
            e.printStackTrace();
        }

        Thread thread =new Thread();

        System.err.println("主线程执行");

        try {
            if (result.get() != null) {
                System.err.println("执行结果为：" + result.get());
            } else {
                System.err.println("未获取到执行结果");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.err.println("任务执行完毕");
    }
}
