update balance.`t_balance` t set t.`balance`  = 10000;
update stock.`t_stock` t set t.`stock`  = 10000;

select * from balance.`t_balance` t;
select * from stock.`t_stock` t;