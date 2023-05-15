---------  DATABASE SCRIPT  ---------
---------  NIET VERANDEREN  ---------

CREATE DATABASE IF NOT EXISTS nerdygadgets_robotarm;
USE nerdygadgets_robotarm;

DROP TABLE IF EXISTS `orderlines`;
CREATE TABLE IF NOT EXISTS `orderlines` (
  `orderline_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
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
  `kleur` varchar(25),
  `plek` int(10) UNIQUE,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;