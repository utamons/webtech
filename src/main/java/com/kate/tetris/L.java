package com.kate.tetris;

import com.kate.model.ArrayCell;

public class L extends Shape {
    public L() {
        super(2,3,"orange");
        activeCell(0,0);
        activeCell(0,1);
        activeCell(0,2);
        activeCell(1,2);
    }
}
