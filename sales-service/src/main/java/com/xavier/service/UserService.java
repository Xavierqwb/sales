package com.xavier.service;

import com.xavier.model.UserModel;

/**
 * 用户服务接口
 * Created by xavier on 2018/1/24.
 */
public interface UserService {

	/**
	 * 验证用户登录
	 * @param account 用户账号
	 * @param password 用户密码（MD5加密）
	 * @return 用户的数据
	 */
	UserModel verifyUser(String account, String password);
}
