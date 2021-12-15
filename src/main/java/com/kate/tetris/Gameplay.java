package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Gameplay {
    private final Field field = new Field();

    public ArrayCell[][] getField() {
        return field.getArr();
    }

    public void reset(){

    }
    public void nextMove(){

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
