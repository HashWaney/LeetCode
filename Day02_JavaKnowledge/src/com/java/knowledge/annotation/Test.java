package com.java.knowledge.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

@RuntimeClassInfo(className = "Test class annotation")
public class Test { // 修饰类变量的只能是public 和 default 类型


    @RuntimeFieldInfo(name = "age", value = 40)
    public static int age;


    @RuntimeMethodInfo(name = "print", id = 12, data = "Hello age")
    public static String print(@RuntimeFieldInfo(name = "message", value = 2) int message) {
        return age + "=" + message;
    }

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        //class 注解
        Class<Test> clazz = Test.class;
        RuntimeClassInfo runtimeClassInfo = clazz.getAnnotation(RuntimeClassInfo.class);
        if (runtimeClassInfo != null) {
            stringBuilder.append("Class注解所修饰元素的修饰符：")
                    .append(Modifier.toString(clazz.getModifiers()))
                    .append("\n")
                    .append("class Name：" + clazz.getSimpleName())
                    .append("\n")
                    .append("class 注解值为：")
                    .append("\n")
                    .append(runtimeClassInfo.className())
                    .append("\n\n");
        }


        // field 注解
        stringBuilder.append("Field注解：").append("\n");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            RuntimeFieldInfo fieldAnnotation = field.getAnnotation(RuntimeFieldInfo.class);
            if (fieldAnnotation != null) {
                //返回表示的字段的Java语言修饰符
                stringBuilder
                        .append("Field所注解元素的修饰符：")
                        .append(Modifier.toString(field.getModifiers())).append("\n")
                        .append("field type:").append(field.getType().getSimpleName()).append(" ")
                        .append("field Name:").append(field.getName()).append("\n")
                        .append("field 注解值为").append("\n")
                        .append(fieldAnnotation.name() + " : " + fieldAnnotation.value())
                        .append("\n");
            }
        }


        //Method 注解
        stringBuilder.append("method 注解：").append("\n");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            RuntimeMethodInfo methodAnnotation = method.getAnnotation(RuntimeMethodInfo.class);
            if (methodAnnotation != null) {
                stringBuilder
                        .append("method所注解元素的修饰符：") // public static
                        .append(Modifier.toString(method.getModifiers())).append("\n")
                        .append("方法返回值：")
                        .append(method.getReturnType().getSimpleName()).append("\n")
                        .append("方法名称:")
                        .append(method.getName()).append("\n");

                stringBuilder.append("注解值如下").append("\n")
                        .append("name:").append(methodAnnotation.name())
                        .append(" id:").append(methodAnnotation.id())
                        .append(" data:").append(methodAnnotation.data());
            }
        }

        System.out.println(stringBuilder.toString());
    }
}
