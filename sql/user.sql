/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ylwoa

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-08-21 21:25:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` char(64) NOT NULL COMMENT '密码密文',
  `user_type` tinyint(4) NOT NULL,
  `phone` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号码',
  `real_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `permit` varchar(20) DEFAULT NULL,
  `delete_flg` int(1) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL COMMENT '创建用户id',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';
