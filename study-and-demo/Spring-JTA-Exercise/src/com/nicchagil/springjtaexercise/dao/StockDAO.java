package com.nicchagil.springjtaexercise.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StockDAO {
	
	@Autowired @Qualifier("stockJdbcTemplate")
    JdbcTemplate jdbcTemplate;
	
    public void updateStockByGoodsId() {
        this.jdbcTemplate.execute("update t_stock t set t.stock = t.stock - 1 where t.goods_id = 10000");
    }

}
