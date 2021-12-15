package com.kate.tetris;

import com.kate.model.ArrayCell;

public class Field {
    private final ArrayCell[][] arr;

    public Field() {
        arr = new ArrayCell[10][10];
        for(int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length;j++){
                arr[i][j]=new ArrayCell();
            }
        }
    }

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
        return arr;
    }
}
