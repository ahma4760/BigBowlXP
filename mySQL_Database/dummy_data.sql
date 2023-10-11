-- Dummy data for reservations table
INSERT INTO `bowling_db`.`reservation` (`customer_name`)
VALUES
  ('John Doe'),
  ('Jane Smith'),
  ('Mike Johnson');

-- Dummy data for user table
INSERT INTO `bowling_db`.`user` (`user_name`, `password`, `is_admin`)
VALUES
  ('admin', 'admin123', 1),
  ('user1', 'password1', 0),
  ('user2', 'password2', 0);

-- Dummy data for dining table
INSERT INTO `bowling_db`.`dining` (`reservation_id`, `date_time`, `table_reservation`)
VALUES
  (1, '2023-10-07 18:00:00', 'Table 1'),
  (2, '2023-10-08 19:30:00', 'Table 2'),
  (3, '2023-10-09 20:15:00', 'Table 3');

-- Dummy data for bowling table
INSERT INTO `bowling_db`.`bowling` (`reservation_id`, `date_time`, `number_of_alleys`, `playtime`)
VALUES
  (1, '2023-10-07 14:00:00', 2, 60),
  (2, '2023-10-08 15:30:00', 3, 90),
  (3, '2023-10-09 16:45:00', 1, 45);

-- Dummy data for airhockey table
INSERT INTO `bowling_db`.`airhockey` (`reservation_id`, `date_time`, `number_of_tables`, `playtime`)
VALUES
  (1, '2023-10-07 16:00:00', 1, 30),
  (2, '2023-10-08 17:45:00', 2, 60),
  (3, '2023-10-09 18:30:00', 1, 45);
