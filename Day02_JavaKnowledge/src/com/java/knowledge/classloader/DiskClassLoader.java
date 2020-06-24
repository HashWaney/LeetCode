package com.java.knowledge.classloader;


import sun.rmi.runtime.Log;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DiskClassLoader extends ClassLoader {


    public String path;

    public DiskClassLoader(String path) {
        this.path = path;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;

        System.err.println("name:" + name);
        byte[] classData = loadClassData(name);

        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            clazz = defineClass(name, classData, 0, classData.length);

            new Thread() {
                @Override
                public void run() {
                    super.run();
                }
            }.start();
        }
        return clazz;
    }


    public byte[] loadClassData(String name) {
        String fileName = getFileName(name);

        File file = new File(path, fileName);
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(file);
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String getFileName(String name) {
        int index = name.lastIndexOf(".");
        if (index != -1) {
            return name.substring(index + 1) + ".class";
        } else {
            return name + ".class";
        }
    }

    public static void main(String[] args) {
        DiskClassLoader diskClassLoader = new DiskClassLoader("/Users/wangqing/Desktop/Java");
        try {
            Class c = diskClassLoader.loadClass("org.hash.classload.test.ClassLoadTest");
            if (c != null) {
                Object o = c.newInstance();
                System.err.println("classloader :" + o.getClass().getClassLoader());
                Method method = c.getDeclaredMethod("sayHello", null);
                method.invoke(o, null);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        MyRunnable runnable = new MyRunnable();

        new Thread(runnable) {
            @Override
            public void run() {
//                System.err.println("Thread run start\n");
//                super.run();
//                System.err.println("Thread run end\n");
            }
        }.start();
//
//
//        Timer timer = new Timer();
//        MyTimerTask timerTask = new MyTimerTask();
//        timer.schedule(timerTask, 2000, 2000);
//
//        new Thread("thread-print1-1") {
//            @Override
//            public void run() {
//                super.run();
//
//                print3();
//            }
//        }.start();
//
//        new Thread("thread-print1-2") {
//            @Override
//            public void run() {
//                super.run();
//
//                print3();
//            }
//        }.start();
        Business business = new Business();
        new Thread("ChildThread") {
            @Override
            public void run() {
                for (int i = 1; i <=50; i++) {
                    business.child(i);
                }
            }
        }.start();
        for (int i = 1; i <=50; i++) {
            business.main(i);
        }


    }




    static class Business {

        boolean isChildHandle = true; //默认子线程执行

        public synchronized void child(int k) {
            if (!isChildHandle) {
                try {
                    this.wait(); //如果当前不是子线程操作，等待 下列代码就不会执行了
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println("child thread seq:" + i + "  loop at the times of " + k);
            }
            isChildHandle = false; //子线程处理完毕，重置标志位
            this.notify(); //唤醒主线程
        }

        public synchronized void main(int k) {
            if (isChildHandle) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 7; i++) {
                System.out.println("main thread seq:" + i + "  loop at the times of " + k);
            }
            isChildHandle = true; //同理
            this.notify();
        }
    }

    public synchronized static void print() {

    }

    public void print2() {
        synchronized (this) {

        }
    }

    public static void print3() {

        String lock = new String("lock");
        synchronized (lock) {
            int i = 5;
            do {

                System.err.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (i-- > 0);
        }

    }

    static class MyTimerTask extends TimerTask {


        private static int count = 0;

        private MyTimerTask() {

        }

        @Override
        public void run() {
            System.err.println("timer task run _" + new Date().getTime());
            count = (count + 1) % 2; // 保证0 和 1


        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.err.println("myRunnable");
        }
    }

    static class MyThread extends Thread {


        @Override
        public void run() {
            System.err.println("myThread");
        }
    }
}
