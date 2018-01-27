CREATE TABLE IF NOT EXISTS `user` (
 `id` varchar(50) NOT NULL AUTO_INCREMENT,
 `name` varchar(500) DEFAULT NULL,
 `surname` varchar(500) DEFAULT NULL,
 PRIMARY KEY (`Id`)
 );
 
 CREATE TABLE IF NOT EXISTS `content` (
 `id` varchar(50) NOT NULL AUTO_INCREMENT,
 PRIMARY KEY (`Id`)
 );
 
 CREATE TABLE IF NOT EXISTS `favorite` (
`user` varchar(50) NOT NULL references `user`,
`content` varchar(50) NOT NULL references `content`,
PRIMARY KEY (`user`, `content`)
);