-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: SmartPlant
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
CREATE DATABASE `SmartPlant`;
 SET NAMES utf8mb4 ;
USE `SmartPlant`;
SET FOREIGN_KEY_CHECKS=0;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Department` (
  `departmentNo` char(12) NOT NULL,
  `name` varchar(20) NOT NULL,
  `employeeQuantity` int(10) NOT NULL,
  PRIMARY KEY (`departmentNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES ('123456789012','name',3);
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Employee` (
  `employeeNo` char(12) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(3) NOT NULL,
  `telephoneNo` char(11) NOT NULL,
  `positionNo` char(12) DEFAULT NULL,
  `departmentNo` char(12) DEFAULT NULL,
  PRIMARY KEY (`employeeNo`),
  KEY `positionNo` (`positionNo`),
  KEY `departmentNo` (`departmentNo`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`positionNo`) REFERENCES `Position` (`positionNo`) ON DELETE SET NULL,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`departmentNo`) REFERENCES `Department` (`departmentNo`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES ('111111111111','female','name222',20,'12345678901','123456789012','123456789012'),('123456789009','male','123',12,'12345678901','123456789012','123456789012'),('12345678901','female','name',19,'12345678901',NULL,NULL),('123456789012','female','name',19,'12345678901','123456789012','123456789012'),('123456789101','male','sss',20,'12345678910',NULL,NULL),('234567890123','male','abc',40,'23456789012',NULL,NULL),('345678901234','male','sad',24,'34567890123',NULL,NULL);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;
--
-- WARNING: old server version. The following dump may be incomplete.
--
DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`root`@`localhost` */ /*!50003 TRIGGER `autoadder` AFTER INSERT ON `Employee` FOR EACH ROW begin update Department set employeeQuantity = employeeQuantity + 1  where new.departmentNo is not null and new.departmentNo = Department.departmentNo;  end */;;
DELIMITER ;
--
-- WARNING: old server version. The following dump may be incomplete.
--
DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`root`@`localhost` */ /*!50003 TRIGGER `autosubtracter` AFTER DELETE ON `Employee` FOR EACH ROW begin update Department set employeeQuantity = employeeQuantity - 1  where old.departmentNo is not null and old.departmentNo = Department.departmentNo;  end */;;
DELIMITER ;

--
-- Table structure for table `Equipment`
--

DROP TABLE IF EXISTS `Equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Equipment` (
  `equipmentNo` char(12) NOT NULL,
  `name` varchar(20) NOT NULL,
  `catagory` varchar(20) NOT NULL,
  `lifeTime` int(2) DEFAULT NULL,
  `purchaseTime` datetime NOT NULL,
  `checkTime` datetime NOT NULL,
  `departmentNo` char(12) DEFAULT NULL,
  PRIMARY KEY (`equipmentNo`),
  KEY `departmentNo` (`departmentNo`),
  CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`departmentNo`) REFERENCES `department` (`departmentNo`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipment`
--

LOCK TABLES `Equipment` WRITE;
/*!40000 ALTER TABLE `Equipment` DISABLE KEYS */;
INSERT INTO `Equipment` VALUES ('123456789012','name','camera',10,'2019-09-01 00:54:30','2019-09-26 00:54:36','123456789012');
/*!40000 ALTER TABLE `Equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Position`
--

DROP TABLE IF EXISTS `Position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Position` (
  `positionNo` char(12) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`positionNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Position`
--

LOCK TABLES `Position` WRITE;
/*!40000 ALTER TABLE `Position` DISABLE KEYS */;
INSERT INTO `Position` VALUES ('123456789012','name');
/*!40000 ALTER TABLE `Position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PositionInDepartment`
--

DROP TABLE IF EXISTS `PositionInDepartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `PositionInDepartment` (
  `positionNo` char(12) NOT NULL,
  `departmentNo` char(12) NOT NULL,
  PRIMARY KEY (`positionNo`,`departmentNo`),
  KEY `positionNo` (`positionNo`),
  KEY `departmentNo` (`departmentNo`),
  CONSTRAINT `positionindepartment_ibfk_1` FOREIGN KEY (`positionNo`) REFERENCES `position` (`positionNo`) ON DELETE CASCADE,
  CONSTRAINT `positionindepartment_ibfk_2` FOREIGN KEY (`departmentNo`) REFERENCES `department` (`departmentNo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PositionInDepartment`
--

LOCK TABLES `PositionInDepartment` WRITE;
/*!40000 ALTER TABLE `PositionInDepartment` DISABLE KEYS */;
INSERT INTO `PositionInDepartment` VALUES ('123456789012','123456789012');
/*!40000 ALTER TABLE `PositionInDepartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Salary`
--

DROP TABLE IF EXISTS `Salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Salary` (
  `salarySerialNo` char(12) NOT NULL,
  `year` datetime NOT NULL,
  `amount` decimal(10,0) NOT NULL,
  `employeeNo` char(12) NOT NULL,
  PRIMARY KEY (`salarySerialNo`),
  KEY `employeeNo` (`employeeNo`),
  CONSTRAINT `salary_ibfk_1` FOREIGN KEY (`employeeNo`) REFERENCES `employee` (`employeeNo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Salary`
--

LOCK TABLES `Salary` WRITE;
/*!40000 ALTER TABLE `Salary` DISABLE KEYS */;
INSERT INTO `Salary` VALUES ('123456789012','2019-10-09 21:17:02',3000,'123456789012');
/*!40000 ALTER TABLE `Salary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
SET FOREIGN_KEY_CHECKS=1;

-- Dump completed on 2019-10-11 15:14:13
