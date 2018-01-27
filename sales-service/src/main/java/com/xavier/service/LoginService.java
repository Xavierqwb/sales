package com.xavier.service;

import javax.servlet.http.HttpSession;

/**
 * 与登录相关的服务接口
 * Created by xavier on 2018/1/21.
 */
public interface LoginService {

	/**
	 * 用户登录
	 * @param name 用户名
	 * @param md5Pwd MD5加密的密码
	 * @param session 会话session
	 * @return 是否成功登录
	 */
	boolean login(String name, String md5Pwd, HttpSession session);

	/**
	 * 用户退出
	 * @param session 会话session
	 */
	void quit(HttpSession session);
}
