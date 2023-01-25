CREATE DATABASE IF NOT EXISTS `routing`;

USE `routing`;

CREATE TABLE IF NOT EXISTS `routing`.`countries`
(
    `code`      VARCHAR(3)   NOT NULL,
    `name`      VARCHAR(255) NOT NULL,
    `latitude`  DOUBLE       NOT NULL,
    `longitude` DOUBLE       NOT NULL,
    PRIMARY KEY (`code`)
);

CREATE TABLE IF NOT EXISTS `routing`.`borders`
(
    `country_code`        VARCHAR(3) NOT NULL,
    `border_country_code` VARCHAR(3) NOT NULL,
    PRIMARY KEY (`country_code`, `border_country_code`),
    INDEX `fk_c_t_bc_idx` (`country_code` ASC),
    INDEX `fk_bc_t_c_idx` (`border_country_code` ASC),
    CONSTRAINT `fk_bc_t_c` FOREIGN KEY (`country_code`)
        REFERENCES `routing`.`countries` (`code`),
    CONSTRAINT `fk_c_t_bc` FOREIGN KEY (`border_country_code`)
        REFERENCES `routing`.`countries` (`code`)
)
