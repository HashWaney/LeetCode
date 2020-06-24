package com.java.knowledge;

import com.java.Bird;

import java.util.*;

public class BirdController {
    public static void main(String[] args) {

        Penguin penguin = new Penguin();
        Goose goose = new Goose();
        //向上转型， 将Penguin转换为Bird类，bird 接收到消息调用move方法，同时penguin也会接收到调用move方法的信息
        doMove(penguin);
        doMove(goose);
        Set<String> set = new HashSet<>();
        set.add("aaa");


        set.add("bbb");
        set.add("aaa");

        TreeSet treeSet = new TreeSet();
        treeSet.add("aaaa");
        treeSet.add("bbbb");
        treeSet.add("aaaa");

        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("aaa");
        linkedHashSet.add("bbb");
        linkedHashSet.add("aaa");


        List<String> list =new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        System.err.println("list:"+list.size());


        Map<String,String> map =new HashMap<>();

        map.put("aaa","1111");
        map.put("bbb","2222");
        map.put("aaa","1111");
        System.err.println("map size:"+map.size());


        System.err.println("set size : " + set.size() + " tree set size:" + treeSet.size()
        +" link set size: "+linkedHashSet.size());

    }

    public static void doMove(Bird bird) {
        bird.move();
    }
}
