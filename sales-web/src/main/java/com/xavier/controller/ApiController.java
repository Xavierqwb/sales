package com.xavier.controller;

import com.xavier.common.BaseResponse;
import com.xavier.service.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xavier on 2018/1/22.
 */
@RestController
@RequestMapping("/sales/api")
public class ApiController {

	private Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Resource
	private LoginService loginService;

	@RequestMapping("/login")
	public BaseResponse login(@RequestParam(value = "userName") String name,
	                          @RequestParam(value = "password") String password,
	                          HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		BaseResponse baseResponse = new BaseResponse();
		boolean isLogin = loginService.login(name, password);
		logger.info("Login Success : " + isLogin);

		if (isLogin) {
			HttpSession session = httpRequest.getSession();
			session.setAttribute("name", name);
			session.setAttribute("password", password);
			session.setAttribute("isLogin", true);
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

	@RequestMapping("/quit")
	public void quit(HttpServletRequest request) {
		logger.info("Quiting");
		HttpSession session = request.getSession();
		session.removeAttribute("isLogin");
		logger.info("Quited");
	}
}
