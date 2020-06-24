package com.java.knowledge;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private final Object originalReporter;


    public DynamicProxy(Object o) {
        this.originalReporter = o;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.err.println(String.format("method %s",method.getName()));


        return method.invoke(proxy, args);

    }
}
