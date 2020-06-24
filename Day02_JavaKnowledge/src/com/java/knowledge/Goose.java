package com.java.knowledge;

import com.java.Bird;

public class Goose extends Bird {
    @Override
    public void move() {
        super.move();
        System.err.println("鹅 走路");
    }
}
