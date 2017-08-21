/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ylwoa

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-08-21 21:25:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `excel_data`
-- ----------------------------
DROP TABLE IF EXISTS `excel_data`;
CREATE TABLE `excel_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `excel_id` bigint(20) NOT NULL,
  `excel_name` varchar(255) NOT NULL COMMENT '表名',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `status` int(1) NOT NULL,
  `delete_flg` int(1) NOT NULL,
  `data_json` text NOT NULL COMMENT '表数据',
  `plan_start_date` date NOT NULL,
  `plan_end_date` date NOT NULL,
  `owner_name` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL COMMENT '创建用户id',
  `create_user_name` varchar(10) NOT NULL,
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL COMMENT '更新用户id',
  `update_user_name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of excel_data
-- ----------------------------
