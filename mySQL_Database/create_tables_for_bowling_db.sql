DROP TABLE IF EXISTS `bowling_db`.`reservation`;
CREATE TABLE `bowling_db`.`reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `bowling_db`.`employee`;
CREATE TABLE `bowling_db`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(16) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `is_admin` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`user_name`)
  );

DROP TABLE IF EXISTS `bowling_db`.`dining`;
CREATE TABLE `bowling_db`.`dining` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `table` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `bowling_db`.`reservation`(`id`)
);

DROP TABLE IF EXISTS `bowling_db`.`bowling`;
CREATE TABLE `bowling_db`.`bowling` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `number_of_alleys` INT NOT NULL,
  `playtime` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `bowling_db`.`reservation`(`id`)
);

DROP TABLE IF EXISTS `bowling_db`.`airhockey`;
CREATE TABLE `bowling_db`.`airhockey` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `number_of_tables` INT NOT NULL,
  `playtime` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `bowling_db`.`reservation`(`id`)
);

DROP TABLE IF EXISTS `bowling_db`.`employee_schedule`;
CREATE TABLE `bowling_db`.`employee_schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_employee_id` INT NOT NULL,
  FOREIGN KEY (`fk_employee_id`) REFERENCES `bowling_db`.`employee`(`id`),
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
);