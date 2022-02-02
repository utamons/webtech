package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Gameplay {
    private final Field field = new Field();
    private Shape currentShape;
    private int currentX;
    private int currentY;

    public Gameplay(){
        Square square = new Square();
        currentShape=new T();
        L l = new L();
        S s = new S();
        Z z = new Z();
        T t = new T();
        Stick stick = new Stick();
        currentX=3;
        currentY=0;
        field.putShape(currentShape, currentX, currentY);
    }

    //Displays the field according to the "getArr" function of Field
    public ArrayCell[][] getField() {
        return field.getArr();
    }

    public void reset() {

    }

    //Sends the shape down by one square
    public void nextMove() {
        ArrayCell[][] shapeArr= currentShape.getShapeArr();
        ArrayCell[][] fieldArr= field.getArr();
        field.putShape(currentShape,currentX,currentY);
        if(currentY+ currentShape.getArrWidth()<10){
            currentY++;
        }
    }



    //Calls the "left" function of Shape
    public void turnLeft() {
        field.turnLeft();
    }

    //Calls the "right" function of Shape
    public void turnRight() {
        field.turnRight();
    }

    public void moveLeft(){
        if(currentX>0) {
            currentX--;
        }
    }
    public void moveRight(){
        if(currentX+ currentShape.getArrHeight()<10) {
            currentX++;
        }
    }

    /*public void nextMove(){
        for (int x = 0; x < arr.length; ++x) {
            for (int y = 0; y < arr[0].length; ++y) {
                arr[x][y] = new ArrayCell();
                arr[x][y].setValue(0);
            }
        }
        draw(x,y);
        if(x< arr.length- square.length){
            x++;
        }

    }

    public void draw(int x,int y) {
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                if (x < arr.length && y < arr[0].length) {
                    arr[x+i][y+j].setValue(square[i][j].getValue());
                    arr[x+i][y+j].setStyle(square[i][j].getStyle());
                }
            }
        }
    }*/
}
