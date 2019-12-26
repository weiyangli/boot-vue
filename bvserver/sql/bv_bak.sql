/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : bv

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-26 17:14:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat_group
-- ----------------------------
DROP TABLE IF EXISTS `chat_group`;
CREATE TABLE `chat_group` (
  `id` bigint(50) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL COMMENT '小组名称',
  `picture` varchar(255) DEFAULT NULL COMMENT '群头像',
  `created_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint(50) DEFAULT '0' COMMENT '创建人',
  `updated_by` bigint(50) DEFAULT '0' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of chat_group
-- ----------------------------
INSERT INTO `chat_group` VALUES ('394758507250843648', '游戏群', null, '2019-12-26 15:49:22', '2019-12-26 15:49:22', '2', '0');

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` bigint(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES ('389301103591686144', '战三');
INSERT INTO `demo` VALUES ('389301426091720704', '战三2');
INSERT INTO `demo` VALUES ('389332073095102464', '1');
INSERT INTO `demo` VALUES ('389332268939739136', '1');
INSERT INTO `demo` VALUES ('389332329560014848', '1');
INSERT INTO `demo` VALUES ('389332455586267136', '1');
INSERT INTO `demo` VALUES ('389332568920555520', '张三');
INSERT INTO `demo` VALUES ('389332763230076928', '张三');
INSERT INTO `demo` VALUES ('389332918285107200', '张三');
INSERT INTO `demo` VALUES ('389333605081415680', '张三');
INSERT INTO `demo` VALUES ('389336784082829312', '张三');
INSERT INTO `demo` VALUES ('389336808170717184', '张三');
INSERT INTO `demo` VALUES ('389336823169548288', '张三');
INSERT INTO `demo` VALUES ('389609143171809280', '张三');
INSERT INTO `demo` VALUES ('389609145394790400', '张三');
INSERT INTO `demo` VALUES ('389609147777155072', '张三');
INSERT INTO `demo` VALUES ('389610193299701760', '张三');
INSERT INTO `demo` VALUES ('389611130357547008', '张三');
INSERT INTO `demo` VALUES ('389611138943287296', '张三');
INSERT INTO `demo` VALUES ('389629305384075264', '张三');
INSERT INTO `demo` VALUES ('389629743747563520', '张三');
INSERT INTO `demo` VALUES ('389629825393885184', '张三');
INSERT INTO `demo` VALUES ('390060870803128320', '111');
INSERT INTO `demo` VALUES ('391046521929334784', '232');
INSERT INTO `demo` VALUES ('391141577222782976', '222');
INSERT INTO `demo` VALUES ('391143130503577600', '222');
INSERT INTO `demo` VALUES ('391151484693118976', 'fgsg');
INSERT INTO `demo` VALUES ('391162552194695168', 'fdssfsd');
INSERT INTO `demo` VALUES ('391475716568383488', 'fdssfsd');
INSERT INTO `demo` VALUES ('391505199975366656', '张三');
INSERT INTO `demo` VALUES ('391505246624415744', '张三');
INSERT INTO `demo` VALUES ('391505503185797120', '张三222');
INSERT INTO `demo` VALUES ('391505514200039424', '张三222');
INSERT INTO `demo` VALUES ('391505655527112704', '张三222');
INSERT INTO `demo` VALUES ('391898262350594048', '张三222');
INSERT INTO `demo` VALUES ('391898262530949120', '张三222');
INSERT INTO `demo` VALUES ('391898411558764544', '张三222');
INSERT INTO `demo` VALUES ('391898412993216512', '张三222');
INSERT INTO `demo` VALUES ('391898601082585088', '张三222');
INSERT INTO `demo` VALUES ('391898674558402560', '张三222');
INSERT INTO `demo` VALUES ('391898760394833920', '张三222');
INSERT INTO `demo` VALUES ('392128370336333824', '张三222');
INSERT INTO `demo` VALUES ('392130484127137792', '张三222');
INSERT INTO `demo` VALUES ('392130545628217344', '张三222');
INSERT INTO `demo` VALUES ('392131252427161600', '张三222');
INSERT INTO `demo` VALUES ('392131588378329088', '张三222');
INSERT INTO `demo` VALUES ('392131642791034880', '张三222');
INSERT INTO `demo` VALUES ('392131727813771264', '张三222');
INSERT INTO `demo` VALUES ('392132480775225344', '张三222');
INSERT INTO `demo` VALUES ('392132482159345664', '张三222');
INSERT INTO `demo` VALUES ('392133057810792448', '张三222');
INSERT INTO `demo` VALUES ('392133729226588160', '张三222');
INSERT INTO `demo` VALUES ('392133827662708736', '张三222');
INSERT INTO `demo` VALUES ('392134086027640832', '张三222');
INSERT INTO `demo` VALUES ('392134209310818304', '张三222');
INSERT INTO `demo` VALUES ('392134209856077824', '张三222');
INSERT INTO `demo` VALUES ('392134233604227072', '张三222');
INSERT INTO `demo` VALUES ('392134499783147520', '张三222');
INSERT INTO `demo` VALUES ('392137624535433216', '张三222');
INSERT INTO `demo` VALUES ('392138183678099456', '张三222');
INSERT INTO `demo` VALUES ('392138884332388352', '张三222');
INSERT INTO `demo` VALUES ('392138905522012160', '张三222');
INSERT INTO `demo` VALUES ('392138944289964032', '张三222');
INSERT INTO `demo` VALUES ('392139184166404096', '张三222');
INSERT INTO `demo` VALUES ('392139208770191360', '张三222');
INSERT INTO `demo` VALUES ('392139240760147968', '张三222');
INSERT INTO `demo` VALUES ('392139340056100864', '张三222');
INSERT INTO `demo` VALUES ('392140369501880320', '张三222');
INSERT INTO `demo` VALUES ('392140387734519808', '张三222');
INSERT INTO `demo` VALUES ('392140519951564800', '张三222');
INSERT INTO `demo` VALUES ('392141603705192448', '张三222');
INSERT INTO `demo` VALUES ('392143785527607296', '张三222');
INSERT INTO `demo` VALUES ('392143909880332288', '张三222');
INSERT INTO `demo` VALUES ('392143987344932864', '张三222');
INSERT INTO `demo` VALUES ('392144001874001920', '张三222');
INSERT INTO `demo` VALUES ('392145428331626496', '张三222');
INSERT INTO `demo` VALUES ('392145982952833024', '张三222');
INSERT INTO `demo` VALUES ('392146319759638528', '张三222');
INSERT INTO `demo` VALUES ('392146475305402368', '张三222');
INSERT INTO `demo` VALUES ('392146729362784256', '张三222');
INSERT INTO `demo` VALUES ('392147841549598720', '张三222');
INSERT INTO `demo` VALUES ('392148169523200000', '张三222');
INSERT INTO `demo` VALUES ('392148800325550080', '张三222');
INSERT INTO `demo` VALUES ('392148860153102336', '张三222');
INSERT INTO `demo` VALUES ('392149672346517504', '张三222');
INSERT INTO `demo` VALUES ('392149805742161920', '张三222');
INSERT INTO `demo` VALUES ('392151008110706688', '张三222');
INSERT INTO `demo` VALUES ('392151088544874496', '张三222');
INSERT INTO `demo` VALUES ('392151192404230144', '张三222');
INSERT INTO `demo` VALUES ('392151446637772800', '张三222');
INSERT INTO `demo` VALUES ('392151467668013056', '张三222');
INSERT INTO `demo` VALUES ('392151578691239936', '张三222');
INSERT INTO `demo` VALUES ('392151597028737024', '张三222');
INSERT INTO `demo` VALUES ('392151982808236032', '张三222');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(50) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `enabled` tinyint(2) DEFAULT '1' COMMENT '是否启用 0：禁用 1：启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, '1');
INSERT INTO `user` VALUES ('2', 'lwy', '李未央', 'e10adc3949ba59abbe56e057f20f883e', null, '1');
INSERT INTO `user` VALUES ('3', 'ljn', '刘金拿', 'e10adc3949ba59abbe56e057f20f883e', null, '1');
INSERT INTO `user` VALUES ('4', 'jay', '周杰伦', 'e10adc3949ba59abbe56e057f20f883e', null, '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('5', '1', '394758507250843648');
INSERT INTO `user_group` VALUES ('8', '2', '394758507250843648');
INSERT INTO `user_group` VALUES ('6', '3', '394758507250843648');
INSERT INTO `user_group` VALUES ('7', '4', '394758507250843648');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '2');
