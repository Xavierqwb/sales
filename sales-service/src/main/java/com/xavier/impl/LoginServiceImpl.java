package com.xavier.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xavier.model.UserModel;
import com.xavier.service.LoginService;
import com.xavier.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 与登录相关的服务接口实现
 * Created by xavier on 2018/1/21.
 */
@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Resource
	private UserService userService;

	@Override
	public boolean login(String name, String md5Pwd, HttpSession session) {
		if (name.equals("seller") || name.equals("buyer")){
			UserModel userModel = userService.verifyUser(name, md5Pwd);
			if (userModel != null) {
				session.setAttribute("userModel", userModel);
				return true;
			}
		}
		return false;
	}

	@Override
	public void quit(HttpSession session) {
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		session.removeAttribute("userModel");
		logger.info("User[{}] quited.", userModel.getAccount());
	}
}
