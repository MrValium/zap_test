CREATE DATABASE IF NOT EXISTS `scaffail_db`;

USE `scaffail_db`;

DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `comment`;

CREATE TABLE IF NOT EXISTS `book` (
   `book_id` INTEGER NOT NULL AUTO_INCREMENT,
   `title` VARCHAR(100) NOT NULL,
   `author` VARCHAR(100) NOT NULL,
   `description` VARCHAR(500) NOT NULL,
   PRIMARY KEY (`book_id`)
);

CREATE TABLE IF NOT EXISTS `comment` (
   `comment_id` INTEGER NOT NULL AUTO_INCREMENT,
   `book_fk` INTEGER NOT NULL,
   `nickname` VARCHAR(100) NOT NULL,
   `comment_text` VARCHAR(500) NOT NULL,
   PRIMARY KEY (`comment_id`),
   FOREIGN KEY (`book_fk`) REFERENCES `book`(`book_id`)
);

INSERT INTO `book` (`title`, `author`, `description`) VALUES
('The Lord of the Rings', 'J. R. R. Tolkien', 'The Lord of the Rings is an epic high-fantasy novel written by English author and scholar J. R. R. Tolkien. The story began as a sequel to Tolkien''s 1937 fantasy novel The Hobbit, but eventually developed into a much larger work. Written in stages between 1937 and 1949, The Lord of the Rings is one of the best-selling novels ever written, with over 150 million copies sold.'),
('The Hobbit', 'J. R. R. Tolkien', 'The Hobbit, or There and Back Again is a children''s fantasy novel by English author J. R. R. Tolkien. It was published on 21 September 1937 to wide critical acclaim, being nominated for the Carnegie Medal and awarded a prize from the New York Herald Tribune for best juvenile fiction. The book remains popular and is recognized as a classic in children''s literature.'),
('One Hundred Years of Solitude', 'Gabriel García Márquez', 'One Hundred Years of Solitude is a landmark 1967 novel by Colombian author Gabriel García Márquez that tells the multi-generational story of the Buendía family, whose patriarch, José Arcadio Buendía, founded the town of Macondo, a fictitious town in the country of Colombia.'),
('The Nonexistent Knight', 'Italo Calvino', 'The Nonexistent Knight is an allegorical fantasy novel by Italian writer Italo Calvino, first published in Italian in 1959 and in English translation in 1962. The tale explores questions of identity, integration with society, and virtue through the adventures of Agilulf, a medieval knight who exemplifies chivalry, piety, and faithfulness but exists only as an empty suit of armour.'),
('The Decameron', 'Giovanni Boccaccio', 'The Decameron is a collection of novellas by the 14th-century Italian author Giovanni Boccaccio. The book is structured as a frame story containing 100 tales told by a group of seven young women and three young men sheltering in a secluded villa just outside Florence to escape the Black Death, which was afflicting the city.');