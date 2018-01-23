package com.xavier.dao;

import com.xavier.aop.MybatisDao;
import com.xavier.model.ProductModel;

import org.springframework.stereotype.Repository;

/**
 * 用于操作产品发布和删除
 * Created by xavier on 2018/1/23.
 */
@Repository
public interface ProductDao {

	public ProductModel getProduct(int id);
}
