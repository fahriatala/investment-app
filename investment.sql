-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2022 at 05:41 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `investment`
--

-- --------------------------------------------------------

--
-- Table structure for table `nab_history`
--

CREATE TABLE `nab_history` (
  `id` int(11) NOT NULL,
  `nab_amount` decimal(16,4) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `topup_list`
--

CREATE TABLE `topup_list` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total_unit` decimal(16,4) DEFAULT NULL,
  `total_amount` decimal(16,2) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_balance`
--

CREATE TABLE `user_balance` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total_unit` decimal(16,4) DEFAULT NULL,
  `total_amount` decimal(16,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='this table is configurations for partners';

-- --------------------------------------------------------

--
-- Table structure for table `user_data`
--

CREATE TABLE `user_data` (
  `id` int(6) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `withdraw_list`
--

CREATE TABLE `withdraw_list` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total_unit` decimal(16,4) DEFAULT NULL,
  `total_amount` decimal(16,2) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nab_history`
--
ALTER TABLE `nab_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `topup_list`
--
ALTER TABLE `topup_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `topup_fk_1` (`user_id`);

--
-- Indexes for table `user_balance`
--
ALTER TABLE `user_balance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_balance_fk_1` (`user_id`);

--
-- Indexes for table `user_data`
--
ALTER TABLE `user_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `withdraw_list`
--
ALTER TABLE `withdraw_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `withdraw_list_fk_1` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nab_history`
--
ALTER TABLE `nab_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `topup_list`
--
ALTER TABLE `topup_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_balance`
--
ALTER TABLE `user_balance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_data`
--
ALTER TABLE `user_data`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `withdraw_list`
--
ALTER TABLE `withdraw_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `topup_list`
--
ALTER TABLE `topup_list`
  ADD CONSTRAINT `topup_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `user_balance`
--
ALTER TABLE `user_balance`
  ADD CONSTRAINT `user_balance_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `withdraw_list`
--
ALTER TABLE `withdraw_list`
  ADD CONSTRAINT `withdraw_list_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
