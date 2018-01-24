package com.xavier.dao;

import com.xavier.annotation.MybatisDao;
import com.xavier.model.ProductModel;

import java.util.List;

/**
 * 用于操作产品发布和删除
 * Created by xavier on 2018/1/23.
 */
@MybatisDao
public interface ProductDao {

	ProductModel getProduct(int id);

	List<ProductModel> listProducts();

	Integer insertProduct(ProductModel productModel);

	Integer deleteProduct(int id);
}
