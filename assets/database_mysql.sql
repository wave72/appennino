CREATE DATABASE  IF NOT EXISTS `ibb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ibb`;
-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- ------------------------------------------------------
-- Server version	5.1.49-3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(64) NOT NULL,
	`val` varchar(64) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ipn_response`
--

DROP TABLE IF EXISTS `ipn_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ipn_response` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`item_number` varchar(64) NOT NULL,
	`payment_status` varchar(64) DEFAULT NULL,
	`payer_email` varchar(64) DEFAULT NULL,
	`mc_gross` varchar(64) DEFAULT NULL,
	`mc_currency` varchar(64) DEFAULT NULL,
	`payment_date` varchar(128) DEFAULT NULL,
	`pending_reason` varchar(64) DEFAULT NULL,
	`payment_type` varchar(64) DEFAULT NULL,
	`participant_found` bit(1) DEFAULT NULL DEFAULT b'0',
	PRIMARY KEY (`id`),
	KEY `unique_item_number` (`item_number`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participant` (
	`item_number` varchar(64) NOT NULL,
	`email` varchar(64) DEFAULT NULL,
	`first_name` varchar(64) DEFAULT NULL,
	`last_name` varchar(64) DEFAULT NULL,
	`created` varchar(64) DEFAULT NULL,
	`arrival_time` varchar(64) DEFAULT NULL,
	`country_name` varchar(64) DEFAULT NULL,
	`food_restrictions` varchar(64) DEFAULT NULL,
	`volunteering` varchar(64) DEFAULT NULL,
	`amount` varchar(64) DEFAULT NULL,
	`payment_dt` varchar(64) DEFAULT NULL,
	`accommodation_type` int(11) NOT NULL,
	PRIMARY KEY (`item_number`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
