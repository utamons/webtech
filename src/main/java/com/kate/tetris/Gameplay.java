package com.kate.tetris;

import com.kate.model.ArrayCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Gameplay {
    private final Field field = new Field(10, 10);
    private Shape currentShape;
    private int currentX;
    private int currentY;
    private final Random random = new Random();
    private static final Logger log = LoggerFactory.getLogger(Gameplay.class);

    public Gameplay() {
        nextShape();
    }

    //Displays the field according to the "getArr" function of Field
    public ArrayCell[][] getField() {
        return field.getArr();
    }

    //Sends the shape down by one square
    public void nextMove() {
        log.debug("Start, currentX={}, currentY={}, shape width={},height={}", currentX, currentY, currentShape.getWidth(), currentShape.getHeight());
        if ((currentY + currentShape.getHeight() <= field.getHeight()) && !field.isCollision(currentX, currentY, currentShape)) {
            field.putShape(currentShape, currentX, currentY);
            currentY++;
        } else {
            if (currentY == 0)
                return; // the end of the game?
            field.clearShape();
            field.adopt(currentX, currentY - 1, currentShape);
            nextShape();
        }
        log.debug("Finish, currentY={}", currentY);
    }

    public void nextShape() {
        log.debug("Start");
        currentX = 3;
        currentY = 0;
        Shape[] shapeArr = new Shape[7];
        shapeArr[0] = new J();
        shapeArr[1] = new L();
        shapeArr[2] = new S();
        shapeArr[3] = new Z();
        shapeArr[4] = new T();
        shapeArr[5] = new Stick();
        shapeArr[6] = new Square();
        int n = random.nextInt(shapeArr.length);
        field.putShape(shapeArr[n], currentX, currentY);
        currentShape = shapeArr[n];
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

    public void moveLeft() {
        if (currentX > 0) {
            currentX--;
        }
    }

    public void moveRight() {
        if (currentX + currentShape.getWidth() < field.getWidth()) {
            currentX++;
        }
    }
}
