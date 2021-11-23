package com.kate.controller;

import com.kate.model.ArrayCell;
import com.kate.model.Coord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Controller
public class Page1Controller {

    private ArrayCell[][] arr;
    private ArrayCell[][] nums;
    private String[] colors = {"blue", "red", "yellow"};
    private int x = 0;
    // ArrayCell[][] nums = new ArrayCell[5][5];
    boolean autoPlay = false;


    @PostMapping(value = "/api/coord", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Coord postCoord(@RequestBody Coord coord) {
        System.out.println(coord.getX() + "," + coord.getY());
        if (c(coord.getX(), coord.getY()) == 1)
            arr[coord.getX()][coord.getY()].setValue(0);
        else
            arr[coord.getX()][coord.getY()].setValue(1);
        return coord;
    }

    // API - Application Programming Interface
    // UI - User Interface
    // GUI Graphical User Interface
    /*
        HTTP requests

        OPTION - интересуется
        GET - получает данные от сервера
        POST - отправляет новые данные на сервер
        PUT - изменяет сушествующие данные на сервере
        DELETE - удаляет существующие данные на сервере
     */
    @GetMapping(value = "/api/arr", produces = "application/json")
    @ResponseBody
    public ArrayCell[][] getArr(@PathParam("move") boolean move) {
        System.out.println("get arr-move- "+move);
        if (move) {
            nextMove();
            x++;
        }
        System.out.println("Длина массива"+arr.length);
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j].getValue() == 0) {
                    arr[i][j].setStyle("background-color:white; color:white");

                } else {
                    if (x == colors.length) {
                        x = 0;
                    }
                    arr[i][j].setStyle("background-color:" + colors[x] + ";" + "color:" + colors[x]);


                }

            }
        }
        return arr;
    }

    @GetMapping(value = "/api/size")
    @ResponseBody
    public String size(@PathParam(value = "num") String num) throws IOException {
        System.out.println(num);
        String[] numbers = num.split(",");
        FileWriter nFile = new FileWriter("file1.txt");
        nFile.write(num);
        nFile.close();
        String num1 = numbers[0];
        String num2 = numbers[1];
        int x = Integer.parseInt(num1);
        int y = Integer.parseInt(num2);
        arr = new ArrayCell[x][y];
        nums = new ArrayCell[x][y];
        clear();
        return "redirect:/page1";
    }


    @GetMapping(value = "/api/clear")
    @ResponseBody
    public void clear() {
        System.out.println("clear");
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                arr[i][j] = new ArrayCell();
                arr[i][j].setValue(0);
            }
        }
    }

    public void play(boolean x) {
        autoPlay = x;
    }


    public Page1Controller() throws IOException {
        File f=new File("file1.txt");
        if(f.exists()) {
            FileReader fr = new FileReader(f);
            Scanner scan = new Scanner(fr);
            String size = scan.nextLine();
            String[] numbers = size.split(",");
            String num1 = numbers[0];
            String num2 = numbers[1];
            int x = Integer.parseInt(num1);
            int y = Integer.parseInt(num2);
            arr = new ArrayCell[x][y];
            nums = new ArrayCell[x][y];
            fr.close();
        }else{
            arr = new ArrayCell[5][5];
            nums = new ArrayCell[5][5];
        }
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

            }
        }
        model.addAttribute("x", turnLeft());
        model.addAttribute("autoPlay", autoPlay);
        System.out.println(autoPlay);
        return "page1";
    }

    public void nextMove() {
        for (int x = 0; x < nums.length; ++x) {
            for (int y = 0; y < nums[0].length; ++y) {
                nums[x][y] = new ArrayCell();
                nums[x][y].setValue(0);
            }
        }

        for (int x = 0; x < nums.length; ++x) {
            for (int y = 0; y < nums[0].length; ++y) {
                nums[x][y].setValue(decide(sumCell(x, y), arr[x][y].getValue()));
            }
        }

        for (int x = 0; x < arr.length; ++x) {
            for (int y = 0; y < arr[0].length; ++y) {
                arr[x][y] = nums[x][y];
            }
        }
    }

    public ArrayCell[][] turnLeft() {
        ArrayCell[][] left = new ArrayCell[arr.length][arr[0].length];
        for (int x = 0; x < arr.length; ++x) {
            for (int y = 0; y < arr[0].length; ++y) {
                left[x][y] = arr[y][x];
            }
        }
        return left;
    }

    private int c(int x, int y) {
        return arr[x][y].getValue();
    }

    public int sumCell(int x, int y) {
        int sum = 0;
        int rightX = arr.length - 1;
        int downY = arr[0].length - 1;
        // Upper left corner
        if (x == 0 && y == 0) {
            sum = c(1, 0) + c(1, 1) + c(0, 1) +
                    c(rightX, 0) + c(0, downY) + c(rightX, downY) +
                    c(rightX, 1) + c(1, downY);
        }
        // Upper right corner
        else if (x == rightX && y == 0) {
            sum = c(rightX - 1, 0) + c(rightX - 1, 1) + c(rightX, 1) +
                    c(0, 0) + c(rightX, downY) + c(0, downY) +
                    c(0, 1) + c(rightX - 1, downY);
        }
        // Down left corner
        else if (x == 0 && y == downY) {
            sum = c(1, downY) + c(0, downY - 1) + c(1, downY - 1) +
                    c(0, 0) + c(rightX, 0) + c(rightX, downY) +
                    c(1, 0) + c(rightX, downY - 1);
        }
        // Down right corner
        else if (x == rightX && y == downY) {
            sum = c(rightX - 1, downY) + c(rightX - 1, downY - 1) + c(rightX, downY - 1) +
                    c(0, 0) + c(0, downY) + c(rightX, 0) +
                    c(rightX - 1, 0) + c(0, downY - 1);
        }
        // Up? border cell
        else if (x > 0 && y == 0 && x < rightX) {
            sum = c(x - 1, y) + c(x + 1, y) +
                    c(x - 1, y + 1) + c(x, y + 1) + c(x + 1, y + 1) +
                    c(x, downY) + c(x - 1, downY) + c(x + 1, downY);
        }
        // down? border cell
        else if (x > 0 && y == downY && x < rightX) {
            sum = c(x + 1, y) + c(x - 1, y) +
                    c(x, y - 1) + c(x + 1, y - 1) + c(x - 1, y - 1) +
                    c(x, 0) + c(x - 1, 0) + c(x + 1, 0);
        }
        // Left border cell
        else if (y > 0 && x == 0 && y < downY) {
            sum = c(0, y - 1) + c(0, y + 1) +
                    c(1, y + 1) + c(1, y - 1) + c(1, y) +
                    c(rightX, y) + c(rightX, y - 1) + c(rightX, y + 1);
        }
        // Right border cell
        else if (y > 0 && x == rightX && y < downY) {
            sum = c(x - 1, y) + c(x - 1, y + 1) + c(x - 1, y - 1) +
                    c(x, y + 1) + c(x, y - 1) +
                    c(0, y) + c(0, y - 1) + c(0, y + 1);
        }
        // Middle cell
        else if (x > 0 && y > 0 && x < rightX && y < downY) {
            sum = c(x - 1, y - 1) + c(x, y - 1) + c(x + 1, y - 1) +
                    c(x - 1, y) + c(x + 1, y) +
                    c(x - 1, y + 1) + c(x, y + 1) + c(x + 1, y + 1);
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



