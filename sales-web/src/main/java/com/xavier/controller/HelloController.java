package com.xavier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xavier on 2018/1/21.
 */
@Controller
public class HelloController {

	@RequestMapping("hello")
	public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
	                    ModelMap modelMap) {
		modelMap.addAttribute("name", name);
		return "hello";
	}

	@RequestMapping("/")
	public String toIndex() {
		return "index";
	}
}
