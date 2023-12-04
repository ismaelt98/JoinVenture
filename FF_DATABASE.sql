CREATE DATABASE  IF NOT EXISTS `joinventure1` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `joinventure1`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: joinventure1
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `demand`
--

DROP TABLE IF EXISTS `demand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demand` (
  `DEMAND_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`DEMAND_ID`),
  UNIQUE KEY `DEMAND_ID_UNIQUE` (`DEMAND_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demand`
--

LOCK TABLES `demand` WRITE;
/*!40000 ALTER TABLE `demand` DISABLE KEYS */;
INSERT INTO `demand` VALUES (1,'APP_DEVELOPMENT'),(8,'CYBER_SECURITY'),(4,'DATABASE'),(5,'IA'),(6,'IOT'),(7,'MACHINE_LEARNING'),(3,'SOFTWARE_DEVELOPMENT'),(2,'WEB_DEVELOPMENT');
/*!40000 ALTER TABLE `demand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frameworks`
--

DROP TABLE IF EXISTS `frameworks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frameworks` (
  `FRAMEWORKS_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`FRAMEWORKS_ID`),
  UNIQUE KEY `FRAMEWORKS_ID_UNIQUE` (`FRAMEWORKS_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frameworks`
--

LOCK TABLES `frameworks` WRITE;
/*!40000 ALTER TABLE `frameworks` DISABLE KEYS */;
INSERT INTO `frameworks` VALUES (41,'ALAMOFIRE'),(14,'ANGULAR'),(10,'APACHE'),(25,'ASP.NET'),(30,'BLAZOR'),(22,'CAKE'),(6,'CHERRYPY'),(21,'CODEIGNITER'),(40,'COMBINE'),(39,'COREDATA'),(34,'CUBA'),(42,'DJANGO'),(26,'ENTITY'),(17,'EXPRESS'),(4,'FASTAPI'),(2,'FLASK'),(38,'FOUNDATION'),(35,'GRAPE'),(33,'HANAMI'),(8,'HIBERNATE'),(11,'JSF'),(23,'LAMINAS'),(19,'LARAVEL'),(18,'NODE'),(1,'NONE'),(31,'ONRAILS'),(12,'PLAY'),(3,'PYRAMID'),(13,'REACT'),(32,'SINATRA'),(7,'SPRING'),(9,'STRUTS'),(16,'SVELTE'),(37,'SWIFTUI'),(20,'SYMPHONY'),(5,'TORNADO'),(36,'UIKIT'),(15,'VUE'),(29,'WCF'),(28,'WPF'),(27,'XAMARIN'),(24,'YII');
/*!40000 ALTER TABLE `frameworks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `integration`
--

DROP TABLE IF EXISTS `integration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `integration` (
  `PROYECT_PROYECT_ID` int unsigned NOT NULL,
  `user_user_id` int unsigned NOT NULL,
  `INTEGRATED_AT` timestamp NOT NULL,
  `integration_id` int unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`integration_id`),
  UNIQUE KEY `integration_id_UNIQUE` (`integration_id`),
  KEY `fk_INTEGRATION_PROYECT1` (`PROYECT_PROYECT_ID`),
  KEY `fk_INTEGRATION_user1` (`user_user_id`),
  CONSTRAINT `fk_INTEGRATION_PROYECT1` FOREIGN KEY (`PROYECT_PROYECT_ID`) REFERENCES `proyect` (`PROYECT_ID`),
  CONSTRAINT `fk_INTEGRATION_user1` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `integration`
--

LOCK TABLES `integration` WRITE;
/*!40000 ALTER TABLE `integration` DISABLE KEYS */;
INSERT INTO `integration` VALUES (1,60,'2023-11-19 23:00:00',1),(1,61,'2023-11-19 23:00:00',2),(1,62,'2023-11-19 23:00:00',3),(1,63,'2023-11-19 23:00:00',4);
/*!40000 ALTER TABLE `integration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lenguage`
--

DROP TABLE IF EXISTS `lenguage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lenguage` (
  `LENGUAGE_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`LENGUAGE_ID`),
  UNIQUE KEY `LENGUAGE_ID_UNIQUE` (`LENGUAGE_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lenguage`
--

LOCK TABLES `lenguage` WRITE;
/*!40000 ALTER TABLE `lenguage` DISABLE KEYS */;
INSERT INTO `lenguage` VALUES (5,'C'),(4,'C#'),(2,'JAVA'),(3,'JAVASCRIPT'),(9,'PHP'),(1,'PYTHON'),(6,'RUBY'),(8,'SQL'),(7,'SWIFT'),(10,'TYPESCRIPT');
/*!40000 ALTER TABLE `lenguage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programmer_role`
--

DROP TABLE IF EXISTS `programmer_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programmer_role` (
  `ROLE_PRO_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ROLE_PRO_ID`),
  UNIQUE KEY `ROLE_PRO_ID_UNIQUE` (`ROLE_PRO_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programmer_role`
--

LOCK TABLES `programmer_role` WRITE;
/*!40000 ALTER TABLE `programmer_role` DISABLE KEYS */;
INSERT INTO `programmer_role` VALUES (16,'BACK_DEVELOPER'),(14,'DATA_ENGINEER'),(15,'FRONT_DEVELOPER'),(17,'FULL-STACK_DEVELOPER'),(10,'PRODUCT_OWNER'),(11,'TEAM_LEADER'),(13,'TECHNICAL_BACK_LEADER'),(12,'TECHNICAL_FRON_LEADER');
/*!40000 ALTER TABLE `programmer_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyect`
--

DROP TABLE IF EXISTS `proyect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyect` (
  `PROYECT_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `NUM_MEMBERS` int unsigned NOT NULL,
  `CREATED_AT` timestamp NOT NULL,
  PRIMARY KEY (`PROYECT_ID`),
  UNIQUE KEY `PROYECT_ID_UNIQUE` (`PROYECT_ID`),
  UNIQUE KEY `NAMES_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyect`
--

LOCK TABLES `proyect` WRITE;
/*!40000 ALTER TABLE `proyect` DISABLE KEYS */;
INSERT INTO `proyect` VALUES (1,'JOIN_VENTURE',4,'2023-11-18 23:00:00'),(2,'BOOKS',3,'2023-11-12 23:00:00'),(3,'FUND',3,'2023-11-20 23:00:00'),(4,'ANTSTOCK',3,'2023-11-19 23:00:00'),(5,'ODS',4,'2023-11-22 23:00:00');
/*!40000 ALTER TABLE `proyect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_set`
--

DROP TABLE IF EXISTS `role_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_set` (
  `user_user_id` int unsigned NOT NULL,
  `PROGRAMMER_ROLE_ROLE_PRO_ID` int unsigned NOT NULL,
  `ROLE_USER_ROLE_ID` int unsigned NOT NULL,
  `role_set_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`role_set_id`),
  KEY `fk_ROLE_SET_PROGRAMMER_ROLE1_idx` (`PROGRAMMER_ROLE_ROLE_PRO_ID`),
  KEY `fk_ROLE_SET_ROLE_USER1_idx` (`ROLE_USER_ROLE_ID`),
  KEY `fk_ROLE_SET_user1` (`user_user_id`),
  CONSTRAINT `fk_ROLE_SET_PROGRAMMER_ROLE1` FOREIGN KEY (`PROGRAMMER_ROLE_ROLE_PRO_ID`) REFERENCES `programmer_role` (`ROLE_PRO_ID`),
  CONSTRAINT `fk_ROLE_SET_ROLE_USER1` FOREIGN KEY (`ROLE_USER_ROLE_ID`) REFERENCES `role_user` (`ROLE_ID`),
  CONSTRAINT `fk_ROLE_SET_user1` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_set`
--

LOCK TABLES `role_set` WRITE;
/*!40000 ALTER TABLE `role_set` DISABLE KEYS */;
INSERT INTO `role_set` VALUES (60,10,4,26),(61,11,4,27),(62,12,4,28),(63,13,4,29);
/*!40000 ALTER TABLE `role_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_user`
--

DROP TABLE IF EXISTS `role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_user` (
  `ROLE_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `ROLE_ID_UNIQUE` (`ROLE_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_user`
--

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
INSERT INTO `role_user` VALUES (4,'ADMIN'),(5,'ENTERPRISE'),(6,'PROGRAMMER');
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sector` (
  `SECTOR_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`SECTOR_ID`),
  UNIQUE KEY `SECTOR_ID_UNIQUE` (`SECTOR_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (1,'CULTURE'),(2,'ENERGIE'),(3,'ENTERTEINMENT'),(8,'FINANCES'),(5,'HEALTH'),(4,'INDUSTRY'),(9,'INSURANCE'),(6,'TECHNOLOGY'),(7,'TURISM');
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set`
--

DROP TABLE IF EXISTS `set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `set` (
  `PROYECT_PROYECT_ID` int unsigned NOT NULL,
  `SECTOR_SECTOR_ID` int unsigned NOT NULL,
  `DEMAND_DEMAND_ID` int unsigned NOT NULL,
  `set_id` int unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`set_id`),
  UNIQUE KEY `set_id_UNIQUE` (`set_id`),
  KEY `fk_SET_DEMAND1_idx` (`DEMAND_DEMAND_ID`),
  KEY `fk_SET_PROYECT1` (`PROYECT_PROYECT_ID`),
  KEY `fk_SET_SECTOR1` (`SECTOR_SECTOR_ID`),
  CONSTRAINT `fk_SET_DEMAND1` FOREIGN KEY (`DEMAND_DEMAND_ID`) REFERENCES `demand` (`DEMAND_ID`),
  CONSTRAINT `fk_SET_PROYECT1` FOREIGN KEY (`PROYECT_PROYECT_ID`) REFERENCES `proyect` (`PROYECT_ID`),
  CONSTRAINT `fk_SET_SECTOR1` FOREIGN KEY (`SECTOR_SECTOR_ID`) REFERENCES `sector` (`SECTOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set`
--

LOCK TABLES `set` WRITE;
/*!40000 ALTER TABLE `set` DISABLE KEYS */;
INSERT INTO `set` VALUES (1,6,1,16),(1,6,2,17),(1,6,5,18),(2,1,1,19),(2,1,2,20),(2,1,5,21),(3,3,1,22),(3,3,2,23),(3,3,5,24),(4,4,1,25),(4,4,2,26),(4,4,5,27),(5,4,1,28),(5,4,2,29),(5,4,5,30);
/*!40000 ALTER TABLE `set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technologies`
--

DROP TABLE IF EXISTS `technologies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technologies` (
  `user_user_id` int unsigned NOT NULL,
  `LENGUAGE_LENGUAGE_ID` int unsigned NOT NULL,
  `FRAMEWORKS_FRAMEWORKS_ID` int unsigned NOT NULL,
  `technology_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`technology_id`),
  KEY `fk_TECHNOLOGIES_FRAMEWORKS1_idx` (`FRAMEWORKS_FRAMEWORKS_ID`),
  KEY `fk_TECHNOLOGIES_LENGUAGE1_idx` (`LENGUAGE_LENGUAGE_ID`),
  KEY `fk_TECHNOLOGIES_user1` (`user_user_id`),
  CONSTRAINT `fk_TECHNOLOGIES_FRAMEWORKS1` FOREIGN KEY (`FRAMEWORKS_FRAMEWORKS_ID`) REFERENCES `frameworks` (`FRAMEWORKS_ID`),
  CONSTRAINT `fk_TECHNOLOGIES_LENGUAGE1` FOREIGN KEY (`LENGUAGE_LENGUAGE_ID`) REFERENCES `lenguage` (`LENGUAGE_ID`),
  CONSTRAINT `fk_TECHNOLOGIES_user1` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technologies`
--

LOCK TABLES `technologies` WRITE;
/*!40000 ALTER TABLE `technologies` DISABLE KEYS */;
INSERT INTO `technologies` VALUES (60,5,4,20),(61,4,4,21),(62,5,3,22),(63,5,5,23);
/*!40000 ALTER TABLE `technologies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `alias` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(300) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `USER__UNIQUE` (`user_id`),
  UNIQUE KEY `EMAIL_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (60,'ERIC','MUÑOZ','Erikmb@gmail.com','Cabeza(01)','2023-11-19 23:00:00','2023-11-20','987456321'),(61,'DAVID','X','David01@gmail.com','Cabeza(02)','2023-11-20 23:00:00','2023-11-21','789456123'),(62,'ISMAEL','Y','Ismael02@gmail.com','Cabeza(03)','2023-11-19 23:00:00','2023-11-21','741258963'),(63,'MARIONNE','Z','Marionee03@gmail.com','Cabeza(04)','2023-11-21 23:00:00','2023-11-22','369852147');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-04 16:42:47
