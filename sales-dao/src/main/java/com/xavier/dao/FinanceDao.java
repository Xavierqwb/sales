package com.xavier.dao;

import com.xavier.annotation.MybatisDao;
import com.xavier.model.BuyModel;
import com.xavier.model.FinanceModel;

import java.util.List;

/**
 * Created by xavier on 2018/1/24.
 */
@MybatisDao
public interface FinanceDao {

	/**
	 * 插入购买记录
	 * @param buyModelList 购买的商品ID、数量的列表
	 * @return 修改的记录行数
	 */
	int insertRecords(List<BuyModel> buyModelList);

	/**
	 * 返回已购买记录
	 * @return 已购买记录列表
	 */
	List<FinanceModel> listFinances();
}
