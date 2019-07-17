SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`
(
  `record_id`            int      NOT NULL auto_increment,
  `record_name`          varchar(255) NOT NULL,
  `company_id` int,
  `record_url` varchar(255),
  `student_id`            varchar(50)   NOT NULL,
  `download_count` int,
  `view_count` int,
  `click_count` int,
  `time` TIMESTAMP ,
  `status` tinyint,
  PRIMARY KEY (`record_id`),
  index `idx_time` (`time`),
  index `idx_record_url` (`record_url`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
