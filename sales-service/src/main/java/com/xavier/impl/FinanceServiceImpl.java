package com.xavier.impl;

import com.xavier.dao.FinanceDao;
import com.xavier.model.FinanceModel;
import com.xavier.service.FinanceService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by xavier on 2018/1/25.
 */
@Service
public class FinanceServiceImpl implements FinanceService{

	@Resource
	private FinanceDao financeDao;

	@Override
	public List<FinanceModel> listFinances() {
		return financeDao.listFinances();
	}
}
