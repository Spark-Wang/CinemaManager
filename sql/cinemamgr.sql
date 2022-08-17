SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `C_id` int(11) NOT NULL AUTO_INCREMENT,
  `C_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `C_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `C_Type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`C_id`, `C_name`) USING BTREE,
  INDEX `C_id`(`C_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `customer` VALUES (1, 'mike', 'mike', 'Manager');
INSERT INTO `customer` VALUES (2, 'amy', '11111', 'User');
INSERT INTO `customer` VALUES (3, 'aaa', 'aaa', 'User');
INSERT INTO `customer` VALUES (4, 'bbb', 'bbb', 'Manager');
INSERT INTO `customer` VALUES (5, 'test', 'test', 'User');
INSERT INTO `customer` VALUES (6, 'cccc', 'cccc', 'User');

DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall`  (
  `H_id` int(11) NOT NULL,
  `H_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `H_capacity` int(11) NULL DEFAULT NULL,
  `H_line` int(11) NULL DEFAULT NULL,
  `H_row` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`H_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `hall` VALUES (1, '激光厅', 100, 10, 10);
INSERT INTO `hall` VALUES (2, 'IMAX', 160, 20, 8);
INSERT INTO `hall` VALUES (3, '激光厅', 300, 15, 20);
INSERT INTO `hall` VALUES (4, '3D激光', 200, 20, 10);
INSERT INTO `hall` VALUES (5, '情侣厅', 100, 10, 10);
INSERT INTO `hall` VALUES (6, '巨幕', 400, 20, 20);

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `M_id` int(11) NOT NULL,
  `M_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `M_price` double(11, 0) NULL DEFAULT NULL,
  `M_durTime` int(11) NULL DEFAULT NULL,
  `M_startTime` timestamp(0) NULL DEFAULT NULL,
  `M_endTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`M_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `movie` VALUES (1, '四海', 30, 120, '2022-08-12 08:00:00', '2022-08-31 20:00:00');
INSERT INTO `movie` VALUES (2, '让子弹飞', 25, 145, '2022-08-13 00:00:00', '2022-09-12 00:00:00');
INSERT INTO `movie` VALUES (3, '天气之子', 30, 130, '2022-08-08 00:00:00', '2022-09-17 00:00:00');
INSERT INTO `movie` VALUES (4, '爱乐之城', 35, 150, '2022-08-06 00:00:00', '2022-09-11 00:00:00');
INSERT INTO `movie` VALUES (5, '千与千寻', 20, 125, '2022-08-06 00:00:00', '2022-09-12 00:00:00');

DROP TABLE IF EXISTS `scence`;
CREATE TABLE `scence`  (
  `M_id` int(11) NOT NULL,
  `H_id` int(11) NOT NULL,
  `S_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`M_id`, `H_id`, `S_time`) USING BTREE,
  INDEX `H_id`(`H_id`) USING BTREE,
  INDEX `S_time`(`S_time`) USING BTREE,
  INDEX `M_id`(`M_id`) USING BTREE,
  CONSTRAINT `H_id` FOREIGN KEY (`H_id`) REFERENCES `hall` (`H_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `M_id` FOREIGN KEY (`M_id`) REFERENCES `movie` (`M_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `scence` VALUES (1, 1, '2022-08-23 15:00:00');
INSERT INTO `scence` VALUES (2, 2, '2022-08-26 10:30:00');
INSERT INTO `scence` VALUES (4, 3, '2022-08-24 16:00:00');
INSERT INTO `scence` VALUES (3, 5, '2022-08-17 18:00:00');

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `C_id` int(11) NOT NULL AUTO_INCREMENT,
  `M_id` int(11) NOT NULL,
  `H_id` int(11) NOT NULL,
  `H_line` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `H_row` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `T_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`C_id`, `M_id`, `H_id`, `H_line`, `H_row`, `T_time`) USING BTREE,
  INDEX `T_time`(`T_time`) USING BTREE,
  INDEX `M_id`(`M_id`) USING BTREE,
  INDEX `H_id`(`H_id`) USING BTREE,
  CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`M_id`) REFERENCES `scence` (`M_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_ibfk_3` FOREIGN KEY (`H_id`) REFERENCES `scence` (`H_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_ibfk_4` FOREIGN KEY (`T_time`) REFERENCES `scence` (`S_time`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`C_id`) REFERENCES `customer` (`C_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `ticket` VALUES (2, 1, 1, '5', '5', '2022-08-23 15:00:00');
INSERT INTO `ticket` VALUES (2, 1, 1, '5', '6', '2022-08-23 15:00:00');
INSERT INTO `ticket` VALUES (2, 1, 1, '5', '7', '2022-08-23 15:00:00');
INSERT INTO `ticket` VALUES (2, 1, 1, '5', '8', '2022-08-23 15:00:00');
INSERT INTO `ticket` VALUES (2, 1, 1, '5', '9', '2022-08-23 15:00:00');
INSERT INTO `ticket` VALUES (5, 2, 2, '3', '3', '2022-08-26 10:30:00');
INSERT INTO `ticket` VALUES (5, 2, 2, '3', '4', '2022-08-26 10:30:00');
INSERT INTO `ticket` VALUES (5, 2, 2, '3', '5', '2022-08-26 10:30:00');
INSERT INTO `ticket` VALUES (5, 2, 2, '3', '6', '2022-08-26 10:30:00');
INSERT INTO `ticket` VALUES (5, 2, 2, '3', '7', '2022-08-26 10:30:00');

SET FOREIGN_KEY_CHECKS = 1;
