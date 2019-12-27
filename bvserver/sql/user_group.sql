/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : bv

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-27 09:46:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(50) NOT NULL COMMENT '用户 id',
  `group_id` bigint(50) NOT NULL COMMENT '小组 id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_group` (`user_id`,`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
