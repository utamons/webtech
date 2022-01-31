package com.kate.controller;

import com.kate.model.ArrayCell;
import com.kate.tetris.Gameplay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

@Controller
public class TetrisController {
    private final Gameplay gameplay=new Gameplay();


    //Displays the page 2 as tetris page
    @GetMapping("/page2")
    public String getPage2() {
        return "tetris";
    }


    //Displays tetris field on the page
    @GetMapping(value = "/api/tetrisarr", produces = "application/json")
    @ResponseBody
    public ArrayCell[][] getArr(@PathParam("move") boolean move) {
        ArrayCell[][] arr=gameplay.getField();
        if (move) {
            gameplay.nextMove();
        }

        return turnLeft(arr);
    }

    //Calls the "left" function from Shape
    @GetMapping("/api/turnleft")
    @ResponseBody
    public void left(){
        gameplay.turnLeft();
    }

    //Calls the "right" function from Shape
    @GetMapping("/api/turnright")
    @ResponseBody
    public void right(){
        gameplay.turnRight();
    }

    public ArrayCell[][] turnLeft(ArrayCell[][] arr) {
        ArrayCell[][] left = new ArrayCell[arr[0].length][arr.length];
        for (int x = 0; x < arr[0].length; ++x) {
            for (int y = 0; y < arr.length; ++y) {
                left[x][y] = arr[y][x];
            }
        }
        return left;
    }

}

