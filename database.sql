CREATE DATABASE IF NOT EXISTS nerdygadgets_robotarm;
USE nerdygadgets_robotarm;

DROP TABLE IF EXISTS `orderlines`;
CREATE TABLE IF NOT EXISTS `orderlines` (
  `orderline_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `aantal` int(11) NOT NULL,

  PRIMARY KEY (`orderline_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `items`;
CREATE TABLE IF NOT EXISTS `items` (
  `item_id` int(11) NOT NULL,
  `item_naam` varchar(25),
  `gewicht_in_kg` float(24), 
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `schap`;
CREATE TABLE IF NOT EXISTS `schap` (
	`plek` int(11) NOT NULL,
	`item_id` int(11) NOT NULL,
    PRIMARY KEY (`plek`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO items (item_id, item_naam, gewicht_in_kg	)
VALUES (1, "Furry pak", 10.0),
		(2, "Game hond", 25.5),
        (3, "TV", 12.9),
        (4, "Vier (4) euro", 5.12),
        (5, "JOUW moeder", 40.0),
        (6, "Ballen", 4.9);

INSERT INTO schap (item_id, plek)
VALUES (4, 2),
		(18, 6),
        (1, 3);

INSERT INTO orders (order_id)
VALUES (1),
		(2),
		(3),
		(4);

INSERT INTO orderlines(orderline_id, order_id, item_id, aantal)
VALUES (1, 1, 4, 1),
		(2, 1, 2, 3),
		(3, 2, 1, 5),
		(4, 2, 6, 2),
		(5, 3, 2, 1),
		(6, 4, 6, 5),
		(7, 4, 2, 3),
		(8, 4, 1, 4),
		(9, 4, 4, 1),
		(10, 4, 5, 2),
		(11, 4, 3, 5);
		

DELETE FROM schap;