-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bms
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `crsNum` varchar(45) NOT NULL,
  `crsName` varchar(45) NOT NULL,
  `crsCredit` varchar(45) NOT NULL,
  `crsMajor` varchar(45) NOT NULL,
  `proNum` varchar(45) NOT NULL,
  `proName` varchar(45) NOT NULL,
  PRIMARY KEY (`crsNum`),
  UNIQUE KEY `crsName_UNIQUE` (`crsName`),
  KEY `fk_Course_Professor1_idx` (`proNum`),
  KEY `fk_Course_Major1_idx` (`crsMajor`),
  CONSTRAINT `fk_Course_Major1` FOREIGN KEY (`crsMajor`) REFERENCES `major` (`mjrName`),
  CONSTRAINT `fk_Course_Professor1` FOREIGN KEY (`proNum`) REFERENCES `professor` (`proNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('A0001','JAVA','3','컴퓨터공학','20001','강미나'),('A0002','SPRING','3','컴퓨터공학','20002','김나비'),('B0001','스피킹','2','영어영문학','20003','박공식'),('B0002','영국사회','2','영어영문학','20005','테스트1'),('B0004','미국사회','2','영어영문학','20006','테스트2'),('C0001','한국사','3','국어국문학','20004','나역사');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `empNum` varchar(45) NOT NULL,
  `empName` varchar(45) NOT NULL,
  `empDpt` varchar(45) NOT NULL,
  `empMgr` varchar(45) NOT NULL,
  `empPw` varchar(45) NOT NULL,
  `empPhone` varchar(45) NOT NULL,
  PRIMARY KEY (`empNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('30001','홍길동','교무처','0','aaa002','0106666'),('30002','아무개','학사처','1','bbb003','0107777');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `mjrNum` varchar(45) NOT NULL,
  `mjrName` varchar(45) NOT NULL,
  PRIMARY KEY (`mjrName`),
  UNIQUE KEY `mjrNum_UNIQUE` (`mjrNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES ('0001','컴퓨터공학'),('0002','영어영문학'),('0003','국어국문학'),('0004','순수물리학');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `proNum` varchar(45) NOT NULL,
  `proName` varchar(45) NOT NULL,
  `proGrade` varchar(45) NOT NULL,
  `proMajor` varchar(45) NOT NULL,
  `proPw` varchar(45) NOT NULL,
  `proPhone` varchar(45) NOT NULL,
  PRIMARY KEY (`proNum`),
  KEY `fk_Professor_Major1_idx` (`proMajor`),
  CONSTRAINT `fk_Professor_Major1` FOREIGN KEY (`proMajor`) REFERENCES `major` (`mjrName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES ('20001','강미나','정교수','컴퓨터공학','2001','0101010'),('20002','김나비','부교수','컴퓨터공학','2002','0102020'),('20003','박공식','정교수','영어영문학','2003','0103030'),('20004','나역사','계약직','국어국문학','2004','0104040'),('20005','테스트1','정교수','영어영문학','2005','0105050'),('20006','테스트2','부교수','영어영문학','2006','0106060');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `stdNum` varchar(45) NOT NULL,
  `stdName` varchar(45) NOT NULL,
  `stdGrade` varchar(45) NOT NULL,
  `stdMajor` varchar(45) NOT NULL,
  `stdPw` varchar(45) NOT NULL,
  `stdPhone` varchar(45) NOT NULL,
  PRIMARY KEY (`stdNum`),
  KEY `fk_Student_Major1` (`stdMajor`),
  CONSTRAINT `fk_Student_Major1` FOREIGN KEY (`stdMajor`) REFERENCES `major` (`mjrName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('10001','윤수현','3','영어영문학','1001','0101111'),('10002','강감찬','2','컴퓨터공학','1002','0102222'),('10003','김좌진','4','컴퓨터공학','1003','0103333'),('10004','갈길가','1','국어국문학','1004','0104444'),('10005','가지마','4','컴퓨터공학','1005','0105555'),('10009','요기요','1','순수물리학','1009','0109876');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `takecourse`
--

DROP TABLE IF EXISTS `takecourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `takecourse` (
  `tk_stdNum` varchar(45) NOT NULL,
  `tkMark` varchar(45) DEFAULT NULL,
  `tk_crsNum` varchar(45) NOT NULL,
  `tk_crsName` varchar(45) NOT NULL,
  PRIMARY KEY (`tk_stdNum`,`tk_crsNum`),
  KEY `fk_TakeCourse_Student_idx` (`tk_stdNum`),
  KEY `fk_TakeCourse_crsNum_idx` (`tk_crsNum`),
  KEY `fk_TakeCourse_crsName_idx` (`tk_crsName`),
  CONSTRAINT `fk_TakeCourse_Student` FOREIGN KEY (`tk_stdNum`) REFERENCES `student` (`stdNum`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TakeCourse_crsName` FOREIGN KEY (`tk_crsName`) REFERENCES `course` (`crsName`),
  CONSTRAINT `fk_TakeCourse_crsNum` FOREIGN KEY (`tk_crsNum`) REFERENCES `course` (`crsNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `takecourse`
--

LOCK TABLES `takecourse` WRITE;
/*!40000 ALTER TABLE `takecourse` DISABLE KEYS */;
INSERT INTO `takecourse` VALUES ('10001',NULL,'B0002','영국사회'),('10001',NULL,'B0004','미국사회'),('10001',NULL,'C0001','한국사'),('10002','A','A0001','JAVA'),('10002','B','A0002','Spring'),('10003','A+','A0002','Spring'),('10005','C+','A0001','JAVA');
/*!40000 ALTER TABLE `takecourse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-11 18:32:10
