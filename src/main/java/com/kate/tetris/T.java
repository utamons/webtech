package com.kate.tetris;

import com.kate.model.ArrayCell;

public class T extends Shape {
    public T() {
        super(3,2,"purple");
        activeCell(1,0);
        activeCell(0,1);
        activeCell(1,1);
        activeCell(2,1);
    }
}
