package com.xavier.service;

import com.xavier.common.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 与登录相关的服务接口
 * Created by xavier on 2018/1/21.
 */
public interface LoginService {

	BaseResponse login(String name, String md5Pwd, HttpServletRequest request, HttpServletResponse response);

	void quit(HttpSession session);
}
