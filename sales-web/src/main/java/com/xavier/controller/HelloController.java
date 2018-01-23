package com.xavier.controller;

import com.xavier.dao.ProductDao;
import com.xavier.model.ProductModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by xavier on 2018/1/21.
 */
@Controller
public class HelloController {

	@Resource
	private ProductDao productDao;

	@RequestMapping("test")
	@ResponseBody
	public ProductModel test() {
		return productDao.getProduct(1);
	}

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
