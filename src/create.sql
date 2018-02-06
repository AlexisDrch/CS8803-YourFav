CREATE TABLE IF NOT EXISTS `user` (
 `id` int NOT NULL AUTO_INCREMENT,
 `name` varchar(500) DEFAULT NULL,
 `surname` varchar(500) DEFAULT NULL,
 PRIMARY KEY (`Id`)
 );
 
 CREATE TABLE IF NOT EXISTS `favorite` (
 `id` varchar(50) NOT NULL,
 `url` varchar(1000) NOT NULL,
 `user` int NOT NULL references `user`,
 PRIMARY KEY (`Id`)
 );
 