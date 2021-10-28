package com.kate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Page3Controller {
    @GetMapping("/page3")
    public String getPage3() {
        return "page3";
    }
}
