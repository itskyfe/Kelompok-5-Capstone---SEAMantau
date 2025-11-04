-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 04, 2025 at 02:37 PM
-- Server version: 8.4.3
-- PHP Version: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_seamantau`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int NOT NULL,
  `status` enum('Aktif','Nonaktif') COLLATE utf8mb4_general_ci DEFAULT 'Aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `status`) VALUES
(1, 'Aktif'),
(2, 'Aktif'),
(3, 'Aktif'),
(4, 'Aktif'),
(5, 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `kapal`
--

CREATE TABLE `kapal` (
  `no_registrasi` varchar(18) COLLATE utf8mb4_general_ci NOT NULL,
  `nelayan_id` int NOT NULL,
  `nama_kapal` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `kategori_kapal` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `jenis_kapal` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `foto_kapal` varchar(2048) COLLATE utf8mb4_general_ci NOT NULL,
  `status_kapal` enum('Aktif','Nonaktif','Dihapus') COLLATE utf8mb4_general_ci DEFAULT 'Nonaktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kapal`
--

INSERT INTO `kapal` (`no_registrasi`, `nelayan_id`, `nama_kapal`, `kategori_kapal`, `jenis_kapal`, `foto_kapal`, `status_kapal`) VALUES
('1231231231231', 86, 'KR Van', 'Kapal Sedang', 'Longline (Rawai Tuna)', 'https://drive.google.com/file/d/18Oi3APu2t6h1XcJMJibNRFXMrJKt-DLc/view?usp=drive_link', 'Aktif'),
('2342342342341', 46, '234234234', 'Kapal Sedang', 'Purse Seine (Cincin)', 'https://drive.google.com/file/d/1zgOvIPf1cet8j14fBSOeYGorMCYdYmzG/view?usp=drive_link', 'Nonaktif'),
('DD 1001 AB', 46, 'KM Selayar Jaya', 'Kapal Kecil', 'Jukung', 'https://drive.google.com/file/d/1NrETbOzNTQzQkI38D6EpevVGFsRKN8nx/view?usp=drive_link', 'Aktif'),
('DD 1002 AC', 47, 'KM Rajuni Makmur', 'Kapal Kecil', 'Katir', 'https://seamantau.id/assets/kapal/kapal_1002.jpg', 'Aktif'),
('DD 1003 AD', 48, 'KM Bira Indah', 'Kapal Kecil', 'Sampan', 'https://seamantau.id/assets/kapal/kapal_1003.jpg', 'Aktif'),
('DD 1004 AE', 49, 'KM Tanakeke I', 'Kapal Kecil', 'Perahu Motor Tempel', 'https://seamantau.id/assets/kapal/kapal_1004.jpg', 'Aktif'),
('DD 1005 AF', 50, 'KM Galesong Baru', 'Kapal Kecil', 'Gillnet (Jaring Insang)', 'https://seamantau.id/assets/kapal/kapal_1005.jpg', 'Aktif'),
('DD 1006 AG', 51, 'KM Paotere 01', 'Kapal Kecil', 'Bagang Apung', 'https://seamantau.id/assets/kapal/kapal_1006.jpg', 'Aktif'),
('DD 1007 AH', 52, 'KM Punaga Lestari', 'Kapal Kecil', 'Bagang Tancap', 'https://seamantau.id/assets/kapal/kapal_1007.jpg', 'Aktif'),
('DD 1008 AJ', 53, 'KM Barru Laut', 'Kapal Kecil', 'Perahu Motor Tempel', 'https://seamantau.id/assets/kapal/kapal_1008.jpg', 'Aktif'),
('DD 1009 AK', 54, 'KM Garongkong', 'Kapal Kecil', 'Sampan', 'https://seamantau.id/assets/kapal/kapal_1009.jpg', 'Aktif'),
('DD 1010 AL', 55, 'KM Sabutung', 'Kapal Kecil', 'Katir', 'https://seamantau.id/assets/kapal/kapal_1010.jpg', 'Aktif'),
('DD 1011 AM', 56, 'KM Liukang', 'Kapal Menengah', 'Cantrang', 'https://seamantau.id/assets/kapal/kapal_1011.jpg', 'Aktif'),
('DD 1012 AN', 57, 'KM Samalona', 'Kapal Menengah', 'Purse Seine (Cincin)', 'https://seamantau.id/assets/kapal/kapal_1012.jpg', 'Aktif'),
('DD 1013 AP', 58, 'KM Pabbiringa', 'Kapal Menengah', 'Longline (Rawai Tuna)', 'https://seamantau.id/assets/kapal/kapal_1013.jpg', 'Aktif'),
('DD 1014 AQ', 59, 'KM Bontosunggu', 'Kapal Menengah', 'Bagang Kapal', 'https://seamantau.id/assets/kapal/kapal_1014.jpg', 'Aktif'),
('DD 1015 AR', 60, 'KM Panaikang', 'Kapal Menengah', 'Cantrang', 'https://seamantau.id/assets/kapal/kapal_1015.jpg', 'Aktif'),
('DD 1016 AS', 61, 'KM Tamalanrea', 'Kapal Menengah', 'Purse Seine (Cincin)', 'https://seamantau.id/assets/kapal/kapal_1016.jpg', 'Aktif'),
('DD 1017 AT', 62, 'KM Bonto Bahari', 'Kapal Menengah', 'Longline (Rawai Tuna)', 'https://seamantau.id/assets/kapal/kapal_1017.jpg', 'Aktif'),
('DD 1018 AU', 63, 'KM Kanreapia', 'Kapal Menengah', 'Bagang Kapal', 'https://seamantau.id/assets/kapal/kapal_1018.jpg', 'Aktif'),
('DD 1019 AV', 64, 'KM Karanrang', 'Kapal Menengah', 'Cantrang', 'https://seamantau.id/assets/kapal/kapal_1019.jpg', 'Aktif'),
('DD 1020 AW', 65, 'KM Langkai', 'Kapal Menengah', 'Purse Seine (Cincin)', 'https://seamantau.id/assets/kapal/kapal_1020.jpg', 'Aktif'),
('DD 1021 AX', 66, 'KM Barrang Lompo', 'Kapal Menengah', 'Longline (Rawai Tuna)', 'https://seamantau.id/assets/kapal/kapal_1021.jpg', 'Aktif'),
('DD 1022 AY', 67, 'KM Bontoa', 'Kapal Menengah', 'Bagang Kapal', 'https://drive.google.com/file/d/1zAc2B9e3fFZc5NAuP3Ry-0a2gVz6Ru1w/view?usp=drive_link', 'Aktif'),
('DD 1023 AZ', 68, 'KM Selayar Sejahtera', 'Kapal Menengah', 'Cantrang', 'https://drive.google.com/file/d/14v_8hIDYekXZcOCtyjNQQN8UQ-gP0q_y/view?usp=drive_link', 'Aktif'),
('DD 1024 BA', 69, 'KM Takalar 02', 'Kapal Menengah', 'Purse Seine (Cincin)', 'https://seamantau.id/assets/kapal/kapal_1024.jpg', 'Aktif'),
('DD 1025 BB', 70, 'KM Mariso Laut', 'Kapal Menengah', 'Longline (Rawai Tuna)', 'https://seamantau.id/assets/kapal/kapal_1025.jpg', 'Aktif'),
('DD 1026 BC', 71, 'KM Paotere 02', 'Kapal Menengah', 'Bagang Kapal', 'https://seamantau.id/assets/kapal/kapal_1026.jpg', 'Aktif'),
('DD 1027 BD', 72, 'KM Bulukumba 01', 'Kapal Besar', 'Purse Seine Besar', 'https://seamantau.id/assets/kapal/kapal_1027.jpg', 'Aktif'),
('DD 1028 BE', 73, 'KM Pangkep Maju', 'Kapal Besar', 'Trawl Laut Dalam', 'https://seamantau.id/assets/kapal/kapal_1028.jpg', 'Aktif'),
('DD 1029 BF', 74, 'KM Lappa', 'Kapal Besar', 'Kapal Pengangkut Ikan', 'https://seamantau.id/assets/kapal/kapal_1029.jpg', 'Aktif'),
('DD 1030 BG', 75, 'KM Bone 01', 'Kapal Besar', 'Purse Seine Besar', 'https://seamantau.id/assets/kapal/kapal_1030.jpg', 'Aktif'),
('DD 1031 BH', 76, 'KM Parepare', 'Kapal Besar', 'Trawl Laut Dalam', 'https://seamantau.id/assets/kapal/kapal_1031.jpg', 'Aktif'),
('DD 1032 BK', 77, 'KM Pinrang', 'Kapal Besar', 'Kapal Pengangkut Ikan', 'https://seamantau.id/assets/kapal/kapal_1032.jpg', 'Aktif'),
('DD 1033 BL', 78, 'KM Barru', 'Kapal Besar', 'Purse Seine Besar', 'https://seamantau.id/assets/kapal/kapal_1033.jpg', 'Aktif'),
('DD 1034 BM', 79, 'KM Soppeng', 'Kapal Besar', 'Trawl Laut Dalam', 'https://seamantau.id/assets/kapal/kapal_1034.jpg', 'Aktif'),
('DD 1035 BN', 80, 'KM Wajo', 'Kapal Besar', 'Kapal Pengangkut Ikan', 'https://seamantau.id/assets/kapal/kapal_1035.jpg', 'Aktif'),
('DD 1036 BP', 81, 'KM Jeneponto', 'Kapal Besar', 'Purse Seine Besar', 'https://seamantau.id/assets/kapal/kapal_1036.jpg', 'Aktif'),
('DD 1037 BQ', 82, 'KM Bantaeng', 'Kapal Besar', 'Trawl Laut Dalam', 'https://seamantau.id/assets/kapal/kapal_1037.jpg', 'Aktif'),
('DD 1038 BR', 83, 'KM Takalar Baru', 'Kapal Besar', 'Kapal Pengangkut Ikan', 'https://seamantau.id/assets/kapal/kapal_1038.jpg', 'Aktif'),
('DD 1039 BS', 84, 'KM Maros', 'Kapal Besar', 'Purse Seine Besar', 'https://drive.google.com/file/d/1zo5yf5pEqmHcbaChJssh0yndLWq1l1JR/view?usp=drive_link', 'Nonaktif'),
('DD 1040 AB', 85, 'KM Makassar', 'Kapal Besar', 'Trawl Laut Dalam', 'https://drive.google.com/file/d/1FCDOpoujKNo1d0I4OYPnyH9W74XI5auy/view?usp=drive_link', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `laporan`
--

CREATE TABLE `laporan` (
  `laporan_id` int NOT NULL,
  `id_nelayan` int NOT NULL,
  `id_pegawai` int DEFAULT NULL,
  `id_kapal` varchar(18) COLLATE utf8mb4_general_ci NOT NULL,
  `id_wilayah` int NOT NULL,
  `status_laporan` enum('Menunggu','Berlayar','Diverifikasi','Ditolak') COLLATE utf8mb4_general_ci DEFAULT 'Menunggu',
  `nama_pelabuhan` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `waktu_berangkat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `alat_tangkap` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `foto_alat_tangkap` varchar(2048) COLLATE utf8mb4_general_ci NOT NULL,
  `waktu_berlabuh` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `catatan` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laporan`
--

INSERT INTO `laporan` (`laporan_id`, `id_nelayan`, `id_pegawai`, `id_kapal`, `id_wilayah`, `status_laporan`, `nama_pelabuhan`, `waktu_berangkat`, `alat_tangkap`, `foto_alat_tangkap`, `waktu_berlabuh`, `catatan`) VALUES
(1, 46, 6, 'DD 1001 AB', 1, 'Diverifikasi', 'Paotere', '2025-07-04 21:30:00', 'Pancing Ulur', 'https://seamantau.id/assets/alat/alat_1001.jpg', '2025-07-05 08:00:00', 'Catatan pegawai (NIP 20250001): verifikasi bobot cakalang 120 kg.'),
(2, 47, 7, 'DD 1002 AC', 2, 'Menunggu', 'Paotere', '2025-07-06 22:00:00', 'Pancing Ulur', 'https://seamantau.id/assets/alat/alat_1002.jpg', '2025-07-07 07:30:00', 'Catatan pegawai (NIP 20250002): menunggu dokumen perizinan.'),
(3, 48, 8, 'DD 1003 AD', 3, 'Ditolak', 'Bira', '2025-07-08 20:50:00', 'Pancing Ulur', 'https://seamantau.id/assets/alat/alat_1003.jpg', '2025-07-09 06:10:00', 'Catatan pegawai (NIP 20250003): koordinat tidak sesuai SOP.'),
(4, 49, 9, 'DD 1004 AE', 4, 'Berlayar', 'Galesong', '2025-07-11 21:20:00', 'Pancing Ulur', 'https://seamantau.id/assets/alat/alat_1004.jpg', NULL, 'Catatan pegawai (NIP 20250004): monitoring perjalanan.'),
(5, 50, 10, 'DD 1005 AF', 5, 'Diverifikasi', 'Paotere', '2025-07-13 21:10:00', 'Gillnet (Jaring Insang)', 'https://seamantau.id/assets/alat/alat_1005.jpg', '2025-07-14 08:20:00', 'Catatan pegawai (NIP 20250005): alat sesuai standar.'),
(6, 51, 11, 'DD 1006 AG', 6, 'Menunggu', 'Paotere', '2025-07-16 21:05:00', 'Bagang', 'https://seamantau.id/assets/alat/alat_1006.jpg', '2025-07-17 06:40:00', 'Catatan pegawai (NIP 20250006): jadwal pembinaan.'),
(7, 52, 12, 'DD 1007 AH', 7, 'Diverifikasi', 'Galesong', '2025-07-19 21:00:00', 'Bagang', 'https://seamantau.id/assets/alat/alat_1007.jpg', '2025-07-20 07:30:00', 'Catatan pegawai (NIP 20250007): hasil layak lelang.'),
(8, 53, 13, 'DD 1008 AJ', 8, 'Berlayar', 'Garongkong', '2025-07-21 21:40:00', 'Pancing Ulur', 'https://seamantau.id/assets/alat/alat_1008.jpg', NULL, 'Catatan pegawai (NIP 20250008): cuaca baik, lanjut operasi.'),
(9, 54, 14, 'DD 1009 AK', 9, 'Menunggu', 'Sabutung', '2025-07-24 21:25:00', 'Pancing Ulur', 'https://seamantau.id/assets/alat/alat_1009.jpg', '2025-07-25 05:20:00', 'Catatan pegawai (NIP 20250009): menunggu rekap timbangan.'),
(10, 55, 15, 'DD 1010 AL', 10, 'Diverifikasi', 'Paotere', '2025-07-26 21:15:00', 'Pancing Ulur', 'https://seamantau.id/assets/alat/alat_1010.jpg', '2025-07-27 06:05:00', 'Catatan pegawai (NIP 20250010): QC sampel cumi OK.'),
(11, 56, 16, 'DD 1011 AM', 11, 'Ditolak', 'Paotere', '2025-07-31 21:30:00', 'Cantrang', 'https://seamantau.id/assets/alat/alat_1011.jpg', '2025-08-01 08:00:00', 'Catatan pegawai (NIP 20250011): rute melintas zona sensitif.'),
(12, 57, 17, 'DD 1012 AN', 12, 'Diverifikasi', 'Samalona', '2025-08-02 21:15:00', 'Purse Seine', 'https://seamantau.id/assets/alat/alat_1012.jpg', '2025-08-03 06:30:00', 'Catatan pegawai (NIP 20250012): hasil sesuai logbook.'),
(13, 58, 18, 'DD 1013 AP', 13, 'Diverifikasi', 'Paotere', '2025-08-04 21:00:00', 'Longline (Rawai Tuna)', 'https://seamantau.id/assets/alat/alat_1013.jpg', '2025-08-05 07:10:00', 'Catatan pegawai (NIP 20250013): target tenggiri 55 kg.'),
(14, 59, 19, 'DD 1014 AQ', 14, 'Menunggu', 'Bontosunggu', '2025-08-07 21:05:00', 'Bagang', 'https://seamantau.id/assets/alat/alat_1014.jpg', '2025-08-08 06:00:00', 'Catatan pegawai (NIP 20250014): validasi foto malam.'),
(15, 60, 20, 'DD 1015 AR', 15, 'Diverifikasi', 'Paotere', '2025-08-09 21:10:00', 'Cantrang', 'https://seamantau.id/assets/alat/alat_1015.jpg', '2025-08-10 07:50:00', 'Catatan pegawai (NIP 20250015): dokumen lengkap.'),
(16, 61, 21, 'DD 1016 AS', 16, 'Diverifikasi', 'Tamalanrea', '2025-08-11 21:25:00', 'Purse Seine', 'https://seamantau.id/assets/alat/alat_1016.jpg', '2025-08-12 06:40:00', 'Catatan pegawai (NIP 20250016): cakalang 95 kg.'),
(17, 62, 22, 'DD 1017 AT', 17, 'Menunggu', 'Bonto Bahari', '2025-08-14 21:35:00', 'Longline (Rawai Tuna)', 'https://seamantau.id/assets/alat/alat_1017.jpg', '2025-08-15 06:35:00', 'Catatan pegawai (NIP 20250017): cek kondisi leader.'),
(18, 63, 23, 'DD 1018 AU', 18, 'Diverifikasi', 'Kanreapia', '2025-08-16 21:20:00', 'Bagang', 'https://seamantau.id/assets/alat/alat_1018.jpg', '2025-08-17 05:50:00', 'Catatan pegawai (NIP 20250018): kerapu 38 kg.'),
(19, 64, 24, 'DD 1019 AV', 19, 'Diverifikasi', 'Karanrang', '2025-08-18 21:30:00', 'Cantrang', 'https://seamantau.id/assets/alat/alat_1019.jpg', '2025-08-19 07:20:00', 'Catatan pegawai (NIP 20250019): ok.'),
(20, 65, 25, 'DD 1020 AW', 20, 'Menunggu', 'Langkai', '2025-08-21 21:10:00', 'Purse Seine', 'https://seamantau.id/assets/alat/alat_1020.jpg', '2025-08-22 06:10:00', 'Catatan pegawai (NIP 20250020): tunggu verifikator.'),
(21, 66, 26, 'DD 1021 AX', 21, 'Diverifikasi', 'Barrang Lompo', '2025-08-23 21:40:00', 'Longline (Rawai Tuna)', 'https://seamantau.id/assets/alat/alat_1021.jpg', '2025-08-24 05:55:00', 'Catatan pegawai (NIP 20250021): hasil layang 70 kg.'),
(22, 67, 27, 'DD 1022 AY', 22, 'Diverifikasi', 'Bontoa', '2025-08-25 21:15:00', 'Bagang', 'https://seamantau.id/assets/alat/alat_1022.jpg', '2025-08-26 06:05:00', 'Catatan pegawai (NIP 20250022): lengkap.'),
(23, 68, 28, 'DD 1023 AZ', 23, 'Menunggu', 'Selayar', '2025-08-28 21:05:00', 'Cantrang', 'https://seamantau.id/assets/alat/alat_1023.jpg', '2025-08-29 07:35:00', 'Catatan pegawai (NIP 20250023): evaluasi rute.'),
(24, 69, 29, 'DD 1024 BA', 24, 'Diverifikasi', 'Paotere', '2025-09-01 21:00:00', 'Purse Seine', 'https://seamantau.id/assets/alat/alat_1024.jpg', '2025-09-02 06:20:00', 'Catatan pegawai (NIP 20250024): cakalang 105 kg.'),
(25, 70, 30, 'DD 1025 BB', 25, 'Diverifikasi', 'Mariso', '2025-09-03 21:30:00', 'Longline (Rawai Tuna)', 'https://seamantau.id/assets/alat/alat_1025.jpg', '2025-09-04 06:20:00', 'Catatan pegawai (NIP 20250025): kakap merah 40 kg.'),
(26, 71, 31, 'DD 1026 BC', 26, 'Berlayar', 'Paotere', '2025-09-06 21:25:00', 'Bagang', 'https://seamantau.id/assets/alat/alat_1026.jpg', NULL, 'Catatan pegawai (NIP 20250026): pemantauan rutin.'),
(27, 72, 32, 'DD 1027 BD', 27, 'Diverifikasi', 'Bulukumba', '2025-09-08 21:10:00', 'Purse Seine Besar', 'https://seamantau.id/assets/alat/alat_1027.jpg', '2025-09-09 07:15:00', 'Catatan pegawai (NIP 20250027): hasil tongkol 90 kg.'),
(28, 73, 33, 'DD 1028 BE', 28, 'Diverifikasi', 'Pangkep', '2025-09-10 21:15:00', 'Trawl', 'https://seamantau.id/assets/alat/alat_1028.jpg', '2025-09-11 06:25:00', 'Catatan pegawai (NIP 20250028): bycatch tercatat.'),
(29, 74, 34, 'DD 1029 BF', 29, 'Menunggu', 'Lappa', '2025-09-13 21:05:00', 'Logistik/Angkut', 'https://seamantau.id/assets/alat/alat_1029.jpg', '2025-09-14 05:30:00', 'Catatan pegawai (NIP 20250029): distribusi es balok.'),
(30, 75, 35, 'DD 1030 BG', 30, 'Diverifikasi', 'Bone', '2025-09-15 21:00:00', 'Purse Seine Besar', 'https://seamantau.id/assets/alat/alat_1030.jpg', '2025-09-16 06:10:00', 'Catatan pegawai (NIP 20250030): kerapu 33 kg.'),
(31, 76, 36, 'DD 1031 BH', 31, 'Diverifikasi', 'Parepare', '2025-09-17 21:35:00', 'Trawl', 'https://seamantau.id/assets/alat/alat_1031.jpg', '2025-09-18 07:30:00', 'Catatan pegawai (NIP 20250031): inspeksi jaring.'),
(32, 77, 37, 'DD 1032 BK', 32, 'Menunggu', 'Pinrang', '2025-09-20 21:20:00', 'Logistik/Angkut', 'https://seamantau.id/assets/alat/alat_1032.jpg', '2025-09-21 06:45:00', 'Catatan pegawai (NIP 20250032): pengangkutan es.'),
(33, 78, 38, 'DD 1033 BL', 33, 'Diverifikasi', 'Barru', '2025-09-22 21:30:00', 'Purse Seine Besar', 'https://seamantau.id/assets/alat/alat_1033.jpg', '2025-09-23 05:50:00', 'Catatan pegawai (NIP 20250033): cumi 36 kg.'),
(34, 79, 39, 'DD 1034 BM', 34, 'Diverifikasi', 'Soppeng', '2025-09-24 21:15:00', 'Trawl', 'https://seamantau.id/assets/alat/alat_1034.jpg', '2025-09-25 06:00:00', 'Catatan pegawai (NIP 20250034): aman.'),
(35, 80, 40, 'DD 1035 BN', 35, 'Menunggu', 'Wajo', '2025-09-27 21:10:00', 'Logistik/Angkut', 'https://seamantau.id/assets/alat/alat_1035.jpg', '2025-09-28 07:05:00', 'Catatan pegawai (NIP 20250035): transfer dingin.'),
(36, 81, 41, 'DD 1036 BP', 36, 'Diverifikasi', 'Jeneponto', '2025-09-30 21:05:00', 'Purse Seine Besar', 'https://seamantau.id/assets/alat/alat_1036.jpg', '2025-10-01 06:35:00', 'Catatan pegawai (NIP 20250036): tuna 92 kg.'),
(37, 82, 42, 'DD 1037 BQ', 37, 'Diverifikasi', 'Bantaeng', '2025-10-02 21:00:00', 'Trawl', 'https://drive.google.com/file/d/1XcacLQSngzFsTCd_GcPevtRcrPmdHjnY/view?usp=drive_link', '2025-11-04 14:34:51', 'Catatan pegawai (NIP 20250037): inspeksi alat OK.'),
(38, 83, 43, 'DD 1038 BR', 38, 'Menunggu', 'Takalar', '2025-10-04 21:25:00', 'Logistik/Angkut', 'https://drive.google.com/file/d/1c3dJNJnoO05xoYzGoaxGV0Y1aZ4HbRfp/view?usp=drive_link', '2025-11-04 14:33:57', 'Catatan pegawai (NIP 20250038): supply BBM.'),
(39, 84, 44, 'DD 1039 BS', 39, 'Diverifikasi', 'Maros', '2025-10-06 21:20:00', 'Purse Seine Besar', 'https://drive.google.com/file/d/1pI24UP4nBy80e79_XfdJ8Ufop1Td_A_2/view?usp=drive_link', '2025-11-04 14:31:54', 'Catatan pegawai (NIP 20250039): tongkol 87 kg.'),
(40, 85, 45, 'DD 1040 AB', 40, 'Menunggu', 'Makassar', '2025-10-09 21:15:00', 'Trawl', 'https://drive.google.com/file/d/1ZbUOjyA-l163O8OfRhnwbYTRQxk9FJ2G/view?usp=drive_link', '2025-11-04 14:36:01', 'Catatan pegawai (NIP 20250040): rekap final.');

-- --------------------------------------------------------

--
-- Table structure for table `laporan_pengaduan`
--

CREATE TABLE `laporan_pengaduan` (
  `pengaduan_id` int NOT NULL,
  `users_id` int NOT NULL,
  `pegawai_id` int DEFAULT NULL,
  `tanggal_pengaduan` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `jenis_pengaduan` enum('Pelanggaran','Kerusakan','Sistem') COLLATE utf8mb4_general_ci NOT NULL,
  `deskripsi` text COLLATE utf8mb4_general_ci NOT NULL,
  `status_pengaduan` enum('Menunggu','Diproses','Selesai') COLLATE utf8mb4_general_ci DEFAULT 'Menunggu',
  `dokumentasi` varchar(2048) COLLATE utf8mb4_general_ci NOT NULL,
  `titik_koordinat` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `catatan` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laporan_pengaduan`
--

INSERT INTO `laporan_pengaduan` (`pengaduan_id`, `users_id`, `pegawai_id`, `tanggal_pengaduan`, `jenis_pengaduan`, `deskripsi`, `status_pengaduan`, `dokumentasi`, `titik_koordinat`, `catatan`) VALUES
(1, 46, 6, '2025-08-01 01:00:00', 'Kerusakan', 'Kerusakan karang tepi pantai akibat jangkar kapal kecil', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_01.jpg', '-5.132,119.422', 'Catatan pegawai (NIP 20250001): survei rencana mooring ramah karang.'),
(2, 47, 7, '2025-08-02 02:15:00', 'Pelanggaran', 'Sampah plastik dan jaring nelayan menumpuk di Pantai Losari', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_02.jpg', '-5.147,119.412', 'Catatan pegawai (NIP 20250002): koordinasi aksi bersih pantai.'),
(3, 48, 8, '2025-08-03 03:20:00', 'Pelanggaran', 'Limbah cair mengalir ke laut dari selokan pesisir Paotere', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_03.jpg', '-5.116,119.421', 'Catatan pegawai (NIP 20250003): cek saluran limbah dan lapor DLH.'),
(4, 49, 9, '2025-08-04 00:40:00', 'Kerusakan', 'Abrasi mengikis area mangrove di Galesong Utara', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_04.jpg', '-5.247,119.411', 'Catatan pegawai (NIP 20250004): rencana penanaman ulang.'),
(5, 50, 10, '2025-08-05 06:05:00', 'Pelanggaran', 'Tumpukan sampah organik di pesisir Takalar', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_05.jpg', '-5.337,119.443', 'Catatan pegawai (NIP 20250005): koordinasi pengangkutan.'),
(6, 51, 11, '2025-08-06 05:50:00', 'Kerusakan', 'Lumpur menutupi lamun akibat pengerukan di muara Maros', 'Selesai', 'https://seamantau.id/assets/pengaduan/pengaduan_06.jpg', '-5.026,119.541', 'Catatan pegawai (NIP 20250006): pantau sedimentasi.'),
(7, 52, 12, '2025-08-07 01:30:00', 'Pelanggaran', 'Tumpahan solar di dermaga Paotere', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_07.jpg', '-5.111,119.412', 'Catatan pegawai (NIP 20250007): bersihkan permukaan air.'),
(8, 53, 13, '2025-08-08 07:10:00', 'Kerusakan', 'Erosi pantai di Barru menyebabkan pohon tumbang', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_08.jpg', '-4.432,119.621', 'Catatan pegawai (NIP 20250008): lapor ke dinas PU.'),
(9, 54, 14, '2025-08-09 08:25:00', 'Pelanggaran', 'Pembuangan limbah domestik dari rumah ke pantai Pangkep', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_09.jpg', '-4.853,119.568', 'Catatan pegawai (NIP 20250009): inspeksi rumah tangga pesisir.'),
(10, 55, 15, '2025-08-10 02:40:00', 'Kerusakan', 'Reruntuhan beton menutupi area lamun dekat Pantai Galesong', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_10.jpg', '-5.283,119.411', 'Catatan pegawai (NIP 20250010): rencana pembersihan struktur.'),
(11, 56, 16, '2025-08-11 01:10:00', 'Pelanggaran', 'Sampah wisatawan menumpuk di pantai Bira', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_11.jpg', '-5.603,120.455', 'Catatan pegawai (NIP 20250011): edukasi pengelola wisata.'),
(12, 57, 17, '2025-08-12 03:05:00', 'Kerusakan', 'Akar mangrove patah akibat banjir rob di Takalar', 'Selesai', 'https://seamantau.id/assets/pengaduan/pengaduan_12.jpg', '-5.351,119.450', 'Catatan pegawai (NIP 20250012): data untuk replanting.'),
(13, 58, 18, '2025-08-12 23:55:00', 'Kerusakan', 'Karang dangkal rusak akibat pijakan snorkeling di Samalona', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_13.jpg', '-5.104,119.338', 'Catatan pegawai (NIP 20250013): edukasi wisata bahari.'),
(14, 59, 19, '2025-08-14 04:35:00', 'Pelanggaran', 'Bekas cat kapal mengotori area pasir pantai Mariso', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_14.jpg', '-5.165,119.403', 'Catatan pegawai (NIP 20250014): tindak pengecatan di darat.'),
(15, 60, 20, '2025-08-15 05:20:00', 'Kerusakan', 'Pohon mangrove roboh akibat penebangan liar di muara Pangkep', 'Selesai', 'https://seamantau.id/assets/pengaduan/pengaduan_15.jpg', '-4.905,119.547', 'Catatan pegawai (NIP 20250015): ajukan rehabilitasi mangrove.'),
(16, 61, 21, '2025-08-16 02:00:00', 'Pelanggaran', 'Busa deterjen dari permukiman mencemari pesisir Makassar', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_16.jpg', '-5.152,119.415', 'Catatan pegawai (NIP 20250016): lapor DLH Kota.'),
(17, 62, 22, '2025-08-17 08:15:00', 'Kerusakan', 'Struktur beton dermaga runtuh ke pantai Parepare', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_17.jpg', '-4.009,119.648', 'Catatan pegawai (NIP 20250017): koordinasi pelabuhan.'),
(18, 63, 23, '2025-08-18 01:45:00', 'Pelanggaran', 'Aktivitas bakar sampah di pinggir pantai Sungguminasa', 'Selesai', 'https://seamantau.id/assets/pengaduan/pengaduan_18.jpg', '-5.223,119.473', 'Catatan pegawai (NIP 20250018): edukasi warga pesisir.'),
(19, 64, 24, '2025-08-19 00:20:00', 'Kerusakan', 'Jalan beton rusak akibat abrasi di pesisir Maros', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_19.jpg', '-5.014,119.554', 'Catatan pegawai (NIP 20250019): verifikasi lokasi abrasi.'),
(20, 65, 25, '2025-08-20 06:45:00', 'Pelanggaran', 'Sampah nelayan menumpuk di dermaga kecil Jeneponto', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_20.jpg', '-5.655,119.749', 'Catatan pegawai (NIP 20250020): tindak lanjut kebersihan.'),
(21, 66, 26, '2025-08-21 02:30:00', 'Kerusakan', 'Karang bercabang patah di kedalaman 6 m (Spermonde)', 'Selesai', 'https://seamantau.id/assets/pengaduan/pengaduan_21.jpg', '-5.133,119.340', 'Catatan pegawai (NIP 20250021): akan ditransplantasi.'),
(22, 67, 27, '2025-08-21 23:50:00', 'Pelanggaran', 'Tumpahan oli tipis di perairan Barrang Lompo', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_22.jpg', '-5.050,119.320', 'Catatan pegawai (NIP 20250022): pantau sebaran.'),
(23, 68, 28, '2025-08-23 07:25:00', 'Kerusakan', 'Fragmen karang terlepas akibat arus kuat (Pulau Samalona)', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_23.jpg', '-5.083,119.330', 'Catatan pegawai (NIP 20250023): siap relokasi fragmen.'),
(24, 69, 29, '2025-08-24 03:35:00', 'Pelanggaran', 'Limbah minyak hitam mengapung di utara Pulau Kodingareng', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_24.jpg', '-5.070,119.270', 'Catatan pegawai (NIP 20250024): dilaporkan ke KSOP.'),
(25, 70, 30, '2025-08-25 01:05:00', 'Kerusakan', 'Terumbu bercorak putih (bleaching) radius ?20 m?', 'Selesai', 'https://seamantau.id/assets/pengaduan/pengaduan_25.jpg', '-5.220,119.380', 'Catatan pegawai (NIP 20250025): catat di log monitoring suhu.'),
(26, 71, 31, '2025-08-26 02:10:00', 'Pelanggaran', 'Pembuangan jaring bekas di Laut Takalar', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_26.jpg', '-5.331,119.430', 'Catatan pegawai (NIP 20250026): penarikan jaring minggu depan.'),
(27, 72, 32, '2025-08-27 06:00:00', 'Kerusakan', 'Lamun rusak akibat pukat terseret di perairan Pangkep', 'Selesai', 'https://seamantau.id/assets/pengaduan/pengaduan_27.jpg', '-4.907,119.491', 'Catatan pegawai (NIP 20250027): lokasi masuk prioritas rehabilitasi.'),
(28, 73, 33, '2025-08-28 00:40:00', 'Pelanggaran', 'Tumpahan cat dari kapal nelayan di Pulau Sabutung', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_28.jpg', '-4.758,119.439', 'Catatan pegawai (NIP 20250028): teguran langsung.'),
(29, 74, 34, '2025-08-29 05:10:00', 'Kerusakan', 'Fragmen acropora terbalik (Liukang Tupabbiring)', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_29.jpg', '-4.958,119.459', 'Catatan pegawai (NIP 20250029): transplantasi dijadwalkan.'),
(30, 75, 35, '2025-08-29 23:30:00', 'Pelanggaran', 'Diduga penangkapan ikan karang destruktif (bom kecil)', 'Selesai', 'https://drive.google.com/file/d/1SKa3v0Q3kiIRTkxnGZMmWqv2SkklN8cU/view?usp=drive_link', '-5.221,119.595', 'Catatan pegawai (NIP 20250030): laporan diteruskan ke aparat.'),
(31, 76, 36, '2025-09-01 01:20:00', 'Kerusakan', 'Karang lunak rusak akibat jangkar kapal wisata', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_31.jpg', '-5.112,119.356', 'Catatan pegawai (NIP 20250031): sosialisasi zona labuh.'),
(32, 77, 37, '2025-09-02 07:15:00', 'Pelanggaran', 'Sampah botol terapung di perairan Pulau Langkai', 'Menunggu', 'https://seamantau.id/assets/pengaduan/pengaduan_32.jpg', '-5.277,119.312', 'Catatan pegawai (NIP 20250032): rencana aksi bersih laut.'),
(33, 78, 38, '2025-09-03 03:50:00', 'Kerusakan', 'Koloni karang rusak akibat arus pasang surut kuat', 'Selesai', 'https://drive.google.com/file/d/1nQlgKG5TMPC7_x5-sKC0wP977TAcLxbT/view?usp=drive_link', '-5.320,119.500', 'Catatan pegawai (NIP 20250033): monitoring pasca-peristiwa.'),
(34, 79, 39, '2025-09-04 00:10:00', 'Pelanggaran', 'Tumpahan solar ringan dekat perairan Selayar', 'Diproses', 'https://seamantau.id/assets/pengaduan/pengaduan_34.jpg', '-6.050,120.470', 'Catatan pegawai (NIP 20250034): penyisiran visual selesai.'),
(35, 80, 40, '2025-09-05 02:25:00', 'Kerusakan', 'Karang masif retak akibat benturan kapal kecil', 'Menunggu', 'https://drive.google.com/file/d/1gs15bF0JviktoCJgWIXul0R86-4g3TJS/view?usp=drive_link', '-5.050,119.320', 'Catatan pegawai (NIP 20250035): pengukuran kerusakan manual.'),
(36, 81, 41, '2025-09-06 05:35:00', 'Kerusakan', 'Koloni karang berubah warna (indikasi bleaching ringan)', 'Selesai', 'https://drive.google.com/file/d/1WHVhBYT1VPPE9VRusgGeDI85bBigGr4M/view?usp=drive_link', '-5.101,119.285', 'Catatan pegawai (NIP 20250036): pantau suhu SST.'),
(37, 82, 42, '2025-09-07 01:55:00', 'Pelanggaran', 'Limbah cair mengambang di perairan dekat Lumu-Lumu', 'Diproses', 'https://drive.google.com/file/d/1OtW6hQqorhaIlyifdirCmcTK7I2kPBgo/view?usp=drive_link', '-5.322,119.389', 'Catatan pegawai (NIP 20250037): ambil sampel air.'),
(38, 83, 43, '2025-09-07 23:40:00', 'Kerusakan', 'Karang patah dan tertutup pasir di barat Selayar', 'Menunggu', 'https://drive.google.com/file/d/17WEDNXnHugk_ZAhufZuj2QcBPmibWMuI/view?usp=drive_link', '-6.112,120.498', 'Catatan pegawai (NIP 20250038): catat untuk rehabilitasi.'),
(39, 84, 44, '2025-09-09 04:05:00', 'Pelanggaran', 'Minyak ringan dari kapal logistik di Spermonde', 'Selesai', 'https://drive.google.com/file/d/1dP64Wio_7zZxJv3l14A-5TYrKuKTmUae/view?usp=drive_link', '-5.174,119.364', 'Catatan pegawai (NIP 20250039): laporan dikirim KSOP.'),
(40, 85, 45, '2025-09-10 06:20:00', 'Kerusakan', 'Terumbu bercabang patah akibat gelombang kuat', 'Diproses', 'https://drive.google.com/file/d/17N5FvuDTarGDzbKuDtDj5ux9VCjGl1JC/view?usp=drive_link', '-5.253,119.421', 'Catatan pegawai (NIP 20250040): pantau lanjutan diving.');

-- --------------------------------------------------------

--
-- Table structure for table `nelayan`
--

CREATE TABLE `nelayan` (
  `nelayan_id` int NOT NULL,
  `nib` varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  `status_nelayan` enum('Aktif','Nonaktif') COLLATE utf8mb4_general_ci DEFAULT 'Nonaktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nelayan`
--

INSERT INTO `nelayan` (`nelayan_id`, `nib`, `status_nelayan`) VALUES
(46, '0038237066770', 'Aktif'),
(47, '5385318928140', 'Aktif'),
(48, '2382028815463', 'Aktif'),
(49, '9351581927688', 'Aktif'),
(50, '7989582632364', 'Aktif'),
(51, '0725181777289', 'Aktif'),
(52, '2528183702310', 'Aktif'),
(53, '1092529574955', 'Aktif'),
(54, '1290399912948', 'Aktif'),
(55, '1935704254441', 'Aktif'),
(56, '7505509736541', 'Aktif'),
(57, '2105630609953', 'Aktif'),
(58, '1395775388856', 'Aktif'),
(59, '8740248895154', 'Aktif'),
(60, '2782442696232', 'Aktif'),
(61, '3607802963522', 'Aktif'),
(62, '4608461876597', 'Aktif'),
(63, '7887664471709', 'Aktif'),
(64, '8392383464313', 'Aktif'),
(65, '1373289626937', 'Aktif'),
(66, '3034750251426', 'Aktif'),
(67, '0673988825907', 'Aktif'),
(68, '0009108469505', 'Aktif'),
(69, '0311932462886', 'Aktif'),
(70, '2992551209717', 'Aktif'),
(71, '3763169082029', 'Aktif'),
(72, '8699866655812', 'Aktif'),
(73, '8279250120606', 'Aktif'),
(74, '4206342584313', 'Aktif'),
(75, '3445765259156', 'Aktif'),
(76, '7096100715321', 'Aktif'),
(77, '2900984479961', 'Aktif'),
(78, '1440989840242', 'Aktif'),
(79, '8655974959635', 'Aktif'),
(80, '8550720482815', 'Aktif'),
(81, '0793949063107', 'Aktif'),
(82, '5956389992003', 'Aktif'),
(83, '9721271923338', 'Aktif'),
(84, '2016726281759', 'Aktif'),
(85, '8766038752004', 'Aktif'),
(86, '1231231231231', 'Nonaktif');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `pegawai_id` int NOT NULL,
  `nip` varchar(18) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`pegawai_id`, `nip`) VALUES
(6, '985145249249002227'),
(7, '808643888288026934'),
(8, '305036306588304204'),
(9, '825281374802162747'),
(10, '815104969761034620'),
(11, '679486362934119217'),
(12, '611955958589108212'),
(13, '538423454361207404'),
(14, '038150988167395897'),
(15, '499717982724600681'),
(16, '395263014594203432'),
(17, '129027055223393340'),
(18, '232040070379068417'),
(19, '799919275024794027'),
(20, '480978852825683446'),
(21, '231269037263461801'),
(22, '441832497900204410'),
(23, '174480776059594561'),
(24, '708186678102381116'),
(25, '593258087992959618'),
(26, '640197413684259435'),
(27, '789440128882168076'),
(28, '637189907104612469'),
(29, '516818450104890886'),
(30, '797388992964942498'),
(31, '042166276722160983'),
(32, '985556413763226726'),
(33, '582399238390814443'),
(34, '756557216384182675'),
(35, '539696456168843544'),
(36, '249988502192076306'),
(37, '812385487384908667'),
(38, '165591211895020020'),
(39, '156341586562859879'),
(40, '558148006817392192'),
(41, '359990608547497113'),
(42, '847329283643348269'),
(43, '458171839772472347'),
(44, '912456571134333295'),
(45, '899936274752278336');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `nama` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `no_hp` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `alamat` text COLLATE utf8mb4_general_ci NOT NULL,
  `role` enum('Nelayan','Pegawai','Admin') COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `nama`, `username`, `password`, `email`, `no_hp`, `alamat`, `role`) VALUES
(1, 'Rizky Febrianto', 'admin', 'admin123', 'admin@gmail.com', '08119000101', 'Panakkukang, Makassar', 'Admin'),
(2, 'Nur Aisyah', 'nur.aisyah', '$2y$10$adMin02hash', 'nur.aisyah@gmail.com', '08129000102', 'Rappocini, Makassar', 'Admin'),
(3, 'Fajar Ramadhan', 'fajar.ramadhan', '$2y$10$adMin03hash', 'fajar.ramadhan@gmail.com', '08139000103', 'Mariso, Makassar', 'Admin'),
(4, 'Dewi Lestari', 'dewi.lestari', '$2y$10$adMin04hash', 'dewi.lestari@gmail.com', '08159000104', 'Tallo, Makassar', 'Admin'),
(5, 'Aditya Nugraha', 'aditya.nugraha', '$2y$10$adMin05hash', 'aditya.nugraha@gmail.com', '08169000105', 'Biringkanaya, Makassar', 'Admin'),
(6, 'Satria Rajawali', 'pegawai', 'pegawai123', 'pegawai@gmail.com', '08219000201', 'Minasa Upa, Makassar', 'Pegawai'),
(7, 'Muhammad Ilham', 'm.ilham', '$2y$10$pgw02', 'm.ilham@gmail.com', '08229000202', 'Veteran Selatan, Makassar', 'Pegawai'),
(8, 'Siti Rahma', 'siti.rahma', '$2y$10$pgw03', 'siti.rahma@gmail.com', '08239000203', 'Hertasning Baru, Makassar', 'Pegawai'),
(9, 'Firman Hidayat', 'firman.h', '$2y$10$pgw04', 'firman.h@gmail.com', '08249000204', 'Alauddin, Makassar', 'Pegawai'),
(10, 'Clara Salsabila', 'clara.salsa', '$2y$10$pgw05', 'clara@gmail.com', '08259000205', 'Griya Antang, Makassar', 'Pegawai'),
(11, 'Irfan Maulana', 'irfan.maulana', '$2y$10$pgw06', 'irfan.maulana@gmail.com', '08269000206', 'Somba Opu, Gowa', 'Pegawai'),
(12, 'Dina Kurnia', 'dina.kurnia', '$2y$10$pgw07', 'dina.kurnia@gmail.com', '08279000207', 'Malino, Gowa', 'Pegawai'),
(13, 'Agus Salim', 'agus.salim', '$2y$10$pgw08', 'agus.salim@gmail.com', '08289000208', 'Pattallassang, Takalar', 'Pegawai'),
(14, 'Nadya Khairunnisa', 'nadya.khair', '$2y$10$pgw09', 'nadya@gmail.com', '08299000209', 'Mariso, Makassar', 'Pegawai'),
(15, 'Randy Saputra', 'randy.saputra', '$2y$10$pgw10', 'randy.saputra@gmail.com', '08319000210', 'Gowa', 'Pegawai'),
(16, 'Yuni Kartika', 'yuni.kartika', '$2y$10$pgw11', 'yuni.kartika@gmail.com', '08329000211', 'Panakkukang, Makassar', 'Pegawai'),
(17, 'Budi Santoso', 'budi.santoso', '$2y$10$pgw12', 'budi.santoso@gmail.com', '08339000212', 'Wajo, Makassar', 'Pegawai'),
(18, 'Rizka Maulida', 'rizka.maulida', '$2y$10$pgw13', 'rizka.maulida@gmail.com', '08349000213', 'Gunung Bawakaraeng, Makassar', 'Pegawai'),
(19, 'Farhan Akbar', 'farhan.akbar', '$2y$10$pgw14', 'farhan.akbar@gmail.com', '08359000214', 'Mandai, Maros', 'Pegawai'),
(20, 'Mega Aprilia', 'mega.aprilia', '$2y$10$pgw15', 'mega.aprilia@gmail.com', '08369000215', 'Pangkajene, Pangkep', 'Pegawai'),
(21, 'Syifa Ramadhani', 'syifa.ramadhani', '$2y$10$pgw16', 'syifa@gmail.com', '08379000216', 'Barru', 'Pegawai'),
(22, 'Yoga Pratama', 'yoga.pratama', '$2y$10$pgw17', 'yoga.pratama@gmail.com', '08389000217', 'Parepare', 'Pegawai'),
(23, 'Indah Lestari', 'indah.lestari', '$2y$10$pgw18', 'indah.lestari@gmail.com', '08399000218', 'Bulukumba', 'Pegawai'),
(24, 'Rahmat Wijaya', 'rahmat.wijaya', '$2y$10$pgw19', 'rahmat.wijaya@gmail.com', '08409000219', 'Takalar', 'Pegawai'),
(25, 'Salsa Nabila', 'salsa.nabila', '$2y$10$pgw20', 'salsa@gmail.com', '08419000220', 'Selayar', 'Pegawai'),
(26, 'Iqbal Maulana', 'iqbal.maulana', '$2y$10$pgw21', 'iqbal.maulana@gmail.com', '08429000221', 'Pangkep', 'Pegawai'),
(27, 'Fina Kurniasih', 'fina.kurniasih', '$2y$10$pgw22', 'fina@gmail.com', '08439000222', 'Maros', 'Pegawai'),
(28, 'Dwiki Saputra', 'dwiki.saputra', '$2y$10$pgw23', 'dwiki@gmail.com', '08449000223', 'Jeneponto', 'Pegawai'),
(29, 'Hilda Putri', 'hilda.putri', '$2y$10$pgw24', 'hilda@gmail.com', '08459000224', 'Bantaeng', 'Pegawai'),
(30, 'Ferry Kurnia', 'ferry.kurnia', '$2y$10$pgw25', 'ferry.kurnia@gmail.com', '08469000225', 'Soppeng', 'Pegawai'),
(31, 'Aulia Rahman', 'aulia.rahman', '$2y$10$pgw26', 'aulia@gmail.com', '08479000226', 'Wajo', 'Pegawai'),
(32, 'Gilang Nugraha', 'gilang.nugraha', '$2y$10$pgw27', 'gilang@gmail.com', '08489000227', 'Bone', 'Pegawai'),
(33, 'Nisa Kharisma', 'nisa.kharisma', '$2y$10$pgw28', 'nisa@gmail.com', '08499000228', 'Sidrap', 'Pegawai'),
(34, 'Raka Pradana', 'raka.pradana', '$2y$10$pgw29', 'raka@gmail.com', '08509000229', 'Pinrang', 'Pegawai'),
(35, 'Aurel Safitri', 'aurel.safitri', '$2y$10$pgw30', 'aurel@gmail.com', '08519000230', 'Enrekang', 'Pegawai'),
(36, 'Dandi Firmansyah', 'dandi.firmansyah', '$2y$10$pgw31', 'dandi@gmail.com', '08529000231', 'Tana Toraja', 'Pegawai'),
(37, 'Nadif Alvaro', 'nadif.alvaro', '$2y$10$pgw32', 'nadif@gmail.com', '08539000232', 'Toraja Utara', 'Pegawai'),
(38, 'Helen Oktaviani', 'helen.oktaviani', '$2y$10$pgw33', 'helen@gmail.com', '08549000233', 'Luwu', 'Pegawai'),
(39, 'Reza Mahendra', 'reza.mahendra', '$2y$10$pgw34', 'reza@gmail.com', '08559000234', 'Luwu Timur', 'Pegawai'),
(40, 'Putra Arya', 'putra.arya', '$2y$10$pgw35', 'putra.arya@gmail.com', '08569000235', 'Luwu Utara', 'Pegawai'),
(41, 'Sania Rahardja', 'sania.rahardja', '$2y$10$pgw36', 'sania@gmail.com', '08579000236', 'Palopo', 'Pegawai'),
(42, 'Hafiz Ramli', 'hafiz.ramli', '$2y$10$pgw37', 'hafiz@gmail.com', '08589000237', 'Parepare', 'Pegawai'),
(43, 'Keysa Amelia', 'keysa.amelia', '$2y$10$pgw38', 'keysa@gmail.com', '08599000238', 'Galesong, Takalar', 'Pegawai'),
(44, 'Bayu Hidayat', 'bayu.hidayat', '$2y$10$pgw39', 'bayu@gmail.com', '08609000239', 'Bonto Bahari, Bulukumba', 'Pegawai'),
(45, 'Taufik Akbar', 'taufik.akbar', '$2y$10$pgw40', 'taufik@gmail.com', '08619000240', 'Paotere, Makassar', 'Pegawai'),
(46, 'Sayid Rafi', 'nelayan', 'nelayan123', 'nelayan@gmail.com', '08219100301', 'Selayar', 'Nelayan'),
(47, 'Basri La Ode', 'basri.laode', '$2y$10$nel02', 'basri.laode@gmail.com', '08229100302', 'Rajuni, Selayar', 'Nelayan'),
(48, 'Syamsul Alam', 'syamsul.alam', '$2y$10$nel03', 'syamsul.alam@gmail.com', '08239100303', 'Bira, Bulukumba', 'Nelayan'),
(49, 'La Ode Rahman', 'laode.rahman', '$2y$10$nel04', 'laode.rahman@gmail.com', '08249100304', 'Tanakeke, Takalar', 'Nelayan'),
(50, 'Hasanuddin', 'hasanuddin', '$2y$10$nel05', 'hasanuddin@gmail.com', '08259100305', 'Galesong, Takalar', 'Nelayan'),
(51, 'Amiruddin', 'amiruddin', '$2y$10$nel06', 'amiruddin@gmail.com', '08269100306', 'Paotere, Makassar', 'Nelayan'),
(52, 'Rahmat H', 'rahmat.h', '$2y$10$nel07', 'rahmat.h@gmail.com', '08279100307', 'Punaga, Takalar', 'Nelayan'),
(53, 'Sudirman', 'sudirman', '$2y$10$nel08', 'sudirman@gmail.com', '08289100308', 'Barru', 'Nelayan'),
(54, 'Arifin', 'arifin.nel', '$2y$10$nel09', 'arifin@gmail.com', '08299100309', 'Garongkong, Barru', 'Nelayan'),
(55, 'La Rani', 'larani', '$2y$10$nel10', 'larani@gmail.com', '08309100310', 'Sabutung, Pangkep', 'Nelayan'),
(56, 'Ramli', 'ramli.nel', '$2y$10$nel11', 'ramli@gmail.com', '08319100311', 'Liukang Tupabbiring, Pangkep', 'Nelayan'),
(57, 'Musakkir', 'musakkir', '$2y$10$nel12', 'musakkir@gmail.com', '08329100312', 'Samalona, Makassar', 'Nelayan'),
(58, 'Usman', 'usman.nel', '$2y$10$nel13', 'usman@gmail.com', '08339100313', 'Pabbiringa, Takalar', 'Nelayan'),
(59, 'Muchtar', 'muchtar', '$2y$10$nel14', 'muchtar@gmail.com', '08349100314', 'Bontosunggu, Jeneponto', 'Nelayan'),
(60, 'Ilham S', 'ilham.s', '$2y$10$nel15', 'ilham.s@gmail.com', '08359100315', 'Panaikang, Maros', 'Nelayan'),
(61, 'Syahrul', 'syahrul', '$2y$10$nel16', 'syahrul@gmail.com', '08369100316', 'Tamalanrea, Makassar', 'Nelayan'),
(62, 'Ridwan', 'ridwan.nel', '$2y$10$nel17', 'ridwan@gmail.com', '08379100317', 'Bonto Bahari, Bulukumba', 'Nelayan'),
(63, 'Jamaluddin', 'jamaluddin', '$2y$10$nel18', 'jamaluddin@gmail.com', '08389100318', 'Kanreapia, Gowa', 'Nelayan'),
(64, 'La Ode Yusuf', 'laode.yusuf', '$2y$10$nel19', 'laode.yusuf@gmail.com', '08399100319', 'Karanrang, Pangkep', 'Nelayan'),
(65, 'Sapri', 'sapri', '$2y$10$nel20', 'sapri@gmail.com', '08409100320', 'Langkai, Makassar', 'Nelayan'),
(66, 'Herman', 'herman.nel', '$2y$10$nel21', 'herman@gmail.com', '08419100321', 'Barrang Lompo, Makassar', 'Nelayan'),
(67, 'Yasir', 'yasir.nel', '$2y$10$nel22', 'yasir@gmail.com', '08429100322', 'Bontoa, Maros', 'Nelayan'),
(68, 'Andika', 'andika.nel', '$2y$10$nel23', 'andika@gmail.com', '08439100323', 'Selayar', 'Nelayan'),
(69, 'Sahabuddin', 'sahabuddin', '$2y$10$nel24', 'sahabuddin@gmail.com', '08449100324', 'Takalar', 'Nelayan'),
(70, 'Fauzi', 'fauzi.nel', '$2y$10$nel25', 'fauzi@gmail.com', '08459100325', 'Mariso, Makassar', 'Nelayan'),
(71, 'Sabri', 'sabri.nel', '$2y$10$nel26', 'sabri@gmail.com', '08469100326', 'Paotere, Makassar', 'Nelayan'),
(72, 'Rusdi', 'rusdi.nel', '$2y$10$nel27', 'rusdi@gmail.com', '08479100327', 'Bulukumba', 'Nelayan'),
(73, 'Halik', 'halik.nel', '$2y$10$nel28', 'halik@gmail.com', '08489100328', 'Pangkep', 'Nelayan'),
(74, 'Lukman', 'lukman.nel', '$2y$10$nel29', 'lukman@gmail.com', '08499100329', 'Lappa, Sinjai', 'Nelayan'),
(75, 'Jufri', 'jufri.nel', '$2y$10$nel30', 'jufri@gmail.com', '08509100330', 'Bone', 'Nelayan'),
(76, 'Irwan', 'irwan.nel', '$2y$10$nel31', 'irwan@gmail.com', '08519100331', 'Parepare', 'Nelayan'),
(77, 'Askar', 'askar.nel', '$2y$10$nel32', 'askar@gmail.com', '08529100332', 'Pinrang', 'Nelayan'),
(78, 'Baso', 'baso.nel', '$2y$10$nel33', 'baso@gmail.com', '08539100333', 'Barru', 'Nelayan'),
(79, 'Rahman', 'rahman.nel', '$2y$10$nel34', 'rahman@gmail.com', '08549100334', 'Soppeng', 'Nelayan'),
(80, 'Usup', 'usup.nel', '$2y$10$nel35', 'usup@gmail.com', '08559100335', 'Wajo', 'Nelayan'),
(81, 'Arman', 'arman.nel', '$2y$10$nel36', 'arman@gmail.com', '08569100336', 'Jeneponto', 'Nelayan'),
(82, 'Yusuf', 'yusuf.nel', '$2y$10$nel37', 'yusuf@gmail.com', '08579100337', 'Bantaeng', 'Nelayan'),
(83, 'Daeng Tiro', 'daeng.tiro', '$2y$10$nel38', 'daeng.tiro@gmail.com', '08589100338', 'Takalar', 'Nelayan'),
(84, 'Haris', 'haris.nel', '$2y$10$nel39', 'haris@gmail.com', '08599100339', 'Maros', 'Nelayan'),
(85, 'Akbar', 'akbar.nel', '$2y$10$nel40', 'akbar@gmail.com', '08609100340', 'Makassar', 'Nelayan'),
(86, 'Ranii', 'user1', '$2a$10$a7rDco29vI6zbCLWWlO3feGLLZJSkqS4U1cVlz44WU44co/g6NzJm', 'user1@gmail.com', '081234567890', 'Samarinda, Kaltim', NULL),
(87, 'Arya Seloka', 'arya.seloka', '$2a$10$8nqzjRdIAkIuOO40O2TL3uokIIWysWIvQgEDhbTVtO6AeYZYzqdV2', 'arya.seloka@gmail.com', '08134537284', 'Balikpapan', NULL),
(88, 'mada sekai', 'madas', '$2a$10$xWBG.VXpYcgKj7XHySzpEuHM3XscakfInjRhMgaa/zB/P/j.CKneW', 'mada@gmail.com', '081376428346', 'Makassar', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `wilayah_tangkap`
--

CREATE TABLE `wilayah_tangkap` (
  `wilayah_id` int NOT NULL,
  `koordinat` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `nama_wilayah` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wilayah_tangkap`
--

INSERT INTO `wilayah_tangkap` (`wilayah_id`, `koordinat`, `nama_wilayah`) VALUES
(1, '-5.140,119.320', 'Perairan Paotere, Makassar'),
(2, '-5.180,119.360', 'Pesisir Losari, Makassar'),
(3, '-5.220,119.400', 'Laut Galesong, Takalar'),
(4, '-5.260,119.440', 'Teluk Takalar'),
(5, '-5.300,119.480', 'Perairan Punaga, Takalar'),
(6, '-5.340,119.520', 'Selat Makassar (Utara Galesong)'),
(7, '-5.380,119.560', 'Pulau Lae-Lae'),
(8, '-5.420,119.600', 'Pulau Samalona'),
(9, '-5.460,119.640', 'Pulau Kodingareng'),
(10, '-5.500,119.680', 'Pulau Langkai'),
(11, '-5.120,119.500', 'Perairan Barrang Lompo'),
(12, '-5.160,119.540', 'Pulau Barrang Caddi'),
(13, '-5.200,119.580', 'Perairan Sabutung, Pangkep'),
(14, '-5.240,119.620', 'Pulau Satando, Pangkep'),
(15, '-5.280,119.660', 'Pulau Kapoposang'),
(16, '-5.320,119.700', 'Perairan Liukang Tupabbiring'),
(17, '-5.360,119.740', 'Pulau Sagara'),
(18, '-5.400,119.780', 'Perairan Balang Lompo'),
(19, '-5.440,119.820', 'Pulau Panikiang, Barru'),
(20, '-5.480,119.860', 'Perairan Barru'),
(21, '-5.060,119.360', 'Perairan Biringkassi, Maros'),
(22, '-5.100,119.400', 'Pesisir Bantimurung, Maros'),
(23, '-5.140,119.440', 'Muara Sungai Tallo'),
(24, '-5.180,119.480', 'Pantai Mariso, Makassar'),
(25, '-5.220,119.520', 'Pesisir Sudiang, Makassar'),
(26, '-5.260,119.560', 'Pantai Tanakeke, Takalar'),
(27, '-5.300,119.600', 'Pulau Sanrobengi'),
(28, '-5.340,119.640', 'Pulau Tanakeke Barat'),
(29, '-5.380,119.680', 'Laut Takalar Selatan'),
(30, '-5.420,119.720', 'Pulau Puntondo, Takalar'),
(31, '-5.460,119.760', 'Pantai Lappa, Sinjai'),
(32, '-5.500,119.800', 'Perairan Bulukumba'),
(33, '-5.540,119.840', 'Pantai Bira, Bulukumba'),
(34, '-5.580,119.880', 'Pulau Liukang Loe, Bulukumba'),
(35, '-5.620,119.920', 'Laut Kajang, Bulukumba'),
(36, '-5.660,119.960', 'Perairan Selayar Utara'),
(37, '-5.700,120.000', 'Pulau Pasi, Selayar'),
(38, '-5.740,120.040', 'Pulau Gusung, Selayar'),
(39, '-5.780,120.080', 'Perairan Benteng, Selayar'),
(40, '-5.820,120.120', 'Pulau Jampea, Selayar');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD UNIQUE KEY `admin_id` (`admin_id`);

--
-- Indexes for table `kapal`
--
ALTER TABLE `kapal`
  ADD PRIMARY KEY (`no_registrasi`),
  ADD KEY `fk_kapal_nelayan` (`nelayan_id`);

--
-- Indexes for table `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`laporan_id`),
  ADD KEY `fk_laporan_nelayan` (`id_nelayan`),
  ADD KEY `fk_laporan_pegawai` (`id_pegawai`),
  ADD KEY `fk_laporan_kapal` (`id_kapal`),
  ADD KEY `fk_laporan_wilayah` (`id_wilayah`);

--
-- Indexes for table `laporan_pengaduan`
--
ALTER TABLE `laporan_pengaduan`
  ADD PRIMARY KEY (`pengaduan_id`),
  ADD KEY `fk_pengaduan_user` (`users_id`),
  ADD KEY `fk_pengaduan_pegawai` (`pegawai_id`);

--
-- Indexes for table `nelayan`
--
ALTER TABLE `nelayan`
  ADD UNIQUE KEY `nelayan_id` (`nelayan_id`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD UNIQUE KEY `pegawai_id` (`pegawai_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `wilayah_tangkap`
--
ALTER TABLE `wilayah_tangkap`
  ADD PRIMARY KEY (`wilayah_id`),
  ADD UNIQUE KEY `uq_wilayah_nama` (`nama_wilayah`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `laporan`
--
ALTER TABLE `laporan`
  MODIFY `laporan_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `laporan_pengaduan`
--
ALTER TABLE `laporan_pengaduan`
  MODIFY `pengaduan_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT for table `wilayah_tangkap`
--
ALTER TABLE `wilayah_tangkap`
  MODIFY `wilayah_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admin_user` FOREIGN KEY (`admin_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `kapal`
--
ALTER TABLE `kapal`
  ADD CONSTRAINT `fk_kapal_nelayan` FOREIGN KEY (`nelayan_id`) REFERENCES `nelayan` (`nelayan_id`);

--
-- Constraints for table `laporan`
--
ALTER TABLE `laporan`
  ADD CONSTRAINT `fk_laporan_kapal` FOREIGN KEY (`id_kapal`) REFERENCES `kapal` (`no_registrasi`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_laporan_nelayan` FOREIGN KEY (`id_nelayan`) REFERENCES `nelayan` (`nelayan_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_laporan_pegawai` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`pegawai_id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_laporan_wilayah` FOREIGN KEY (`id_wilayah`) REFERENCES `wilayah_tangkap` (`wilayah_id`) ON DELETE CASCADE;

--
-- Constraints for table `laporan_pengaduan`
--
ALTER TABLE `laporan_pengaduan`
  ADD CONSTRAINT `fk_pengaduan_pegawai` FOREIGN KEY (`pegawai_id`) REFERENCES `pegawai` (`pegawai_id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_pengaduan_user` FOREIGN KEY (`users_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `nelayan`
--
ALTER TABLE `nelayan`
  ADD CONSTRAINT `fk_nelayan_user` FOREIGN KEY (`nelayan_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD CONSTRAINT `fk_pegawai_user` FOREIGN KEY (`pegawai_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
