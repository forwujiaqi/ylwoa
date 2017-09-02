/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ylwoa

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-08-21 21:25:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `job_record`
-- ----------------------------
DROP TABLE IF EXISTS `job_record`;
CREATE TABLE `job_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `record_name` varchar(255) NOT NULL COMMENT '表名',
  `record_content` text NOT NULL,
  `record_type` int(1) NOT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `owner_name` varchar(100) DEFAULT NULL,
  `delete_flg` int(1) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL COMMENT '创建用户id',
  `create_user_name` varchar(20) NOT NULL,
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL COMMENT '更新用户id',
  `update_user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_record
-- ----------------------------
