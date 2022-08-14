-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: pulaupari
-- ------------------------------------------------------
-- Server version	5.5.5-10.6.8-MariaDB

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `user` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` enum('Pemilik','Admin','Operator','Bagian Keuangan') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Andre','1','1','Admin'),(2,'Wawan','2','2','Pemilik'),(3,'Dano','3','3','Operator'),(4,'Lidya','4','4','Bagian Keuangan');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paket_wisata`
--

DROP TABLE IF EXISTS `paket_wisata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paket_wisata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_wisata` int(11) DEFAULT NULL,
  `paket_wisata` varchar(100) DEFAULT NULL,
  `harga_wisata` int(11) DEFAULT NULL,
  `via_pembayaran` varchar(100) DEFAULT NULL,
  `id_penginapan` int(11) DEFAULT NULL,
  `nama_penginapan` varchar(100) DEFAULT NULL,
  `id_pengunjung` int(11) DEFAULT NULL,
  `nama_pengunjung` varchar(100) DEFAULT NULL,
  `deskripsi_makanan_minuman` varchar(100) DEFAULT NULL,
  `deskripsi_tambahan` varchar(100) DEFAULT NULL,
  `dari` date DEFAULT NULL,
  `sampai` date DEFAULT NULL,
  `total_tanggal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paket_wisata`
--

LOCK TABLES `paket_wisata` WRITE;
/*!40000 ALTER TABLE `paket_wisata` DISABLE KEYS */;
INSERT INTO `paket_wisata` VALUES (1,1,'Paket 1',150000,'Transfer',1,'Penginapan 1',1,'Pengunjung 1','Paket Makanan 1','Tambahan 1','2022-07-14','2022-07-20',7),(2,1,'Paket 1',150000,'Cash',1,'Penginapan 1',2,'Pengunjung 2','Paket Makanan 1','Tambahan 1','2022-07-14','2022-07-20',7),(3,5,'Paket 5',1000000,'Transfer',4,'Penginapan 4',3,'Pengunjung 3','Paket Makanan 5','Tambahan 5','2022-07-14','2022-07-20',7),(4,1,'Paket 1',150000,'Cash',1,'Penginapan 1',1,'Pengunjung 1','Paket Makanan 1','Tambahan 1','2022-08-12','2022-08-12',0);
/*!40000 ALTER TABLE `paket_wisata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penginapan`
--

DROP TABLE IF EXISTS `penginapan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penginapan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penginapan`
--

LOCK TABLES `penginapan` WRITE;
/*!40000 ALTER TABLE `penginapan` DISABLE KEYS */;
INSERT INTO `penginapan` VALUES (1,'Penginapan 1'),(2,'Penginapan 2'),(3,'Penginapan 3'),(4,'Penginapan 4'),(5,'Penginapan 5');
/*!40000 ALTER TABLE `penginapan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pengunjung`
--

DROP TABLE IF EXISTS `pengunjung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pengunjung` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `nik` varchar(10) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telepon` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pengunjung`
--

LOCK TABLES `pengunjung` WRITE;
/*!40000 ALTER TABLE `pengunjung` DISABLE KEYS */;
INSERT INTO `pengunjung` VALUES (1,'Pengunjung 1','0','Alamat 1','0'),(2,'Pengunjung 2','0','Alamat 2','0'),(3,'Pengunjung 3','0','Alamat 3','0'),(4,'Pengunjung 4','0','Alamat 4','0');
/*!40000 ALTER TABLE `pengunjung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportasi`
--

DROP TABLE IF EXISTS `transportasi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transportasi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportasi`
--

LOCK TABLES `transportasi` WRITE;
/*!40000 ALTER TABLE `transportasi` DISABLE KEYS */;
INSERT INTO `transportasi` VALUES (1,'Transportasi 1'),(2,'Transportasi 2'),(3,'Transportasi 3'),(4,'Transportasi 4'),(5,'Transportasi 5');
/*!40000 ALTER TABLE `transportasi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wisata`
--

DROP TABLE IF EXISTS `wisata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wisata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paket` varchar(100) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `id_penginapan` int(11) DEFAULT NULL,
  `nama_penginapan` varchar(100) DEFAULT NULL,
  `id_transportasi` int(11) DEFAULT NULL,
  `nama_transportasi` varchar(100) DEFAULT NULL,
  `deskripsi_makanan_minuman` varchar(100) DEFAULT NULL,
  `deskripsi_tambahan` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wisata`
--

LOCK TABLES `wisata` WRITE;
/*!40000 ALTER TABLE `wisata` DISABLE KEYS */;
INSERT INTO `wisata` VALUES (1,'Paket 1',150000,1,'Penginapan 1',1,'Transportasi 1','Paket Makanan 1','Tambahan 1'),(2,'Paket 2',200000,2,'Penginapan 2',1,'Transportasi 1','Paket Makanan 2','Tambahan 2'),(3,'Paket 3',300000,2,'Penginapan 2',2,'Transportasi 2','Paket Makanan 3','Tambahan 3'),(4,'Paket 4',500000,3,'Penginapan 3',3,'Transportasi 3','Paket Makanan 4','Tambahan 5'),(5,'Paket 5',100000,1,'Paket 5',1,'Transportasi 4','Paket Makanan 5','Tambahan 5');
/*!40000 ALTER TABLE `wisata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pulaupari'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-12 23:40:00
