package com.xavier.service;

import com.xavier.model.UserModel;

/**
 * 用户服务接口
 * Created by xavier on 2018/1/24.
 */
public interface UserService {

	UserModel getUser(String account);

	UserModel verifyUser(String account, String password);
}
