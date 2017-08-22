package com.nicchagil.springjtaexercise.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceDAO {
	
	@Autowired @Qualifier("balanceJdbcTemplate")
    JdbcTemplate jdbcTemplate;
	
    public void updateBalanceByUserId() {
        this.jdbcTemplate.execute("update t_balance t set t.balance = t.balance - 1 where t.user_id = 10000");
    }

}
