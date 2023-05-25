---------  DATABASE SCRIPT  ---------
---------  NIET VERANDEREN  ---------

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
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `schap`;
CREATE TABLE IF NOT EXISTS `schap` (
	`plek` int(11) NOT NULL,
	`item_id` int(11) NOT NULL,
    PRIMARY KEY (`plek`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO items (item_id, item_naam)
VALUES (1, "Furry pak"),
		(2, "Game hond"),
        (3, "TV"),
        (4, "Vier (4) euro"),
        (5, "JOUW moeder"),
        (6, "Ballen");

INSERT INTO schap (item_id, plek)
VALUES (4, 2),
		(18, 6),
        (1, 3);

INSERT INTO orders (order_id)
VALUES (1),
		(2),
		(3);

INSERT INTO orderlines(orderline_id, order_id, item_id, aantal)
VALUES (1, 1, 4, 1),
		(2, 1, 2, 3),
		(3, 2, 1, 5),
		(4, 2, 6, 2),
		(5, 3, 2, 1);

DELETE FROM schap;


