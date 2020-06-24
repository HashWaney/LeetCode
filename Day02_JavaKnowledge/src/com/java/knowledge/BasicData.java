package com.java.knowledge;

public class BasicData {

    public static void main(String[] args) {
        byte b;
        char c;
        short s;
        int i;
        long l;
        float f;
        double d;
        boolean bo;

        //将基本数据类型转换为其对应的对象



        //整型
        //    byte 1个字节
        // 短整型 short 2个字节
        // 整型 int   4个字节
        // 长整型 long 8个字节
        // 浮点型
        // 单精度 float 4个字节
        // 双精度 double 8个字节
        //字符型 char 2 个字节

        // 布尔型 boolean  1 个字节
        int a = (int) 2.3;
        // [-128 127]

         byte test1 = 12;
       final  byte test2 = 2;
       final byte test3 =12, test5=4;



//        test1 += test2;

//        test1 =test1+test2;
        System.err.println("test1 :"+test1);

        System.err.println(String.format(" byte %d\n char  %d\n short %d\n int %d\n long %d\n float %d\n double %d\n a =%d\n", Byte.BYTES, Character.BYTES, Short.BYTES,
                Integer.BYTES, Long.BYTES, Float.BYTES, Double.BYTES, a));
    }
}
