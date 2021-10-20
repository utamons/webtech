package com.kate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/")
public class HelloController {

	@GetMapping("/")
	public String getIndex(Model model) {
		Random random = new Random();
		int[][] x = new int[3][3];
		for (int i=0; i<3;++i)
			for (int j=0; j<3;++j)
				x[i][j] = random.nextInt(10);

		model.addAttribute("name", "Kate");
		model.addAttribute("dt", new Date());
		model.addAttribute("x",x);
		return "index";
	}
}
