package com.xavier.service;

import javax.servlet.http.HttpSession;

/**
 * 与登录相关的服务接口
 * Created by xavier on 2018/1/21.
 */
public interface LoginService {

	boolean login(String name, String md5Pwd, HttpSession session);

	void quit(HttpSession session);
}
