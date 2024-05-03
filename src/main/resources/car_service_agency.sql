
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` bigint unsigned NOT NULL,
  `updated_at` bigint unsigned NOT NULL,
  `uuid` varchar(40) NOT NULL,
  `user_id` int NOT NULL,
  `vehicle_model_id` int NOT NULL,
  `status` varchar(50) NOT NULL,
  `order_id` int DEFAULT NULL,
  `notes` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `appointment_operator_time_slot_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment_operator_time_slot_mapping` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` bigint unsigned NOT NULL,
  `updated_at` bigint unsigned NOT NULL,
  `appointment_id` int NOT NULL,
  `date` bigint DEFAULT NULL,
  `time_slot_id` int NOT NULL,
  `operator_id` int NOT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `appointment_time_slot_mapping_UN` (`operator_id`,`date`,`time_slot_id`,`is_active`),
  KEY `appointment_time_slot_mapping_FK_appointment_id` (`appointment_id`),
  KEY `appointment_time_slot_mapping_FK_time_slot_id` (`time_slot_id`),
  CONSTRAINT `appointment_time_slot_mapping_FK_appointment_id` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`),
  CONSTRAINT `appointment_time_slot_mapping_FK_operator_id` FOREIGN KEY (`operator_id`) REFERENCES `operator` (`id`),
  CONSTRAINT `appointment_time_slot_mapping_FK_time_slot_id` FOREIGN KEY (`time_slot_id`) REFERENCES `time_slots` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` bigint unsigned NOT NULL,
  `updated_at` bigint unsigned NOT NULL,
  `uuid` varchar(40) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `time_slots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_slots` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` bigint unsigned NOT NULL,
  `updated_at` bigint unsigned NOT NULL,
  `start_hour` int unsigned NOT NULL,
  `end_hour` int unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

INSERT INTO time_slots(created_at, updated_at, start_hour, end_hour)
VALUES (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 0, 1),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 0, 1),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 1, 2),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 2, 3),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 3, 4),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 4, 5),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 5, 6),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 6, 7),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 7, 8),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 8, 9),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 9, 10),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 10, 11),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 11, 12),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 12, 13),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 13, 14),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 14, 15),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 15, 16),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 16, 17),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 17, 18),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 18, 19),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 19, 20),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 20, 21),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 21, 22),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 22, 23),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 23, 0)
;

INSERT INTO operator(created_at, updated_at, uuid, name, latitude, longitude, is_active)
VALUES (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 'ff7b728e-413e-4d87-83ec-62fc045d86c4', 'The bike doctor', 28.648346, 77.345071, TRUE),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 'd140e717-91dd-4df9-a80d-429ecb196fdf', 'Bike Expert', 28.648346, 77.345071, TRUE),
       (UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000, 'cbdefc21-0011-4424-ab2d-065d080e7266', 'D.M. Bike Point', 28.648346, 77.345071, TRUE)
;
