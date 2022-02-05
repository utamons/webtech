package com.kate.tetris;

import com.kate.model.ArrayCell;

public class S extends Shape {
    public S() {
        super(2,3,"green");
        activeCell(0,0);
        activeCell(0,1);
        activeCell(1,1);
        activeCell(1,2);
    }
}
