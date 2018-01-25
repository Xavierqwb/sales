package com.xavier.utils;

import com.xavier.dto.ProductShowDTO;
import com.xavier.model.FinanceModel;
import com.xavier.model.ProductModel;

/**
 * Created by xavier on 2018/1/25.
 */
public class DataTransferUtil {

	public static ProductShowDTO toProductShowDTO(ProductModel productModel,
	                                              FinanceModel financeModel) {
		ProductShowDTO productShowDTO = new ProductShowDTO();
		productShowDTO.setId(productModel.getId());
		productShowDTO.setTitle(productModel.getTitle());
		productShowDTO.setSummary(productModel.getSummary());
		productShowDTO.setImage(productModel.getImage());
		productShowDTO.setDetail(productModel.getDetail());
		productShowDTO.setOriginPrice(productModel.getPrice());
		if (financeModel != null) {
			productShowDTO.setPrice(financeModel.getPrice());
			productShowDTO.setBought(true);
		}
		return productShowDTO;
	}
}
