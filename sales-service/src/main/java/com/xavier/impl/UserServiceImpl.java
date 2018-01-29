package com.xavier.impl;

import com.xavier.dao.UserDao;
import com.xavier.model.UserModel;
import com.xavier.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务接口实现
 * Created by xavier on 2018/1/24.
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public UserModel verifyUser(String account, String password) {
		return userDao.verifyUser(account, password);
	}

	public static void main(String[] args){

	}
}
