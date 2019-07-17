SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`
(
  `department_id`            int NOT NULL auto_increment,
  `department_name`          varchar(40),
  `pid`  int,
  PRIMARY KEY (`department_id`),
  index `idx_department_name` (`department_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
