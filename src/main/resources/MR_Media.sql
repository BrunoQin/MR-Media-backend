-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mr_media
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Current Database: `mr_media`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `mr_media` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mr_media`;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `avatar` varchar(128) NOT NULL,
  `active` int(11) NOT NULL,
  `wechat_number` varchar(64) NOT NULL,
  `id_number` varchar(128) NOT NULL,
  `talent_type` int(11) NOT NULL,
  `settle_type` int(11) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `location` varchar(128) NOT NULL,
  `email` varchar(45) NOT NULL,
  `settle_account` varchar(45) NOT NULL,
  `real_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid_fk_idx` (`uid`),
  CONSTRAINT `uid_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,13,'img/join.jpg',0,'wechatBruno','2654625451235',3,0,'12331212523','shanghai','324234@df.com','4154721312312','秦博'),(2,14,'img/join.jpg',1,'wechatHoracio','2654625451235',1,1,'54321212523','shanghai','rersthrt34@df.com','4154721312312','黄日天');
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_USER_ADMIN_idx` (`uid`),
  CONSTRAINT `fk_USER_ADMIN` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,1,'12312312');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `avatar` varchar(45) NOT NULL,
  `wechat_number` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `id_number` varchar(45) NOT NULL,
  `real_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `agent_uid_fk_idx` (`uid`),
  CONSTRAINT `agent_uid_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (1,1,'1231','123123','123123','12312312','21312312','12312'),(2,10,'img/join.jpg','wechat1223','12332112312','123@123.com','1323124214124','qwer'),(3,11,'img/join.jpg','wechat414123','12332112312','1223@123.com','132sda3124214124','efsdfsdf'),(4,12,'img/join.jpg','wechat543534','12332112315','12sfa23@123.com','132ssdfda3124214124','fdgsdfg');
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `authority` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `authority_admin_fk_idx` (`admin_id`),
  CONSTRAINT `authority_admin_fk` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `text_content` varchar(256) NOT NULL,
  `link` varchar(256) DEFAULT NULL,
  `pictures_url` varchar(256) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `when_created` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sender_idx` (`sender`),
  KEY `reciever_idx` (`receiver`),
  CONSTRAINT `reciever` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sender` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `location` varchar(256) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_id_idx` (`owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platform`
--

DROP TABLE IF EXISTS `platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actor_id` int(11) NOT NULL,
  `valid_day` int(11) NOT NULL,
  `valid_hour` int(11) NOT NULL,
  `gift_count` int(11) DEFAULT NULL,
  `settle_count` int(11) DEFAULT NULL,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `plat_form_fk_idx` (`actor_id`),
  CONSTRAINT `plat_form_fk` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platform`
--

LOCK TABLES `platform` WRITE;
/*!40000 ALTER TABLE `platform` DISABLE KEYS */;
INSERT INTO `platform` VALUES (7,1,12,43,2377,3764,'Douyu');
/*!40000 ALTER TABLE `platform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator_id` int(11) NOT NULL,
  `recommend_id` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `text_content` varchar(256) NOT NULL,
  `action` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `creator_id_idx` (`creator_id`),
  KEY `recommend_id_idx` (`recommend_id`),
  CONSTRAINT `creator_id` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `recommend_id` FOREIGN KEY (`recommend_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settle`
--

DROP TABLE IF EXISTS `settle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actor_id` int(11) NOT NULL,
  `platform_id` varchar(45) NOT NULL,
  `platform_name` varchar(45) NOT NULL,
  `platform` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `settle_actor_fk_idx` (`actor_id`),
  CONSTRAINT `settle_actor_fk` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settle`
--

LOCK TABLES `settle` WRITE;
/*!40000 ALTER TABLE `settle` DISABLE KEYS */;
INSERT INTO `settle` VALUES (1,1,'rrr','rrr','douyu','2017-04-01 00:00:00',100),(2,1,'123123','秦小波','Douyu','2017-01-01 00:00:00',1235),(7,2,'123321','horaio','YY','2017-03-01 00:00:00',2135452);
/*!40000 ALTER TABLE `settle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(24) NOT NULL,
  `password` varchar(128) NOT NULL DEFAULT 'password',
  `level` int(11) NOT NULL,
  `super_id` int(11) NOT NULL,
  `disable` int(11) NOT NULL DEFAULT '0',
  `token` varchar(128) DEFAULT NULL,
  `valid_time` datetime DEFAULT NULL,
  `open_id` varchar(128) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `real_name` varchar(45) NOT NULL,
  `id_number` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `super_id_uid_fk_idx` (`super_id`),
  CONSTRAINT `super_id_uid_fk` FOREIGN KEY (`super_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ddd','ddd',1,1,0,'a6c90bdd01d18502d72cd529447c4cb7','2017-04-30 12:09:29',NULL,1,'ddd','213123'),(10,'111','password',1,1,0,NULL,NULL,NULL,2,'qwer','1323124214124'),(11,'123','password',1,1,0,NULL,NULL,NULL,2,'efsdfsdf','132sda3124214124'),(12,'37656','password',1,1,0,NULL,NULL,NULL,2,'fdgsdfg','132ssdfda3124214124'),(13,'rrr','password',2,10,0,NULL,NULL,NULL,3,'秦博','2654625451235'),(14,'horacio','password',1,1,0,NULL,NULL,NULL,3,'黄日天','2654625451235');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mr_media'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-31 13:58:26
