package com.xavier.impl;

import com.xavier.dao.CartDao;
import com.xavier.model.CartRecordModel;
import com.xavier.service.CartService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 购物车服务实现
 * Created by xavier on 2018/1/24.
 */
@Service
public class CartServiceImpl implements CartService{

	@Resource
	private CartDao cartDao;

	@Override
	public List<CartRecordModel> listProductsInCart() {
		return cartDao.listProducts();
	}
}
