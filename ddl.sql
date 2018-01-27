CREATE TABLE product(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`title` VARCHAR(20) NOT NULL COMMENT '商品名称',
	`summary` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '摘要',
	`image` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '图片地址',
	`detail` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '正文',
	`price` BIGINT NOT NULL DEFAULT 0 COMMENT '价格',
	`sold_num` INT NOT NULL DEFAULT 0 COMMENT '已经卖出的数量',
	`deleted` SMALLINT NOT NULL DEFAULT 1 COMMENT '0:删除，1:未删除',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`account` VARCHAR(10) NOT NULL COMMENT '账号',
	`md5_pwd` VARCHAR(255) NOT NULL COMMENT 'MD5加密后的密码',
	`type` INT NOT NULL COMMENT '账户类型：0.卖家;1.买家',
	`nick_name` VARCHAR(10) NOT NULL COMMENT '昵称',
	PRIMARY KEY (`id`),
	INDEX idx_account(`account`,`md5_pwd`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (`account`, `md5_pwd`, `type`, `nick_name`)
VALUES ('seller', '981c57a5cfb0f868e064904b8745766f', 0, '卖家'),
('buyer', '37254660e226ea65ce6f1efd54233424', 1, '买家');

CREATE TABLE cart(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` INT NOT NULL COMMENT '用户ID',
	`product_id` INT NOT NULL COMMENT '商品ID',
	`num` INT NOT NULL COMMENT '商品数量',
	`deleted` INT NOT NULL DEFAULT 1 COMMENT '0:删除，1:未删除',
	PRIMARY KEY (`id`),
	UNIQUE KEY unique_user_product(`user_id`, `product_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE finance(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` INT NOT NULL COMMENT '用户ID',
	`product_id` INT NOT NULL COMMENT '商品ID',
	`num` INT NOT NULL COMMENT '商品数量',
	`price` INT NOT NULL COMMENT '购买时的价格',
	`purchase_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '购买的时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;