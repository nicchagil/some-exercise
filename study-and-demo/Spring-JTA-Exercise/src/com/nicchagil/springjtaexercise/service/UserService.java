package com.nicchagil.springjtaexercise.service;

import java.util.Random;

import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.nicchagil.springjtaexercise.dao.BalanceDAO;
import com.nicchagil.springjtaexercise.dao.StockDAO;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JtaTransactionManager jtaTransactionManager;

	@Autowired
	private BalanceDAO userDAO;

	@Autowired
	private StockDAO userRoleDAO;

	public void updateBalanceAndStock() {
		UserTransaction userTransaction = jtaTransactionManager.getUserTransaction();
		try {
			userTransaction.begin(); // 事务开始

			this.userDAO.updateBalanceByUserId(); // 扣减余额
			
			if (new Random().nextInt(2) == 0) {
				throw new RuntimeException("模拟异常");
			}
			
			this.userRoleDAO.updateStockByGoodsId(); // 扣减库存
			
			userTransaction.commit(); // 提交事务
			this.logger.info("事务提交成功");
		} catch (Exception e) {
			this.logger.error("出现异常：{}", e);
			
			/* 回滚事务 */
			try {
				userTransaction.rollback();
				this.logger.info("事务回滚成功");
			} catch (Exception se) {
				this.logger.error("事务回滚失败：{}", se);
			}
		}
	}

}
