package com.xavier.controller;

import com.xavier.common.BaseResponse;
import com.xavier.model.BuyModel;
import com.xavier.service.CartService;
import com.xavier.service.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xavier on 2018/1/22.
 */
@Controller
@RequestMapping("/sales/api")
public class ApiController {

	private Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Resource
	private LoginService loginService;

	@Resource
	private CartService cartService;

	/**
	 * 登录认证接口
	 *
	 * @param name         用户名
	 * @param password     md5加密后的密码
	 * @param httpRequest  HttpServletRequest对象，用来提取Session
	 * @param httpResponse HttpServletResponse对象，用来返回Cookie
	 * @return 返回认证结果
	 */
	@RequestMapping("/login")
	@ResponseBody
	public BaseResponse login(@RequestParam(value = "userName") String name,
	                          @RequestParam(value = "password") String password,
	                          HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		BaseResponse baseResponse = new BaseResponse();

		HttpSession session = httpRequest.getSession();
		if (loginService.login(name, password, session)) {
			logger.info("User[{}] logged in.", name);
			session.setMaxInactiveInterval(1800);
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(1800);
			httpResponse.addCookie(cookie);

			baseResponse.setMessage("登录成功");
			baseResponse.setSuccess(true);
			baseResponse.setCode(200);
			return baseResponse;
		}
		baseResponse.setSuccess(false);
		return baseResponse;
	}

	/**
	 * 退出登录接口，退出清除session标志
	 *
	 * @param request HttpServletRequest对象，用来提取Session
	 */
	@RequestMapping("/quit")
	public void quit(HttpServletRequest request) {
		loginService.quit(request.getSession());
	}

	@RequestMapping("/buy")
	@ResponseBody
	public BaseResponse buy(@RequestBody List<BuyModel> buyModelList) {
		BaseResponse baseResponse = new BaseResponse();
		buyModelList = buyModelList.stream()
			.filter(buyModel -> buyModel.getNumber() > 0).collect(Collectors.toList());
		cartService.buyProducts(buyModelList);
		baseResponse.setCode(200);
		baseResponse.setSuccess(true);
		return baseResponse;
	}
}
