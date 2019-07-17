SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
  `student_id`            varchar(50)   NOT NULL,
  `student_name`          varchar(8) NOT NULL,
  `department_id`         INT,
  `phone`                  varchar(11),
  `gender` tinyint,
  `openid` varchar(255),
  PRIMARY KEY (`student_id`),
  index `idx_student_name` (`student_name`)
 ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
