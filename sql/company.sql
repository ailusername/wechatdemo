SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`
(
  `company_id`            int       NOT NULL auto_increment,
  `company_name`          varchar(100) NOT NULL,
  PRIMARY KEY (`company_id`),
  index `idx_company_name` (`company_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
