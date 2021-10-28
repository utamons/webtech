package com.kate.controller;

import com.kate.model.ArrayCell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class Page1Controller {

    @GetMapping("/page1")
    public String getPage1(Model model) {
        ArrayCell[][]x = new ArrayCell[10][10];
        Random rnd = new Random();
        for (int i=0;i<x.length;++i) {
            for (int j=0;j<x[0].length;++j){
                x[i][j] = new ArrayCell();
                x[i][j].setValue(rnd.nextInt(10));
                if (x[i][j].getValue()%2 == 0) {
                    x[i][j].setStyle("color:red");
                }else {
                    x[i][j].setStyle("color:green");
                }
            }
        }
        model.addAttribute("x",x);
        return "page1";
    }
}
