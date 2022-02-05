package com.kate.tetris;

import com.kate.model.ArrayCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Shape {
    protected ArrayCell[][] shapeArr;
    private static final Logger log = LoggerFactory.getLogger(Shape.class);
    protected String color;

    public Shape(int width, int height, String color) {
        shapeArr = new ArrayCell[width][height];
        this.color = color;
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                shapeArr[x][y] = new ArrayCell();
            }
        }
    }

    //Turns the shape to the right
    public void turnRight() {
        log.debug("Start");
        final int tempHeight = getWidth();
        final int tempWidth = getHeight();
        ArrayCell[][] temp = new ArrayCell[tempWidth][tempHeight];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                temp[tempWidth - y - 1][x] = shapeArr[x][y];
            }
        }
        shapeArr = temp;
        log.debug("Finish");
    }

    //Turns the shape to the left
    public void turnLeft() {
        log.debug("Start");
        final int tempHeight = getWidth();
        final int tempWidth = getHeight();
        ArrayCell[][] temp = new ArrayCell[tempWidth][tempHeight];
        for (int x = 0; x < tempWidth; x++) {
            for (int y = 0; y < tempHeight; y++) {
                temp[x][y] = shapeArr[Math.abs(y - (tempHeight - 1))][x];
            }
        }
        shapeArr = temp;
        log.debug("Finish");
    }

    public int getHeight() {
        return shapeArr[0].length;
    }

    public int getWidth() {
        return shapeArr.length;
    }

    public ArrayCell[][] getShapeArr() {
        return shapeArr;
    }

    protected void activeCell(int x, int y) {
        shapeArr[x][y] = new ArrayCell();
        shapeArr[x][y].setValue(1);
        shapeArr[x][y].setStyle("background-color: " + color + "; color:" + color);
    }
}
