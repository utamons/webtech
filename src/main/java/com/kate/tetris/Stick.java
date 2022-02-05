package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Stick extends Shape {
    public Stick() {
        super(1,4,"cyan");
        for (int i=0;i<shapeArr[0].length;++i) {
            activeCell(0, i);
        }
    }
}
