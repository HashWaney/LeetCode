package com.java.knowledge;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;

public class CloseGuardHooker {


    private volatile static Object originalReporter;

    private volatile boolean mIsTryHook;

    public void hook() {
        if (!mIsTryHook) {
            tryHook();
            mIsTryHook = true;
        }

    }

    private void tryHook() {
        try {

            Class<?> closeGuardClazz = Class.forName("dalvik.system.CloseGuard");
            //Reporter 为CloseGuard中的一个内部类
            Class<?> closeGuardReporters =
                    Class.forName("dalvik.system.CloseGuard$Reporter");


            Method methodGetReporter = closeGuardClazz
                    .getDeclaredMethod("getReporter");

            originalReporter = methodGetReporter.invoke(null);



            // 方法是带有参数的
            Method methodSetReporter = closeGuardClazz
                    .getDeclaredMethod("setReporter", closeGuardReporters);

            //setEnabled(boolean enabled)
            Method methodSetEnabled = closeGuardClazz.getMethod("setEnabled", boolean.class);
            methodSetEnabled.invoke(null, true);


            ClassLoader classLoader = closeGuardReporters.getClassLoader();
            if (classLoader == null) {
                return;
            }

            methodSetReporter.invoke(null, Proxy.newProxyInstance(
                    classLoader,
                    new Class<?>[]{closeGuardReporters},
                    new DynamicProxy(originalReporter)
            ));


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
