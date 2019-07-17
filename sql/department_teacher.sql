SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department_teacher
-- ----------------------------
DROP TABLE IF EXISTS `department_teacher`;
CREATE TABLE `department_teacher`
(
  `department_id`                     int(11) NOT NULL,
  `teacher_id`                        varchar(11) NOT NULL,
  PRIMARY KEY (`department_id`,`teacher_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
