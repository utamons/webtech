package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Z extends Shape {
    public Z() {
        shapeArr = new ArrayCell[2][3];
        arrHeight=shapeArr.length;
        arrWidth=shapeArr[0].length;
        for (int y = 0; y < shapeArr.length; ++y) {
            for (int x = 0; x < shapeArr[0].length; ++x) {
                shapeArr[y][x] = new ArrayCell();
                shapeArr[y][x].setValue(1);
                shapeArr[y][x].setStyle("background-color: red; color:red");
            }
        }
        shapeArr[0][2] = new ArrayCell();
        shapeArr[0][2].setValue(0);
        shapeArr[0][2].setStyle("background-color: white; color:white");
        shapeArr[1][0] = new ArrayCell();
        shapeArr[1][0].setValue(0);
        shapeArr[1][0].setStyle("background-color: white; color:white");
    }
}
