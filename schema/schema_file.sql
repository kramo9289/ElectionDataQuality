-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: kevramos.cveujd3ttzr7.us-east-2.rds.amazonaws.com    Database: kevramos
-- ------------------------------------------------------
-- Server version	8.0.32

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

--
-- Table structure for table `complete_md_precinct`
--

DROP TABLE IF EXISTS `complete_md_precinct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complete_md_precinct` (
  `totvot16` int DEFAULT NULL,
  `totvot18` int DEFAULT NULL,
  `totvotpres` int DEFAULT NULL,
  `repvot16` int DEFAULT NULL,
  `repvot18` int DEFAULT NULL,
  `repvotpres` int DEFAULT NULL,
  `demvot16` int DEFAULT NULL,
  `demvot18` int DEFAULT NULL,
  `demvotpres` int DEFAULT NULL,
  `othvot16` int DEFAULT NULL,
  `othvot18` int DEFAULT NULL,
  `othvotpres` int DEFAULT NULL,
  `pop100` int DEFAULT NULL,
  `nhisov18` int DEFAULT NULL,
  `hisov18` int DEFAULT NULL,
  `whiteov18` int DEFAULT NULL,
  `blackov18` int DEFAULT NULL,
  `aminov18` int DEFAULT NULL,
  `asianov18` int DEFAULT NULL,
  `hawov18` int DEFAULT NULL,
  `otherov18` int DEFAULT NULL,
  `statefp` int DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `origname` varchar(32) DEFAULT NULL,
  `countyname` varchar(16) DEFAULT NULL,
  `districtid` int DEFAULT NULL,
  `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `complete_nc_precinct`
--

DROP TABLE IF EXISTS `complete_nc_precinct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complete_nc_precinct` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aminov18` int DEFAULT NULL,
  `asianov18` int DEFAULT NULL,
  `blackov18` int DEFAULT NULL,
  `hawov18` int DEFAULT NULL,
  `hisov18` int DEFAULT NULL,
  `nhisov18` int DEFAULT NULL,
  `otherov18` int DEFAULT NULL,
  `pop100` int DEFAULT NULL,
  `whiteov18` int DEFAULT NULL,
  `totvot16` int DEFAULT NULL,
  `totvot18` int DEFAULT NULL,
  `totvotpres` int DEFAULT NULL,
  `repvot16` varchar(11) DEFAULT NULL,
  `repvot18` int DEFAULT NULL,
  `repvotpres` varchar(4) DEFAULT NULL,
  `demvot16` varchar(6) DEFAULT NULL,
  `demvot18` int DEFAULT NULL,
  `demvotpres` int DEFAULT NULL,
  `othvot16` int DEFAULT NULL,
  `othvot18` int DEFAULT NULL,
  `othvotpres` int DEFAULT NULL,
  `name` varchar(18) DEFAULT NULL,
  `statefp` int DEFAULT NULL,
  `countyname` varchar(12) DEFAULT NULL,
  `origname` varchar(49) DEFAULT NULL,
  `districtid` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2707 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `complete_ri_precinct`
--

DROP TABLE IF EXISTS `complete_ri_precinct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complete_ri_precinct` (
  `aminov18` int DEFAULT NULL,
  `asianov18` int DEFAULT NULL,
  `blackov18` int DEFAULT NULL,
  `hawov18` int DEFAULT NULL,
  `hisov18` int DEFAULT NULL,
  `nhisov18` int DEFAULT NULL,
  `otherov18` int DEFAULT NULL,
  `pop100` int DEFAULT NULL,
  `whiteov18` int DEFAULT NULL,
  `totvot16` int DEFAULT NULL,
  `totvot18` int DEFAULT NULL,
  `totvotpres` int DEFAULT NULL,
  `repvot16` int DEFAULT NULL,
  `repvot18` int DEFAULT NULL,
  `repvotpres` int DEFAULT NULL,
  `demvot16` int DEFAULT NULL,
  `demvot18` int DEFAULT NULL,
  `demvotpres` int DEFAULT NULL,
  `othvot16` int DEFAULT NULL,
  `othvot18` int DEFAULT NULL,
  `othvotpres` int DEFAULT NULL,
  `origname` varchar(4) DEFAULT NULL,
  `name` varchar(21) DEFAULT NULL,
  `countyname` varchar(16) DEFAULT NULL,
  `statefp` varchar(2) DEFAULT NULL,
  `districtid` varchar(1) DEFAULT NULL,
  `FID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `demographics`
--

DROP TABLE IF EXISTS `demographics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demographics` (
  `id` int NOT NULL,
  `aminov18` int DEFAULT NULL,
  `asianov18` int DEFAULT NULL,
  `blackov18` int DEFAULT NULL,
  `hawov18` int DEFAULT NULL,
  `hisov18` int DEFAULT NULL,
  `otherov18` int DEFAULT NULL,
  `pop100` int DEFAULT NULL,
  `whiteov18` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `districts`
--

DROP TABLE IF EXISTS `districts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `districts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `congid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `shape_geojson` longtext COLLATE utf8mb4_general_ci,
  `statefp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4404 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `elections`
--

DROP TABLE IF EXISTS `elections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elections` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dem` int DEFAULT NULL,
  `other` int DEFAULT NULL,
  `rep` int DEFAULT NULL,
  `tot` int DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `year` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precinct_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhq582bwomii91u4eiu0dhecq7` (`precinct_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32249 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `errors_fixed`
--

DROP TABLE IF EXISTS `errors_fixed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `errors_fixed` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  `error_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precinctid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precinctname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `relevant_info` longtext COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `errors_unfixed`
--

DROP TABLE IF EXISTS `errors_unfixed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `errors_unfixed` (
  `id` int NOT NULL,
  `error_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `neighbors`
--

DROP TABLE IF EXISTS `neighbors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `neighbors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_precinct_id` int DEFAULT NULL,
  `second_precinct_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKietbrs6wmfl1q9cp83mxuwwxj` (`first_precinct_id`),
  KEY `FK73mbya0unbjbqxiw2v03xs9en` (`second_precinct_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29263 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `precincts`
--

DROP TABLE IF EXISTS `precincts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precincts` (
  `id` int NOT NULL,
  `countyname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `districtid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `origname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `shape_geojson` longtext COLLATE utf8mb4_general_ci,
  `statefp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sources`
--

DROP TABLE IF EXISTS `sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sources` (
  `id` int NOT NULL AUTO_INCREMENT,
  `demographic` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `district` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precinct` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `state` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `statefp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `voting` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `ogr_fid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `state_geojson` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `statefp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ogr_fid`),
  CONSTRAINT `states_chk_1` CHECK (json_valid(`state_geojson`))
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-27 16:59:47
