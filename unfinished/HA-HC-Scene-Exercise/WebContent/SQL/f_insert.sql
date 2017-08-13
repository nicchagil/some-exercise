DELIMITER $$

USE `blog`$$

DROP FUNCTION IF EXISTS `f_insert`$$

CREATE DEFINER=`root`@`%` FUNCTION `f_insert`(
    num BIGINT
) RETURNS bigint(20)
BEGIN
    DECLARE i INT DEFAULT 0;
    
    WHILE i < num DO
        insert into `t_coupon`(`type`,`code`,`status`) values (1, floor(RAND() * 100000000), 0);
        SET i = i +1;
    END WHILE;
    
    return i;
END$$

DELIMITER ;