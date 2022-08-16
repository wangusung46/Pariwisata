-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2022 at 12:21 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pulaupari`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `user` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` enum('Pemilik','Admin','Operator','Bagian Keuangan') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `nama`, `user`, `password`, `role`) VALUES
(1, 'Andre', '1', '1', 'Admin'),
(2, 'Wawan', '2', '2', 'Pemilik'),
(3, 'Dano', '3', '3', 'Operator'),
(4, 'Lidya', '4', '4', 'Bagian Keuangan');

-- --------------------------------------------------------

--
-- Table structure for table `paket_wisata`
--

CREATE TABLE `paket_wisata` (
  `id` int(11) NOT NULL,
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
  `total_tanggal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paket_wisata`
--

INSERT INTO `paket_wisata` (`id`, `id_wisata`, `paket_wisata`, `harga_wisata`, `via_pembayaran`, `id_penginapan`, `nama_penginapan`, `id_pengunjung`, `nama_pengunjung`, `deskripsi_makanan_minuman`, `deskripsi_tambahan`, `dari`, `sampai`, `total_tanggal`) VALUES
(1, 1, 'Paket 1', 150000, 'Transfer', 1, 'Penginapan 1', 1, 'Pengunjung 1', 'Paket Makanan 1', 'Tambahan 1', '2022-07-14', '2022-07-20', 7),
(2, 1, 'Paket 1', 150000, 'Cash', 1, 'Penginapan 1', 2, 'Pengunjung 2', 'Paket Makanan 1', 'Tambahan 1', '2022-07-14', '2022-07-20', 7),
(3, 5, 'Paket 5', 1000000, 'Transfer', 4, 'Penginapan 4', 3, 'Pengunjung 3', 'Paket Makanan 5', 'Tambahan 5', '2022-07-14', '2022-07-20', 7),
(4, 1, 'Paket 1', 150000, 'Cash', 1, 'Penginapan 1', 1, 'Pengunjung 1', 'Paket Makanan 1', 'Tambahan 1', '2022-08-12', '2022-08-12', 0);

-- --------------------------------------------------------

--
-- Table structure for table `penginapan`
--

CREATE TABLE `penginapan` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penginapan`
--

INSERT INTO `penginapan` (`id`, `nama`) VALUES
(1, 'Penginapan 1'),
(2, 'Penginapan 2'),
(3, 'Penginapan 3'),
(4, 'Penginapan 4'),
(5, 'Penginapan 5');

-- --------------------------------------------------------

--
-- Table structure for table `pengunjung`
--

CREATE TABLE `pengunjung` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `nik` varchar(20) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telepon` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengunjung`
--

INSERT INTO `pengunjung` (`id`, `nama`, `nik`, `alamat`, `telepon`) VALUES
(1, 'Pengunjung 1', '0', 'Alamat 1', '0'),
(2, 'Pengunjung 2', '0', 'Alamat 2', '0'),
(3, 'Pengunjung 3', '0', 'Alamat 3', '0'),
(4, 'Pengunjung 4', '0', 'Alamat 4', '0'),
(6, 'andre', '1234567', 'gg', '23232121');

-- --------------------------------------------------------

--
-- Table structure for table `transportasi`
--

CREATE TABLE `transportasi` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transportasi`
--

INSERT INTO `transportasi` (`id`, `nama`) VALUES
(1, 'Transportasi 1'),
(2, 'Transportasi 2'),
(3, 'Transportasi 3'),
(4, 'Transportasi 4'),
(5, 'Transportasi 5');

-- --------------------------------------------------------

--
-- Table structure for table `wisata`
--

CREATE TABLE `wisata` (
  `id` int(11) NOT NULL,
  `paket` varchar(100) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `id_penginapan` int(11) DEFAULT NULL,
  `nama_penginapan` varchar(100) DEFAULT NULL,
  `id_transportasi` int(11) DEFAULT NULL,
  `nama_transportasi` varchar(100) DEFAULT NULL,
  `deskripsi_makanan_minuman` varchar(100) DEFAULT NULL,
  `deskripsi_tambahan` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wisata`
--

INSERT INTO `wisata` (`id`, `paket`, `harga`, `id_penginapan`, `nama_penginapan`, `id_transportasi`, `nama_transportasi`, `deskripsi_makanan_minuman`, `deskripsi_tambahan`) VALUES
(1, 'Paket 1', 150000, 1, 'Penginapan 1', 1, 'Transportasi 1', 'Paket Makanan 1', 'Tambahan 1'),
(2, 'Paket 2', 200000, 2, 'Penginapan 2', 1, 'Transportasi 1', 'Paket Makanan 2', 'Tambahan 2'),
(3, 'Paket 3', 300000, 2, 'Penginapan 2', 2, 'Transportasi 2', 'Paket Makanan 3', 'Tambahan 3'),
(4, 'Paket 4', 500000, 3, 'Penginapan 3', 3, 'Transportasi 3', 'Paket Makanan 4', 'Tambahan 5'),
(5, 'Paket 5', 100000, 1, 'Paket 5', 1, 'Transportasi 4', 'Paket Makanan 5', 'Tambahan 5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `paket_wisata`
--
ALTER TABLE `paket_wisata`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `penginapan`
--
ALTER TABLE `penginapan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pengunjung`
--
ALTER TABLE `pengunjung`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transportasi`
--
ALTER TABLE `transportasi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wisata`
--
ALTER TABLE `wisata`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `paket_wisata`
--
ALTER TABLE `paket_wisata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `penginapan`
--
ALTER TABLE `penginapan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pengunjung`
--
ALTER TABLE `pengunjung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `transportasi`
--
ALTER TABLE `transportasi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `wisata`
--
ALTER TABLE `wisata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
