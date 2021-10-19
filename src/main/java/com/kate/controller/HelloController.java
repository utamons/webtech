package com.kate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/")
public class HelloController {

	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("name", "Kate");
		model.addAttribute("dt", new Date());
		return "index";
	}
}
