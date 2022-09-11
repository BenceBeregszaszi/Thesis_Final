-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: schedule
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `city`
--

DROP DATABASE IF EXISTS schedule;

CREATE DATABASE schedule;

USE schedule;

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` datetime(6) DEFAULT NULL,
  `post_code` varchar(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city_link`
--

DROP TABLE IF EXISTS `city_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city_link` (
  `restaurant_id` bigint NOT NULL,
  `city_id` bigint NOT NULL,
  PRIMARY KEY (`restaurant_id`,`city_id`),
  KEY `FKm81ukuawj2c4i0igxvpqt47am` (`city_id`),
  CONSTRAINT `FKhd7tt15d2th5rfh1lf41etogf` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FKm81ukuawj2c4i0igxvpqt47am` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city_link`
--

LOCK TABLES `city_link` WRITE;
/*!40000 ALTER TABLE `city_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `city_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` datetime(6) DEFAULT NULL,
  `seat_number` int NOT NULL,
  `time` datetime(6) NOT NULL,
  `city_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `restaurant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs3olwbwpvjum1glqkc6p7east` (`city_id`),
  KEY `FKm4oimk0l1757o9pwavorj6ljg` (`user_id`),
  KEY `FKbna4xjm3tqln2j6kda3fl2yiy` (`restaurant_id`),
  CONSTRAINT `FKbna4xjm3tqln2j6kda3fl2yiy` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FKm4oimk0l1757o9pwavorj6ljg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKs3olwbwpvjum1glqkc6p7east` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` datetime(6) DEFAULT NULL,
  `max_seats_number` int NOT NULL,
  `name` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` datetime(6) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2022-09-10 20:11:12


INSERT INTO restaurant (id, version, name, max_seats_number) VALUES (1, CURRENT_TIMESTAMP, 'Buona Sera', 30);
INSERT INTO restaurant (id, version, name, max_seats_number) VALUES (2, CURRENT_TIMESTAMP, 'Dabo Döner', 20);
INSERT INTO restaurant (id, version, name, max_seats_number) VALUES (3, CURRENT_TIMESTAMP, 'Hippolit terrace', 10);
INSERT INTO restaurant (id, version, name, max_seats_number) VALUES (4, CURRENT_TIMESTAMP, 'Erőd Klub', 50);
INSERT INTO restaurant (id, version, name, max_seats_number) VALUES (5, CURRENT_TIMESTAMP, 'Corner', 30);

INSERT INTO city (id, version, post_code) VALUES (1, CURRENT_TIMESTAMP, 3300);
INSERT INTO city (id, version, post_code) VALUES (2, CURRENT_TIMESTAMP, 5400);
INSERT INTO city (id, version, post_code) VALUES (3, CURRENT_TIMESTAMP, 5000);
INSERT INTO city (id, version, post_code) VALUES (4, CURRENT_TIMESTAMP, 5012);
INSERT INTO city (id, version, post_code) VALUES (5, CURRENT_TIMESTAMP, 4356);

INSERT INTO user (id, version, username, password, email, authority) VALUES (1, CURRENT_TIMESTAMP, 'admin', 'admin', 'admin@gmail.com', 'ADMIN');
INSERT INTO user (id, version, username, password, email, authority) VALUES (1, CURRENT_TIMESTAMP, 'user', 'user', 'user@gmail.com', 'USER');

INSERT INTO reservation (id, version, user_id, seat_number, city_id, restaurant_id, time) VALUES (1, 1, 1, 4, 4, 2, CURRENT_TIMESTAMP);
INSERT INTO reservation (id, version, user_id, seat_number, city_id, restaurant_id, time) VALUES (1, 2, 1, 8, 1, 1, CURRENT_TIMESTAMP);