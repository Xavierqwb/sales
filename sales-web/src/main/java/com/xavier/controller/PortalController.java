package com.xavier.controller;

import com.xavier.model.ProductModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 主站页面控制器
 * Created by xavier on 2018/1/21.
 */
@Controller
@RequestMapping("/sales")
public class PortalController {

	private Logger logger = LoggerFactory.getLogger(PortalController.class);

	/**
	 * 首页接口
	 * @return 返回首页
	 */
	@RequestMapping("")
	public String homePage() {
		return "index";
	}

	/**
	 * 登录页面接口
	 * @return 返回登录页面
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 发布页面接口
	 * @return 返回发布页面
	 */
	@RequestMapping("/publish")
	public String publish() {
		return "publish";
	}

	/**
	 * 发布成功页面接口
	 * @param input 传入的参数，表单格式
	 * @param map ModelMap，向前端传递参数
	 * @return 发布成功页面
	 */
	@RequestMapping(value = "/publishSubmit", method = RequestMethod.POST)
	public String publishSubmit(@RequestBody String input,
	                            ModelMap map) {
		map.addAttribute("id", 1);
		ProductModel productModel = ProductModel.parseProduct(input);
		logger.info(productModel.toString());
		return "publishSubmit";
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
