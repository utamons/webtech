package com.kate.tetris;

import com.kate.model.ArrayCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Field {
    private final ArrayCell[][] arr;
    private Shape shape;
    private int x, y;
    private static final Logger log = LoggerFactory.getLogger(Field.class);

    //Creates the field
    public Field(int width, int height) {
        arr = new ArrayCell[width][height];
        clear();
    }

    private void clear() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                arr[x][y] = new ArrayCell();
            }
        }
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public static void print(ArrayCell[][] arr) {
        for (int y = 0; y < arr[0].length; y++) {
            for (int x = 0; x < arr.length; x++) {
                if (arr[x][y] == null) {
                    System.out.print("N");
                } else {
                    System.out.print(arr[x][y].getValue());
                }
            }
            System.out.println();
        }
    }

    public void putShape(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    //Draws the shape on the field
    private void draw(int x, int y, ArrayCell[][] field) {
        ArrayCell[][] shapeArr = shape.getShapeArr();
        for (int shapeY = 0; shapeY < shape.getHeight(); shapeY++) {
            for (int shapeX = 0; shapeX < shape.getWidth(); shapeX++) {
                if (x < getWidth() && y < getHeight()) {
                    ArrayCell fieldCell = field[x + shapeX][y + shapeY];
                    ArrayCell shapeCell = shapeArr[shapeX][shapeY];
                    if (shapeCell == null) {
                        log.error("ShapeCell at {},{} is null", shapeX, shapeY);
                    }
                    if (fieldCell == null) {
                        log.error("fieldCell at {},{} is null", x + shapeX, y + shapeY);
                    }
                    fieldCell.setValue(shapeCell.getValue());
                    fieldCell.setStyle(shapeCell.getStyle());
                }
            }
        }
    }

    //Fills the field with white or colored cells according to whether or not a shape is present
    public ArrayCell[][] getArr() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (arr[x][y].getValue() == 0) {
                    arr[x][y].setStyle("background-color:white; color:white");
                } else {
                    arr[x][y].setStyle("background-color: brown; color:brown");
                }
            }
        }
        if (shape != null) {
            //todo клонировать массив (погуглить как создать копию массива)
            ArrayCell[][] fieldClone = new ArrayCell[getWidth()][getHeight()];
            for (int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    fieldClone[x][y] = arr[x][y].copy();
                }
            }
            //todo отобразить shape в клон
            draw(x, y, fieldClone);
            //todo вернуть клон
            return fieldClone;
        }

        return arr;
    }

    //Calls the "left" function of Shape
    public void turnLeft() {
        shape.turnLeft();
    }

    //Calls the "right" function of Shape
    public void turnRight() {
        shape.turnRight();
    }

    public void adopt(int x, int y, Shape shape) {
        log.debug("Start");
        for (int shapeY = 0; shapeY < shape.shapeArr[0].length; ++shapeY) {
            for (int shapeX = 0; shapeX < shape.shapeArr.length; ++shapeX) {
                if (shape.shapeArr[shapeX][shapeY].getValue() == 1) {
                    int arrY, arrX;
                    arrY = shapeY + y;
                    arrX = shapeX + x;
                    arr[arrX][arrY].setValue(1);
                }

            }
        }
        log.debug("Finish");
        fall();
    }

    public void clearShape() {
        log.debug("Invoke");
        shape = null;
    }

    public boolean isCollision(int x, int y, Shape shape) {
        log.debug("Start");
        ArrayCell[][] shapeArr = shape.getShapeArr();
        for (int shapeY = 0; shapeY < shape.getHeight(); ++shapeY) {
            for (int shapeX = 0; shapeX < shape.getWidth(); ++shapeX) {
              //  log.debug("shapeX={},shapeY={},x={},y={}",shapeX,shapeY,x,y);
                if (arr[shapeX + x][shapeY + y].getValue() == 1 && shapeArr[shapeX][shapeY].getValue() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getHeight() {
        return arr[0].length;
    }

    public int getWidth() {
        return arr.length;
    }

    public void fall(){
        log.debug("Start");
        boolean wasFall;
        do {
            wasFall=false;
            int rowCellCount;
            for (int y = getHeight() - 1; y > 0; y--) {
                 rowCellCount = 0;
                for (int x = 0; x < getWidth() - 1; x++) {
                    if (arr[x][y].getValue() == 1) {
                        rowCellCount++;
                    }
                }
                wasFall=rowReplacement(rowCellCount, wasFall, y);

            }
        }while (wasFall);
        log.debug("Finish");
    }

    public boolean rowReplacement(int rowCellCount, boolean wasFall, int numStr){
        if (rowCellCount == getWidth() - 1) {
            wasFall = true;
            for (int y = numStr - 1; y > 0; y--) {
                for (int x = 0; x < getWidth(); x++) {
                    arr[x][y] = arr[x][y - 1];
                }
            }
        }
        return wasFall;
    }
}



