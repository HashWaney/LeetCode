package com.java.knowledge.multithread;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCustomerProducer {

    private static Integer count = 0;
    private static final Integer FULL = 10;
    //1
    private static String LOCK = "lock";


    ///2
    private static Lock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
//        waitAndNotify();
        reentrantLock();
//        blockQueue();
    }

    private static void blockQueue() {


    }


    public static void reentrantLock() {
        new Thread(new Producer1(), "生产者1").start();
        new Thread(new Customer1(), "消费者1").start();
        new Thread(new Producer1(), "生产者2").start();
        new Thread(new Customer1(), "消费者2").start();
        new Thread(new Producer1(), "生产者3").start();
        new Thread(new Customer1(), "消费者3").start();

    }

    private static void waitAndNotify() {

        //1
        new Thread(new Producer(), "生产者1").start();
        new Thread(new Consumer(), "消费者1").start();
        new Thread(new Producer(), "生产者2").start();
        new Thread(new Consumer(), "消费者2").start();
        new Thread(new Producer(), "生产者3").start();
        new Thread(new Consumer(), "消费者3").start();
        new Thread(new Producer(), "生产者4").start();
        new Thread(new Consumer(), "消费者4").start();
        //2
//    new Thread(new Producer(), "Producer-Thread-1").start();
//    new Thread(new Producer(), "Producer-Thread-2").start();
//    new Thread(new Producer(), "Producer-Thread-3").start();
//    new Thread(new Producer(), "Producer-Thread-4").start();
//    new Thread(new Customer(), "Customer-Thread-1").start();
//    new Thread(new Customer(), "Customer-Thread-2").start();
//    new Thread(new Producer(), "Customer-Thread-3").start();
//    new Thread(new Producer(), "Customer-Thread-4").start();
        //对比：第一种方式交替创建生产者消费者线程 ： 达到了我们需要的
        //第二种先创建完生产者线程，在创建消费者线程，第二种就会出现一直进行消费生产的局面，无法退出
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count == FULL) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    static class Producer1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == FULL) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    notEmpty.signal(); //存储空间不为空
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Customer1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == 0) {
                        try {
                            notFull.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    notFull.signal();
                } finally {
                    lock.unlock();
                }
            }

        }
    }

//    public static void waitAndNotify()
//        new Thread(new Producer(), "Producer-Thread-1").start();
//        new Thread(new Producer(), "Producer-Thread-2").start();
//        new Thread(new Producer(), "Producer-Thread-3").start();
//        new Thread(new Producer(), "Producer-Thread-4").start();
//        new Thread(new Customer(), "Customer-Thread-1").start();
//        new Thread(new Customer(), "Customer-Thread-2").start();
//        new Thread(new Producer(), "Customer-Thread-3").start();
//        new Thread(new Producer(), "Customer-Thread-4").start();


//
//    }

//    static class Producer implements Runnable {
//        @Override
//        public void run() {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(3000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //解决同步问题
//                synchronized (fullLock) {
//                    while (count == 10) {
//                        // wait  full
//                        try {
//                            fullLock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    count++;
//                    //线程间同步消息
//                    System.out.println(Thread.currentThread().getName() + " producer product count :" + count);
//                    fullLock.notifyAll();
//                }
//            }
//        }
//    }
//
//
//    static class Customer implements Runnable {
//        @Override
//        public void run() {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(3000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                synchronized (fullLock) {
//                    while (count == 0) {
//                        try {
//                            fullLock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    count--;
//                    System.out.println(Thread.currentThread().getName() + " customer custom count:" + count);
//                    fullLock.notifyAll();
//                }
//            }
//
//        }
//    }
}
