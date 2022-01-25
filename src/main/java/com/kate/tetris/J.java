package com.kate.tetris;

import com.kate.model.ArrayCell;

public class J extends Shape {
    public J() {
        shapeArr = new ArrayCell[2][3];
        arrHeight=shapeArr.length;
        arrWidth=shapeArr[0].length;
        shapeArr[0][0] = new ArrayCell();
        shapeArr[0][0].setValue(1);
        shapeArr[0][0].setStyle("background-color: blue; color:blue");
        for (int x = 1; x < shapeArr[0].length; ++x) {
            shapeArr[0][x] = new ArrayCell();
            shapeArr[0][x].setValue(0);
            shapeArr[0][x].setStyle("background-color: white; color:white");
        }
        for (int y = 1; y < shapeArr.length; ++y) {
            for (int x = 0; x < shapeArr[0].length; ++x) {
                shapeArr[y][x] = new ArrayCell();
                shapeArr[y][x].setValue(1);
                shapeArr[y][x].setStyle("background-color: blue; color:blue");
            }
        }
    }
}
