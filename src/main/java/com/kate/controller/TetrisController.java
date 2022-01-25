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


    @GetMapping("/page2")
    public String getPage2() {
        return "tetris";
    }


    @GetMapping(value = "/api/tetrisarr", produces = "application/json")
    @ResponseBody
    public ArrayCell[][] getArr(@PathParam("move") boolean move) {
        ArrayCell[][] arr=gameplay.getField();
        if (move) {
            gameplay.nextMove();
        }

        return turnLeft(arr);
    }
    @GetMapping("/api/left")
    @ResponseBody
    public void left(){
        gameplay.left();
    }

    @GetMapping("/api/right")
    @ResponseBody
    public void right(){
        gameplay.right();
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

