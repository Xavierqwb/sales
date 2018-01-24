package com.xavier.dao;

import com.xavier.annotation.MybatisDao;
import com.xavier.model.BuyModel;

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
}
