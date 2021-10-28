package com.kate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/")
public class MenuController {

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

	@PostMapping("/selectMenuItem")
	public String postIndex(@RequestParam(value = "menuItem") String menuItem) {
		switch (menuItem) {
			case "1":
				return "redirect:/page1";
			case "2":
				return "redirect:/page2";
			case "3":
				return "redirect:/page3";
			default:
				return "index";
		}
	}
}
