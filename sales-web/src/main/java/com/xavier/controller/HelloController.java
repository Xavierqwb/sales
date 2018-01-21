package com.xavier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xavier on 2018/1/21.
 */
@RestController
public class HelloController {

	@RequestMapping("hello")
	public String hello() {
		return "Hello World!";
	}
}
