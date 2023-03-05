DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `description` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO product (name, description) VALUES ('Soap','The finest soap you can face');
INSERT INTO product (name, description) VALUES ('After sun','When there is sun, there is aftersun');

DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
    `reviewId` int(11) NOT NULL AUTO_INCREMENT,
    `productId` int(11) NOT NULL,
    `author` varchar(45) NOT NULL,
    `subject` varchar(255) NOT NULL,
    `content` varchar(255) NOT NULL,
    PRIMARY KEY (`reviewId`)
);
INSERT INTO review (productId, author,subject,content) VALUES (5,'James','Luces','Las luces funcionan bien');
INSERT INTO review (productId, author,subject,content) VALUES (2,'Maria','Pelota','Las pelota bota bien');


DROP TABLE IF EXISTS `recommendation`;
CREATE TABLE `recommendation` (
    `recommendationId` int(11) NOT NULL AUTO_INCREMENT,
    `productId` int(11) NOT NULL,
    `author` varchar(45) NOT NULL,
    `rate` int (255) NOT NULL,
    `content` varchar(255) NOT NULL,
    PRIMARY KEY (`recommendationId`)
);

INSERT INTO recommendation (productId, author,rate,content) VALUES (2,'James',10,'good product');
INSERT INTO recommendation (productId, author,rate,content) VALUES (1,'Ana',5,'Not bad');


