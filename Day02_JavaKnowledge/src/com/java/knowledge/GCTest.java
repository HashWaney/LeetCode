package com.java.knowledge;

public class GCTest {


    public static class ReferenceCountingGC {
        public Object instance = null;

        private static final int _1MB = 1024 * 1024;
        private byte[] bigSize = new byte[2 * _1MB];
    }

    /**
     *  A  和 B 相互引用， 因此计数器不可能为0 ，单纯的引用计数算法无法回收
     * @param args
     */
    public static void main(String[] args) {
        ReferenceCountingGC referenceCountingA =new ReferenceCountingGC();
        ReferenceCountingGC referenceCountingB =new ReferenceCountingGC();
        referenceCountingA.instance =referenceCountingB;
        referenceCountingB.instance =referenceCountingA;
        referenceCountingA=null;
        referenceCountingB=null;
        System.gc();
    }

}
