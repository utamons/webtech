package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Square implements Shape {
    private ArrayCell[][] square;
    public Square() {
        square = new ArrayCell[2][2];
        for (int y = 0; y < square.length; ++y) {
            for (int x = 0; x < square[0].length; ++x) {
                square[y][x] = new ArrayCell();
                square[y][x].setValue(1);
                square[y][x].setStyle("background-color: green; color:green");
            }
        }
    }
}
