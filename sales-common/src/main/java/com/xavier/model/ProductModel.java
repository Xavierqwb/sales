package com.xavier.model;

import lombok.Data;

/**
 * Created by xavier on 2018/1/23.
 */
@Data
public class ProductModel {
	private String title;

	private String summary;

	private String image;

	private String detail;

	private Long price;

	public static ProductModel parseProduct(String input) {
		ProductModel productModel = new ProductModel();
		String[] props = input.split("&");
		productModel.setTitle(props[0].substring(6));
		productModel.setSummary(props[1].substring(8));
		productModel.setImage(props[3].substring(6));
		productModel.setDetail(props[5].substring(7));
		productModel.setPrice(Long.valueOf(props[6].substring(6)));

		return productModel;
	}
}
