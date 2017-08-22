DROP TABLE IF EXISTS `t_balance`;

CREATE TABLE `t_balance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `balance` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_balance` */

insert  into `t_balance`(`id`,`user_id`,`balance`) values (1,10000,10000);

