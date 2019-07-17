SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
  `teacher_id`            varchar(11) NOT NULL,
  `teacher_name`          varchar(8) NOT NULL,
  `department_id`         INT,
  `phone`                  varchar(11),
  `gender` tinyint,
  `openid` varchar(255),
  PRIMARY KEY (`teacher_id`),
  index `idx_department_id` (`department_id`),
  index `idx_teacher_name` (`teacher_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
