package com.kate.tetris;

import com.kate.model.ArrayCell;

public class J extends Shape {
    public J() {
        super(2, 3, "blue");
        activeCell(1,0);
        activeCell(1,1);
        activeCell(1,2);
        activeCell(0,2);
    }
}
