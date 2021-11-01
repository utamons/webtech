package com.kate.controller;

import com.kate.model.ArrayCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class Page1Controller {

    private ArrayCell[][] x;
    ArrayCell[][] nums = new ArrayCell[10][10];
    public Page1Controller() {
        x = new ArrayCell[10][10];
        for (int i = 0; i < x.length; ++i) {
            for (int j = 0; j < x[0].length; ++j) {
                x[i][j] = new ArrayCell();
                x[i][j].setValue(0);
            }
        }
    }

    @GetMapping("/page1")
    public String getPage1(Model model) {

        for (int i = 0; i < x.length; ++i) {
            for (int j = 0; j < x[0].length; ++j) {
                if (x[i][j].getValue() == 0) {
                    x[i][j].setStyle("background-color:white; color:white");

                } else {
                    x[i][j].setStyle("background-color:green; color:green");

                }
                System.out.print(x[i][j].getValue()+" ");
            }
            System.out.println();
        }
        model.addAttribute("x", x);

        return "page1";
    }

    @PostMapping("/setNumber")
    public String number(@RequestParam(value = "num") String num) {
        String[] numbers = num.split(",");
        String num1 = numbers[0];
        String num2 = numbers[1];
        int n = Integer.parseInt(num1);
        int y = Integer.parseInt(num2);
        x[n][y].setValue(1);

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

        for (int i = 0; i < x.length; ++i) {
            for (int j = 0; j < x[0].length; ++j) {
                x[i][j]=nums[i][j];
            }
        }

        return "redirect:/page1";
    }

    /*public String isAlive(){
        for (int i = 0; i < x.length; ++i) {
        for (int j = 0; j < x[0].length; ++j) {




            if (x[i][j].getValue() == 1 && x[i+1][j].getValue() == 1 && x[i-1][j].getValue() == 1){
                x[i][j].setValue(1);
            }else if(x[i][j].getValue() == 1 && x[i][j+1].getValue() == 1 && x[i][j-1].getValue() == 1){
                x[i][j].setValue(1);
            }else if(x[i][j].getValue() == 1 && x[i+1][j+1].getValue() == 1 && x[i-1][j-1].getValue() == 1){
                x[i][j].setValue(1);
            }else if(x[i][j].getValue() == 1 && x[i-1][j+1].getValue() == 1 && x[i+1][j-1].getValue() == 1){
                x[i][j].setValue(1);
            }

        }
    }
        return "redirect:/page1";
    }
    public String isDead(){
        for (int i = 0; i < x.length; ++i) {
            for (int j = 0; j < x[0].length; ++j) {
                if (x[i][j].getValue() == 1 && x[i+1][j].getValue() == 0 && x[i-1][j].getValue() == 0){
                    x[i][j].setValue(0);
                }else if(x[i][j].getValue() == 1 && x[i][j+1].getValue() == 0 && x[i][j-1].getValue() == 0){
                    x[i][j].setValue(0);
                }else if(x[i][j].getValue() == 1 && x[i+1][j+1].getValue() == 0 && x[i-1][j-1].getValue() == 0){
                    x[i][j].setValue(0);
                }else if(x[i][j].getValue() == 1 && x[i-1][j+1].getValue() == 0 && x[i+1][j-1].getValue() == 0){
                    x[i][j].setValue(0);
                }

            }
        }
        return "redirect:/page1";
    }
    public String isBorn(){
        for (int i = 0; i < x.length; ++i) {
            for (int j = 0; j < x[0].length; ++j) {
                if (x[i][j].getValue() == 0 && x[i-1][j].getValue() == 1 && x[i-1][j+1].getValue() == 1 &&x[i+1][j-1].getValue() == 1  ){
                    x[i][j].setValue(1);
                }else if(x[i][j].getValue() == 0 && x[i-1][j-1].getValue() == 1 && x[i-1][j].getValue() == 1 &&x[i][j-1].getValue() == 1){
                    x[i][j].setValue(1);
                }else if(x[i][j].getValue() == 0 && x[i-1][j-1].getValue() == 1 && x[i-1][j].getValue() == 1 &&x[i-1][j+1].getValue() == 1 ){
                    x[i][j].setValue(1);
                }
                else if(x[i][j].getValue() == 0 && x[i-1][j-1].getValue() == 1 && x[i][j-1].getValue() == 1 &&x[i+1][j].getValue() == 1 ){
                    x[i][j].setValue(1);
                }
                else if(x[i][j].getValue() == 0 && x[i][j-1].getValue() == 1 && x[i+1][j-1].getValue() == 1 &&x[i+1][j].getValue() == 1 ){
                    x[i][j].setValue(1);
                }
                else if(x[i][j].getValue() == 0 && x[i+1][j].getValue() == 1 && x[i+1][j+1].getValue() == 1 && x[i][j+1].getValue() == 1 ){
                    x[i][j].setValue(1);
                }
                else if(x[i][j].getValue() == 0 && x[i+1][j+1].getValue() == 1 && x[i-1][j+1].getValue() == 1 &&x[i][j+1].getValue() == 1 ){
                    x[i][j].setValue(1);
                }
                else if(x[i][j].getValue() == 0 && x[i+1][j+1].getValue() == 1 && x[i][j+1].getValue() == 1 &&x[i+1][j-1].getValue() == 1 ){
                    x[i][j].setValue(1);
                }
            }
        }
        return "redirect:/page1";
    }
    public String isKilled(){
        for (int i = 0; i < x.length; ++i) {
            for (int j = 0; j < x[0].length; ++j) {
                if (x[i][j].getValue() == 1 && x[i-1][j].getValue() == 1 && x[i-1][j+1].getValue() == 1 &&x[i][j+1].getValue() == 1 && x[i+1][j+1].getValue() == 1 ){
                    x[i][j].setValue(0);
                }else if(x[i][j].getValue() == 1 && x[i+1][j].getValue() == 1 && x[i+1][j+1].getValue() == 1 &&x[i][j+1].getValue() == 1 && x[i-1][j+1].getValue() == 1){
                    x[i][j].setValue(0);
                }else if(x[i][j].getValue() == 1 && x[i+1][j+1].getValue() == 1 && x[i+1][j].getValue() == 1 &&x[i+1][j-1].getValue() == 1 && x[i][j-1].getValue() == 1){
                    x[i][j].setValue(0);
                }
                else if(x[i][j].getValue() == 1 && x[i-1][j-1].getValue() == 1 && x[i-1][j].getValue() == 1 &&x[i-1][j+1].getValue() == 1 && x[i][j+1].getValue() == 1){
                    x[i][j].setValue(0);
                }
                else if(x[i][j].getValue() == 1 && x[i-1][j-1].getValue() == 1 && x[i][j-1].getValue() == 1 &&x[i+1][j-1].getValue() == 1 && x[i+1][j].getValue() == 1){
                    x[i][j].setValue(0);
                }
                else if(x[i][j].getValue() == 1 && x[i][j-1].getValue() == 1 && x[i+1][j+1].getValue() == 1 && x[i][j+1].getValue() == 1 && x[i-1][j+1].getValue() == 1){
                    x[i][j].setValue(0);
                }
                else if(x[i][j].getValue() == 1 && x[i+1][j-1].getValue() == 1 && x[i-1][j-1].getValue() == 1 &&x[i+1][j].getValue() == 1 && x[i][j+1].getValue() == 1){
                    x[i][j].setValue(0);
                }
                else if(x[i][j].getValue() == 1 && x[i][j-1].getValue() == 1 && x[i-1][j-1].getValue() == 1 &&x[i-1][j].getValue() == 1 && x[i+1][j+1].getValue() == 1){
                    x[i][j].setValue(0);
                }
            }
            }
        return "redirect:/page1";
        }*/
    public int upLeft() {
        int sum;
        if(x[0][1].getValue()==1 && x[1][1].getValue()==1 && x[1][0].getValue()==1){
            sum=3;
        }else if(x[0][1].getValue()==1 && x[1][1].getValue()==1 || x[1][0].getValue()==1 && x[1][1].getValue()==1 || x[1][1].getValue()==1 && x[0][1].getValue()==1 ){
            sum=2;
        }else if(x[0][1].getValue()==1 || x[1][1].getValue()==1 || x[1][0].getValue()==1){
            sum=1;
        }else{
            sum=0;
        }
        return sum;
    }
            public int upRight(){
                int sum;
                if(x[0][8].getValue()==1 && x[1][8].getValue()==1 && x[1][9].getValue()==1){
                    sum=3;
                }else if(x[0][1].getValue()==1 && x[1][8].getValue()==1 || x[1][9].getValue()==1 && x[1][8].getValue()==1 || x[1][8].getValue()==1 && x[0][8].getValue()==1 ){
                    sum=2;
                }else if(x[0][1].getValue()==1 || x[1][8].getValue()==1 || x[1][9].getValue()==1){
                    sum=1;
                }else{
                    sum=0;
                }
                return sum;
            }
            public int DownLeft(){
                int sum;
                if(x[9][1].getValue()==1 && x[8][1].getValue()==1 && x[8][0].getValue()==1){
                    sum=3;
                }else if(x[9][1].getValue()==1 && x[8][1].getValue()==1 || x[8][0].getValue()==1 && x[8][1].getValue()==1 || x[8][1].getValue()==1 && x[9][1].getValue()==1 ){
                    sum=2;
                }else if(x[9][1].getValue()==1 || x[8][1].getValue()==1 || x[8][0].getValue()==1){
                    sum=1;
                }else{
                    sum=0;
                }
                return sum;
            }
            public int DownRight(){
                int sum;
                if(x[9][8].getValue()==1 && x[8][8].getValue()==1 && x[8][9].getValue()==1){
                    sum=3;
                }else if(x[9][1].getValue()==1 && x[8][8].getValue()==1 || x[8][9].getValue()==1 && x[8][8].getValue()==1 || x[8][8].getValue()==1 && x[9][8].getValue()==1 ){
                    sum=2;
                }else if(x[9][1].getValue()==1 || x[8][8].getValue()==1 || x[8][9].getValue()==1){
                    sum=1;
                }else{
                    sum=0;
                }
                return sum;
            }


            public int Up(){
                int sum;
                for (int i = 0; i < x.length; ++i) {
                    for (int j = 0; j < x[0].length; ++j) {
                        if(x[0][j-1].getValue()==1 && x[0][j+1].getValue()==1 && x[1][j-1].getValue()==1 && x[1][j].getValue()==1 && x[1][j+1].getValue()==1){
                            sum=5;
                        }else if(x[0][j-1].getValue()==1 && x[0][j+1].getValue()==1 && x[1][j-1].getValue()==1 && x[1][j].getValue()==1 ||
                                x[0][j-1].getValue()==1 && x[0][j+1].getValue()==1 && x[1][j-1].getValue()==1 && x[1][j+1].getValue()==1 ||
                        ){
                            sum=4;
                        }else if(){
                            sum=3;
                        }
                        else if(){
                            sum=2;
                        }
                        else if(x[0][j-1].getValue()==1 || x[0][j+1].getValue()==1 || x[1][j-1].getValue()==1 || x[1][j].getValue()==1 || x[1][j+1].getValue()==1){
                            sum=1;
                        }else{
                            sum=0;
                        }
                    }
                }
                        return sum;
            }
            public int Down(){
                int sum=0;
                return sum;
            }
            public int Left(){
                int sum=0;
                return sum;
            }
            public int Rigth(){
                int sum=0;
                return sum;
            }


            public int Center(){
                int sum=0;
                return sum;
            }


            public int Decide(){
                int sum=0;
                return sum;
            }

        }



