-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 02, 2023 at 11:23 AM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `evisaprocessing`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminlogin`
--

CREATE TABLE `adminlogin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adminlogin`
--

INSERT INTO `adminlogin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `executive`
--

CREATE TABLE `executive` (
  `id` int(50) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `address` varchar(55) NOT NULL,
  `username` varchar(55) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `executive`
--

INSERT INTO `executive` (`id`, `name`, `address`, `username`, `contact`, `gender`, `date`, `password`) VALUES
(1, 'joyall', 'trichy', 'joy', '994861482', 'Male', '28/2/2023', '123');

-- --------------------------------------------------------

--
-- Table structure for table `renewalvisa`
--

CREATE TABLE `renewalvisa` (
  `id` int(55) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `vnumber` varchar(55) NOT NULL,
  `birth` varchar(50) NOT NULL,
  `idnumber` varchar(50) NOT NULL,
  `image` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `renewalvisa`
--

INSERT INTO `renewalvisa` (`id`, `name`, `contact`, `country`, `vnumber`, `birth`, `idnumber`, `image`) VALUES
(2, 'joyal', '8525811519', 'India', '96185544', '1/3/2023', '5654252', 'gimg20230301082840');

-- --------------------------------------------------------

--
-- Table structure for table `userregister`
--

CREATE TABLE `userregister` (
  `id` int(55) NOT NULL auto_increment,
  `name` varchar(55) NOT NULL,
  `address` varchar(55) NOT NULL,
  `username` varchar(50) NOT NULL,
  `contact` varchar(55) NOT NULL,
  `gender` varchar(55) NOT NULL,
  `date` varchar(50) NOT NULL,
  `password` varchar(55) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `userregister`
--

INSERT INTO `userregister` (`id`, `name`, `address`, `username`, `contact`, `gender`, `date`, `password`) VALUES
(1, 'joyal', 'trichy', 'jo', '894564138', 'Male', '28/2/2023', '123');

-- --------------------------------------------------------

--
-- Table structure for table `visaapply`
--

CREATE TABLE `visaapply` (
  `id` int(50) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `pnumber` varchar(50) NOT NULL,
  `birth` varchar(50) NOT NULL,
  `idnumber` varchar(50) NOT NULL,
  `image` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `visaapply`
--

INSERT INTO `visaapply` (`id`, `name`, `contact`, `country`, `pnumber`, `birth`, `idnumber`, `image`) VALUES
(1, 'joyal', '8525811519', 'India', '5918621865845', '1/3/2023', '89189645566855', 'gimg20230301051105');
