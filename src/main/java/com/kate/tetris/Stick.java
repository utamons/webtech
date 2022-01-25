package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Stick extends Shape {
    public Stick() {
        shapeArr = new ArrayCell[1][4];
        arrHeight=shapeArr.length;
        arrWidth=shapeArr[0].length;
        for (int x = 0; x < shapeArr[0].length; ++x) {
                shapeArr[0][x] = new ArrayCell();
                shapeArr[0][x].setValue(1);
                shapeArr[0][x].setStyle("background-color: cyan; color:cyan");

        }
    }
}
