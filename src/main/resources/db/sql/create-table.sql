CREATE TABLE IF NOT EXISTS shop.product (
  `product_name` varchar(255) DEFAULT NULL,
  `stock` number DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL
) DEFAULT CHARSET=utf8;

