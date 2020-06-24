package com.java.knowledge.array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestArray {


    //Chooser 原始版本
    public class ChooserOriginal {
        private final Object[] choiceArray;

        public ChooserOriginal(Collection collection) {
            choiceArray = collection.toArray();
        }

        //要想使用这个类，将需要将Object转换每次调用这个方法所要的类型
        //如果搞错类型，就会导致类型转换异常
        //那就需要将 ChooserOriginal转换为泛型---》
        public Object choose() {
            Random random = ThreadLocalRandom.current();
            return choiceArray[random.nextInt(choiceArray.length)];
        }
    }

    //Chooser 泛型版本
    public class ChooserGenericArray<T> {

        private final T[] choiceArray;


        // TODO 无法在运行时检查转换的安全性，因为程序在运行时不知道T是什么类型（元素类型在运行时就会被擦出）
        public ChooserGenericArray(Collection<T> collection) {
            choiceArray = (T[]) collection.toArray();
        }

        public T choose() {
            Random random = ThreadLocalRandom.current();
            return choiceArray[random.nextInt(choiceArray.length)];
        }
    }

    //Chooser 列表版本

    /**
     * 这个版本代码 运行速度可能慢一点，但是在运行时不会得到ClassCastException
     *
     * 数组是协变的 （子类数组是父类数组的子类型，意味着 子类数组可以用父类数组来接收，然后通过父类型数组取值，如果添加的数据不是父类型，那么就会在运行时抛出类型转换异常）
     *       具体的 泛型擦除 List<String> list =new ArrayList<>(), ---- list 在运行时就是一个List 和List<String>无关
     *
     *       提供了运行时的类型安全，但是没有编译时的类型安全保证
     *
     *
     * 泛型不可变
     *     类型擦除
     *
     *     提供了编译时的类型安全，但是经过泛型擦除之后无法保证运行时类型安全；
     *
     *
     *     那么列表保证了
     *
     * @param <T>
     */
    public class ChooserGenericList<T> {

        private final List<T> choiceList;

        public ChooserGenericList(Collection collection) {
            choiceList = new ArrayList<>(collection);
        }

        public T choose() {
            Random random = ThreadLocalRandom.current();
            return choiceList.get(random.nextInt(choiceList.size()));

        }

    }


}
