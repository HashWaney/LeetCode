package com.java.knowledge;

/**
 * 类的加载时机：
 * 1。加载：
 *              - 1 使用new 关键字实例化对象，读取或设置一个静态变量，调用静态方法
 *              - 2 反射调用
 *              - 3 当初始化类的时候，父类没有进行初始化，触发其父类的初始化
 *              - 4
 *
 *
 * 2。验证
 * 3。准备
 * 4。解析
 * 5。初始化
 * 6。使用
 * 7。卸载
 */
public class ClassLoading {
    static{
        System.err.println("Super Class init!!");
    }

    public static int value = 2;


}
