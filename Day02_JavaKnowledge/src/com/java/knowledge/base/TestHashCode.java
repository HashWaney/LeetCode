package com.java.knowledge.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestHashCode {
    public static void main(String[] args) {
        Test testA = new Test();
        testA.setPersonId(13);
        testA.setName("Hash");
        Test testB = new Test();
        testB.setPersonId(13);
        testB.setName("Hash");
        Set<Test> set = new HashSet<>();
        set.add(testA);
        set.add(testB);
        String str = "abc";
        String str1 = new String("abc");
//        System.out.println(str.equals(str1) + " " + (str == str1));
//        System.out.println("testA hashCode:" + testA.hashCode() + "  testB hashCode:" + testB.hashCode()
//                + " testA == testB :" + (testA.hashCode() == testB.hashCode()));
//        System.out.println(testA.equals(testB));
//        System.out.println(set);

    }

    static class Test {
        protected int personId;

        protected String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPersonId() {
            return personId;
        }

        public void setPersonId(int personId) {
            this.personId = personId;
        }





        //重写还是重载
//        @Override
//        public int hashCode() {
//            return personId % 10;
//        }

        //
//        @Override
//        public boolean equals(Object obj) {
//            if (obj == null) {
//                return false;
//            }
//            if (obj == this) {
//                return true;
//            }
//
//            if (obj instanceof Test) {
//                if (((Test) obj).getPersonId() == this.getPersonId() && ((Test) obj).getName() == this.getName()) {
//                    return true;
//                }
//            }
//            return false;
//        }
    }
}
