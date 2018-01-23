package com.xavier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by xavier on 2018/1/21.
 */
@Controller
@RequestMapping("/sales")
public class PortalController {

	private Logger logger = LoggerFactory.getLogger(PortalController.class);

	@RequestMapping("")
	public String homePage() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 验证登录，在FreeMarker中不需要，FreeMarker会自动将session中的属性加入到model中，如果再在model中加入重复属性会出bug
	 * 使用ThymeLeaf的话就需要验证登录
	 * @param session HTTPSession，会话对象，从中取出isLogin判断登录状态
	 * @param map ModelMap对象，存入属性传给前端模板
	 */
	private void verifyLogin(HttpSession session, ModelMap map) {
		Boolean isLogin = (Boolean) session.getAttribute("isLogin");
		if (isLogin != null && isLogin) {
			if (!map.containsAttribute("isLogin")) {
				map.addAttribute("isLogin", true);
			}
			if (!map.containsAttribute("name")) {
				map.addAttribute("name", session.getAttribute("name"));
			} else {
				map.replace("name", session.getAttribute("name"));
			}
		}
	}
}
