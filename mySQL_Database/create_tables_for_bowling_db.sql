DROP TABLE IF EXISTS `bowling_db`.`reservations`;
CREATE TABLE `bowling_db`.`reservations` (
  `reservation_id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`reservation_id`)
);

DROP TABLE IF EXISTS `bowling_db`.`user`;
CREATE TABLE `bowling_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(16) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `is_admin` TINYINT(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE (`user_name`)
  );

DROP TABLE IF EXISTS `bowling_db`.`dining`;
CREATE TABLE `bowling_db`.`dining` (
  `dining_id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `table` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`dining_id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `bowling_db`.`reservations`(`reservation_id`)
);

DROP TABLE IF EXISTS `bowling_db`.`bowling`;
CREATE TABLE `bowling_db`.`bowling` (
  `bowling_id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `number_of_alleys` INT NOT NULL,
  `playtime` INT NOT NULL,
  PRIMARY KEY (`bowling_id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `bowling_db`.`reservations`(`reservation_id`)
);

DROP TABLE IF EXISTS `bowling_db`.`airhockey`;
CREATE TABLE `bowling_db`.`airhockey` (
  `airhockey_id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `number_of_tables` INT NOT NULL,
  `playtime` INT NOT NULL,
  PRIMARY KEY (`airhockey_id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `bowling_db`.`reservations`(`reservation_id`)
);
