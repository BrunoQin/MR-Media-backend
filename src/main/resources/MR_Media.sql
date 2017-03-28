-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: mr_media
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `actor_video`
--

DROP TABLE IF EXISTS `actor_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) DEFAULT NULL,
  `location` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `video_owner_idx` (`owner_id`),
  CONSTRAINT `ower_id_user_id_fk` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_video`
--

LOCK TABLES `actor_video` WRITE;
/*!40000 ALTER TABLE `actor_video` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
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
-- Table structure for table `authority_group`
--

DROP TABLE IF EXISTS `authority_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `authority_id_idx` (`authority_id`),
  CONSTRAINT `authority_id` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority_group`
--

LOCK TABLES `authority_group` WRITE;
/*!40000 ALTER TABLE `authority_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `authority_group` ENABLE KEYS */;
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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(24) NOT NULL,
  `real_name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL DEFAULT 'password',
  `avatar` varchar(128) DEFAULT NULL,
  `authority` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `super_id` int(11) DEFAULT NULL,
  `disable` int(11) NOT NULL DEFAULT '0',
  `token` varchar(128) DEFAULT NULL,
  `valid_time` datetime DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `location` varchar(64) DEFAULT NULL,
  `settle_account` varchar(64) NOT NULL,
  `active` int(11) NOT NULL DEFAULT '0',
  `open_id` varchar(128) DEFAULT NULL,
  `wechat_number` varchar(64) DEFAULT NULL,
  `phone_number` varchar(64) DEFAULT NULL,
  `talent_type` int(11) DEFAULT NULL,
  `settle_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `super_id_uid_fk_idx` (`super_id`),
  CONSTRAINT `super_id_uid_fk` FOREIGN KEY (`super_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ddd','ddd','ddd','ddd3ae0623d-1576-48b2-b50f-9aa362083764Screen Shot 2017-03-13 at 8.50.53 AM.png',0,0,NULL,0,'0762399554d8baec2736541722964941','2017-04-12 14:55:31',NULL,NULL,'000',0,NULL,NULL,NULL,0,0),(2,'qb','qb','qb',NULL,1,1,1,0,NULL,NULL,NULL,NULL,'000',0,NULL,NULL,NULL,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_uploaded_picture`
--

DROP TABLE IF EXISTS `user_uploaded_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_uploaded_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) DEFAULT NULL,
  `location` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_id_idx` (`owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_uploaded_picture`
--

LOCK TABLES `user_uploaded_picture` WRITE;
/*!40000 ALTER TABLE `user_uploaded_picture` DISABLE KEYS */;
INSERT INTO `user_uploaded_picture` VALUES (1,1,'ddd8bc27afc-08be-4dd6-8049-784b5bfa9562Screen Shot 2017-03-13 at 8.44.38 AM.png'),(2,1,'ddd14caaadd-2f93-4553-8039-1127878c596bScreen Shot 2017-03-13 at 8.50.53 AM.png'),(3,1,'dddaaff3905-365b-456d-b6cb-9cb9c9973dc6Screen Shot 2017-03-13 at 9.07.10 AM.png'),(4,1,'ddd00b3b3c7-5714-4d21-aa73-634f07f7c365Screen Shot 2017-03-13 at 9.07.10 AM.png'),(5,1,'dddb2ed1004-47e2-4f20-8334-916d66c2369aScreen Shot 2017-03-13 at 9.07.10 AM.png');
/*!40000 ALTER TABLE `user_uploaded_picture` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-13 14:59:49
