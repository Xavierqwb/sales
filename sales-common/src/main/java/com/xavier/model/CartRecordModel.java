package com.xavier.model;

import lombok.Data;

/**
 * 购物车记录模型
 * Created by xavier on 2018/1/24.
 */
@Data
public class CartRecordModel {

	private int id;

	private String name;

	private Integer number;

	private Long price;
}
