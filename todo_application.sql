-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:4306
-- Generation Time: Oct 31, 2022 at 02:05 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `todo_application`
--

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `description`, `title`, `user_id`) VALUES
(1, 'Watch Angular /Typescript on Youtube', 'Learn Angular/TypeScript', 5),
(2, 'Watch SpringBoot on Youtube', 'Learn SpringBoot', 5),
(3, 'Watch Java on Youtube', 'Learn Java', 5),
(4, 'Watch Typescript on Youtube', 'Learn Typescript', 5),
(5, 'Watch JS on UDEMY', 'Learn JS', 5),
(9, 'Configure Delete Endpoints\n', 'Connect the DeleteMapping of SpringBoot to Angular', 6),
(10, 'Configure Edit Function in SpringBoot and Angular', 'Configure Edit Task Done', 6),
(14, 'Endpoints Connected', 'Configure Update Done', 5),
(22, 'You are done with Angular!', 'Learn ReactJS', 7);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `date_time_registered` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `date_time_registered`, `password`, `username`) VALUES
(5, '2022-10-30 15:30:22', '$2a$10$ZON2mfdsDdbhI4CkyuNzUuB8OluGZoeY24PSewpoRXrtwBOB0LMPW', 'almar28'),
(6, '2022-10-31 03:53:41', '$2a$10$OuMLrk6HeMnOKynZ4E87WOoLdVSTMyWIbHl6SJgJIJJc0UIMCiijW', 'agmar28'),
(7, '2022-10-31 11:41:48', '$2a$10$3xWA4UFc/rIohw1lVTCrPepYW73b/zpmzOjLIvpJa31amXyEXS9LW', 'marmar28');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6s1ob9k4ihi75xbxe2w0ylsdh` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `FK6s1ob9k4ihi75xbxe2w0ylsdh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
