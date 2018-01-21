package com.xavier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xavier on 2018/1/21.
 */
@Controller
@RequestMapping("/sales")
public class PortalController {

	@RequestMapping("/")
	public String homePage() {
		return "homePage";
	}
}
