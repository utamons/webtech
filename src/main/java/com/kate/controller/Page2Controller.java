package com.kate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Page2Controller {
    @GetMapping("/page2")
    public String getPage2() {
        return "page2";
    }
}
