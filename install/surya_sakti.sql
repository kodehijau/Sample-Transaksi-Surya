-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 11, 2013 at 06:21 PM
-- Server version: 5.5.31
-- PHP Version: 5.3.10-1ubuntu3.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `surya_sakti`
--

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE IF NOT EXISTS `pesanan` (
  `id_pesanan` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `alamat` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `hp` varchar(12) COLLATE latin1_general_ci NOT NULL,
  `kode_barang` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `harga_barang` varchar(8) COLLATE latin1_general_ci NOT NULL,
  `jml_barang` varchar(3) COLLATE latin1_general_ci NOT NULL,
  `total` varchar(8) COLLATE latin1_general_ci NOT NULL,
  UNIQUE KEY `id_pesanan` (`id_pesanan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_barang`
--

CREATE TABLE IF NOT EXISTS `tbl_barang` (
  `kode_barang` int(4) NOT NULL AUTO_INCREMENT,
  `nama_barang` varchar(15) COLLATE latin1_general_ci NOT NULL,
  `harga` int(8) NOT NULL,
  PRIMARY KEY (`kode_barang`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=27 ;

--
-- Dumping data for table `tbl_barang`
--

INSERT INTO `tbl_barang` (`kode_barang`, `nama_barang`, `harga`) VALUES
(1, 'Arak B', 1100),
(2, 'Arak K', 300),
(3, 'Anggur Merah', 900),
(4, 'Angker', 1250),
(5, 'Bir B', 1400),
(6, 'Bir K', 400),
(7, 'Carlsberg', 1100),
(8, 'Fanta', 350),
(9, 'Guiness B', 1100),
(10, 'Guiness K', 400),
(11, 'Heineken', 1200),
(12, 'Intisari', 800),
(13, 'Kecap B', 700),
(14, 'Kecap K', 150),
(15, 'Kristal', 3500),
(16, 'Kuda Mas', 750),
(17, 'Madu K', 200),
(18, 'Marjan', 350),
(19, 'Mcdonald', 2000),
(20, 'Mensen B', 1000),
(22, 'Mix Max', 150),
(23, 'Orson', 250),
(24, 'Sosro', 200),
(25, 'Tomat', 200),
(26, 'Beling', 300);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
