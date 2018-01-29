package com.xavier.controller;

import com.xavier.annotation.RestfulController;
import com.xavier.annotation.WebController;
import com.xavier.common.BaseResponse;
import com.xavier.model.UserModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xavier on 2018/1/21.
 */
@Controller
public class HelloController {

	@WebController
	@RequestMapping("/")
	public String hello(
		@RequestParam(value = "name", required = false, defaultValue = "World") String name,
		ModelMap modelMap) {
		if (name.equals("error")) {
			throw new RuntimeException("test1");
		}
		modelMap.addAttribute("name", name);
		return "hello";
	}

	@RestfulController
	@RequestMapping("/test")
	@ResponseBody
	public BaseResponse<UserModel> test(@RequestParam("id") int id) {
		BaseResponse<UserModel> baseResponse = new BaseResponse<>();
		UserModel userModel = new UserModel();
		userModel.setAccount("asdas");
		userModel.setId(1);
		baseResponse.setData(userModel);
		baseResponse.setCode(200);
		baseResponse.setSuccess(true);
		baseResponse.setMessage("test");
		if (id == 1) {
			throw new RuntimeException("test2");
		}
		return baseResponse;
	}

}
