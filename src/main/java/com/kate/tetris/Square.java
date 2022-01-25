package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Square extends Shape {


    public Square() {
        shapeArr = new ArrayCell[2][2];
        for (int y = 0; y < shapeArr.length; ++y) {
            for (int x = 0; x < shapeArr[0].length; ++x) {
                shapeArr[y][x] = new ArrayCell();
                shapeArr[y][x].setValue(1);
                shapeArr[y][x].setStyle("background-color: yellow; color:yellow");
            }
        }
    }
}
