-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: policlinici
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `idAdministrator` int(11) NOT NULL,
  PRIMARY KEY (`idAdministrator`),
  CONSTRAINT `administrator_ibfk_1` FOREIGN KEY (`idAdministrator`) REFERENCES `utilizator` (`idUtilizator`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `angajat`
--

DROP TABLE IF EXISTS `angajat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `angajat` (
  `idAngajat` int(11) NOT NULL,
  `Salariu` int(11) DEFAULT NULL,
  `NumarOrePerLuna` int(11) DEFAULT NULL,
  `Functie` enum('inspector resurse umane','receptioner','contabil','asistent medical','medic') DEFAULT NULL,
  `Departament` int(11) DEFAULT NULL,
  `idUnitate` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAngajat`),
  KEY `Departament` (`Departament`),
  KEY `Unitate` (`idUnitate`),
  CONSTRAINT `angajat_fk_unitate` FOREIGN KEY (`idUnitate`) REFERENCES `unitati_medicale` (`idUnitateMedicala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `angajat_ibfk_1` FOREIGN KEY (`idAngajat`) REFERENCES `utilizator` (`idUtilizator`),
  CONSTRAINT `angajat_ibfk_2` FOREIGN KEY (`Departament`) REFERENCES `departament` (`idDepartament`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `angajat`
--

LOCK TABLES `angajat` WRITE;
/*!40000 ALTER TABLE `angajat` DISABLE KEYS */;
INSERT INTO `angajat` VALUES (3,2000,50,'medic',3,1),(4,2000,50,'medic',3,1),(5,2000,50,'medic',3,1),(6,2000,50,'medic',3,1),(7,2000,50,'medic',3,1),(8,2000,50,'medic',3,1),(9,2000,50,'medic',3,1),(10,2000,50,'medic',3,1),(11,10000,100,'medic',3,1),(13,10000,100,'medic',3,5),(14,1000,100,'asistent medical',3,5),(15,1000,100,'receptioner',1,5),(23,32323,33,'medic',3,5),(24,321323,3,'medic',3,5),(27,3000,40,'asistent medical',3,5),(28,3500,35,'asistent medical',3,7),(29,3500,35,'asistent medical',3,7),(30,4000,40,'medic',3,7),(31,12000,120,'medic',3,1),(32,3000,30,'contabil',2,1),(33,1000,30,'contabil',2,1),(35,45565,2,'inspector resurse umane',1,5);
/*!40000 ALTER TABLE `angajat` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `policlinici`.`angajat_BEFORE_DELETE` BEFORE DELETE ON `angajat` FOR EACH ROW
BEGIN

delete from orar_angajat where old.idAngajat=orar_angajat.idAngajat
and orar_angajat.Nume_zi in ('Luni','Marti','Miercuri','Joi',
'Vineri','Sambata');
delete from medic where old.idAngajat=idMedic;
delete from asistentmedical where old.idAngajat=idAsistent;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `asistentmedical`
--

DROP TABLE IF EXISTS `asistentmedical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asistentmedical` (
  `idAsistent` int(11) NOT NULL,
  `Tip` enum('generalist','laborator','radiologie') DEFAULT NULL,
  `Grad` enum('principal','secundar') DEFAULT NULL,
  PRIMARY KEY (`idAsistent`),
  CONSTRAINT `asistentmedical_ibfk_1` FOREIGN KEY (`idAsistent`) REFERENCES `angajat` (`idAngajat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistentmedical`
--

LOCK TABLES `asistentmedical` WRITE;
/*!40000 ALTER TABLE `asistentmedical` DISABLE KEYS */;
/*!40000 ALTER TABLE `asistentmedical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competente`
--

DROP TABLE IF EXISTS `competente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competente` (
  `idCompetenta` int(11) NOT NULL AUTO_INCREMENT,
  `Denumire` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCompetenta`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competente`
--

LOCK TABLES `competente` WRITE;
/*!40000 ALTER TABLE `competente` DISABLE KEYS */;
INSERT INTO `competente` VALUES (1,'EEG'),(2,'RMN'),(3,'Dializa'),(4,'Radiografie'),(5,'asistent medical');
/*!40000 ALTER TABLE `competente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concediu`
--

DROP TABLE IF EXISTS `concediu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concediu` (
  `idConcediu` int(11) NOT NULL AUTO_INCREMENT,
  `idAngajat` int(11) NOT NULL,
  `data_inceput` date DEFAULT NULL,
  `durata_concediu` int(11) DEFAULT NULL,
  `data_final` date DEFAULT NULL,
  PRIMARY KEY (`idConcediu`),
  KEY `fk_concediu_idx` (`idAngajat`),
  CONSTRAINT `fk_concediu` FOREIGN KEY (`idAngajat`) REFERENCES `angajat` (`idAngajat`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concediu`
--

LOCK TABLES `concediu` WRITE;
/*!40000 ALTER TABLE `concediu` DISABLE KEYS */;
INSERT INTO `concediu` VALUES (2,3,'2018-01-08',NULL,'2018-01-30'),(3,4,'2018-01-08',NULL,'2018-01-30'),(4,4,'2017-11-08',NULL,'2017-11-30'),(5,5,'2017-12-20',NULL,'2018-01-10'),(6,3,'2018-01-20',-19,'2018-01-01'),(7,3,'2018-01-01',19,'2018-01-20'),(8,3,'2018-01-01',20,'2018-01-20'),(9,3,'2018-02-28',-8,'2018-02-19');
/*!40000 ALTER TABLE `concediu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contabil`
--

DROP TABLE IF EXISTS `contabil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contabil` (
  `idContabil` int(11) NOT NULL,
  PRIMARY KEY (`idContabil`),
  CONSTRAINT `contabil_ibfk_1` FOREIGN KEY (`idContabil`) REFERENCES `angajat` (`idAngajat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contabil`
--

LOCK TABLES `contabil` WRITE;
/*!40000 ALTER TABLE `contabil` DISABLE KEYS */;
/*!40000 ALTER TABLE `contabil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departament`
--

DROP TABLE IF EXISTS `departament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departament` (
  `idDepartament` int(11) NOT NULL AUTO_INCREMENT,
  `Nume` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idDepartament`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departament`
--

LOCK TABLES `departament` WRITE;
/*!40000 ALTER TABLE `departament` DISABLE KEYS */;
INSERT INTO `departament` VALUES (1,'Resurse umane'),(2,'Financiar-contabil'),(3,'Medical');
/*!40000 ALTER TABLE `departament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostice`
--

DROP TABLE IF EXISTS `diagnostice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnostice` (
  `idDiagnostic` int(11) NOT NULL AUTO_INCREMENT,
  `Diagnostic` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idDiagnostic`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostice`
--

LOCK TABLES `diagnostice` WRITE;
/*!40000 ALTER TABLE `diagnostice` DISABLE KEYS */;
INSERT INTO `diagnostice` VALUES (1,'0'),(2,'bolnav'),(3,'dad'),(4,'bolna'),(5,'si'),(6,'Bronsita'),(7,'cataracta');
/*!40000 ALTER TABLE `diagnostice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspector_hr`
--

DROP TABLE IF EXISTS `inspector_hr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inspector_hr` (
  `idInspector` int(11) NOT NULL,
  PRIMARY KEY (`idInspector`),
  CONSTRAINT `inspector_hr_ibfk_1` FOREIGN KEY (`idInspector`) REFERENCES `angajat` (`idAngajat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspector_hr`
--

LOCK TABLES `inspector_hr` WRITE;
/*!40000 ALTER TABLE `inspector_hr` DISABLE KEYS */;
/*!40000 ALTER TABLE `inspector_hr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medic`
--

DROP TABLE IF EXISTS `medic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medic` (
  `idMedic` int(11) NOT NULL,
  `Grad` enum('specialist','primar') DEFAULT NULL,
  `CodParafa` int(11) DEFAULT NULL,
  `TitluStiintific` enum('doctorand') DEFAULT NULL,
  `PostDidactic` enum('preparator','asistent','lector','conferentiar','profesor') DEFAULT NULL,
  `Procent` decimal(5,4) DEFAULT NULL,
  PRIMARY KEY (`idMedic`),
  CONSTRAINT `medic_ibfk_1` FOREIGN KEY (`idMedic`) REFERENCES `angajat` (`idAngajat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medic`
--

LOCK TABLES `medic` WRITE;
/*!40000 ALTER TABLE `medic` DISABLE KEYS */;
INSERT INTO `medic` VALUES (3,'specialist',1,'','profesor',9.9999),(4,'specialist',1,'','profesor',9.9999),(5,'specialist',1,'','profesor',9.9999);
/*!40000 ALTER TABLE `medic` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger trigger_delete_medici before delete on medic
for each row

begin 

delete from medici_competente where old.idMedic=medici_competente.idMedic;
delete from medici_specialitati where old.idMedic=medici_specialitati.idMedic;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `medici_competente`
--

DROP TABLE IF EXISTS `medici_competente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medici_competente` (
  `idMedic` int(11) NOT NULL,
  `idCompetenta` int(11) NOT NULL,
  PRIMARY KEY (`idMedic`,`idCompetenta`),
  KEY `medici_competente_ibfk_2_idx` (`idCompetenta`),
  CONSTRAINT `medici_competente_ibfk_1` FOREIGN KEY (`idMedic`) REFERENCES `medic` (`idMedic`),
  CONSTRAINT `medici_competente_ibfk_2` FOREIGN KEY (`idCompetenta`) REFERENCES `competente` (`idCompetenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medici_competente`
--

LOCK TABLES `medici_competente` WRITE;
/*!40000 ALTER TABLE `medici_competente` DISABLE KEYS */;
INSERT INTO `medici_competente` VALUES (3,1),(4,1),(5,1),(3,2),(4,2),(5,2),(3,3),(4,3),(5,3);
/*!40000 ALTER TABLE `medici_competente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medici_specialitati`
--

DROP TABLE IF EXISTS `medici_specialitati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medici_specialitati` (
  `idMedic` int(11) NOT NULL,
  `idSpecialitate` int(11) NOT NULL,
  PRIMARY KEY (`idMedic`,`idSpecialitate`),
  KEY `idSpecialitate` (`idSpecialitate`),
  CONSTRAINT `medici_specialitati_ibfk_1` FOREIGN KEY (`idMedic`) REFERENCES `medic` (`idMedic`),
  CONSTRAINT `medici_specialitati_ibfk_2` FOREIGN KEY (`idSpecialitate`) REFERENCES `specialitate` (`idSpecialitate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medici_specialitati`
--

LOCK TABLES `medici_specialitati` WRITE;
/*!40000 ALTER TABLE `medici_specialitati` DISABLE KEYS */;
INSERT INTO `medici_specialitati` VALUES (3,2),(4,2),(5,2),(3,3),(4,3),(5,3);
/*!40000 ALTER TABLE `medici_specialitati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orar`
--

DROP TABLE IF EXISTS `orar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orar` (
  `idUnitate` int(11) NOT NULL,
  `Tip_Orar` enum('Unitate medicala','Angajat') NOT NULL,
  `Luni_deschidere` time DEFAULT NULL,
  `Marti_deschidere` time DEFAULT NULL,
  `Marti_inchidere` time DEFAULT NULL,
  `Miercuri_deschidere` time DEFAULT NULL,
  `Miercuri_inchidere` time DEFAULT NULL,
  `Joi_deschidere` time DEFAULT NULL,
  `Joi_inchidere` time DEFAULT NULL,
  `Vineri_deschidere` time DEFAULT NULL,
  `Vineri_inchidere` time DEFAULT NULL,
  `Sambata_deschidere` time DEFAULT NULL,
  `Sambata_inchidere` time DEFAULT NULL,
  PRIMARY KEY (`idUnitate`,`Tip_Orar`),
  KEY `unitate_medicala` (`idUnitate`),
  CONSTRAINT `fk_orar_unitate` FOREIGN KEY (`idUnitate`) REFERENCES `unitati_medicale` (`idUnitateMedicala`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orar`
--

LOCK TABLES `orar` WRITE;
/*!40000 ALTER TABLE `orar` DISABLE KEYS */;
INSERT INTO `orar` VALUES (5,'Unitate medicala','08:00:00','08:00:00','18:00:00','08:00:00','18:00:00','08:00:00','18:00:00','08:00:00','18:00:00','08:00:00','08:00:00'),(7,'Unitate medicala','08:00:00','08:00:00','18:00:00','08:00:00','18:00:00','08:00:00','18:00:00','08:00:00','18:00:00','08:00:00','08:00:00');
/*!40000 ALTER TABLE `orar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orar_angajat`
--

DROP TABLE IF EXISTS `orar_angajat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orar_angajat` (
  `idAngajat` int(11) NOT NULL,
  `Nume_Zi` varchar(50) NOT NULL,
  `Locatie` varchar(50) DEFAULT NULL,
  `Ora_inceput` time DEFAULT NULL,
  `Ora_inchidere` time DEFAULT NULL,
  PRIMARY KEY (`idAngajat`,`Nume_Zi`),
  CONSTRAINT `Orar_angajat_ibfk_1` FOREIGN KEY (`idAngajat`) REFERENCES `angajat` (`idAngajat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orar_angajat`
--

LOCK TABLES `orar_angajat` WRITE;
/*!40000 ALTER TABLE `orar_angajat` DISABLE KEYS */;
INSERT INTO `orar_angajat` VALUES (3,'Joi','Medlife','08:00:00','16:00:00'),(3,'Luni','Medlife','08:00:00','16:00:00'),(3,'Marti','Medlife','08:00:00','16:00:00'),(3,'Miercuri','Medlife','08:00:00','16:00:00'),(3,'Sambata','Medlife','08:00:00','16:00:00'),(3,'Vineri','Medlife','08:00:00','16:00:00'),(4,'Joi','Medlife','08:00:00','16:00:00'),(4,'Luni','Medlife','08:00:00','16:00:00'),(4,'Marti','Medlife','08:00:00','16:00:00'),(4,'Miercuri','Medlife','08:00:00','16:00:00'),(4,'Sambata','Medlife','08:00:00','16:00:00'),(4,'Vineri','Medlife','08:00:00','16:00:00'),(5,'Joi','Medlife','08:00:00','16:00:00'),(5,'Luni','Medlife','08:00:00','16:00:00'),(5,'Marti','Medlife','08:00:00','16:00:00'),(5,'Miercuri','Medlife','08:00:00','16:00:00'),(5,'Sambata','Medlife','08:00:00','16:00:00'),(5,'Vineri','Medlife','08:00:00','16:00:00'),(6,'Joi','Medlife','08:00:00','16:00:00'),(6,'Luni','Medlife','08:00:00','16:00:00'),(6,'Marti','Medlife','08:00:00','16:00:00'),(6,'Miercuri','Medlife','08:00:00','16:00:00'),(6,'Sambata','Medlife','08:00:00','16:00:00'),(6,'Vineri','Medlife','08:00:00','16:00:00'),(7,'Joi','Medlife','08:00:00','16:00:00'),(7,'Luni','Medlife','08:00:00','16:00:00'),(7,'Marti','Medlife','08:00:00','16:00:00'),(7,'Miercuri','Medlife','08:00:00','16:00:00'),(7,'Sambata','Medlife','08:00:00','16:00:00'),(7,'Vineri','Medlife','08:00:00','16:00:00'),(8,'Joi','Medlife','08:00:00','16:00:00'),(8,'Luni','Medlife','08:00:00','16:00:00'),(8,'Marti','Medlife','08:00:00','16:00:00'),(8,'Miercuri','Medlife','08:00:00','16:00:00'),(8,'Sambata','Medlife','08:00:00','16:00:00'),(8,'Vineri','Medlife','08:00:00','16:00:00'),(9,'Joi','Medlife','08:00:00','16:00:00'),(9,'Luni','Medlife','08:00:00','16:00:00'),(9,'Marti','Medlife','08:00:00','16:00:00'),(9,'Miercuri','Medlife','08:00:00','16:00:00'),(9,'Sambata','Medlife','08:00:00','16:00:00'),(9,'Vineri','Medlife','08:00:00','16:00:00'),(10,'Joi','Medlife','08:00:00','16:00:00'),(10,'Luni','Medlife','08:00:00','16:00:00'),(10,'Marti','Medlife','08:00:00','16:00:00'),(10,'Miercuri','Medlife','08:00:00','16:00:00'),(10,'Sambata','Medlife','08:00:00','16:00:00'),(10,'Vineri','Medlife','08:00:00','16:00:00'),(11,'Joi','Medlife','08:00:00','15:00:00'),(11,'Luni','Medlife','08:00:00','15:00:00'),(11,'Marti','Medlife','08:00:00','15:00:00'),(11,'Miercuri','Medlife','08:00:00','15:00:00'),(11,'Sambata','Medlife','08:00:00','15:00:00'),(11,'Vineri','Medlife','08:00:00','15:00:00'),(27,'Joi','Medlife','08:00:00','08:00:00'),(27,'Luni','Medlife','08:00:00','15:00:00'),(27,'Marti','Medlife','08:00:00','15:00:00'),(27,'Miercuri','Medlife','08:00:00','15:00:00'),(27,'Sambata','Medlife','08:00:00','08:00:00'),(27,'Vineri','Medlife','08:00:00','08:00:00'),(28,'Joi','Medlife','08:00:00','14:00:00'),(28,'Luni','Medlife','08:00:00','13:00:00'),(28,'Marti','Medlife','08:00:00','13:00:00'),(28,'Miercuri','Medlife','08:00:00','14:00:00'),(28,'Sambata','Medlife','08:00:00','13:00:00'),(28,'Vineri','Medlife','08:00:00','16:00:00'),(29,'Joi','Medlife','08:00:00','08:00:00'),(29,'Luni','Medlife','08:00:00','08:00:00'),(29,'Marti','Medlife','08:00:00','08:00:00'),(29,'Miercuri','Medlife','08:00:00','08:00:00'),(29,'Sambata','Medlife','08:00:00','08:00:00'),(29,'Vineri','Medlife','08:00:00','08:00:00'),(30,'Joi','Medlife','08:00:00','08:00:00'),(30,'Luni','Medlife','08:00:00','08:00:00'),(30,'Marti','Medlife','08:00:00','08:00:00'),(30,'Miercuri','Medlife','08:00:00','08:00:00'),(30,'Sambata','Medlife','08:00:00','08:00:00'),(30,'Vineri','Medlife','08:00:00','08:00:00'),(32,'Joi','Medstar','08:00:00','16:00:00'),(32,'Luni','Medstar','08:00:00','16:00:00'),(32,'Marti','Medstar','08:00:00','16:00:00'),(32,'Miercuri','Medstar','08:00:00','16:00:00'),(32,'Sambata','Medstar','08:00:00','16:00:00'),(32,'Vineri','Medstar','08:00:00','16:00:00'),(33,'Joi','Medlife','08:00:00','13:00:00'),(33,'Luni','Medlife','08:00:00','12:00:00'),(33,'Marti','Medlife','08:00:00','10:00:00'),(33,'Miercuri','Medlife','08:00:00','13:00:00'),(33,'Sambata','Medlife','08:00:00','13:00:00'),(33,'Vineri','Medlife','08:00:00','11:00:00'),(35,'Joi','Medlife','08:00:00','08:00:00'),(35,'Luni','Medlife','08:00:00','08:00:00'),(35,'Marti','Medlife','08:00:00','08:00:00'),(35,'Miercuri','Medlife','08:00:00','08:00:00'),(35,'Sambata','Medlife','08:00:00','08:00:00'),(35,'Vineri','Medlife','08:00:00','08:00:00');
/*!40000 ALTER TABLE `orar_angajat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orar_specific`
--

DROP TABLE IF EXISTS `orar_specific`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orar_specific` (
  `idAngajat` int(11) NOT NULL,
  `data_specifica` date NOT NULL,
  `Locatie` varchar(50) DEFAULT NULL,
  `Ora_inceput` time DEFAULT NULL,
  `Ora_inchidere` time DEFAULT NULL,
  PRIMARY KEY (`idAngajat`,`data_specifica`),
  CONSTRAINT `Orar_angajat_specific_ibfk_1` FOREIGN KEY (`idAngajat`) REFERENCES `angajat` (`idAngajat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orar_specific`
--

LOCK TABLES `orar_specific` WRITE;
/*!40000 ALTER TABLE `orar_specific` DISABLE KEYS */;
INSERT INTO `orar_specific` VALUES (3,'2018-01-08','BlueLife','16:00:00','19:00:00'),(3,'2018-02-12','MedLife','16:00:00','19:00:00'),(3,'2018-02-20','Medstar','08:00:00','16:00:00'),(3,'2018-02-21','Medstar','00:00:01','00:00:02'),(3,'2018-02-27','BlueLife','16:00:00','19:00:00');
/*!40000 ALTER TABLE `orar_specific` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacienti`
--

DROP TABLE IF EXISTS `pacienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacienti` (
  `idPacient` int(11) NOT NULL AUTO_INCREMENT,
  `DataFisa` date DEFAULT NULL,
  `Nume` varchar(50) DEFAULT NULL,
  `Prenume` varchar(50) DEFAULT NULL,
  `DataNasterii` date DEFAULT NULL,
  `Adresa` varchar(50) DEFAULT NULL,
  `Telefon` varchar(10) DEFAULT NULL,
  `CNP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idPacient`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacienti`
--

LOCK TABLES `pacienti` WRITE;
/*!40000 ALTER TABLE `pacienti` DISABLE KEYS */;
INSERT INTO `pacienti` VALUES (1,'2017-12-21','Bilc','Sergiu','1998-10-07','Al. Garbau','745282647',123456),(2,'2017-12-21','Galan','Narcisa','1998-10-07','Al. Garbau','745282647',123457),(3,'2017-12-21','Ciprian','Boghian','1998-10-07','Al. Garbau','745282647',123458),(4,'2017-12-27','Stancu','Alina','0000-00-00','1996-10-15','745282647',12789),(5,'2017-12-27','Stancu','Alina','0000-00-00','1996-15-10','745282647',127899),(6,'2017-12-27','Stancu','Alina','0000-00-00','Al. Padin','745282647',127898),(7,'2017-12-27','Stancu','Alina','1996-10-15','Al. Padin','745282647',127896),(8,'2017-12-27','','','1234-10-10','','0',12345654);
/*!40000 ALTER TABLE `pacienti` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `policlinici`.`pacienti_BEFORE_DELETE` BEFORE DELETE ON `pacienti` FOR EACH ROW
BEGIN

delete from consulatii where old.idPacient=consulatii.idPacient;
delete from programari where old.idPacient=programari.idPacient;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `programari`
--

DROP TABLE IF EXISTS `programari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programari` (
  `idProgramare` int(11) NOT NULL AUTO_INCREMENT,
  `idPacient` int(11) DEFAULT NULL,
  `idMedic` int(11) DEFAULT NULL,
  `DataProgramare` date DEFAULT NULL,
  `OraProgramare` time DEFAULT NULL,
  PRIMARY KEY (`idProgramare`),
  KEY `idPacient` (`idPacient`),
  KEY `idMedic` (`idMedic`),
  CONSTRAINT `programari_ibfk_1` FOREIGN KEY (`idPacient`) REFERENCES `pacienti` (`idPacient`),
  CONSTRAINT `programari_ibfk_2` FOREIGN KEY (`idMedic`) REFERENCES `medic` (`idMedic`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programari`
--

LOCK TABLES `programari` WRITE;
/*!40000 ALTER TABLE `programari` DISABLE KEYS */;
INSERT INTO `programari` VALUES (17,1,3,'2017-12-28','16:00:00'),(18,2,3,'2017-12-28','17:00:00'),(19,1,3,'2018-01-01','16:00:00'),(20,1,4,'2018-01-01','17:00:00'),(21,1,3,'1111-11-11','00:00:00'),(22,1,3,'2011-11-11','00:00:00'),(23,1,3,'2011-11-11','00:00:00'),(24,1,3,'2018-01-03','06:00:00'),(25,1,3,'2018-01-07','14:00:00'),(26,2,3,'2018-01-07','14:00:00'),(27,1,3,'2018-01-02','18:00:00'),(28,2,NULL,'2018-02-21','10:30:00'),(29,2,NULL,'1111-11-11','04:00:00'),(30,2,NULL,'1111-11-11','04:00:00'),(31,2,NULL,'1111-11-11','04:00:00'),(32,2,NULL,'2019-03-22','12:30:00'),(33,2,NULL,'2018-01-01','18:00:00');
/*!40000 ALTER TABLE `programari` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `policlinici`.`programari_BEFORE_DELETE` BEFORE DELETE ON `programari` FOR EACH ROW
BEGIN
delete from programari where idProgramare=old.idProgramare;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `programari_servicii`
--

DROP TABLE IF EXISTS `programari_servicii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programari_servicii` (
  `idProgramare` int(11) NOT NULL,
  `idServiciu` int(11) NOT NULL,
  PRIMARY KEY (`idProgramare`,`idServiciu`),
  KEY `fk_servicii_idx` (`idServiciu`),
  CONSTRAINT `fk_programari` FOREIGN KEY (`idProgramare`) REFERENCES `programari` (`idProgramare`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_servicii` FOREIGN KEY (`idServiciu`) REFERENCES `servicii` (`idServiciu`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programari_servicii`
--

LOCK TABLES `programari_servicii` WRITE;
/*!40000 ALTER TABLE `programari_servicii` DISABLE KEYS */;
INSERT INTO `programari_servicii` VALUES (21,7),(22,7),(23,7),(25,7),(26,7),(22,9),(23,9),(24,9),(24,11),(27,11),(29,17),(30,17),(31,17),(32,17),(30,30),(31,30),(31,32),(33,32);
/*!40000 ALTER TABLE `programari_servicii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raport_analize`
--

DROP TABLE IF EXISTS `raport_analize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `raport_analize` (
  `idRaport` int(11) NOT NULL AUTO_INCREMENT,
  `idAsistent` int(11) NOT NULL,
  `idPacient` int(11) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `rezultat` varchar(45) DEFAULT NULL,
  `idProgramare` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRaport`),
  KEY `pacient_idx` (`idPacient`),
  KEY `asistent_idx` (`idAsistent`),
  KEY `programare_idx` (`idProgramare`),
  CONSTRAINT `asistent` FOREIGN KEY (`idAsistent`) REFERENCES `angajat` (`idAngajat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pacient` FOREIGN KEY (`idPacient`) REFERENCES `pacienti` (`idPacient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `programare` FOREIGN KEY (`idProgramare`) REFERENCES `programari` (`idProgramare`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raport_analize`
--

LOCK TABLES `raport_analize` WRITE;
/*!40000 ALTER TABLE `raport_analize` DISABLE KEYS */;
INSERT INTO `raport_analize` VALUES (15,14,2,'2018-02-21','da',32);
/*!40000 ALTER TABLE `raport_analize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raport_medical`
--

DROP TABLE IF EXISTS `raport_medical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `raport_medical` (
  `idRaport` int(11) NOT NULL,
  `idMedic` int(11) DEFAULT NULL,
  `idPacient` int(11) DEFAULT NULL,
  `idMedicRecomandare` int(11) DEFAULT NULL,
  `idAsistent` int(11) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Istoric` longtext,
  `Simptome` text,
  `Investigatii` text,
  `Diagnostic` int(11) DEFAULT NULL,
  `Recomandari` text,
  `Plata_efectuata` binary(1) DEFAULT NULL,
  PRIMARY KEY (`idRaport`),
  KEY `primaryIndex` (`idRaport`),
  KEY `fk_pacient_idx` (`idPacient`),
  KEY `fk_medic_idx` (`idMedic`),
  KEY `fk_medicAsistent_idx` (`idMedicRecomandare`),
  KEY `fk_diagnostic_idx` (`Diagnostic`),
  CONSTRAINT `fk_diagnostic` FOREIGN KEY (`Diagnostic`) REFERENCES `diagnostice` (`idDiagnostic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medic` FOREIGN KEY (`idMedic`) REFERENCES `medic` (`idMedic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicAsistent` FOREIGN KEY (`idMedicRecomandare`) REFERENCES `medic` (`idMedic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pacient` FOREIGN KEY (`idPacient`) REFERENCES `pacienti` (`idPacient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programare` FOREIGN KEY (`idRaport`) REFERENCES `programari` (`idProgramare`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raport_medical`
--

LOCK TABLES `raport_medical` WRITE;
/*!40000 ALTER TABLE `raport_medical` DISABLE KEYS */;
INSERT INTO `raport_medical` VALUES (24,3,1,NULL,NULL,'1997-01-01','sergiu','are','mere',NULL,'pere',NULL),(25,3,3,NULL,NULL,'2018-01-08','0','0','0',2,'0','0'),(26,3,2,NULL,NULL,'2018-01-10','0','0','0',7,'0',NULL);
/*!40000 ALTER TABLE `raport_medical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receptioner`
--

DROP TABLE IF EXISTS `receptioner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receptioner` (
  `idReceptioner` int(11) NOT NULL,
  PRIMARY KEY (`idReceptioner`),
  CONSTRAINT `receptioner_ibfk_1` FOREIGN KEY (`idReceptioner`) REFERENCES `angajat` (`idAngajat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receptioner`
--

LOCK TABLES `receptioner` WRITE;
/*!40000 ALTER TABLE `receptioner` DISABLE KEYS */;
/*!40000 ALTER TABLE `receptioner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicii`
--

DROP TABLE IF EXISTS `servicii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicii` (
  `idServiciu` int(11) NOT NULL AUTO_INCREMENT,
  `Denumire` varchar(45) DEFAULT NULL,
  `Specialitate` int(11) DEFAULT NULL,
  `Competenta` varchar(50) NOT NULL,
  `Pret` int(11) DEFAULT NULL,
  `Durata` time DEFAULT NULL,
  PRIMARY KEY (`idServiciu`),
  KEY `Specialitate` (`Specialitate`),
  CONSTRAINT `servicii_ibfk_1` FOREIGN KEY (`Specialitate`) REFERENCES `specialitate` (`idSpecialitate`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicii`
--

LOCK TABLES `servicii` WRITE;
/*!40000 ALTER TABLE `servicii` DISABLE KEYS */;
INSERT INTO `servicii` VALUES (7,'RMN',6,'RMN',150,'01:00:00'),(9,'ECG',6,'',150,'01:00:00'),(11,'EEG',6,'EEG',150,'01:00:00'),(12,'Calciu urinar',9,'',12,'00:00:01'),(13,'LTT',9,'',632,'00:00:30'),(16,'Analiza LTT',9,'',60,'00:00:12'),(17,'Bicarbonat',NULL,'asistent medical',60,'04:00:00'),(30,'Calciu ionic - ser',NULL,'asistent medical',12,'01:00:00'),(31,'Calciu seric - ser',NULL,'asistent medical',12,'01:00:00'),(32,'Calciu urinar',NULL,'asistent medical',12,'01:00:00'),(33,'Clor alte produse',NULL,'asistent medical',10,'01:00:00');
/*!40000 ALTER TABLE `servicii` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `policlinici`.`servicii_BEFORE_DELETE` BEFORE DELETE ON `servicii` FOR EACH ROW
BEGIN
delete from programari_servicii where idServiciu=old.idServiciu;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `specialitate`
--

DROP TABLE IF EXISTS `specialitate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialitate` (
  `idSpecialitate` int(11) NOT NULL AUTO_INCREMENT,
  `Specialitate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idSpecialitate`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialitate`
--

LOCK TABLES `specialitate` WRITE;
/*!40000 ALTER TABLE `specialitate` DISABLE KEYS */;
INSERT INTO `specialitate` VALUES (2,'Pediatrie'),(3,'Medicina de familie'),(4,'Boli infectioase'),(5,'Pneumologie'),(6,'Cardiologie'),(7,'Psihiatrie'),(8,'Urologie'),(9,'Analize laborator');
/*!40000 ALTER TABLE `specialitate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `superadministrator`
--

DROP TABLE IF EXISTS `superadministrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `superadministrator` (
  `idSuperAdministrator` int(11) NOT NULL,
  PRIMARY KEY (`idSuperAdministrator`),
  CONSTRAINT `superadministrator_ibfk_1` FOREIGN KEY (`idSuperAdministrator`) REFERENCES `utilizator` (`idUtilizator`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `superadministrator`
--

LOCK TABLES `superadministrator` WRITE;
/*!40000 ALTER TABLE `superadministrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `superadministrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unitati_medicale`
--

DROP TABLE IF EXISTS `unitati_medicale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unitati_medicale` (
  `idUnitateMedicala` int(11) NOT NULL AUTO_INCREMENT,
  `Denumire` varchar(50) DEFAULT NULL,
  `Adresa` varchar(50) DEFAULT NULL,
  `DescriereServicii` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idUnitateMedicala`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unitati_medicale`
--

LOCK TABLES `unitati_medicale` WRITE;
/*!40000 ALTER TABLE `unitati_medicale` DISABLE KEYS */;
INSERT INTO `unitati_medicale` VALUES (1,'Medstar','Al. Garbau','Este frumos!'),(5,'Bluelife','str.Obervator nr.1','E frumos si aici!'),(7,'Medlife','Str.Primaverii nr.25','E foarte frumos!');
/*!40000 ALTER TABLE `unitati_medicale` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `policlinici`.`unitati_medicale_BEFORE_DELETE` BEFORE DELETE ON `unitati_medicale` FOR EACH ROW
BEGIN

set SQL_SAFE_UPDATES = 0;
delete from orar_angajat where Locatie=old.Denumire;
delete from orar where idUnitate=old.idUnitateMedicala;
delete from angajat where idUnitate=old.idUnitateMedicala;
SET FOREIGN_KEY_CHECKS=1;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `utilizator`
--

DROP TABLE IF EXISTS `utilizator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilizator` (
  `idUtilizator` int(11) NOT NULL AUTO_INCREMENT,
  `CNP` bigint(20) DEFAULT NULL,
  `Nume` varchar(50) DEFAULT NULL,
  `Prenume` varchar(50) DEFAULT NULL,
  `Parola` varchar(45) DEFAULT NULL,
  `Adresa` varchar(50) DEFAULT NULL,
  `Telefon` decimal(10,0) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `IBAN` decimal(10,0) DEFAULT NULL,
  `Contract` int(11) DEFAULT NULL,
  `DataAngajarii` date DEFAULT NULL,
  `TipUtilizator` enum('Administrator','Anagajat') DEFAULT NULL,
  `IdUnitate` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUtilizator`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizator`
--

LOCK TABLES `utilizator` WRITE;
/*!40000 ALTER TABLE `utilizator` DISABLE KEYS */;
INSERT INTO `utilizator` VALUES (3,12345,'Pop','Ioana','parola1','Str. Arinilor Baia Mare',263381660,'ioanapop@yahoo.com',1248569875,1,'2016-05-05','Administrator',1),(4,12346,'Popescu','Maria','parola2','Str. Virtutii, Baia Mare',740601859,'mariapopescu@yahoo.com',1248179898,2,'1995-08-12','Administrator',2),(5,12774,'Nume','Prenume','parola3','Str. Visinilor, Bucuresti',745282647,'@gmail.com',3,14775,'2018-01-01','',1),(6,12349,'Dinu','Cornel','parola4','Calea Victoriei, Bucuresti',748971852,'dinu@yahoo.com',1248179811,4,'1998-04-15','',1),(7,12340,'Popa','Ion','parola5','Str. Florilor, Baia Mare',748971853,'popa@yahoo.com',1248179812,5,'2013-09-12','',1),(8,12341,'Florescu','Mihaela','parola6','Str. Principala, Bistrita',748971854,'florescu@yaho.com',1248179813,6,'2005-06-13','',2),(9,12342,'Salvan','Diana','parola7','Str. 1 Decembire, Cluj',748971855,'salvan@yaho.com',1248179814,7,'1989-08-17','',2),(10,12343,'Tudorescu','Florina','parola8','Str. Primaverii, Timisoara',748971856,'tudorescu@yaho.com',1248179815,8,'2016-11-23','',2),(11,12330,'Banica','Andreea','parola9','Str. Decebal, Bistrita',748971857,'banica@yaho.com',1248179816,9,'2012-06-16','Administrator',3),(12,12331,'Timoce','Victoria','parola10','Str. Dorobantilor, Cluj',748971858,'timoce@yaho.com',1248179817,10,'1968-03-25','',3),(13,12332,'Lazar','Gina','parola11','Str. M. Eminescu, Timisoara',748971860,'lazar@yaho.com',1248179818,11,'2002-12-14','',3),(14,12333,'Muresan','Daniel','parola12','Str. Bistritei, Cluj',748971861,'muresan@yaho.com',1248179819,12,'2012-08-24','',3),(15,12334,'Ilovan','Tudor','parola13','Str. Manastur,Cluj',748971862,'ilovan@yaho.com',1248179820,13,'1970-10-13','',1),(16,12335,'Stancu','Alina','parola14','Str. Fericirii, Bucuresti',748971863,'stancu@yaho.com',1248179821,14,'2000-03-28','',2),(17,123454555,'BIcl','Serer','parola','sadsa',999,'dsada',0,1,'1111-11-11','Administrator',NULL),(18,12359,'Bilc','Sergiu','parola','Str. Ciobanului nr. 20',745282647,'segib@gmail.com',0,334,'1111-11-11','Administrator',NULL),(19,1,'a','a','a','a',1,'a',0,2232,'1111-11-11','Anagajat',NULL),(20,1323,'q','q','q','q',2,'q',0,23,'1111-11-11','Anagajat',NULL),(21,23131,'a','a','a','a',1,'a',0,321321,'1111-11-11','Anagajat',NULL),(22,123131,'a','a','a','a',1,'a',0,321,'1111-11-11','Anagajat',NULL),(23,4324131,'da','d','ads','d',1,'dd',0,32,'1111-11-11','Anagajat',NULL),(24,123333,'a','a','a','a',1,'a`',0,23,'1111-11-11','Anagajat',NULL),(25,3231414,'a','a','a','a',99,'a',0,3,'1111-11-11','Anagajat',NULL),(26,1122131,'a','a','a','a',1,'a',0,321,'1111-11-11','Anagajat',NULL),(27,13232,'Neag','Elena','parolaas','Bucuresti',745282647,'e.marianeag@gmail.com',0,123412,'2018-01-05','Anagajat',NULL),(28,13333,'Chifor','Margareta','parola333','Primaverii',747474747,'@gmail.com',0,6634,'2017-02-09','Anagajat',NULL),(29,12399,'Bilc','Mihaela','parola99','Garbau',745282647,'Dada',0,2345,'2018-01-01','Anagajat',NULL),(30,14356,'Elisabeta','Lipa','parola43','Bucuresti',74,'@gmail.com',0,15453,'1111-11-11','Anagajat',NULL),(31,21222,'Alina','Popa','parola222','Al. Garbau',74528267,'@gmail.com',0,22222,'2018-11-11','Anagajat',NULL),(32,13456,'b','b','paroal111','b',0,'0',0,333,'2018-01-03','Anagajat',NULL),(33,123433,'Boghian','Ciprian','parola332','bucegi',745282647,'gmail',0,123477,'2018-01-09','Anagajat',NULL),(34,22222,'vfsd','cdfd','parola','scsd',455664,'sdsfsfs',0,454654,'2018-12-01','Anagajat',NULL),(35,33333,'Bghian','Cipri','parola','Str Clabucet',74056824,'cipri@yahoo.com',0,156552354,'2017-10-15','Anagajat',NULL);
/*!40000 ALTER TABLE `utilizator` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `policlinici`.`utilizator_BEFORE_DELETE` BEFORE DELETE ON `utilizator` FOR EACH ROW
BEGIN
SET FOREIGN_KEY_CHECKS=0;
delete from medic where idMedic=old.idUtilizator;
delete from angajat where idAngajat=old.idUtilizator;
SET FOREIGN_KEY_CHECKS=1;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'policlinici'
--
/*!50003 DROP FUNCTION IF EXISTS `calculare_angajat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calculare_angajat`(_idAngajat integer,indexLuna integer) RETURNS int(11)
BEGIN


set @_concediuInceput=null;
set @_concediuFinal=null;
set @_lunaInceput=null;
set @_lunaFinal=null;
set @_salar= null;
set @_lunaSalar = null;

if((month(current_Date)-indexLuna) <= 0) then
	set @_lunaSalar= 12 + (month(current_Date)-indexLuna);
else
	set @_lunaSalar= (month(current_Date)-indexLuna);
end if;

select data_inceput into @_concediuInceput
from concediu 
where _idAngajat=idAngajat and month(data_inceput)=@_lunaSalar;

select data_final into @_concediuFinal
from concediu 
where _idAngajat=idAngajat and month(data_final)=@_lunaSalar;


if(@_concediuInceput is not null ) then
	set @_lunaInceput=month(@_concediuInceput);
    set @_ziInceput=day(@_concediuInceput);
    set @_lunaFinal=0;
end if;
if(@_concediuFinal is not null ) then
	set @_lunaFinal=month(@_concediuFinal);
    if(@_concediuInceput is  null) then
    set @_lunaInceput=0;
    end if;
end if;

if(@_concediuInceput is not null or @_concediuFinal is not null) then


	if(@_lunaInceput != @_lunaFinal) then

    
		if(@_lunaInceput = @_lunaSalar) then
			set @_ziConcediu1 = (select day(last_day(@_concediuInceput))) - @_ziInceput;
            select  (angajat.Salariu - (angajat.Salariu/substring(last_day(@_concediuInceput),-2)*@_ziConcediu1))
            into @_salar
            from angajat,programari where idAngajat=_idAngajat and month(programari.DataProgramare)=@_lunaSalar
            limit 1;
        else
			if(@_lunaFinal = @_lunaSalar) then
				set @_ziConcediu2=substring(day(@_concediuFinal),-2);
				select  (angajat.Salariu - (angajat.Salariu/substring(last_day(@_concediuFinal),-2)*@_ziConcediu2))
				into @_salar
                from angajat,programari where idAngajat=_idAngajat and month(programari.DataProgramare)=@_lunaSalar
                limit 1;
              
			end if;
		
		end if;
	else
    
		(select (angajat.Salariu - (day(@_concediuFinal) - day(@_concediuInceput)+1)*(angajat.Salariu/substring( last_day(@_concediuFinal),-2)))
         into @_salar 
        from angajat,programari where idAngajat=_idAngajat and month(programari.DataProgramare)=@_lunaSalar)
        limit 1;
       
	end if;
    else
		select angajat.Salariu into @_salar from angajat where angajat.idAngajat=_idAngajat;
end if;

RETURN @_salar;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `calculare_medic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calculare_medic`(_idAngajat integer,indexLuna integer) RETURNS int(11)
BEGIN


if((month(current_Date)-indexLuna) <= 0) then
	set @_lunaSalar= 12 + (month(current_Date)-indexLuna);
else
	set @_lunaSalar= (month(current_Date)-indexLuna);
end if;

select (select policlinici.calculare_angajat(_idAngajat,indexLuna)) into @_salar1;


select (sum(servicii.pret)*medic.Procent)/100 as salariu 
into @_salariu
from programari,programari_servicii,servicii, raport_medical,angajat,medic where 
programari.idProgramare = programari_servicii.idProgramare and
 programari_servicii.idServiciu = servicii.idServiciu and raport_medical.idRaport is not null 
 and angajat.idAngajat=programari.idMedic and programari.idMedic=medic.idMedic
 and month(programari.DataProgramare)=@_lunaSalar
 and idAngajat=_idAngajat ;
 
 if(@_salariu is not null) then
 set @_salariu=@_salariu + @_salar1;
RETURN @_salariu;
else
Return @_salar1;
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `profit_medic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `profit_medic`(_idAngajat integer) RETURNS int(11)
BEGIN


select sum(servicii.pret) as salariu 
into @_venituri
from programari,programari_servicii,servicii, raport_medical,angajat,medic where 
programari.idProgramare = programari_servicii.idProgramare and
 programari_servicii.idServiciu = servicii.idServiciu and raport_medical.idRaport is not null 
 and angajat.idAngajat=programari.idMedic and programari.idMedic=medic.idMedic
 and idAngajat=_idAngajat ;
 
SELECT TIMESTAMPDIFF(MONTH, DataAngajarii, current_date)
into @_luni from utilizator 
where idUtilizator=_idAngajat;
 
select @_luni*angajat.Salariu + sum(servicii.pret)*(medic.Procent/100) as salariu
into @_cheltuieli 
from programari,programari_servicii,servicii, raport_medical,angajat,medic where 
programari.idProgramare = programari_servicii.idProgramare and
 programari_servicii.idServiciu = servicii.idServiciu and raport_medical.idRaport is not null 
 and angajat.idAngajat=programari.idMedic and programari.idMedic=medic.idMedic
 and idAngajat=_idAngajat ;

set @_profit := @_venituri-@_cheltuieli;
RETURN @_profit;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_angajat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_angajat`(_id int(11),
 _salar int(11), _ore int(11),
 _functie varchar(50), _dep int(11), _unitate varchar(50)
 /*
loc_1 varchar(50),
loc_2 varchar(50),loc_3 varchar(50),
loc_4 varchar(50),loc_5 varchar(50),
loc_6 varchar(50),
luni_d time,luni_i time,marti_d time,marti_i time,
miercuri_d time,miercuri_i time, joi_d time,
joi_i time, vineri_d time, vineri_i time,
 sambata_d time, sambata_i time*/
 )
BEGIN 


set @_id = null;
set @_departament=null;
set @_unitate=null;
select @_id := idUtilizator from utilizator where idUtilizator = _id;
select @_departament := idDepartament from departament where idDepartament = _dep;
select @_unitate := idUnitateMedicala from unitati_medicale where Denumire=_unitate;

if(@_id is not null) then
	if((_dep = 3 and _functie in ('Medic','Asistent medical')) or
    (_dep = 2 and _functie= 'contabil') or
    (_dep = 1 and _functie in ('receptioner','inspector resurse umane') )) then
		
        insert into angajat values(_id, _salar, _ore, _functie, _dep, @_unitate);
		
        /*insert into orar_angajat values(@_id,'Luni',loc_1,luni_d,luni_i);
		insert into orar_angajat values(@_id,'Marti',loc_2,marti_d,marti_i);
		insert into orar_angajat values(@_id,'Miercuri',loc_3,miercuri_d,miercuri_i);
		insert into orar_angajat values(@_id,'Joi',loc_4,joi_d,joi_i);
		insert into orar_angajat values(@_id,'Vineri',loc_5,vineri_d,vineri_i);
		insert into orar_angajat values(@_id,'Sambata',loc_6,sambata_d,sambata_i);
		*/
    end if;
end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_asistent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_asistent`(
_id int(11), _tip varchar(50), _grad varchar(50))
BEGIN

set @_id = null;
select @_id := idAngajat from angajat where idAngajat = _id and Functie='Asistent medical' ;

if(@_id is not null) then
	insert into asistentmedical values(@_id,_tip,_grad);
end if;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_concediu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_concediu`(
nume varchar(50),prenume varchar(50),
functie varchar(50),_data_inceput date,
_data_final date)
BEGIN

set @_idAngajat=null;
select @_idAngajat := idUtilizator
from utilizator,angajat
where utilizator.Nume=nume and
 utilizator.Prenume=prenume and angajat.Functie=functie 
 and angajat.idAngajat=utilizator.idUtilizator;

if(@_idAngajat is not null) then

insert into concediu(idAngajat,data_inceput,data_final,durata_concediu) values
(@_idAngajat,_data_inceput,_data_final,((SELECT DATEDIFF(_data_final,_data_inceput) AS days)+1));
end if;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_medic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_medic`(
_id int(11),_spec1 varchar(50),_spec2 varchar(50), _grad varchar(50),
_Parafa int(11),_titlu varchar(50),_postDidactic varchar(50),_procent int,
_Comp1 varchar(50),_Comp2 varchar(50),_Comp3 varchar(50))
BEGIN

set @_id = null;
set @_spec1=null;
set @_spec2=null;
set @_comp1 = null; 
set @_comp2 = null; 
set @_comp3 = null; 
set @_comp4 = null; 
set @_comp5 = null; 

select @_id := idAngajat from angajat where idAngajat = _id and Functie='Medic' ;
select @_spec1 := idSpecialitate from specialitate where _spec1=Specialitate;
select @_spec2 := idSpecialitate from specialitate where _spec2=Specialitate;
select @_comp1 := idCompetenta from competente where _Comp1=Denumire;
select @_comp2 := idCompetenta from competente where _Comp2=Denumire;
select @_comp3 := idCompetenta from competente where _Comp3=Denumire;


if(@_id is not null) then
	insert into medic values(_id,_grad,_Parafa,_titlu,
    _postDidactic,_procent);
    if(@_spec1 is not null) then
		insert into medici_specialitati values(_id,@_spec1);
    end if;
    if(@_spec2 is not null) then
		insert into medici_specialitati values(_id,@_spec2);
    end if;
    if(@_comp1 is not null) then
		insert into medici_competente values(_id,@_comp1);
    end if;
    if(@_comp2 is not null) then
		insert into medici_competente values(_id,@_comp2);
    end if;
    if(@_comp3 is not null) then
		insert into medici_competente values(_id,@_comp3);
	end if;
end if;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_orar_angajat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_orar_angajat`(
_id int,loc_1 varchar(50),
loc_2 varchar(50),loc_3 varchar(50),
loc_4 varchar(50),loc_5 varchar(50),
loc_6 varchar(50),
luni_d time,luni_i time,marti_d time,marti_i time,
miercuri_d time,miercuri_i time, joi_d time,
joi_i time, vineri_d time, vineri_i time,
 sambata_d time, sambata_i time)
BEGIN

set @_id = null;
select @_id := idUtilizator from utilizator where idUtilizator=_id;

if(@_id is not null) then
	insert into orar_angajat values(@_id,'Luni',loc_1,luni_d,luni_i);
    insert into orar_angajat values(@_id,'Marti',loc_2,marti_d,marti_i);
    insert into orar_angajat values(@_id,'Miercuri',loc_3,miercuri_d,miercuri_i);
    insert into orar_angajat values(@_id,'Joi',loc_4,joi_d,joi_i);
    insert into orar_angajat values(@_id,'Vineri',loc_5,vineri_d,vineri_i);
    insert into orar_angajat values(@_id,'Sambata',loc_6,sambata_d,sambata_i);
end if; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_orar_specific` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_orar_specific`(
nume varchar(50),prenume varchar(50),functie varchar(50),
_data date,_locatie varchar(50),_ora_inceput varchar(50),_ora_final varchar(50)
)
BEGIN

set @_idAngajat=null;
select @_idAngajat := idUtilizator
from utilizator,angajat
where utilizator.Nume=nume and
 utilizator.Prenume=prenume and angajat.Functie=functie 
 and angajat.idAngajat=utilizator.idUtilizator;
 
 
 if(@_idAngajat is not null) then

insert into orar_specific values(@_idAngajat,_data,_locatie,_ora_inceput,_ora_final);

end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_pacient` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_pacient`(
_data date,_Nume varchar(50),_Prenume varchar(50),
_data_nasterii date,_adresa varchar(50),
_telefon varchar(10), _CNP bigint(20)
)
BEGIN

	set @_id=null;
	select @_id:=idPacient from pacienti where _CNP=CNP;
    
    if(@_id is null) then
		insert into pacienti(DataFisa,Nume,Prenume,DataNasterii,
        Adresa,Telefon,CNP) 
        values
        (_data,_Nume,_Prenume,_data_nasterii,_adresa,_telefon,_CNP);
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_programare` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_programare`(_CNP bigint(20),
_CNPMedic int,_serviciu1 varchar(50),_serviciu2 varchar(50),_serviciu3 varchar(50),
_data date,_ora time)
BEGIN

set @_idServ=null;
set @_idPacient=null;
set @_idMedic=null;
select @_idMedic := idUtilizator from utilizator where CNP=_CNPMedic;
select @_idPacient:=idPacient from pacienti where CNP=_CNP;
select @_idServ1:=idServiciu from servicii where _serviciu1=Denumire;
select @_idServ2:=idServiciu from servicii where _serviciu2=Denumire;
select @_idServ3:=idServiciu from servicii where _serviciu3=Denumire;

if(@_idPacient is not null ) then

insert into programari(idPacient,idMedic,DataProgramare,OraProgramare)
    values (@_idPacient,@_idMedic,_data,_ora);
    
select @_idProgramare:=idProgramare from programari
 where @_idPacient=idPacient and _data=DataProgramare;
 
insert into programari_servicii values (@_idProgramare,@_idServ1);
	if(@_idServ2 is not null) then
		insert into programari_servicii values (@_idProgramare,@_idServ2);
    end if;
    if(@_idServ3 is not null) then
		insert into programari_servicii values (@_idProgramare,@_idServ3);
    end if;
end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_raport` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_raport`(_idProgramare int, _idMedic int,_idPacient int,_idMedicRec int,
_idAsistent int,_Data date,_Istoric text,_Simptome text,_Investigatii text,_Diagnostic varchar(50),
_recomandari text)
BEGIN

set @_idProgramare=null;
select @_idProgramare:=idProgramare from programari
 where _idProgramare=idProgramare;
set @_idDiagnostic=null;
select @_idDiagnostic:=idDiagnostic from diagnostice
 where _Diagnostic=Diagnostic;
 
if(@_idDiagnostic is null) then
insert into diagnostice(Diagnostic) values(_Diagnostic);
end if;

if(@_idProgramare is not null) then
insert into raport_medical values
(_idProgramare,_idMedic,_idPacient,_idMedicRec,_idAsistent,
_Data,_Istoric,_Simptome,_Investigatii,@_idDiagnostic,_recomandari,0);
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_serviciu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_serviciu`( _Denumire varchar(50), 
_specialitate varchar(50),
_competenta varchar(50),
_pret int,
_durata time)
BEGIN

	set @_idSpecialitate=null;
    
	select @_idSpecialitate := idSpecialitate from specialitate where Specialitate=_specialitate;
    
    if(@_idSpecialitate is not null) then
		insert into servicii (Denumire,Specialitate,Competenta,Pret,Durata)
        values (_Denumire,@_idSpecialitate,_competenta,_pret,_durata);
    end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_unitate_medicala` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_unitate_medicala`(Nume varchar(50),_Adresa varchar(50),Descriere varchar(50),
luni_d time,luni_i time,marti_d time,marti_i time,miercuri_d time, miercuri_i time,
joi_d time, joi_i time, vineri_d time,vineri_i time, sambata_d time,sambata_i time)
BEGIN

	insert into unitati_medicale(Denumire,Adresa,DescriereServicii) 
		values(Nume,_Adresa,Descriere);
	
    SET @_id = NULL;
  	SELECT @_id := idUnitateMedicala FROM unitati_medicale WHERE Denumire = Nume;
    
    insert into orar values(@_id,'Unitate medicala',luni_d,marti_d,marti_i,
    miercuri_d,miercuri_i,joi_d,joi_i,vineri_d,vineri_i,sambata_d,sambata_d); 
	
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `adaugare_utilizator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_utilizator`(_CNP BIGINT, _Nume varchar(50), _Prenume varchar(50),
 _Parola varchar(50), _Adresa varchar(50),_Telefon decimal(10,0),_Email varchar(50),
_IBAN decimal(10,0),_Contract int(11),_Data date,_Tip varchar(50),Nume_unitate varchar(50))
BEGIN

set @_id=null;
select @_id := idUnitateMedicala from unitati_medicale where Denumire=Nume_unitate;

insert into utilizator 
(CNP,Nume,Prenume,Parola,Adresa,Telefon,Email,IBAN,Contract,
DataAngajarii,TipUtilizator, IdUnitate)
values
(_CNP,_Nume,_Prenume,_Parola,_Adresa,_Telefon,_Email,_IBAN,_Contract,_Data,_Tip,@_id);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `aduagare_orar_angajat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `aduagare_orar_angajat`(
_id int,loc_1 varchar(50),
loc_2 varchar(50),loc_3 varchar(50),
loc_4 varchar(50),loc_5 varchar(50),
loc_6 varchar(50),
luni_d time,luni_i time,marti_d time,marti_i time,
miercuri_d time,miercuri_i time, joi_d time,
joi_i time, vineri_d time, vineri_i time,
 sambata_d time, sambata_i time)
BEGIN

set @_id = null;
select @_id := idUtilizator from utilizator where idUtilizator=_id;

if(@_id is not null) then
	insert into orar_angajat values(@_id,'Luni',loc_1,luni_d,luni_i);
    insert into orar_angajat values(@_id,'Marti',loc_2,marti_d,marti_i);
    insert into orar_angajat values(@_id,'Miercuri',loc_3,miercuri_d,miercuri_i);
    insert into orar_angajat values(@_id,'Joi',loc_4,joi_d,joi_i);
    insert into orar_angajat values(@_id,'Vineri',loc_5,vineri_d,vineri_i);
    insert into orar_angajat values(@_id,'Sambata',loc_6,sambata_d,sambata_i);
end if; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `date_angajat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `date_angajat`(ID int)
BEGIN

select CNP,Nume,Prenume,Adresa,Telefon,IBAN,DataAngajarii,Contract,angajat.functie,Email
from utilizator,angajat where utilizator.idUtilizator=ID and idUtilizator=idAngajat ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `gasire_concediu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `gasire_concediu`(nume varchar(50),prenume varchar(50),functie varchar(50))
BEGIN

SELECT data_inceput,data_final,durata from concediu,angajat,utilizator
WHere utilizator.Nume=nume and utilizator.Prenume=prenume
 and angajat.Functie=functie and angajat.idAngajat=utilizator.idUtilizator
and angajat.idAngajat=concediu.idAngajat;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `gasire_orar_angajat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `gasire_orar_angajat`(nume varchar(50),prenume varchar(50),functie varchar(50),zi_orar varchar(20))
BEGIN

select Ora_inceput,Ora_inchidere,Locatie
from orar_angajat,utilizator,angajat
where utilizator.Nume=nume and
 utilizator.Prenume=prenume and angajat.Functie=functie 
 and angajat.idAngajat=utilizator.idUtilizator and orar_angajat.idAngajat=angajat.idAngajat
 and orar_angajat.Nume_Zi=zi_orar;



END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `orar_specific` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `orar_specific`(
nume varchar(50),prenume varchar(50),functie varchar(50))
BEGIN


select data_specifica,Locatie,Ora_inceput,Ora_inchidere
from utilizator,angajat,orar_specific
where utilizator.Nume=nume and
utilizator.Prenume=prenume and angajat.Functie=functie 
and angajat.idAngajat=utilizator.idUtilizator 
and orar_specific.idAngajat=angajat.idAngajat;



END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `profit_specialitate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `profit_specialitate`()
BEGIN


select sum(servicii.pret),specialitate.Specialitate as salariu 
from programari,programari_servicii,servicii, raport_medical,angajat,medic,specialitate,medici_specialitati where 
programari.idProgramare = programari_servicii.idProgramare and
 programari_servicii.idServiciu = servicii.idServiciu and raport_medical.idRaport is not null 
 and angajat.idAngajat=programari.idMedic and programari.idMedic=medic.idMedic and
 medic.idMedic=medici_specialitati.idMedic and medici_specialitati.idSpecialitate=specialitate.idSpecialitate
 group by specialitate.idSpecialitate;
 
 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `raport_analize` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `raport_analize`(_idProgramare int, 
_idAsistent int,
_idPacient int,
_Data date,
_Rezultat1 varchar(50),
_rezultat2 varchar(50),
_rezultat3 varchar(50))
BEGIN

set @_idProgramare=null;
select @_idProgramare:=idProgramare from programari
 where _idProgramare=idProgramare;


if(@_idProgramare is not null) then
insert into raport_analize(idProgramare,idAsistent,idPacient,
data,rezultat) values(@_idProgramare,_idAsistent,_idPacient,_Data,_Rezultat1);
if(_rezultat2 is not null) then
insert into raport_analize(idProgramare,idAsistent,idPacient,
data,rezultat) values(@_idProgramare,_idAsistent,_idPacient,_Data,_Rezultat2);
end if;
if(_rezultat3 is not null) then
insert into raport_analize(idProgramare,idAsistent,idPacient,
data,rezultat) values(@_idProgramare,_idAsistent,_idPacient,_Data,_Rezultat3);
end if;
end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `vizualizare_orar_angajat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `vizualizare_orar_angajat`()
BEGIN


select utilizator.Nume,utilizator.Prenume,(select Locatie FROM orar_angajat where Nume_Zi='luni'
and orar_angajat.idAngajat=3) as 'Locatie luni',
(select Ora_inceput FROM orar_angajat where Nume_Zi='luni'
and orar_angajat.idAngajat=3) as 'Inceput Luni',
(select Ora_inchidere FROM orar_angajat where Nume_Zi='luni'
and orar_angajat.idAngajat=3) as 'Final luni',

(select Locatie FROM orar_angajat where Nume_Zi='marti'
and orar_angajat.idAngajat=3) as 'Locatie marti',
(select Ora_inceput FROM orar_angajat where Nume_Zi='marti'
and orar_angajat.idAngajat=3) as 'Inceput marti',
(select Ora_inchidere FROM orar_angajat where Nume_Zi='marti'
and orar_angajat.idAngajat=3) as 'Final marti',

(select Locatie FROM orar_angajat where Nume_Zi='miercuri'
and orar_angajat.idAngajat=3) as 'Locatie miercuri',
(select Ora_inceput FROM orar_angajat where Nume_Zi='miercuri'
and orar_angajat.idAngajat=3) as 'Inceput mniercuri',
(select Ora_inchidere FROM orar_angajat where Nume_Zi='miercuri'
and orar_angajat.idAngajat=3) as 'Final miercuri',

(select Locatie FROM orar_angajat where Nume_Zi='joi'
and orar_angajat.idAngajat=3) as 'Locatie joi',
(select Ora_inceput FROM orar_angajat where Nume_Zi='joi'
and orar_angajat.idAngajat=3) as 'Inceput joi',
(select Ora_inchidere FROM orar_angajat where Nume_Zi='joi'
and orar_angajat.idAngajat=3) as 'Final joi',

(select Locatie FROM orar_angajat where Nume_Zi='vineri'
and orar_angajat.idAngajat=3) as 'Locatie vineri',
(select Ora_inceput FROM orar_angajat where Nume_Zi='vineri'
and orar_angajat.idAngajat=3) as 'Inceput vineri',
(select Ora_inchidere FROM orar_angajat where Nume_Zi='vineri'
and orar_angajat.idAngajat=3) as 'Final vineri'

from utilizator;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-06 11:29:16
