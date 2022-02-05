package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Z extends Shape {
    public Z() {
        super(2,3,"red");
        activeCell(1,0);
        activeCell(0,1);
        activeCell(1,1);
        activeCell(0,2);
    }
}
