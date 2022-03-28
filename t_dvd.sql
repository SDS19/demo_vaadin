/*
 Navicat MySQL Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 28/03/2022 14:11:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dvd
-- ----------------------------
DROP TABLE IF EXISTS `t_dvd`;
CREATE TABLE `t_dvd`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `imdbScore` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `year` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `runtime` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dvd
-- ----------------------------
INSERT INTO `t_dvd` VALUES ('33d8deeac7d549cb9bae7de589342645', 'Die Verurteilten', '9.3', '1994', '142');
INSERT INTO `t_dvd` VALUES ('37eafd68469d4c988b6e29ed3a238ddc', 'The Dark Knight', '9.1', '2008', '152');
INSERT INTO `t_dvd` VALUES ('ab7637f741a34a7e848442c565f40fc1', 'Matrix', '8.7', '1999', '136');
INSERT INTO `t_dvd` VALUES ('c9d09adc1b7c41b9a673126e2c18ed6d', 'Frrest Dump', '8.8', '1994', '142');
INSERT INTO `t_dvd` VALUES ('ffefb65b89fa46ca8fa7413e9c9f4666', 'Schindlers Liste', '9.0', '1993', '195');

SET FOREIGN_KEY_CHECKS = 1;
