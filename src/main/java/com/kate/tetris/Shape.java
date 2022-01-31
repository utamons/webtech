package com.kate.tetris;

import com.kate.model.ArrayCell;

public abstract class Shape {
    protected ArrayCell[][] shapeArr;
    protected int arrHeight;
    protected int arrWidth;

    public void print(){
        for (int y = 0; y < shapeArr.length; ++y) {
            for (int x = 0; x < shapeArr[0].length; ++x) {
                if(shapeArr[y][x]==null){
                    System.out.print("N"+" ");
                }else{
                    System.out.print(shapeArr[y][x].getValue()+" ");
                }

            }
            System.out.println();
        }
    }

    //Turns the shape to the right
    public void turnRight(){
        final int tempHeight=arrWidth;
        final int tempWidth=arrHeight;
        ArrayCell[][] temp = new ArrayCell[tempHeight][tempWidth];
        for (int y = 0; y < tempHeight; y++) {
            for (int x = 0; x < tempWidth; x++) {
                temp[y][x] = shapeArr[arrHeight - x - 1][y];
            }

        }
        shapeArr=temp;
        arrWidth=tempWidth;
        arrHeight=tempHeight;
    }
    //Turns the shape to the left
    public void turnLeft(){
        final int tempHeight=arrWidth;
        final int tempWidth=arrHeight;
        ArrayCell[][] temp = new ArrayCell[tempHeight][tempWidth];
        for (int y = 0; y < tempHeight; y++) {
            for (int x = 0; x < tempWidth; x++) {
                temp[y][x] = shapeArr[x][Math.abs(y-(tempHeight-1))];
            }

        }
        shapeArr=temp;
        arrWidth=shapeArr[0].length;
        arrHeight=shapeArr.length;
    }

    public int getArrHeight() {
        return arrHeight;
    }

    public ArrayCell[][] getShapeArr() {
        return shapeArr;
    }
}
