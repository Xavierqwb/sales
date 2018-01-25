package com.xavier.impl;

import com.xavier.dao.CartDao;
import com.xavier.dao.FinanceDao;
import com.xavier.model.BuyModel;
import com.xavier.model.CartRecordModel;
import com.xavier.service.CartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 购物车服务实现
 * Created by xavier on 2018/1/24.
 */
@Service
public class CartServiceImpl implements CartService{

	private Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Resource
	private CartDao cartDao;

	@Resource
	private FinanceDao financeDao;

	@Override
	public List<CartRecordModel> listProductsInCart() {
		return cartDao.listProducts();
	}

	@Override
	public void buyProducts(List<BuyModel> buyModelList) {
		financeDao.insertRecords(buyModelList);
		cartDao.deleteAll();
	}

	@Override
	public int addProductsToCart(List<CartRecordModel> cartRecordModels) {
		int rows = cartDao.insertProductRecords(cartRecordModels);
		logger.info("{} records have been add to cart.", rows);
		return rows;
	}
}
