package com.xavier.service;

import com.xavier.model.FinanceModel;

import java.util.List;

/**
 * Created by xavier on 2018/1/25.
 */
public interface FinanceService {

	/**
	 * 列出所有已购买的商品
	 * @return 已购买商品的列表
	 */
	List<FinanceModel> listFinances();

	/**
	 * 取得已购买的某一件商品
	 * @param id 已购买商品的ID
	 * @return 已购买的商品
	 */
	FinanceModel getFinanceModel(int id);
}
