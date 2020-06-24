package com.java.knowledge;

/**
 * 对于静态字段，只有直接定义这个字段的类才会初始化，
 * 子类是通过引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化
 */
public class ClassLoadingInit  {
    public static void main(String[] args) {
        //Super Class init!!
        //loading class :value 2
        System.err.println("loading class :value "+SubClassLoading.value);

        // findClass ： 维护双亲委托机制
        // loadClass：  破坏双亲委托机制






    }
}
