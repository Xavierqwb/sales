package com.xavier.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import lombok.Data;

/**
 * Created by xavier on 2018/1/23.
 */
@Data
public class ProductModel {

	private static final String CHARSET = "utf-8";

	private int id;

	private String title;

	private String summary;

	private String image;

	private String detail;

	// 单位：分
	private Long price;

	public static ProductModel parseProduct(String input) {
		ProductModel productModel = new ProductModel();
		String[] props = input.split("&");
		String title = props[0].substring(6);
		String summary = props[1].substring(8);
		String image = props[3].substring(6);
		String detail = props[5].substring(7);
		try {
			title = URLDecoder.decode(title, CHARSET);
			summary = URLDecoder.decode(summary, CHARSET);
			image = URLDecoder.decode(image, CHARSET);
			detail = URLDecoder.decode(detail, CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		productModel.setTitle(title);
		productModel.setSummary(summary);
		productModel.setImage(image);
		productModel.setDetail(detail);
		productModel.setPrice((long) Math.floor(Double.valueOf(props[6].substring(6)) * 100));

		return productModel;
	}
}
