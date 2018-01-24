package com.xavier.model;

import lombok.Data;

/**
 * 购物车记录模型
 * Created by xavier on 2018/1/24.
 */
@Data
public class CartRecordModel {

	private int id;

	private String title;

	private Integer num;

	private Long price;
}
