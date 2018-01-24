package com.xavier.dao;

import com.xavier.annotation.MybatisDao;
import com.xavier.model.CartRecordModel;

import java.util.List;

/**
 * 购物车Dao
 * Created by xavier on 2018/1/24.
 */
@MybatisDao
public interface CartDao {

	List<CartRecordModel> listProducts();
}
