package com.xavier.dao;

import com.xavier.annotation.MybatisDao;
import com.xavier.model.UserModel;

import org.apache.ibatis.annotations.Param;

/**
 * 用户Dao
 * Created by xavier on 2018/1/24.
 */
@MybatisDao
public interface UserDao {

	UserModel verifyUser(@Param("account") String account, @Param("password") String password);
}
