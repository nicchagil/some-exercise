
select * from t_coupon t where t.`status` = 1;

-- 未领取
select count(*) from t_coupon t where t.`status` = 1;
-- 已领取
select count(*) from t_coupon t where t.`status` = 0;

-- 重置数据
update t_coupon t set t.`status` = 0 and t.`user_id` = null;

-- 删除数据
DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '优惠券类型：100元优惠券1,50元优惠券2',
  `code` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '优惠券码',
  `status` int(11) DEFAULT NULL COMMENT '状态：没使用0，已使用1',
  `user_id` bigint(20) DEFAULT NULL COMMENT '领取人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 插入数据
select f_insert(2000);

/** Redis OPS
keys *coupon*
get "\xac\xed\x00\x05t\x00\ncoupon_100"
set "\xac\xed\x00\x05t\x00\ncoupon_100" 200
 */
 
/* 执行时间 */
-- 领取数量 200
select count(*) from t_coupon t where t.`status` = 1;
-- 1
select max(t.`user_id`) - min(t.`user_id`) from t_coupon t where t.`status` = 1;

-- 领取数量 2000
select count(*) from t_coupon t where t.`status` = 1;
-- 8
select max(t.`user_id`) - min(t.`user_id`) from t_coupon t where t.`status` = 1;