package com.java.knowledge;

import com.java.Bird;

public class Penguin extends Bird {
    @Override
    public void move() {
        super.move();
        System.err.println("企鹅 游泳");
    }
}
