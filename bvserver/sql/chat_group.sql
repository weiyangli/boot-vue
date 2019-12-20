/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : bv

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-20 16:44:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat_group
-- ----------------------------
DROP TABLE IF EXISTS `chat_group`;
CREATE TABLE `chat_group` (
  `id` bigint(50) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL COMMENT '小组名称',
  `createdTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `createdBy` bigint(50) DEFAULT '0' COMMENT '创建人',
  `updatedBy` bigint(50) DEFAULT '0' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
