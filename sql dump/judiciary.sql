-- MySQL dump 10.13  Distrib 8.0.16, for osx10.13 (x86_64)
--
-- Host: localhost    Database: judiciary
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cases`
--

DROP TABLE IF EXISTS `cases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cases` (
  `case_id` int(5) NOT NULL AUTO_INCREMENT,
  `person` varchar(12) DEFAULT NULL,
  `person_lawyer` varchar(12) DEFAULT NULL,
  `defendent` varchar(12) DEFAULT NULL,
  `defendent_lawyer` varchar(12) DEFAULT NULL,
  `case_description` varchar(2000) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `judge` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cases`
--

LOCK TABLES `cases` WRITE;
/*!40000 ALTER TABLE `cases` DISABLE KEYS */;
INSERT INTO `cases` VALUES (1,'123456789012','098765432109','123456789012',NULL,'\njaswanth snatcched my gold chain last night.','ongoing','111111111111'),(2,'123456789012','098765432109','123456789012',NULL,'my dog was missing.','pending','111111111111'),(3,'123456789012','098765432109','123456789012',NULL,'\nmy laptop was missing.','pending','111111111111'),(4,'123456789012','098765432109','123456789012',NULL,'\nmy shoe was missing.','pending','111111111111'),(5,'123456789012','098765432109','222222222222',NULL,'my dog was missing.','pending',NULL),(6,'123456789012','098765432109','123456789012',NULL,'my shoe was missing.','pending','111111111111'),(7,'123456789012','098765432109','222222222222',NULL,'En kenattha kaanom!','pending',NULL),(8,'123456789012','098765432109','222222222222',NULL,'my lollipop melted, I want it back.','pending',NULL),(9,'098765432109','333333333333','123456789012',NULL,'my pen was missing.','pending',NULL);
/*!40000 ALTER TABLE `cases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hearings`
--

DROP TABLE IF EXISTS `hearings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hearings` (
  `case_id` int(4) DEFAULT NULL,
  `hearing_1ts` varchar(20) DEFAULT NULL,
  `hearing_1` varchar(1000) DEFAULT NULL,
  `hearing_2ts` varchar(20) DEFAULT NULL,
  `hearing_2` varchar(1000) DEFAULT NULL,
  `hearing_3ts` varchar(20) DEFAULT NULL,
  `hearing_3` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hearings`
--

LOCK TABLES `hearings` WRITE;
/*!40000 ALTER TABLE `hearings` DISABLE KEYS */;
INSERT INTO `hearings` VALUES (1,NULL,'my dog w\\as ecdec.',NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hearings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login` (
  `adhaar` varchar(12) NOT NULL,
  `password` varchar(400) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`adhaar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('010101010101','Jasppd321!','admin'),('098765432109','Jasppd12345!','lawyer'),('111111111111','Jasppd123!!!','judge'),('123456789012','Jasppd1231','person'),('222222222222','Jasppd123!','person'),('333333333333','Jasppd321!','lawyer');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maillogin`
--

DROP TABLE IF EXISTS `maillogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `maillogin` (
  `mailid` tinyblob,
  `password` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maillogin`
--

LOCK TABLES `maillogin` WRITE;
/*!40000 ALTER TABLE `maillogin` DISABLE KEYS */;
INSERT INTO `maillogin` VALUES (_binary 'jaswanth.sp@zusas.com','Jasppd123!'),(_binary 'pavithira.p@zusas.com','Jasppd321!');
/*!40000 ALTER TABLE `maillogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `sender` varchar(100) DEFAULT NULL,
  `receiver` varchar(100) DEFAULT NULL,
  `message_desc` varchar(1000) DEFAULT NULL,
  `sent_success` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('jaswanth.sp@zumail.com','pavithira.p@zusas.com','hi da how are you','true'),('jaswanth.sp@zumail.com','pavithira.p@zusas.com','please give permission to allow access','true'),('jaswanth.sp@zumail.com','pavithira.p@zusas.com','please use this mail','true'),('pavithira.p@zusas.com','jaswanth.sp@zusas.com','hi da how are you','true'),('samyuktha.s@zusas.com','pavithira.p@zusas.com','I mailed you yesterday.','true'),('samjastha.s@zusas.com','pavithira.p@zusas.com','I mailed you yesterday.','true'),('jaswanth.sp@zusas.com','pavithira.p@zusas.com','I mailed you yesterday.','true'),('dhanabagyam.p@zusas.com','pavithira.p@zusas.com','I mailed you yesterday, but till now you didn\'t reply to me a single word what are you thinking man, please answer the yesterday\'s question.','true');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `profile` (
  `sno1` int(5) DEFAULT NULL,
  `adhaar` varchar(12) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `contact_no` varchar(10) DEFAULT NULL,
  `email_id` varchar(100) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `Role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`adhaar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (2,'098765432109','pavithira.p',17,'7038155709','pavithirashree@gmail.com','50,adharsh nagar,paruvachi,erode district','lawyer'),(3,'111111111111','dhanabagyam.p',27,'9942716143','dhanabagyamponnusamy.p@gmail.com','45,tsr layout 2nd street,kongu main road,tirupur - 641607','judge'),(1,'123456789012','jaswanth.sp',17,'6381360525','jaswanth.sp@zohocorp.com','45,tsr layout,kongu main road,tirupur-641607','person'),(4,'222222222222','jaswant',12,'1234567890','jaswant@zoho.com','esdrfghjk,xcfgvhbj.','person'),(5,'333333333333','ponnusamy.rm',25,'9842229577','ponnusamy.rm@gmail.com','23,anna nagar,chennai - 600010','lawyer');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-23 12:04:42
