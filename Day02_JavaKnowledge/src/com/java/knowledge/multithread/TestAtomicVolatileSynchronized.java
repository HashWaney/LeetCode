package com.java.knowledge.multithread;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicVolatileSynchronized {

    private static volatile int volatileCount = 0;
    private static volatile boolean volatileFlag = true;
    private static AtomicInteger atomicCount = new AtomicInteger(0);
    private static int synchronizedCount = 0;
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(true);


    public static void main(String[] args) {
//        volatileCount();
//        atomicCount();
//        synchronizedCount();
        testCas();
    }

    private static void testCas() {
        CasRunnable casRunnable = new CasRunnable();
        new Thread(casRunnable, "Thread-1").start();
        new Thread(casRunnable, "Thread-2").start();
    }

    static class CasRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " outer flag:" + atomicBoolean.get());
            if (atomicBoolean.compareAndSet(true, false)) {
                System.out.println(Thread.currentThread().getName() + " inner flag:" + atomicBoolean.get());
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Thread1 将共享内存标志置为true，即Thread1完成了任务，终止了，那么这个时候共享标志位为true，Thread2就可以进行数据的更改了，然后退出。

                atomicBoolean.set(true);
                System.out.println("执行完毕---->" + Thread.currentThread().getName() + "准备退出 flag:" + atomicBoolean.get());
            } else {
                System.err.println("重试机制：" + Thread.currentThread().getName() + " flag:" + atomicBoolean.get());
                try {
                    Thread.sleep(300);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                run();
            }

        }
    }

    private static void volatileCount() {
        for (int i = 0; i < 10; i++) {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //虽然使用volatile关键字修饰int变量，在多线程环境下，也很难保证没问题
                    //所以一般用来修饰标志位
                    volatileFlag = !volatileFlag;
                    System.err.println("volatile count :" + (++volatileCount) + " volatile flag:" + volatileFlag);

                }
            });
            executor.shutdown();

        }

    }


    private static void atomicCount() {
        for (int i = 0; i < 10; i++) {
            Executors.newFixedThreadPool(3).execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.err.println("atomicCount:" + atomicCount.incrementAndGet());
                }

            });
        }
    }

    private static void synchronizedCount() {
        for (int i = 0; i < 10; i++) {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (String.class) {
                        System.err.println("synchronizedCount:" + (++synchronizedCount));
                    }
                }
            });
            executor.shutdown();
        }

    }


}
