-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pharmacy
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `data_nascimento` date NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `morada` varchar(255) DEFAULT NULL,
  `naturalidade` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'','2019-01-04',NULL,NULL,NULL,'Sheridan Oliveira','MASCULINO',NULL),(2,'','2019-01-02','zaina@gmail.com','av.coop','Mozambique','Zainadine Abdul','MASCULINO','825004957'),(3,'','2019-01-04','zainadineduarte@gmail.com','24 de julho nr 2317','mocambicana','zainadine','MASCULINO','843805781'),(4,'','2019-01-06',NULL,NULL,NULL,'Sheridan Oliveira','MASCULINO',NULL),(5,'','2019-01-06',NULL,NULL,NULL,'Sheridan Oliveira','MASCULINO',NULL),(6,'','2019-01-06',NULL,NULL,NULL,'Sheridan Oliveira','MASCULINO',NULL),(7,'','2019-01-06',NULL,NULL,NULL,'Sheridan Oliveira','MASCULINO',NULL),(8,'','2019-01-06',NULL,NULL,NULL,'Sheridan Oliveira','MASCULINO',NULL),(9,'','2019-01-06',NULL,NULL,NULL,'Sheridan Oliveira','MASCULINO',NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_movimento`
--

DROP TABLE IF EXISTS `cliente_movimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente_movimento` (
  `Cliente_id` bigint(20) NOT NULL,
  `compras_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_hku1naajwdmu6etxogqlo5lj3` (`compras_id`),
  KEY `FKle3xu32w6vueiws1kefsjaflu` (`Cliente_id`),
  CONSTRAINT `FKh6h6j0i4ithnhk98dws750i57` FOREIGN KEY (`compras_id`) REFERENCES `movimento` (`id`),
  CONSTRAINT `FKle3xu32w6vueiws1kefsjaflu` FOREIGN KEY (`Cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_movimento`
--

LOCK TABLES `cliente_movimento` WRITE;
/*!40000 ALTER TABLE `cliente_movimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente_movimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'',NULL,NULL,'Medimoc',NULL),(2,'',NULL,NULL,'Sheridan',NULL);
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor_medicamento`
--

DROP TABLE IF EXISTS `fornecedor_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor_medicamento` (
  `Fornecedor_id` bigint(20) NOT NULL,
  `medicamentos_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_60c30r7hpadn112xdlt54bs9s` (`medicamentos_id`),
  KEY `FKt0fyvvt9ly44cr5kg69kusydf` (`Fornecedor_id`),
  CONSTRAINT `FKq01edw4k4nvu6v0iire9we7d1` FOREIGN KEY (`medicamentos_id`) REFERENCES `medicamento` (`id`),
  CONSTRAINT `FKt0fyvvt9ly44cr5kg69kusydf` FOREIGN KEY (`Fornecedor_id`) REFERENCES `fornecedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor_medicamento`
--

LOCK TABLES `fornecedor_medicamento` WRITE;
/*!40000 ALTER TABLE `fornecedor_medicamento` DISABLE KEYS */;
INSERT INTO `fornecedor_medicamento` VALUES (2,4);
/*!40000 ALTER TABLE `fornecedor_medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `fabricante` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `precoUnitario` double NOT NULL,
  `quantidadeStock` int(11) NOT NULL,
  `prazo` date NOT NULL,
  `fornecedor_id` bigint(20) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `paisOrigem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaymxp1gqkv4nugdm8g2ggeqnq` (`fornecedor_id`),
  CONSTRAINT `FKaymxp1gqkv4nugdm8g2ggeqnq` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamento`
--

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` VALUES (1,'','fabricante 1','Paracetamol',250,28,'2019-01-01',1,'111',NULL),(2,'','fabricante 1','Paracetamol',500,12,'2019-01-01',1,'111',NULL),(3,'\0','fabricante 1','xarope',100,0,'2019-01-01',1,'111',NULL),(4,'','Medimoc','Bomba de Asma',2000,10,'2019-01-25',2,'111',NULL);
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimento`
--

DROP TABLE IF EXISTS `movimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) NOT NULL,
  `registador_id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `dataRealizacao` date NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKplmg0c07l40c7fvktjhgxf5s5` (`registador_id`),
  KEY `FKqcbufm56ymwnyiqv97b24qra2` (`cliente_id`),
  CONSTRAINT `FKplmg0c07l40c7fvktjhgxf5s5` FOREIGN KEY (`registador_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqcbufm56ymwnyiqv97b24qra2` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimento`
--

LOCK TABLES `movimento` WRITE;
/*!40000 ALTER TABLE `movimento` DISABLE KEYS */;
INSERT INTO `movimento` VALUES (1,'ENTRADA',2,'','2019-01-15',NULL),(2,'SAIDA',2,'','2019-01-15',NULL),(3,'ENTRADA',2,'','2019-01-15',NULL),(4,'SAIDA',1,'','2019-01-15',1),(5,'SAIDA',1,'','2019-01-15',1),(6,'ENTRADA',1,'','2019-01-25',1),(8,'ENTRADA',1,'','2019-01-30',NULL),(9,'ENTRADA',1,'','2019-01-30',NULL),(10,'SAIDA',1,'','2019-01-30',NULL);
/*!40000 ALTER TABLE `movimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimento_medicamento`
--

DROP TABLE IF EXISTS `movimento_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimento_medicamento` (
  `movimentos_id` bigint(20) NOT NULL,
  `medicamentos_id` bigint(20) NOT NULL,
  KEY `FK2eqkjf17uo17hpcvhn9ti3por` (`medicamentos_id`),
  KEY `FK7f9s5brcsy68dnw799x7v347o` (`movimentos_id`),
  CONSTRAINT `FK2eqkjf17uo17hpcvhn9ti3por` FOREIGN KEY (`medicamentos_id`) REFERENCES `medicamento` (`id`),
  CONSTRAINT `FK7f9s5brcsy68dnw799x7v347o` FOREIGN KEY (`movimentos_id`) REFERENCES `movimento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimento_medicamento`
--

LOCK TABLES `movimento_medicamento` WRITE;
/*!40000 ALTER TABLE `movimento_medicamento` DISABLE KEYS */;
INSERT INTO `movimento_medicamento` VALUES (6,3),(8,4),(9,4),(10,4);
/*!40000 ALTER TABLE `movimento_medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `profilename` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e3cxevuy0jnk2w71lidiqdxcb` (`profilename`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'','Administrador informatico'),(4,'','Director Geral'),(5,'','Caixa');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessionhelper`
--

DROP TABLE IF EXISTS `sessionhelper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessionhelper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `selectedUser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_68m9w1biqldy0wwsllc1r2sbc` (`selectedUser`),
  CONSTRAINT `FK6g74m8cd6507kpm2k9951p4u3` FOREIGN KEY (`selectedUser`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessionhelper`
--

LOCK TABLES `sessionhelper` WRITE;
/*!40000 ALTER TABLE `sessionhelper` DISABLE KEYS */;
INSERT INTO `sessionhelper` VALUES (1,1);
/*!40000 ALTER TABLE `sessionhelper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccao`
--

DROP TABLE IF EXISTS `transaccao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaccao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `code` bigint(20) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_if174yhk7av9f4t03aigb96ry` (`code`),
  UNIQUE KEY `UK_h7nxqj3ic3xbt073q9p9fkxv0` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccao`
--

LOCK TABLES `transaccao` WRITE;
/*!40000 ALTER TABLE `transaccao` DISABLE KEYS */;
INSERT INTO `transaccao` VALUES (1,'',203,'/application/forms/Add-Movimento.fxml'),(2,'',202,'/application/forms/Add-Medicamento.fxml'),(3,'',204,'/application/forms/Modify-Medicamento.fxml'),(4,'',205,'/application/forms/Venda.fxml'),(5,'',301,'/application/views/View-Medicamentos.fxml'),(6,'',206,'/application/forms/Add-Client.fxml'),(7,'',306,'/application/views/View-Client.fxml'),(8,'',302,'/application/views/View-Movimentos.fxml');
/*!40000 ALTER TABLE `transaccao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccao_profile`
--

DROP TABLE IF EXISTS `transaccao_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaccao_profile` (
  `transaccoes_id` bigint(20) NOT NULL,
  `profiles_id` bigint(20) NOT NULL,
  KEY `FKfq5qtlr9ail1xl2vpcmwpenk8` (`profiles_id`),
  KEY `FK80i1x6kjr10ix1v81w2rhwy73` (`transaccoes_id`),
  CONSTRAINT `FK80i1x6kjr10ix1v81w2rhwy73` FOREIGN KEY (`transaccoes_id`) REFERENCES `transaccao` (`id`),
  CONSTRAINT `FKfq5qtlr9ail1xl2vpcmwpenk8` FOREIGN KEY (`profiles_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccao_profile`
--

LOCK TABLES `transaccao_profile` WRITE;
/*!40000 ALTER TABLE `transaccao_profile` DISABLE KEYS */;
INSERT INTO `transaccao_profile` VALUES (2,1),(2,1),(2,4),(1,1),(1,4),(3,1),(3,4),(4,1),(4,5),(4,4),(6,1),(6,4),(5,1),(5,4),(8,1),(8,4),(7,1),(7,1),(7,4);
/*!40000 ALTER TABLE `transaccao_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `profile_id` bigint(20) NOT NULL,
  `sessionHelper_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKhuydpjm1myork5g111lxmst2t` (`profile_id`),
  KEY `FKjr78f7e1wrjojw1fn6peliyjn` (`sessionHelper_id`),
  CONSTRAINT `FKhuydpjm1myork5g111lxmst2t` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`),
  CONSTRAINT `FKjr78f7e1wrjojw1fn6peliyjn` FOREIGN KEY (`sessionHelper_id`) REFERENCES `sessionhelper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'','Lazaro Mathe Junior','b02+rIk5vO17Xyza0Adkdw==:lFdtaKppOTkwjCxxCqFTdg==','+258825004957','lazaro',1,NULL),(2,'','Zainadine Duarte Mangue Abdulcadre','UwHH3tStXmOM6YWA1/ddvQ==:BM1kcdu7AIyJkF2TbFysLg==','+258843805781','zainadine',1,NULL),(7,'','Sheridan','Wpi5o4FfphlSpQa1RWr7cA==:ER5kYcTYqV9ahQUor/skjA==','+258','Sheridan',4,NULL),(8,'','caixa','TpvWIcoHmkSp5WwrTip8EA==:1s9W1ifmKaQbfQt/usx9iw==','+258','caixa',5,NULL);
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

-- Dump completed on 2019-01-30 12:24:48
