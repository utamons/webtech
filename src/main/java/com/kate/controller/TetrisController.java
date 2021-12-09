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
    private ArrayCell[][] nums;
    private ArrayCell[][] square;

    @GetMapping("/page2")
    public String getPage2(Model model) {
        return "tetris";
    }

    public TetrisController(){
        arr = new ArrayCell[10][15];
        nums = new ArrayCell[arr.length][arr[0].length];
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
        square[1][1].setStyle("background-color:white; color:white");
        square[1][1].setValue(0);
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
        draw(2,2);
        return arr;
    }

    public void nextMove(){
        for (int x = 0; x < nums.length; ++x) {
            for (int y = 0; y < nums[0].length; ++y) {
                nums[x][y] = new ArrayCell();
                nums[x][y].setValue(0);
            }
        }

        for(int y=0;y< arr.length;y++){
            for(int x=0;x<arr[0].length;x++){
                if(arr[y][x].getValue()==1) {
                    if (y < arr.length - 1) {
                        nums[y + 1][x].setValue(1);
                        nums[y][x].setValue(0);
                    }else{
                        nums[y][x].setValue(1);
                    }
                }
            }
        }
        for (int x = 0; x < arr.length; ++x) {
            System.arraycopy(nums[x], 0, arr[x], 0, arr[0].length);
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
      /*  arr[x][y].setValue(1);
        arr[x][y].setStyle("background-color: green; color:green");
        arr[x+1][y].setValue(1);
        arr[x+1][y].setStyle("background-color: green; color:green");
        arr[x][y+1].setValue(1);
        arr[x][y+1].setStyle("background-color: green; color:green");
        arr[x+1][y+1].setValue(1);
        arr[x+1][y+1].setStyle("background-color: green; color:green");*/
    }
}

