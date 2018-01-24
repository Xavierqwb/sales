package com.xavier.model;

import java.sql.Timestamp;

import lombok.Data;

/**
 * Created by xavier on 2018/1/24.
 */
@Data
public class FinanceModel {

	private int id;

	private int userId;

	private int productId;

	private int num;

	private Timestamp purchaseTime;
}
