package com.java.knowledge.multithread;


public class TestMultiThreadShareData {

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        for (int i = 0; i < 4; i++) {
            new Thread(runnable, "窗口" + i).start();
        }

    }




    static class MyRunnable implements Runnable {
        private int count = 10;

        private Object lock = new Object();

        public  void sellTicket() {
            if (count > 0) {
                count--;
                System.err.println(Thread.currentThread().getName() + "正在卖票：还剩下：" + count + "张票");

            } else {
                System.err.println("票已经卖完了");
                return;
            }
        }


        @Override
        public void run() {
            synchronized (this) { //同步
                while (count > 0) {
                    sellTicket(); //同步代码块
                    try {

                        // 在sellTicket中休眠。是为了自己不执行，也不让自己执行，
                        //为了缓解CPU压力，
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

//            while (count > 0) {
//                sellTicket(); //同步代码块
//                try {
//
//                    // 在sellTicket中休眠。是为了自己不执行，也不让自己执行，
//                    //为了缓解CPU压力，
//                    Thread.sleep(100);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }

        }
    }
}
