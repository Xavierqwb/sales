package com.xavier.model;

import lombok.Data;

/**
 * 用户数据模型
 * Created by xavier on 2018/1/24.
 */
@Data
public class UserModel {

	private int id;

	private String account;

	private String password;

	/**
	 * 0:卖家  1:买家
	 */
	private int type;

	private String nickName;
}
