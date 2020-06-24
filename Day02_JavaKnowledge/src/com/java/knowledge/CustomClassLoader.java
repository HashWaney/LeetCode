package com.java.knowledge;


import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader {


    public static class KeepClassLoader extends ClassLoader {


        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            System.err.println("KeepClassLoader  name :" + name);
            //            File file =getClassFile();
            File file = getClassFile();
            try {

                //读取.class的二进制流
                FileInputStream fileInputStream = new FileInputStream(file);
                FileChannel fileChannel = fileInputStream.getChannel();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                WritableByteChannel writableByteChannel = Channels.newChannel(byteArrayOutputStream);
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                while (true) {
                    int i = fileChannel.read(allocate);
                    if (i == 0 || i == -1) {
                        break;
                    }

                    allocate.flip();
                    writableByteChannel.write(allocate);
                    allocate.clear();
                }
                fileInputStream.close();

                byte[] bytes = byteArrayOutputStream.toByteArray();
                Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
                return c;

            } catch (Exception e) {
                e.printStackTrace();
            }


            return super.findClass(name);


        }


        public int add(int a, int b) {
            return a + b;

        }

        public File getClassFile() {
            // 这就是一个.class 文件 --- 》 以这个被加载类的全限定名称 即 com.java.knowledge.Person --- > 二进制流
            File file = new File("/Users/wangqing/Desktop/Java/LeetCode/Day02_JavaKnowledge/out/production/Day02_JavaKnowledge/com/java/knowledge/Person.class");
            return file;
        }
    }

    public static class KillClassLoader extends ClassLoader {


        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            try {
                String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
                System.err.println(String.format("fileName %s\n",fileName));
                InputStream inputStream = getClass().getResourceAsStream(fileName);
                if (inputStream == null) {
                    return super.loadClass(name);

                }

                try {
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    Class<?> c = defineClass(fileName, bytes, 0, bytes.length);
                    return c;
                } catch (Exception e) {
                    e.printStackTrace();
                    return super.loadClass(name);
                }

            } catch (Exception e) {
                return super.loadClass(name);


            }


        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        KeepClassLoader keepClassLoader = new KeepClassLoader();
//        Object obj = Class.forName("com.java.knowledge.Person", true, keepClassLoader).newInstance();
//        System.err.println(obj);
//        System.err.println(obj.getClass().getClassLoader());
//        System.err.println(obj.getClass().getClassLoader().getParent());
//        System.err.println(obj.getClass().getClassLoader().getParent().getParent());



        KillClassLoader killClassLoader =new KillClassLoader();
        ///Users/wangqing/Desktop/Java/LeetCode/Day02_JavaKnowledge/src/com/java/knowledge/CustomClassLoader.java
        System.err.println(killClassLoader.getClass().getClassLoader().getParent());
        Object instance = killClassLoader.loadClass("com.java.knowledge.CustomClassLoader").newInstance();

//        System.err.println(instance);

    }


//    @Override
//    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//        return super.loadClass(name, resolve);
//    }
}
