package com.xavier.impl;

import com.xavier.dao.ProductDao;
import com.xavier.model.ProductModel;
import com.xavier.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 对商品操作的服务
 * Created by xavier on 2018/1/24.
 */
@Service
public class ProductServiceImpl implements ProductService{

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Resource
	private ProductDao productDao;

	@Override
	public int publishProduct(ProductModel productModel) {
		int rows = productDao.insertProduct(productModel);
		logger.info("{} product has been inserted, id is {}", rows, productModel.getId());
		return productModel.getId();
	}

	@Override
	public boolean deleteProduct(int id) {
		int rows = productDao.deleteProduct(id);
		if (rows > 0) {
			logger.info("{} product with id:{} has been deleted.", rows, id);
			return true;
		}
		logger.info("There is no product with id:{}", id);
		return false;
	}

	@Override
	public List<ProductModel> listProduct() {
		return productDao.listProducts();
	}
}
