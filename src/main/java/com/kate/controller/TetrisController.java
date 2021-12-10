package com.kate.controller;

import com.kate.model.ArrayCell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.io.IOException;

@Controller
public class TetrisController {
    private ArrayCell[][] arr;
    private ArrayCell[][] square;
    private int x=0;
    private int y=0;

    @GetMapping("/page2")
    public String getPage2(Model model) {
        return "tetris";
    }

    public TetrisController(){
        arr = new ArrayCell[10][10];

        square = new ArrayCell[2][2];
        for (int y = 0; y < arr.length; ++y) {
            for (int x = 0; x < arr[0].length; ++x) {
                arr[y][x] = new ArrayCell();
                arr[y][x].setValue(0);
            }
        }
        for (int y = 0; y < square.length; ++y) {
            for (int x = 0; x < square[0].length; ++x) {
                square[y][x] = new ArrayCell();
                square[y][x].setValue(1);
                square[y][x].setStyle("background-color: green; color:green");
            }
        }
    }

    @GetMapping(value = "/api/tetrisarr", produces = "application/json")
    @ResponseBody
    public ArrayCell[][] getArr(@PathParam("move") boolean move) throws IOException {
        if (move) {
            nextMove();
        }
      //  move();

        for (ArrayCell[] arrayCells : arr) {
            for (int x = 0; x < arr[0].length; ++x) {
                if (arrayCells[x].getValue() == 0) {
                    arrayCells[x].setStyle("background-color:white; color:white");
                } else if (arrayCells[x].getStyle().equals("background-color:white; color:white") ||
                        arrayCells[x].getStyle().equals("")) {
                    arrayCells[x].setStyle("background-color: green; color:green");
                }
            }
        }
   //


        return arr;
    }
    public void nextMove(){
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
    }
}

