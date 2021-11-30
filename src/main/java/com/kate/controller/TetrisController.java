package com.kate.controller;

import com.kate.model.ArrayCell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TetrisController {
    private ArrayCell[][] arr;
    private ArrayCell[][] nums;

    @GetMapping("/page2")
    public String getPage2(Model model) {
        arr = new ArrayCell[5][5];
        nums = new ArrayCell[5][5];

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                arr[i][j] = new ArrayCell();
                arr[i][j].setValue(0);
                System.out.println(arr[i][j]);
            }

           /* for (ArrayCell[] arrayCells : arr) {
                for (ArrayCell arrayCell : arrayCells) {
                    if (arrayCell.getValue() == 0) {
                        arrayCell.setStyle("background-color:white; color:white");
                    } else {
                        arrayCell.setStyle("background-color:green; color:green");

                    }

                }
            }*/

        }
        arr[0][1].setValue(1);
        arr[0][1].setStyle("green");
        arr[1][1].setValue(1);
        arr[1][1].setStyle("green");
        arr[2][1].setValue(1);
        arr[2][1].setStyle("green");

        return "tetris";
    }


}

