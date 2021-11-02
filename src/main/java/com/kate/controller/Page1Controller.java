package com.kate.controller;

import com.kate.model.ArrayCell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Page1Controller {

    private ArrayCell[][] arr;
    ArrayCell[][] nums = new ArrayCell[20][20];

    public Page1Controller() {
        arr = new ArrayCell[20][20];
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                arr[i][j] = new ArrayCell();
                arr[i][j].setValue(0);
            }
        }
    }

    @GetMapping("/page1")
    public String getPage1(Model model) {

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j].getValue() == 0) {
                    arr[i][j].setStyle("background-color:white; color:white");

                } else {
                    arr[i][j].setStyle("background-color:green; color:green");

                }
                System.out.print(arr[i][j].getValue() + " ");
            }
            System.out.println();
        }
        model.addAttribute("x", arr);

        return "page1";
    }

    @PostMapping("/setNumber")
    public String number(@RequestParam(value = "num") String num) {
        String[] numbers = num.split(",");
        String num1 = numbers[0];
        String num2 = numbers[1];
        int x = Integer.parseInt(num1);
        int y = Integer.parseInt(num2);
        arr[x][y].setValue(1);

        return "redirect:/page1";
    }

    @PostMapping("/move")
    public String moveNum() {

        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums[0].length; ++j) {
                nums[i][j] = new ArrayCell();
                nums[i][j].setValue(0);
            }
        }

        /*
           Идём по всем элементам массива
           Считаем decide(sumCell(i,j),arr[i][j].getValue);
           Записываем результат в nums[i][j]
         */
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums[0].length; ++j) {
                nums[i][j].setValue( decide(sumCell(i,j),arr[i][j].getValue()));
            }
        }

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                arr[i][j] = nums[i][j];
            }
        }

        return "redirect:/page1";
    }

    public int sumCell(int x, int y) {
        int sum = 0;
        int rightX = arr.length - 1;
        int downY = arr[0].length - 1;
        // Upper left corner
        if (x == 0 && y == 0) {
            sum = arr[1][0].getValue() + arr[1][1].getValue() + arr[0][1].getValue()+ arr[rightX][0].getValue()+ arr[0][rightX].getValue()+ arr[rightX][rightX].getValue()+ arr[rightX][1].getValue()+ arr[1][rightX].getValue();
        }
        // Upper right corner
        else if (x == rightX && y == 0) {
            sum = arr[rightX - 1][0].getValue() + arr[rightX - 1][1].getValue() + arr[rightX][1].getValue()+ arr[0][0].getValue()+ arr[rightX][rightX].getValue()+ arr[0][rightX].getValue()+ arr[0][1].getValue()+ arr[rightX-1][rightX].getValue();
        }
        // Down left corner
        else if(x == 0 && y == arr.length){
            sum = arr[arr.length-2][0].getValue() + arr[arr.length-2][1].getValue() + arr[rightX][1].getValue() + arr[0][0].getValue() + arr[rightX][0].getValue() + arr[rightX][rightX].getValue()+ arr[1][0].getValue()+ arr[rightX][rightX-1].getValue();
        }
        // Down right corner
        else if (x == rightX && y == arr.length) {
            sum = arr[rightX - 1][rightX].getValue() + arr[rightX - 1][arr.length-2].getValue() + arr[rightX][arr.length-2].getValue()+ arr[0][0].getValue()+ arr[0][rightX].getValue()+ arr[rightX][0].getValue()+ arr[rightX-1][0].getValue()+ arr[0][rightX-1].getValue();
        }
        // Upper border cell
        else if (x > 0 && y == 0 && x < rightX) {
            sum = arr[x - 1][0].getValue() + arr[x - 1][1].getValue() + arr[x][1].getValue() + arr[x + 1][0].getValue() + arr[x + 1][1].getValue()+ arr[x + 1][y].getValue()+arr[x - 1][y].getValue();
        }
        // Down border cell
        else if (x > 0 && y == arr.length - 1 && x < rightX) {
            sum = arr[x - 1][y].getValue() + arr[x - 1][y-1].getValue() + arr[x][y-1].getValue() + arr[x + 1][y].getValue() + arr[x + 1][y-1].getValue()+arr[x - 1][0].getValue()+arr[x + 1][0].getValue();
        }
        // Left border cell
        else if (y > 0 && x == 0 && y < rightX) {
            sum = arr[y - 1][0].getValue() + arr[y - 1][1].getValue() + arr[y][1].getValue() + arr[y + 1][0].getValue() + arr[y + 1][1].getValue()+arr[y - 1][x].getValue()+arr[y + 1][x].getValue();
        }
        // Right border cell
        else if (y > 0 && x == arr.length - 1 && y < rightX) {
            sum = arr[y - 1][x].getValue() + arr[y - 1][x-1].getValue() + arr[y][x-1].getValue() + arr[y + 1][x].getValue() + arr[y + 1][x-1].getValue()+arr[y - 1][0].getValue()+arr[y + 1][0].getValue();
        }
        // Middle cell
        else if (x > 0 && y > 0 && x < rightX && y < downY) {
            sum = arr[x - 1][y - 1].getValue() + arr[x][y - 1].getValue() + arr[x + 1][y - 1].getValue() +
                    arr[x - 1][y].getValue() + arr[x + 1][y].getValue() +
                    arr[x - 1][y + 1].getValue() + arr[x][y + 1].getValue() + arr[x + 1][y + 1].getValue();
        }
        return sum;
    }


    public int decide(int sum, int cellValue) {
        if (sum == 3)
            return 1;
        else if (sum == 2)
            return cellValue;
        else
            return 0;


    }
}



