DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
    `reviewId` int(11) NOT NULL AUTO_INCREMENT,
    `productId` int(11) NOT NULL,
    `author` varchar(45) NOT NULL,
    `subject` varchar(255) NOT NULL,
    `content` varchar(255) NOT NULL,
    PRIMARY KEY (`reviewId`)
);
