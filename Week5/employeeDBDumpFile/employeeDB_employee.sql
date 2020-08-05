-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: employeeDB
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `Employee_ID` varchar(30) NOT NULL,
  `Employee_Name` varchar(45) NOT NULL,
  `Hire_Date` datetime NOT NULL,
  `Department_ID` varchar(30) NOT NULL,
  `IS_Active` varchar(45) NOT NULL,
  `Employee_Last_Date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Employee_ID`),
  KEY `Department_ID` (`Department_ID`),
  CONSTRAINT `Department_ID_fk` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`Department_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('E1','Glen','1994-03-06 00:00:00','D1','Y',NULL),('E10','Amina','2003-06-14 00:00:00','D3','Y',NULL),('E11','Sarita','2006-08-05 00:00:00','D1','Y',NULL),('E12','Mary','1998-05-08 00:00:00','D3','N','2010-05-28'),('E13','Nick','2004-06-14 00:00:00','D3','Y',NULL),('E2','Rekha','1995-04-15 00:00:00','D3','Y',NULL),('E3','Sara','1994-04-06 00:00:00','D3','N','1996-06-06'),('E4','Natasha','2001-06-14 00:00:00','D4','Y',NULL),('E5','Svetlana','2004-08-05 00:00:00','D3','Y',NULL),('E6','Mark','1996-05-08 00:00:00','D1','N','1997-04-09'),('E7','Sabina','2002-06-14 00:00:00','D3','Y',NULL),('E8','Darrel','2005-08-05 00:00:00','D3','Y',NULL),('E9','Kwong','1997-05-08 00:00:00','D4','N','1998-05-09');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05  7:33:33
