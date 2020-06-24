package com.java.knowledge;

/**
 * 可达性性分析算法的逃逸
 */
@SuppressWarnings("unused")
public class FinalizeEscape {

    public static FinalizeEscape SAVE_SELF = null;

    public void isAlive() {
        System.err.println("is Alive");
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.err.println("finalize method execute");
        FinalizeEscape.SAVE_SELF = this;
    }

    public static void main(String[] args) throws Exception {
        SAVE_SELF = new FinalizeEscape();

        SAVE_SELF = null;
        System.gc();

        Thread.sleep(300);

        if (SAVE_SELF != null) {
            //TODO 自救成功
            SAVE_SELF.isAlive();
        }else{
            System.err.println("is dead");
        }



        // 第二次调用gc 并不会触发对象的finalize方法，任何对象的finalize方法只会被系统触发一次，因此第二次调用isAlive方法并不会触发this的赋值。
        SAVE_SELF = null;
        System.gc();

        Thread.sleep(300);

        if (SAVE_SELF != null) {
            SAVE_SELF.isAlive();
        }else{
            //TODO 自救失败
            System.err.println("is dead");
        }

    }

}
