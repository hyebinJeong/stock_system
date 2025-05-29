use stock_db;

CREATE TABLE stock_db(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         stock_name VARCHAR(50) NOT NULL,
                         ticker VARCHAR(10) NOT NULL,
                         price INT NOT NULL,
                         holding_qty INT NOT NULL
);