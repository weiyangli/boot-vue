/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : bv

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-19 17:20:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` bigint(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES ('389301103591686144', '战三');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`  bigint(50) NOT NULL,
  `name` varchar(255)  DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('2', '普通管理员', 'ROLE_COMMON_ADMIN');
INSERT INTO `role` VALUES ('3', '普通用户', 'ROLE_USER');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(50) NOT NULL,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `picture` varchar(255) DEFAULT NULL COMMENT '头像',
  `enabled` tinyint(2) DEFAULT '1' COMMENT '是否启用 0：禁用 1：启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, '1');
INSERT INTO `user` VALUES ('2', 'lwy', 'lwy', 'e10adc3949ba59abbe56e057f20f883e', null, '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(50) NOT NULL,
  `role_id` bigint(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_key` (`user_id`,`role_id`) COMMENT '角色和用户一对一绑定'
) ENGINE=InnoDB;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '2');

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
) ENGINE=InnoDB;