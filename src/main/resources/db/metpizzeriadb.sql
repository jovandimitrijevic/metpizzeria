-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2020 at 11:56 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `metpizzeriadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `ITEM_ID` int(11) NOT NULL,
  `product_type` int(11) DEFAULT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `price` float NOT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  `image_path` varchar(250) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`ITEM_ID`, `product_type`, `name`, `price`, `description`, `image_path`) VALUES
(1, 2, 'Mythos', 1.4, '0.3l, alcoholic drink', '/img/mythos.jpg'),
(2, 2, 'Tuborg', 2, '0.5l, alcoholic drink', '/img/tuborg.jpg'),
(3, 2, 'Carlsberg', 2.7, '0.5l, alcoholic drink', '/img/carlsberg.jpg'),
(4, 2, 'Heineken', 2.2, '0.3l, alcoholic drink', '/img/heineken.jpg'),
(5, 2, 'Coca Cola', 1.7, '0.5l, non-alcoholic drink', '/img/cola.jpg'),
(6, 2, 'Sprite', 1.5, '0.5l, non-alcoholic drink', '/img/sprite.png'),
(7, 2, 'Fanta', 1.7, '0.5l, non-alcoholic drink', '/img/fanta.png'),
(8, 1, 'Capricossa', 12, '50cm. Tomato sauce, mozzarella cheese, ham, fresh mushrooms', '/img/capricossa.jpg'),
(9, 1, 'Margharita', 10, '50cm. Diced tomato & mozzarella, finished with oregano & garlic', '/img/margharita.jpg'),
(10, 1, 'Double Pepperoni', 13, '50cm. Double cheese and double pepperoni, on a tomato base', '/img/double-pepperoni.jpg'),
(11, 1, 'Vegeterian', 12, '50cm. Mushrooms, Onions, Green Peppers & Sweetcorn', '/img/vegeterian.jpg'),
(12, 1, 'Mexicana', 14, '50cm. Spicy Beef, Onions, Sliced Tomato, Green Chillies', '/img/mexicana.jpg'),
(13, 1, 'BBQ Beast', 14, '50cm. BBQ sauce, Chicken, Pepperoni, Meatballs, Green peppers, red peppers, Jalapeno Peppers', '/img/bbq-beast.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `ORDER_ID` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `items` varchar(250) COLLATE utf8_bin NOT NULL,
  `sum` float NOT NULL,
  `status_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`ORDER_ID`, `user_id`, `items`, `sum`, `status_id`) VALUES
(25, 8, 'Item 1: Double Pepperoni ', 13, 2),
(26, 8, 'Item 1: Capricossa Item 2: Tuborg ', 14, 1),
(27, 4, 'Item 1: Margharita Item 2: Mythos ', 11.4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `order_status`
--

CREATE TABLE `order_status` (
  `STATUS_ID` int(11) NOT NULL,
  `status` varchar(11) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `order_status`
--

INSERT INTO `order_status` (`STATUS_ID`, `status`) VALUES
(1, 'Pending'),
(2, 'Accepted'),
(3, 'Cancelled');

-- --------------------------------------------------------

--
-- Table structure for table `product_type`
--

CREATE TABLE `product_type` (
  `type_id` int(11) NOT NULL,
  `type` varchar(10) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `product_type`
--

INSERT INTO `product_type` (`type_id`, `type`) VALUES
(1, 'Pizza'),
(2, 'Drink');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `USER_ID` int(11) NOT NULL,
  `first_name` varchar(30) COLLATE utf8_bin NOT NULL,
  `last_name` varchar(30) COLLATE utf8_bin NOT NULL,
  `username` varchar(30) COLLATE utf8_bin NOT NULL,
  `password` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(30) COLLATE utf8_bin NOT NULL,
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `enabled` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`USER_ID`, `first_name`, `last_name`, `username`, `password`, `email`, `address`, `enabled`) VALUES
(1, 'Marko', 'Markovic', 'markomarkovic', '$2y$12$/skzvQiOM9MbY29avvekfeeB4VkjFieoUAT5ZGGXF13ITGf9vTHS6', 'markomarkovic@gmail.com', 'Vizantijski bulevar 23, Nis', 1),
(2, 'Petar', 'Peric', 'peraperic', '$2y$12$8N/UxR7VqAvpxBpaS1eU8uwn/5iMDn6/MNr7xgWh8UIOY.NIzD/Wa', 'peraperic@gmail.com', 'Bulevar Medijana 22, Nis', 1),
(4, 'Jovan', 'Dimitrijevic', 'jovandimitrijevic', '$2a$10$5yDPlZYZgv5eY2EbNwER8u0KHJcZqUw8ik0BPtID.2GrqF1umGhD.', 'jovandimitrijevic@gmail.com', 'Bulevar Nemanjica BB, Nis', 1),
(8, 'Lazar', 'Lazic', 'lazarlazic', '$2a$10$WWHg6U9BfFvEdoMKR/vCwOzO7foHt4PvsfNuENy7GCcjOXt3xOsIq', 'lazarlazic@gmail.com', 'Bulevar Nemanjica 24, Nis', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_item`
--

CREATE TABLE `user_item` (
  `item_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_item`
--

INSERT INTO `user_item` (`item_id`, `user_id`) VALUES
(1, 1),
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(4, 2),
(8, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`ITEM_ID`),
  ADD UNIQUE KEY `ITEM_PK` (`ITEM_ID`),
  ADD KEY `PRODUCT_TYPE` (`product_type`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`ORDER_ID`),
  ADD UNIQUE KEY `ORDER_PK` (`ORDER_ID`),
  ADD KEY `RELATIONSHIP_4_FK` (`user_id`),
  ADD KEY `order_ibfk_1` (`status_id`);

--
-- Indexes for table `order_status`
--
ALTER TABLE `order_status`
  ADD PRIMARY KEY (`STATUS_ID`);

--
-- Indexes for table `product_type`
--
ALTER TABLE `product_type`
  ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`USER_ID`),
  ADD UNIQUE KEY `USER_PK` (`USER_ID`);

--
-- Indexes for table `user_item`
--
ALTER TABLE `user_item`
  ADD KEY `RELATIONSHIP_5_FK` (`item_id`),
  ADD KEY `RELATIONSHIP_7_FK` (`user_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FK_ROLE` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `ITEM_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `order_status`
--
ALTER TABLE `order_status`
  MODIFY `STATUS_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`product_type`) REFERENCES `product_type` (`type_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_ORDERS_RELATIONS_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`STATUS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_item`
--
ALTER TABLE `user_item`
  ADD CONSTRAINT `FK_USER_ITE_RELATIONS_ITEM` FOREIGN KEY (`item_id`) REFERENCES `item` (`ITEM_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_USER_ITE_RELATIONS_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
