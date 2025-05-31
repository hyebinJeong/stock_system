# create database stock_db;
# use stock_db;
#
# CREATE USER 'user'@'localhost' IDENTIFIED BY '1234';
# GRANT ALL PRIVILEGES ON stock_db.* TO 'user'@'localhost';
# FLUSH PRIVILEGES;
#
#
# create table stocks
# (
#     id          INT AUTO_INCREMENT PRIMARY KEY,
#     stock_name  VARCHAR(50),
#     ticker      VARCHAR(10),
#     price       INT,
#     holding_qty int
# );
#
# insert into stocks (stock_name, ticker, price, holding_qty)
# values ('Nvidia', '1001', 1200000, 5),
#        ('CocaCola', '1002', 75000, 10),
#        ('KangwonLand', '1003', 18300, 8);
#
# select *
# from stocks;