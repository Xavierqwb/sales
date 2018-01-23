package com.xavier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by xavier on 2018/1/21.
 */
@Controller
@RequestMapping("/sales")
public class PortalController {

	private Logger logger = LoggerFactory.getLogger(PortalController.class);

	@RequestMapping("")
	public String homePage(HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		verifyLogin(session, modelMap);
		return "index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		verifyLogin(session, modelMap);
		return "login";
	}

	private void verifyLogin(HttpSession session, ModelMap map) {
		Boolean isLogin = (Boolean) session.getAttribute("isLogin");
		if (isLogin != null && isLogin) {
			map.addAttribute("isLogin", true);
			map.addAttribute("name", session.getAttribute("name"));
		}
	}
}
