package com.kate.tetris;

public class Test extends Shape {
    public Test() {
        super(10, 1, "yellow");
        for (int i=0;i<shapeArr.length;++i) {
            activeCell(i, 0);
        }
    }
}
