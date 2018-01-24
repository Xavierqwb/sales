package com.xavier.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xavier.common.BaseResponse;
import com.xavier.service.LoginService;
import com.xavier.utils.Security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 与登录相关的服务接口实现
 * Created by xavier on 2018/1/21.
 */
@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public BaseResponse login(String name, String md5Pwd, HttpServletRequest request, HttpServletResponse response) {
		BaseResponse baseResponse = new BaseResponse();
		if (name.equals("seller") && md5Pwd.equals(Security.md5("relles"))
		       || name.equals("buyer") && md5Pwd.equals(Security.md5("reyub"))){
			logger.info("User[{}] logged in.", name);
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("password", md5Pwd);
			session.setAttribute("isLogin", true);
			session.setMaxInactiveInterval(1800);
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(1800);
			response.addCookie(cookie);

			baseResponse.setMessage("登录成功");
			baseResponse.setSuccess(true);
			baseResponse.setCode(200);
			return baseResponse;
		}

		baseResponse.setSuccess(false);
		return baseResponse;
	}

	@Override
	public void quit(HttpSession session) {
		session.removeAttribute("isLogin");
		String name = (String) session.getAttribute("name");
		session.removeAttribute("name");
		logger.info("User[{}] quited.", name);
	}
}
