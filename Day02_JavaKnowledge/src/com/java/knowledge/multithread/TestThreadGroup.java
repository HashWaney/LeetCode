package com.java.knowledge.multithread;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TestThreadGroup {
    public static void main(String[] args) {
        new Thread("Thread-1") {
            @Override
            public void run() {
                print();
            }
        }.start();
        new Thread("Thread-2") {
            @Override
            public void run() {
                print();
            }
        }.start();

        new Thread("Thread-3") {
            @Override
            public void run() {
                printName();
            }
        }.start();

        new Thread("Thread-4") {
            @Override
            public void run() {
                printName();
            }
        }.start();
    }


    public synchronized static void print() {
        System.err.println();
        System.err.println("<------Thread start---------\n");
        System.err.println("Current Thread name:" + Thread.currentThread().getName()+"\n");
        String source = "abc";
        char[] cs = source.toCharArray();
        for (int i = 0; i < 10; i++) {
            System.err.println("-------------->");
            for (int j = 0; j < cs.length; j++) {
                System.err.println(cs[j]);
            }
        }

        System.err.println("------Thread end--------->");
        System.err.println();
    }

    public synchronized static void printName() {
        synchronized (String.class)
        {
            System.err.println();
            System.err.println("<------Thread start---------\n");
            System.err.println("Current Thread name:" + Thread.currentThread().getName()+"\n");
            for (int i = 0; i < 10; i++) {
                System.err.println("------------>");
                System.err.println("the index is :" + (i + 1));
            }
            System.err.println("------Thread end--------->");
            System.err.println();
        }


    }
}
