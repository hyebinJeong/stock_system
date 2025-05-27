CREATE DATABASE stock_db;
USE stock_db;

CREATE USER 'user'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON stock_db.* TO 'user'@'localhost';
FLUSH PRIVILEGES;


CREATE TABLE stock_db(
    id INT AUTO_INCREMENT PRIMARY KEY,
    stock_name VARCHAR(50) NOT NULL,
    ticker VARCHAR(10) NOT NULL,
    price INT NOT NULL,
    holding_qty INT NOT NULL
);