package com.java.knowledge.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestBlockingQueue {
    private final static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
    protected static Integer count = 0;

    public static void main(String[] args) {
//        for (int i = 0; i < 4; i++) {
////            new Thread(new Producer(), "Producer-Thread-" + i).start();
////            new Thread(new Customer(), "Customer-Thread-" + i).start();
////        }
        //
        new Thread(new Producer(), "Producer-Thread-" + 1).start();
        new Thread(new Customer(), "Customer-Thead-" + 1).start();
        new Thread(new Producer(), "Producer-Thread-" + 2).start();
        new Thread(new Customer(), "Customer-Thead-" + 2).start();
        new Thread(new Producer(), "Producer-Thread-" + 3).start();
        new Thread(new Customer(), "Customer-Thead-" + 3).start();
        new Thread(new Producer(), "Producer-Thread-" + 4).start();
        new Thread(new Customer(), "Customer-Thead-" + 4).start();

    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    blockingQueue.add(1); // 队列满了 此处会进行阻塞
                    count++;
                    System.err.println(" producer count:"+count+"\n");
                    System.out.println(Thread.currentThread().getName() + " 生产者生产，目前总共有" + count);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Customer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    blockingQueue.take();
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
