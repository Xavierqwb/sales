package com.xavier.service;

import com.xavier.model.BuyModel;
import com.xavier.model.ProductModel;

import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * 商品服务的接口
 * Created by xavier on 2018/1/24.
 */
public interface ProductService {

	/**
	 * 根据ID返回商品
	 * @param id 主键ID
	 */
	ProductModel getProduct(int id);

	/**
	 * 发布商品的接口，保存到db中
	 * @param productFrom 商品数据
	 * @param map 返回前端的模型
	 * @return 数据库改动记录
	 */
	int publishProduct(String productFrom, ModelMap map);

	/**
	 * 编辑商品属性
	 * @param id 商品ID
	 * @param productModel 商品属性
	 * @return 修改行数
	 */
	int editProduct(int id, ProductModel productModel);

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

	/**
	 * 记录下已经售出的商品
	 * @param buyModelList 售出的商品列表
	 */
	void soldProducts(List<BuyModel> buyModelList);

	/**
	 * 查询未购买的商品列表
	 * @return 未购买的商品列表
	 */
	List<ProductModel> notBoughtProductList();

	/**
	 * 查询已购买的商品列表
	 * @return 已购买的商品列表
	 */
	List<ProductModel> boughtProductList();

}
