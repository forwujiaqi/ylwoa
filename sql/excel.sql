/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ylwoa

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-08-21 21:25:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `excel`
-- ----------------------------
DROP TABLE IF EXISTS `excel`;
CREATE TABLE `excel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `excel_type` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of excel
-- ----------------------------
