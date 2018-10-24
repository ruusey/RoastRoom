-- MySQL dump 10.13  Distrib 5.6.20, for Win64 (x86_64)
--
-- Host: localhost    Database: forum
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `idcomments` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(200) DEFAULT NULL,
  `post` int(20) NOT NULL,
  `timestamp` varchar(45) NOT NULL,
  `ip` varchar(20) NOT NULL,
  PRIMARY KEY (`idcomments`),
  UNIQUE KEY `comment_UNIQUE` (`comment`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'Oh cool! There\'s comments? that\'s<br> dope!',1,'2014/11/25 18:25:33','192.168.1.70'),(2,'Ru this is the dumbest',1,'2014/11/25 19:05:46','50.160.148.223'),(3,'Yeah! Ru totally sucks!',2,'2014/11/25 19:06:38','50.160.148.223'),(4,'Well see the thing is, thats just<br> not true',2,'2014/11/25 19:07:10','192.168.1.70'),(5,'yes, #Rdyfrpdc',3,'2014/11/25 20:59:10','192.168.1.70'),(6,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa<br>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa<br>aaaaa',7,'2014/11/28 21:38:13','192.168.1.70'),(7,'i agree',10,'2014/12/01 10:50:54','66.213.143.10'),(8,'Okay awesome, we are all super pumped',12,'2014/12/01 13:15:10','66.213.143.10'),(9,'damn right i do',13,'2014/12/01 13:47:38','66.213.143.10'),(10,'yeh i heard barry loves em!\n',15,'2014/12/01 13:53:53','66.213.143.10'),(11,'damn right he does',13,'2014/12/01 13:54:18','66.213.143.10'),(12,'he loves them fatties',16,'2014/12/03 12:04:23','192.168.1.70'),(13,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',16,'2014/12/03 15:59:16','192.168.1.70'),(14,'asdasd',23,'2015/02/20 20:43:10','192.168.1.70'),(15,'adsssssssssssssssssssssssss',23,'2015/02/20 20:51:33','192.168.1.70'),(16,'This is a test',23,'2015/02/23 21:39:58','192.168.1.70'),(17,'Tight dude',27,'2015/05/08 14:13:40','66.213.143.10');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `post` varchar(1000) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'192.168.1.70','First production build of The Roast Room. Enjoy','2014/11/25 18:20:33','Anonymous'),(2,'50.160.148.223','Ru sucks','2014/11/25 19:06:14','Anonymous'),(3,'192.168.1.70','Andy is possibly more dumb than tyrone','2014/11/25 19:08:03','Anonymous'),(4,'192.168.1.70','asdasd','2014/11/28 20:06:59','Anonymous'),(5,'192.168.1.70','asd','2014/11/28 20:13:31','Anonymous'),(6,'192.168.1.70','this is a test','2014/11/28 20:14:18','Anonymous'),(7,'192.168.1.70','What a cool new feature!!!','2014/11/28 20:32:15','Anonymous'),(8,'192.168.1.70','aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','2014/11/30 00:02:38','Anonymous'),(9,'192.168.1.70','aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss','2014/11/30 00:04:19','Anonymous'),(10,'66.213.143.10','john kibler is a poo head','2014/12/01 10:50:45','Anonymous'),(11,'66.213.143.10','Rangar DC is literally the gayest person ever','2014/12/01 12:35:36','Anonymous'),(12,'66.213.143.10','IMPORTANT ANNOUNCEMENT TOMORROW AT 3:22 AM. IT\'S REALLY IMPORTANT!','2014/12/01 12:41:21','Anonymous'),(13,'66.213.143.244','Jack loves touching boners.','2014/12/01 13:44:39','Anonymous'),(14,'66.213.143.10','jack is dumb af','2014/12/01 13:47:15','Anonymous'),(15,'66.213.143.10','boners are tight','2014/12/01 13:47:26','Anonymous'),(16,'66.213.143.10','I think blake huber is a cock luvr','2014/12/01 13:59:20','Anonymous'),(17,'24.98.207.45','Test123','2014/12/07 11:31:46','Anonymous'),(18,'24.98.207.45','Assfghhahahdbahwha\n','2014/12/07 11:32:12','Anonymous'),(19,'24.98.207.45','Abcdefghijklmnopqrstuvwxyz','2014/12/07 11:32:53','Anonymous'),(20,'66.213.143.10','Tyler barry is a fgt','2015/02/20 12:09:12','Anonymous'),(21,'66.213.143.10','Benton is a nerd and likes to play minecraft','2015/02/20 12:10:36','Anonymous'),(22,'66.213.143.10','blake needs to write his paper','2015/02/20 12:11:10','Anonymous'),(23,'66.213.143.10','Blake is gY','2015/02/20 12:17:25','Anonymous'),(24,'66.213.143.10','John Kibs sucks a dick','2015/02/24 10:27:19','Anonymous'),(25,'66.213.143.10','asddasdasdasdasdasdasdasdasdasd','2015/02/24 10:57:47','Anonymous'),(26,'66.213.143.10','I hate history ','2015/02/24 10:58:04','Anonymous'),(27,'66.213.143.10','Whats the move this weekend?','2015/05/08 14:12:47','Anonymous'),(28,'10.1.10.240','DUDEEEEEEEEE','2015/08/24 23:58:33','Anonymous'),(29,'66.213.143.10','fuckin boners','2015/08/27 12:48:29','Anonymous'),(30,'0:0:0:0:0:0:0:1','Sammy Bags fucks black girls ONLY','2018/10/23 18:02:12','Anonymous'),(31,'71.68.146.246','Tide Pods: 50% Tide, 50% Pod, 50% Xanax','2018/10/23 18:44:40','Anonymous');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votes`
--

DROP TABLE IF EXISTS `votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votes` (
  `user_id` varchar(36) NOT NULL DEFAULT '',
  `content_id` int(11) NOT NULL,
  `vote` tinyint(4) NOT NULL,
  `ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`content_id`,`vote`),
  UNIQUE KEY `content_id_UNIQUE` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votes`
--

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
INSERT INTO `votes` VALUES ('57b4c312-2d6f-4326-8612-78ca989986b6',27,-1,'71.68.146.246'),('57b4c312-2d6f-4326-8612-78ca989986b6',28,1,'71.68.146.246'),('57b4c312-2d6f-4326-8612-78ca989986b6',29,1,'71.68.146.246'),('57b4c312-2d6f-4326-8612-78ca989986b6',30,1,'71.68.146.246'),('57b4c312-2d6f-4326-8612-78ca989986b6',31,1,'71.68.146.246');
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-23 23:46:12
