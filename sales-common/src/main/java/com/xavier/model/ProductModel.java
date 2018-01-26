package com.xavier.model;

import com.xavier.utils.FormToMapUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

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
		Map<String, String> propsMap = null;
		try {
			propsMap = FormToMapUtil.formToMap(URLDecoder.decode(input, CHARSET));
		} catch (UnsupportedEncodingException e) {
			// ignore
			e.printStackTrace();
		}
		productModel.setId(Integer.valueOf(propsMap.get("id")));
		productModel.setTitle(propsMap.get("title"));
		productModel.setSummary(propsMap.get("summary"));
		productModel.setImage(propsMap.get("image"));
		productModel.setDetail(propsMap.get("detail"));
		productModel.setPrice((long) Math.floor(Double.valueOf(propsMap.get("price")) * 100));

		return productModel;
	}
}
