package com.java.knowledge.reflect;

import java.lang.reflect.Field;

public class TestReflect {

    private int age;

    private String name;


    public static void main(String[] args) {
        Class<TestReflect> reflectClass = TestReflect.class;
        for (Field declaredField : reflectClass.getDeclaredFields()) {
            System.out.println("field Name:'" + declaredField.getName() + "'");
        }
        ;
    }
}
