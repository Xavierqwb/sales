package com.xavier.service;

import com.xavier.model.ProductModel;

import java.util.List;

/**
 * 商品服务的接口
 * Created by xavier on 2018/1/24.
 */
public interface ProductService {

	/**
	 * 发布商品的接口，保存到db中
	 * @param productModel 商品数据
	 * @return 数据库改动记录
	 */
	int publishProduct(ProductModel productModel);

	/**
	 * 删除商品的接口，在db中逻辑删除
	 * @param id 商品的主键id
	 * @return 是否删除成果
	 */
	boolean deleteProduct(int id);

	/**
	 * 查询所有商品的列表
	 * @return 所有商品的列表
	 */
	List<ProductModel> listProduct();

}
