-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: SmartPlant
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

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
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
INSERT INTO `Department` VALUES ('191200020000','财务部',5),('191200030000','销售部',2),('191200040000','技术部',2),('191200050000','生产部',5),('191200060000','人事部',4),('191200070000','采购部',2);
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
INSERT INTO `Employee` VALUES ('191200021001','female','张柳',25,'15615600011','191200021000','191200020000'),('191200022001','female','张琪',25,'15651611100','191200022000','191200020000'),('191200023001','male','张霸',29,'15651633310','191200023000','191200020000'),('191200024001','male','张玖',26,'15651111111','191200024000','191200020000'),('191200025001','female','张诗',19,'15665111166','191200025000','191200020000'),('191200031001','male','Stephen',25,'15651633316','191200031000','191200030000'),('191200032001','male','张三',25,'15651633333','191200032000','191200030000'),('191200041001','female','张思',23,'15651611116','191200041000','191200040000'),('191200042001','male','张武',35,'15651612126','191200042000','191200040000'),('191200051001','male','张石屹',29,'15651666616','191200051000','191200050000'),('191200052001','male','张石耳',28,'15651622216','191200052000','191200050000'),('191200053001','male','张宇燚',25,'15651632316','191200053000','191200050000'),('191200054001','male','张宇耳',25,'15651623216','191200054000','191200050000'),('191200055001','male','张朝辉',34,'13962812191','191200055000','191200050000'),('191200061001','male','周义明',23,'15651634315','191200061000','191200060000'),('191200062001','male','张爱民',45,'15151644415','191200062000','191200060000'),('191200063001','female','张艺',18,'15651677715','191200063000','191200060000'),('191200064001','male','张轩宇',45,'15651644151','191200064000','191200060000'),('191200071001','male','张轩燚',23,'14541544415','191200071000','191200070000'),('191200072001','female','张艾清',24,'15656166619','191200072000','191200070000');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `autoadder` AFTER INSERT ON `Employee` FOR EACH ROW begin update Department set employeeQuantity = employeeQuantity + 1  where new.departmentNo is not null and new.departmentNo = Department.departmentNo;  end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `autosubtracter` AFTER DELETE ON `Employee` FOR EACH ROW begin update Department set employeeQuantity = employeeQuantity - 1  where old.departmentNo is not null and old.departmentNo = Department.departmentNo;  end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Equipment`
--

DROP TABLE IF EXISTS `Equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
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
INSERT INTO `Position` VALUES ('191200021000','首席财务官'),('191200022000','总会计师'),('191200023000','财务总监'),('191200024000','审计主管'),('191200025000','会计'),('191200031000','销售部部长'),('191200032000','销售员'),('191200041000','技术部部长'),('191200042000','技术员'),('191200051000','生产部经理'),('191200052000','车间主任'),('191200053000','生产班组长'),('191200054000','内勤'),('191200055000','工人'),('191200061000','人事部总监'),('191200062000','人事部经理'),('191200063000','人事部主管'),('191200064000','人事专员'),('191200071000','采购部经理'),('191200072000','采购员');
/*!40000 ALTER TABLE `Position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PositionInDepartment`
--

DROP TABLE IF EXISTS `PositionInDepartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
INSERT INTO `PositionInDepartment` VALUES ('191200021000','191200020000'),('191200022000','191200020000'),('191200023000','191200020000'),('191200024000','191200020000'),('191200025000','191200020000'),('191200031000','191200030000'),('191200032000','191200030000'),('191200041000','191200040000'),('191200042000','191200040000'),('191200051000','191200050000'),('191200052000','191200050000'),('191200053000','191200050000'),('191200054000','191200050000'),('191200055000','191200050000'),('191200061000','191200060000'),('191200062000','191200060000'),('191200063000','191200060000'),('191200064000','191200060000'),('191200071000','191200070000'),('191200072000','191200070000');
/*!40000 ALTER TABLE `PositionInDepartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Salary`
--

DROP TABLE IF EXISTS `Salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `user` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES ('admin','1234'),('zhang','123');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-18  2:17:38
