package com.java.knowledge.classloader;

import java.util.Random;

public class Test {


    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int k = new Random().nextInt();
                    ThreadShareData.getInstance().setName("name:" + k);
                    ThreadShareData.getInstance().setAge(k);
                    try {
                        Thread.sleep(10); //模仿线程竞争
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread().getName() + " put value :" + k);
                    new A().get();
                    new B().get();

                }
            }).start();
        }
    }

    static class A {
        public void get() {
            ThreadShareData instance = ThreadShareData.getInstance();

            System.out.println("A from " + Thread.currentThread().getName() + " get value:" + instance.getName()
            );
        }
    }

    static class B {
        public void get() {
            ThreadShareData instance = ThreadShareData.getInstance();
            System.out.println("B from " + Thread.currentThread().getName() + " get value:" + instance.getName());
        }
    }


    static class ThreadShareData {
        private ThreadShareData() {

        }

        private static ThreadLocal<ThreadShareData> local = new ThreadLocal<>();

        public static ThreadShareData getInstance() {
            ThreadShareData threadShareData = local.get();
            if (threadShareData == null) {
                threadShareData = new ThreadShareData();
                local.set(threadShareData);
            }
            return threadShareData;
        }

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }
}


