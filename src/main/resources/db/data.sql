-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: youpassed
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Dumping data for table `examEntities`
--

LOCK TABLES `examEntities` WRITE;
/*!40000 ALTER TABLE `examEntities` DISABLE KEYS */;
INSERT INTO `examEntities` VALUES (1,'ua_lang_lit',0),(2,'ua_history',0),(3,'math',0),(4,'physics',0),(5,'chemistry',0),(6,'biology',0),(7,'geography',0),(8,'foreign_lang',0),(9,'creativity',0);
/*!40000 ALTER TABLE `examEntities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `major_exams`
--

LOCK TABLES `major_exams` WRITE;
/*!40000 ALTER TABLE `major_exams` DISABLE KEYS */;
INSERT INTO `major_exams` VALUES (1,1,1),(2,1,3),(3,1,4),(4,2,1),(5,2,2),(6,2,7),(7,3,1),(8,3,2),(9,3,8),(10,4,1),(11,4,3),(12,4,8),(13,5,1),(14,5,2),(15,5,9),(16,6,1),(17,6,8),(18,6,7),(19,7,1),(20,7,3),(21,7,8),(22,8,1),(23,8,2),(24,8,3),(25,9,1),(26,9,3),(27,9,4),(28,10,1),(29,10,5),(30,10,3),(31,11,1),(32,11,3),(33,11,4),(34,12,1),(35,12,2),(36,12,9),(37,13,1),(38,13,6),(39,13,3),(40,14,1),(41,14,6),(42,14,5),(43,15,1),(44,15,2),(45,15,9);
/*!40000 ALTER TABLE `major_exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `majorEntities`
--

LOCK TABLES `majorEntities` WRITE;
/*!40000 ALTER TABLE `majorEntities` DISABLE KEYS */;
INSERT INTO `majorEntities` VALUES (1,'Computer Science',10),(2,'History',2),(3,'Philology',3),(4,'Economics',0),(5,'Music',4),(6,'Journalism',2),(7,'Sociology',4),(8,'Law',3),(9,'Mechanical Engineering',2),(10,'Chemical Techologies',6),(11,'Electronics',7),(12,'Architecture',1),(13,'Agriculture',3),(14,'Medicine',2),(15,'Design',0);
/*!40000 ALTER TABLE `majorEntities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `marks`
--

LOCK TABLES `marks` WRITE;
/*!40000 ALTER TABLE `marks` DISABLE KEYS */;
INSERT INTO `marks` VALUES (1,1,6,190),(2,1,5,150),(3,5,1,200),(4,5,2,160),(5,5,8,190);
/*!40000 ALTER TABLE `marks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (0,'student'),(1,'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `student_majors`
--

LOCK TABLES `student_majors` WRITE;
/*!40000 ALTER TABLE `student_majors` DISABLE KEYS */;
INSERT INTO `student_majors` VALUES (1,1,14),(2,5,3),(3,5,6);
/*!40000 ALTER TABLE `student_majors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,'god@godmail.god','r/1/X+2mH5d/TJhlbBizHQ==','God','God',1,'salt'),(1,'pavlov@dog.com','r/1/X+2mH5d/TJhlbBizHQ==','Dog','Pavlov',0,'salt'),(2,'potter@hogwarts.edu','r/1/X+2mH5d/TJhlbBizHQ==','Harry','Potter',0,'salt'),(3,'nelokh@mail.ru','r/1/X+2mH5d/TJhlbBizHQ==','Ne','Lokh',0,'salt'),(4,'nechuy@ukr.net','r/1/X+2mH5d/TJhlbBizHQ==','Ivan','Nechuy-Levytsky',0,'salt'),(5,'macho1863@yahoo.com','r/1/X+2mH5d/TJhlbBizHQ==','Olha','Kobylianska',0,'salt'),(6,'mascot@disney.com','r/1/X+2mH5d/TJhlbBizHQ==','Mickey','Mouse',0,'salt'),(7,'billie@gmail.com','r/1/X+2mH5d/TJhlbBizHQ==','Billie','Holiday',0,'salt'),(8,'count@transylmail.ro','r/1/X+2mH5d/TJhlbBizHQ==','Count','Dracula',0,'salt'),(9,'beatles@royalmail.uk','r/1/X+2mH5d/TJhlbBizHQ==','Michelle','Mabelle',0,'salt'),(10,'moviestar@cornhub.com','r/1/X+2mH5d/TJhlbBizHQ==','Sasha','Grey',0,'salt'),(11,'tolia49@i.ua','r/1/X+2mH5d/TJhlbBizHQ==','Prodam','Samokat',0,'salt'),(12,'heroyam@slava.ua','r/1/X+2mH5d/TJhlbBizHQ==','Stepan','Bandera',0,'salt'),(13,'legalize@jamail.jah','r/1/X+2mH5d/TJhlbBizHQ==','Bob','Marley',0,'salt'),(14,'virgin@paradise.org','r/1/X+2mH5d/TJhlbBizHQ==','Mary','Virgin',0,'salt'),(15,'krevetko@seafood.org','r/1/X+2mH5d/TJhlbBizHQ==','Shrimp','Azovsky',1,'salt'),(28,'asdds@sda.dsad','XSvhvIKD9v18qj00smKQ9w==','ASDsss','Dsdssds',0,'QÎéëµ¡ÿ\rÍh_UñŸ‘');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-03 16:49:15
