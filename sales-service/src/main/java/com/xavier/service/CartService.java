package com.xavier.service;

import com.xavier.model.BuyModel;
import com.xavier.model.CartRecordModel;

import java.util.List;

/**
 * 购物车服务接口
 * Created by xavier on 2018/1/24.
 */
public interface CartService {

	/**
	 * 列出购物车中的商品
	 * @return 商品列表
	 */
	List<CartRecordModel> listProductsInCart();

	/**
	 * 对购物车的商品进行购买
	 * @param buyModelList 购买的商品ID、数量的列表
	 */
	void buyProducts(List<BuyModel> buyModelList);

	/**
	 * 将商品加入购物车
	 * @param cartRecordModels 商品记录列表
	 * @return 加入的数量
	 */
	int addProductsToCart(List<CartRecordModel> cartRecordModels);
}
