package com.kate.tetris;

public class Square extends Shape {
    public Square() {
        super(2,2,"yellow");
        for(int y = 0; y< getHeight(); ++y) {
            for (int x = 0; x< getWidth(); ++x) {
                activeCell(x,y);
            }
        }
    }
}
