package com.java.knowledge.base;

import sun.rmi.runtime.Log;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

public class Father<T> {

    //静态方法是属于类的 如果要是用这个方法，肯定创建出这个类
    public static void print() {
        System.out.println("father print");
    }

    public void log() {
        System.out.println("father log ");
    }

    static class Child extends Father {
        @Override
        public void log() {
            System.out.println("child log ");


        }

        public int addSum(int a, int b) {
            return a + b;
        }


        public String sysClassInfo() {
            Class<? extends Child> aClass = this.getClass();
            for (Method declaredMethod : aClass.getDeclaredMethods()) {
                /**
                 * 也就是说调用Method的getParameterTypes 是用来获取方法的参数类型的，如果没有参数就直接返回
                 * 好比addSum方法 打印为int int ,因为方法中传递了两个int类型的参数
                 */
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                for (Class<?> parameterType : parameterTypes) {
                    System.out.println("parameterType: " + parameterType.getName());
                }

            }

            return "lala";
        }

    }

    static <T> T[] pickTwoElement(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return toArray(a, c);
            case 1:
                return toArray(a, b);
            case 2:
                return toArray(b, c);

        }//Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String; 编译时期不会产生任何警告，但是在运行时期，会抛出一个ClassCastException
        throw new AssertionError();

    }


    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(T a, T c) {
        Object[] objects = {a, c};
        return (T[]) objects;
    }

    public static void main(String[] args) {
        Father father = new Child();
        Child child = new Child();
        Father father1 = new Father();
        father1.log(); //father log
        System.out.println("-----------0");
        child.log();
        child.addSum(1, 1);
        child.sysClassInfo();
        // child log
        System.out.println("-----------1");
        child.print(); //father print
        System.out.println("------------2");
        father.log(); // child log          father --- > child  运行时确定类型 new 的 是堆中的实例
        // ，                而定义的变量是Father -> 指向child类型的应用，因此类型虽然定义
        System.out.println("------------3");
        Father.print(); //
        System.out.println("------------4");
        String[] strings = pickTwoElement("Good", "Fast", "Code");
        father.print();
    }
}
