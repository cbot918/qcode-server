CREATE DATABASE IF NOT EXISTS `qcode`;

USE `qcode`;

CREATE TABLE `book` (
  `id` serial PRIMARY KEY,
  `title` varchar(255) NOT NULL,
  `isbn` varchar(255) NOT NULL,
  `language` varchar(255) NOT NULL,
  `image` varchar(255),
  `date_published` date
);

CREATE TABLE `authors` (
  `id` serial PRIMARY KEY,
  `book_id` bigint unsigned NOT NULL,
  `author_id` bigint unsigned NOT NULL
);

CREATE TABLE `author` (
  `id` serial PRIMARY KEY,
  `name` varchar(255) NOT NULL
);

ALTER TABLE `authors` ADD FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);

ALTER TABLE `authors` ADD FOREIGN KEY (`author_id`) REFERENCES `author` (`id`);