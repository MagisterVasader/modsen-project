INSERT INTO `routing`.`countries` (`code`, `name`, `latitude`, `longitude`)
VALUES ("US", "United States", "38", "-97");
INSERT INTO `routing`.`countries` (`code`, `name`, `latitude`, `longitude`)
VALUES ("CAN", "Canada", "60", "-95");

INSERT INTO `routing`.`borders` (country_code, border_country_code)
VALUES ("US", "CAN");
INSERT INTO `routing`.`borders` (country_code, border_country_code)
VALUES ("CAN", "US");