package com.kate.tetris;

import com.kate.model.ArrayCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Gameplay {
    private final Field field = new Field();
    private Shape currentShape;
    private int currentX;
    private int currentY;
    private Random random=new Random();
    private static final Logger log = LoggerFactory.getLogger(Gameplay.class);

    public Gameplay(){
        Square square = new Square();
        currentShape=new T();
        L l = new L();
        S s = new S();
        Z z = new Z();
        T t = new T();
        J j=new J();
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
        log.debug("Start, currentY={}",currentY);
        if(!field.isCollision(currentY, currentX, currentShape) && (currentY+ currentShape.getArrWidth()<10)){
            field.putShape(currentShape,currentX,currentY);
            currentY++;
        }else{
            field.clearShape();
            field.adopt(currentY,currentX,currentShape);
            nextShape();
        }
        log.debug("Finish, currentY={}",currentY);
    }

    public void nextShape(){
        log.debug("Start");
        currentX=0;
        currentY=3;
        Shape[] shapeArr = new Shape[7];
        shapeArr[0]=new J();
        shapeArr[1]=new L();
        shapeArr[2]=new S();
        shapeArr[3]=new Z();
        shapeArr[4]=new T();
        shapeArr[5]=new Stick();
        shapeArr[6]=new Square();
        int n = random.nextInt(shapeArr.length);
        field.putShape(shapeArr[n],currentX,currentY);
        currentShape=shapeArr[n];
        log.debug("Finish");
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
}
