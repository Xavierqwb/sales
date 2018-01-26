package com.xavier.impl;

import com.xavier.dao.FinanceDao;
import com.xavier.dao.ProductDao;
import com.xavier.model.FinanceModel;
import com.xavier.model.ProductModel;
import com.xavier.service.ProductService;
import com.xavier.utils.DataTransferUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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
	public ProductModel getProduct(int id) {
		return productDao.getProduct(id);
	}

	@Override
	public int publishProduct(String productFrom, ModelMap map) {
		ProductModel productModel = ProductModel.parseProduct(productFrom);
		int rows = productDao.insertProduct(productModel);
		map.addAttribute("id", productModel.getId());
		logger.info("{} product has been published, id is {}", rows, productModel.getId());
		return productModel.getId();
	}

	@Override
	public int editProduct(int id, ProductModel productModel) {
		return productDao.updateProduct(id, productModel);
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

	@Override
	public List<ProductModel> notBoughtProductList() {
		return productDao.notBuyProducts();
	}

	@Override
	public List<ProductModel> boughtProductList() {
		return productDao.buyProducts();
	}
}
