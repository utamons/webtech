package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Field {
    private final ArrayCell[][] arr;
    private Shape shape;
    private int x, y;

    //Creates the field
    public Field() {
        arr = new ArrayCell[10][10];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = new ArrayCell();
            }
        }
    }

    public boolean putShape(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        return true;
    }

    //Draws the shape on the field
    private void draw(int x, int y, ArrayCell[][] clone) {
        ArrayCell[][] shapeArr = shape.getShapeArr();
        for (int i = 0; i < shapeArr.length; i++) {
            for (int j = 0; j < shapeArr[0].length; j++) {
                if (x < clone.length && y < clone[0].length) {
                    clone[x + i][y + j].setValue(shapeArr[i][j].getValue());
                    clone[x + i][y + j].setStyle(shapeArr[i][j].getStyle());
                }
            }

        }
    }

    //Fills the field with white or colored cells according to whether or not a shape is present
    public ArrayCell[][] getArr() {
        for (ArrayCell[] arrayCells : arr) {
            for (int x = 0; x < arr[0].length; ++x) {
                if (arrayCells[x].getValue() == 0) {
                    arrayCells[x].setStyle("background-color:white; color:white");
                } else {
                    arrayCells[x].setStyle("background-color: green; color:green");
                }
            }
        }
        if (shape != null) {
            //todo клонировать массив (погуглить как создать копию массива)
            ArrayCell[][] clone = new ArrayCell[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    clone[i][j] = arr[i][j].copy();
                }
            }
            //todo отобразить shape в клон
            draw(x, y, clone);
            //todo вернуть клон
            return clone;
        }

        return arr;
    }

    //Calls the "left" function of Shape
    public void left() {
        shape.left();
    }

    //Calls the "right" function of Shape
    public void right() {
        shape.right();
    }
}
