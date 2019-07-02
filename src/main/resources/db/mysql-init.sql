/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : my_mybatis_logs

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 02/07/2019 16:37:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log_message
-- ----------------------------
DROP TABLE IF EXISTS `log_message`;
CREATE TABLE `log_message`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `parameter` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数',
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `spend_time` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `result` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_message
-- ----------------------------
INSERT INTO `log_message` VALUES (1145974383882272770, '{\"id\":14}', 'getUserList', '2019-07-02 16:35:59', 26, '[{\"age\":123,\"deleted\":true,\"email\":\"test2@baomidou.com\",\"enable\":false,\"id\":2,\"name\":\"jake\"},{\"age\":26,\"deleted\":true,\"email\":\"test3@baomidou.com\",\"enable\":true,\"id\":3,\"name\":\"Tom\"},{\"age\":26,\"deleted\":false,\"email\":\"test4@baomidou.com\",\"enable\":true,\"id\":4,\"name\":\"Sandy\"},{\"age\":24,\"deleted\":false,\"email\":\"test5@baomidou.com\",\"enable\":false,\"id\":5,\"name\":\"Billie\"},{\"age\":11,\"deleted\":true,\"email\":\"change.com\",\"enable\":false,\"id\":1145974189044269057,\"name\":\"sun\"},{\"age\":12,\"deleted\":false,\"email\":\"abc.com\",\"enable\":true,\"id\":1145974190956871682,\"name\":\"test\"}]');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '删除',
  `enable` bit(1) NULL DEFAULT NULL COMMENT '启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'jake', 123, 'test2@baomidou.com', b'1', b'0');
INSERT INTO `user` VALUES (3, 'Tom', 26, 'test3@baomidou.com', b'1', b'1');
INSERT INTO `user` VALUES (4, 'Sandy', 26, 'test4@baomidou.com', b'0', b'1');
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', b'0', b'0');
INSERT INTO `user` VALUES (1145974189044269057, 'sun', 11, 'change.com', b'1', b'0');
INSERT INTO `user` VALUES (1145974190956871682, 'test', 12, 'abc.com', b'0', b'1');

SET FOREIGN_KEY_CHECKS = 1;
