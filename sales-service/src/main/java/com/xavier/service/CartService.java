package com.xavier.service;

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
}
