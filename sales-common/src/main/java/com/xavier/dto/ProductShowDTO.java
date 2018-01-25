package com.xavier.dto;

import lombok.Data;

/**
 * Created by xavier on 2018/1/25.
 */
@Data
public class ProductShowDTO {

	private int id;

	private String title;

	private String summary;

	private String image;

	private String detail;

	private Long originPrice;
	// 单位：分，购买时的价格
	private Long price;

	private boolean bought =false;

}
