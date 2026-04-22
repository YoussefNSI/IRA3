-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           11.8.4-MariaDB - MariaDB Server
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour bestioles
DROP DATABASE IF EXISTS `bestioles`;
CREATE DATABASE IF NOT EXISTS `bestioles` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `bestioles`;

-- Listage de la structure de table bestioles. animal
DROP TABLE IF EXISTS `animal`;
CREATE TABLE IF NOT EXISTS `animal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `species_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ht4up5vd7qxcx9okgsdj4538` (`species_id`),
  CONSTRAINT `FK8ht4up5vd7qxcx9okgsdj4538` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Listage des données de la table bestioles.animal : ~6 rows (environ)
INSERT INTO `animal` (`id`, `color`, `name`, `sex`, `species_id`) VALUES
	(1, 'Gris tacheté', 'Max', 'M', 1),
	(2, 'Blanc', 'Médor', 'M', 2),
	(3, 'Noir', 'Loulou', 'F', 1),
	(4, 'Brun', 'Zia', 'F', 2),
	(5, 'Blanc', 'Lou', 'F', 3),
	(6, 'Roux', 'Minouchette', 'M', 1);

-- Listage de la structure de table bestioles. person
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `mdp` varchar(100) NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Listage des données de la table bestioles.person : ~8 rows (environ)
INSERT INTO `person` (`id`, `age`, `firstname`, `lastname`, `login`, `mdp`, `active`) VALUES
	(1, 22, 'Henri', 'Lamarque', 'hla', '*****', 1),
	(2, 24, 'Sylvie', 'Lamarque', 'sla', '*****', 1),
	(3, 55, 'Jean', 'Vintroi', 'jvi', '*****', 1),
	(4, 80, 'Paul', 'Demaine', 'pde', '*****', 1),
	(5, 45, 'Sophie', 'Nero', 'sne', '*****', 1),
	(6, 17, 'Pierre', 'Sansane', 'sva', '*****', 1),
	(7, 33, 'John', 'Mangolo', 'jma', '*****', 1),
	(8, 26, 'Bill', 'Wagner', 'bwa', '*****', 1);

-- Listage de la structure de table bestioles. person_animals
DROP TABLE IF EXISTS `person_animals`;
CREATE TABLE IF NOT EXISTS `person_animals` (
  `person_id` int(11) NOT NULL,
  `animals_id` int(11) NOT NULL,
  PRIMARY KEY (`person_id`,`animals_id`),
  KEY `FKpt8c3kig54emvkm6a1gcq1ury` (`animals_id`),
  CONSTRAINT `FKpt8c3kig54emvkm6a1gcq1ury` FOREIGN KEY (`animals_id`) REFERENCES `animal` (`id`),
  CONSTRAINT `FKq2c74s7eeeoy8tq0sqsjmvbmw` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Listage des données de la table bestioles.person_animals : ~7 rows (environ)
INSERT INTO `person_animals` (`person_id`, `animals_id`) VALUES
	(2, 1),
	(7, 1),
	(4, 2),
	(3, 3),
	(5, 4),
	(2, 5),
	(8, 6);

-- Listage de la structure de table bestioles. person_role
DROP TABLE IF EXISTS `person_role`;
CREATE TABLE IF NOT EXISTS `person_role` (
  `person_id` int(11) NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`person_id`,`role_id`) USING BTREE,
  KEY `FKpt8c3kig54emvkm6a1gcq1ury` (`role_id`) USING BTREE,
  CONSTRAINT `FK_Person_Role_Person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK_Person_Role_Role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table bestioles.person_role : ~0 rows (environ)

-- Listage de la structure de table bestioles. role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table bestioles.role : ~3 rows (environ)
INSERT INTO `role` (`id`, `label`) VALUES
	(1, 'ROLE_EMPLOYE'),
	(2, 'ROLE_USER'),
	(3, 'ROLE_ADMIN');

-- Listage de la structure de table bestioles. species
DROP TABLE IF EXISTS `species`;
CREATE TABLE IF NOT EXISTS `species` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `common_name` varchar(50) NOT NULL,
  `latin_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Listage des données de la table bestioles.species : ~3 rows (environ)
INSERT INTO `species` (`id`, `common_name`, `latin_name`) VALUES
	(1, 'Chat', 'Felis silvestris catus'),
	(2, 'Chien', 'Canis lupus familiaris'),
	(3, 'Lapin', 'Oryctolagus cuniculus');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
