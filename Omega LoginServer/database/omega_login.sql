-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 07 Août 2013 à 14:21
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `omega_login`
--

-- --------------------------------------------------------

--
-- Structure de la table `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User` varchar(30) NOT NULL,
  `Password` varchar(40) NOT NULL,
  `Email` varchar(40) DEFAULT NULL,
  `Rights` tinyint(4) NOT NULL DEFAULT '0',
  `Banned` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `accounts`
--

INSERT INTO `accounts` (`ID`, `User`, `Password`, `Email`, `Rights`, `Banned`) VALUES
(1, 'test', '206c80413b9a96c1312cc346b7d2517b84463edd', NULL, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `gameservers`
--

CREATE TABLE IF NOT EXISTS `gameservers` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) NOT NULL,
  `Host` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `gameservers`
--

INSERT INTO `gameservers` (`ID`, `Name`, `Host`) VALUES
(1, 'Test server 1', '25.185.18.225'),
(2, 'Test server 2', '127.0.0.1');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
