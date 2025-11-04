# 🌊 SEAMantau
*Smart Ecosystem and Activity Monitoring for Marine Conservation*

<img width="609" height="609" alt="Logo SEAMantau" src="https://github.com/user-attachments/assets/a896b410-a418-4cda-b2ba-9cf9afe8ba22" />



## 💡 Deskripsi Program
Merupakan sistem digital yang dirancang untuk memantau aktivitas perikanan tangkap dan kondisi ekosistem pesisir secara real-time.
Melalui integrasi data, teknologi, dan partisipasi masyarakat, SEAMantau membantu mencatat alat tangkap, lokasi operasi, serta laporan kerusakan lingkungan laut.
Program ini mendukung konservasi keanekaragaman hayati laut dan pengelolaan pesisir berkelanjutan, sejalan dengan SDG 14: Life Below Water dan 
Focus Area Consevation of Marine Biodeversity and Coastal Ecosystem

## 🧩 Fitur Program
- **Login & Validasi Data**  
Setiap pengguna harus login menggunakan akun yang telah terdaftar. Sistem memvalidasi data pengguna untuk memastikan keamanan dan hak akses sesuai perannya.

- **Register**  
User dan Nelayan dapat mendaftarkan akun dengan mengisi data diri.

- **Membuat Laporan Penangkapan**  
Nelayan memasukkan data seperti waktu berangkat, alat tangkap yang digunakan, hasil tangkapan, dan wilayah operasi. Laporan ini menjadi sumber utama data aktivitas laut.

- **Membuat Laporan Pengaduan**  
Fitur ini memungkinkan pengguna mengirimkan laporan terkait pencemaran, atau kerusakan ekosistem pesisir lengkap dengan bukti foto dan titik koordinat.

- **Melihat Status Laporan**  
User dan Nelayan dapat melihat apakah laporan mereka menunggu, diverifikasi, atau ditolak, termasuk catatan dari pegawai pelabuhan.

- **Verifikasi Laporan**  
Pegawai pelabuhan atau instansi terkait meninjau laporan penangkapan dan pengaduan, lalu memberikan status akhir beserta catatan koreksi jika diperlukan.

- **Mengelola Wilayah Tangkap**  
Fitur ini mengatur koordinat wilayah tangkap, zona konservasi, dan pelabuhan yang digunakan sebagai acuan legalitas dan pengawasan aktivitas penangkapan.

- **Melihat & Memperbarui Profil**  
Pengguna dapat memperbarui data seperti kontak, alamat, dan informasi izin.

- **Manajemen Data Kapal**  
Data meliputi nomor registrasi, jenis kapal, alat tangkap, dan status operasional. Admin dapat meninjau dan memperbarui data untuk konsistensi database nasional.

- **Invalid Login**  
Sistem memberikan peringatan apabila username atau password salah, membantu meningkatkan keamanan sistem dan mencegah akses tidak sah.

## 🧠 Penerapan OOP (Object Oriented Programming)
- **Encapsulation**  
  Enkapsulasi diterapkan dengan menjadikan atribut bersifat private dan menyediakan akses melalui metode getter dan setter. Hal ini memastikan data hanya bisa diubah melalui mekanisme validasi yang terkontrol.
  ```
  // model/User.java
  public class User {
      private String nama;
      private String username;
      private String email;

    // getter dan setter
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
  }
  ```
  ```
  // controller/AuthController.java
  public class AuthController {
      private final UserDAO userDAO = new UserDAO(); // atribut dienkapsulasi

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) return null;
        // validasi login dilakukan di sini
        return user;
    }
  }
  ```
  
- **Inheritance**  
  LoginLimiter bertindak sebagai kelas dasar yang mendefinisikan perilaku umum pembatas login. Kelas AttemptLimiter mewarisi dan menyesuaikan perilaku tersebut.
  ```
  // controller/LoginLimiter.java
  public abstract class LoginLimiter {
      protected int failedAttempts = 0;
      protected final int MAX_LOGIN_ATTEMPTS = 3;

    public abstract void onFailedAttempt();
    public abstract void onLimitReached();
  }
  ```
  ```
  // controller/AttemptLimiter.java
  public class AttemptLimiter extends LoginLimiter {
      @Override
      public void onFailedAttempt() {
          System.out.println("Percobaan login gagal.");
    }

    @Override
    public void onLimitReached() {
        System.out.println("Anda telah mencapai batas percobaan login.");
    }
  }
  ```
- **Polymorphism**  
  Metode abstrak onFailedAttempt() pada LoginLimiter dioverride di AttemptLimiter dengan implementasi berbeda. Begitu pula, metode save() pada DAO bisa menerima berbagai jenis entity seperti Pegawai, Nelayan, atau User.
  ```
  // controller/LoginLimiter.java
  public abstract class LoginLimiter {
      public abstract void onFailedAttempt();
  }
  
  // controller/AttemptLimiter.java
  public class AttemptLimiter extends LoginLimiter {
      @Override
      public void onFailedAttempt() {
          System.out.println("Login gagal. Coba lagi!");
      }
  }
  ```
  ```
  // DAO polymorphism
  userDAO.save(new Pegawai());
  userDAO.save(new Nelayan());
  ```
  
- **Abstraction**
  Controller tidak perlu tahu bagaimana file diunggah ke Google Drive atau bagaimana Hibernate bekerja. Semua detail teknis disembunyikan oleh GoogleDriveService dan DAO.
  ```
  // controller/LaporanPengaduanController.java
  public class LaporanPengaduanController {
      private final LaporanPengaduanDAO dao = new LaporanPengaduanDAO();

    public void buatLaporanBaru(LaporanPengaduan laporan, File fotoFile, String driveFolderId) throws Exception {
        String fotoUrl = GoogleDriveService.uploadFile(fotoFile.getAbsolutePath(), driveFolderId);
        laporan.setDokumentasi(fotoUrl);
        dao.save(laporan); // detail query disembunyikan oleh DAO
    }
  }
  ```
- **Interface**
  DAO pattern menggunakan interface GenericDAO yang mengatur kontrak dasar setiap operasi database. Controller hanya tahu fungsi save() atau update() tanpa perlu tahu bagaimana detail SQL-nya.
  ```
  // dao/GenericDAO.java
  public interface GenericDAO<T> {
      void save(T entity);
      void update(T entity);
      void delete(T entity);
  }
  ```
  ```
  // dao/UserDAO.java
  public class UserDAO implements GenericDAO<User> {
      @Override
      public void save(User entity) {
          // Implementasi Hibernate
      }
  }
  ```
## 🗂️ Struktur Folder
```
  SEAMantau/
  │
  ├── controller/
  │   ├── AuthController.java
  │   ├── AttemptLimiter.java
  │   ├── LoginLimiter.java
  │   ├── LaporanPengaduanController.java
  │   ├── LaporanPenangkapanController.java
  │   ├── PegawaiController.java
  │   ├── NelayanController.java
  │   ├── WilayahController.java
  │   ├── VerifikasiController.java
  │   └── UserController.java
  │
  ├── dao/
  │   ├── KapalDAO.java
  │   ├── LaporanDAO.java
  │   ├── LaporanPengaduanDAO.java
  │   ├── NelayanDAO.java
  │   ├── UserDAO.java
  │   └── WilayahTangkapDAO.java
  │
  ├── image/
  │   └── (berisi ikon atau aset visual aplikasi)
  │
  ├── model/
  │   ├── Admin.java
  │   ├── Kapal.java
  │   ├── Laporan.java
  │   ├── LaporanPengaduan.java
  │   ├── Nelayan.java
  │   ├── Pegawai.java
  │   ├── User.java
  │   └── WilayahTangkap.java
  │   Main.java
  │
  ├── model/enums/
  │   ├── JenisPengaduan.java
  │   ├── KategoriKapal.java
  │   ├── Role.java
  │   ├── StatusAdmin.java
  │   ├── StatusKapal.java
  │   ├── StatusLaporan.java
  │   ├── StatusNelayan.java
  │   └── StatusPengaduan.java
  │
  ├── util/
  │   ├── DBConnection.java
  │   ├── GoogleDriveService.java
  │   ├── HibernateUtil.java
  │   └── credentials.json
  │
  └── view/
      ├── Main.java
      ├── LandingPage.java
      ├── LoginPage.java
      ├── SignUpUser.java
      ├── MenuAdmin.java
      ├── MenuPegawai.java
      ├── MenuNelayan.java
      ├── LaporanPenangkapan.java
      ├── FormLaporanPengaduan.java
      ├── ProfilUser.java
      ├── ProfilPegawai.java
      ├── VerifikasiLaporanPengaduan.java
      ├── MapPicker.java
      └── ManajemenWilayahTangkap.java
```
- `controller`	Berisi logika bisnis dan penghubung antara view dan model. Mengatur alur data, autentikasi, validasi, dan aksi CRUD.
- `dao`	Data Access Object, menangani koneksi dan transaksi dengan database menggunakan Hibernate ORM.
- `image`	Menyimpan file aset grafis seperti logo, ikon, atau ilustrasi aplikasi.
- `model`	Berisi representasi entitas dari sistem (User, Kapal, Laporan, dsb) yang terhubung dengan tabel database.
- `model.enums`	Menyimpan enumerasi (tipe data tetap) seperti kategori kapal, status laporan, atau peran pengguna.
- `util`	Kelas pendukung untuk koneksi database, integrasi API eksternal (Google Drive), dan konfigurasi Hibernate.
- `view`	Lapisan antarmuka pengguna (UI) berbasis Java Swing — tempat pengguna berinteraksi dengan aplikasi.

## ⚙️ Library & Framework

<details>
  <summary>1. Framework Utama</summary>

| Framework / Library                                               | Versi | Fungsi Utama                                                                                                                |
| ----------------------------------------------------------------- | ----- | --------------------------------------------------------------------------------------------------------------------------- |
| Hibernate ORM (`hibernate-core-5.6.15.Final.jar`)                 | 5.6.x | Framework ORM untuk menghubungkan objek Java dengan tabel database (ORM). Mempermudah proses CRUD tanpa query SQL langsung. |
| Jakarta Persistence API (JPA) (`jakarta.persistence-api-2.2.jar`) | 2.2   | Standar spesifikasi untuk pemetaan objek ke database yang digunakan oleh Hibernate.                                         |
| MySQL Connector/J (`mysql-connector-j-9.4.0.jar`)                 | 9.4   | Driver JDBC untuk menghubungkan aplikasi dengan database MySQL.                                                             |
| Java Swing / AWT                                                  | -     | Framework GUI bawaan Java yang digunakan untuk membuat antarmuka pengguna pada package `view`.                              |
| Log4j (`log4j-1.2.17.jar`)                                        | 1.2   | Framework logging untuk mencatat error, event sistem, dan debugging aplikasi.                                               |

</details>

<details>
  <summary>2. Integrasi Cloud (Google Drive API)</summary>

| Library                                                                                              | Versi | Fungsi Utama                                                                                    |
| ---------------------------------------------------------------------------------------------------- | ----- | ----------------------------------------------------------------------------------------------- |
| Google API Client (`google-api-client-1.34.1.jar`)                                                   | 1.34  | Library utama untuk mengakses berbagai layanan Google API.                                      |
| Google Drive Service API (`google-api-services-drive-v3-rev20251019-2.0.0.jar`)                      | v3    | Menyediakan endpoint untuk upload, download, dan manajemen file di Google Drive.                |
| Google OAuth Client (`google-oauth-client-java6-1.39.0.jar`, `google-oauth-client-jetty-1.34.1.jar`) | 1.39  | Menangani autentikasi OAuth 2.0 agar aplikasi dapat mengakses akun Google dengan izin pengguna. |
| Google HTTP Client + Gson (`google-http-client-gson-1.43.3.jar`)                                     | 1.43  | Menangani komunikasi HTTP dan parsing JSON antara aplikasi dan API Google.                      |
| Guava (`guava-31.1-jre.jar`)                                                                         | 31.1  | Library utilitas buatan Google untuk caching, koleksi, dan concurrency.                         |
| Gson (`gson-2.10.1.jar`)                                                                             | 2.10  | Digunakan untuk konversi data JSON, seperti file `credentials.json` Google API.                 |

</details>

<details>
  <summary>3. Database & ORM Pendukung</summary>

| Library                                                                                    | Versi | Fungsi                                                                         |
| ------------------------------------------------------------------------------------------ | ----- | ------------------------------------------------------------------------------ |
| JPA & Hibernate Commons (`hibernate-commons-annotations.jar`, `hibernate-jpa-2.1-api.jar`) | -     | Dependency Hibernate untuk anotasi entity dan pemetaan ORM.                    |
| Dom4J (`dom4j-2.1.3.jar`)                                                                  | 2.1   | Parser XML yang digunakan oleh Hibernate untuk membaca konfigurasi `.cfg.xml`. |
| JAXB (Java Architecture for XML Binding) (`jaxb-api-2.3.1.jar`, `jaxb-runtime-2.3.1.jar`)  | 2.3   | Memproses file konfigurasi XML dan serialisasi data.                           |
| JBoss Logging (`jboss-logging-3.5.0.Final.jar`)                                            | 3.5   | Dependency Hibernate untuk logging internal.                                   |
| Commons Logging (`commons-logging-1.2.jar`)                                                | 1.2   | Sistem logging umum yang digunakan di beberapa library.                        |
| ANTLR (`antlr-2.7.7.jar`)                                                                  | 2.7   | Parser HQL yang digunakan oleh Hibernate untuk menerjemahkan query.            |

</details>

<details>
  <summary>4. Security dan Utility</summary>

| Library                                                               | Versi    | Fungsi                                                                                                    |
| --------------------------------------------------------------------- | -------- | --------------------------------------------------------------------------------------------------------- |
| JBCrypt (`jbcrypt-0.4.jar`)                                           | 0.4      | Untuk enkripsi password pengguna sebelum disimpan ke database (pada `AuthController`).                    |
| FlatLaf (`flatlaf-3.6.1.jar`)                                         | 3.6      | Library UI modern yang membuat tampilan Swing lebih bersih dan elegan.                                    |
| JNA (Java Native Access) (`jna-5.5.0.jar`, `jna-platform-5.18.1.jar`) | 5.5–5.18 | Memungkinkan akses fungsi native OS, digunakan untuk fitur seperti `MapPicker` dan integrasi sistem file. |
| AbsoluteLayout (`AbsoluteLayout.jar`)                                 | -        | Layout manager bawaan NetBeans GUI builder, digunakan untuk pengaturan posisi komponen antarmuka.         |

</details>

<details>
  <summary>5. Library Tambahan (Dependency Hibernate & Google)</summary>

| Library                                                                             | Fungsi                                                        |
| ----------------------------------------------------------------------------------- | ------------------------------------------------------------- |
| ByteBuddy (`byte-buddy-1.12.22.jar`)                                                | Membantu Hibernate melakukan bytecode enhancement runtime.    |
| Javassist (`javassist-3.29.2-GA.jar`)                                               | Alternatif bytecode manipulator untuk ORM.                    |
| Grpc & Core Context (`grpc-core-1.54.0.jar`, `grpc-context-1.54.0.jar`)             | Dependency internal Google API Client.                        |
| Opencensus (`opencensus-api-0.31.1.jar`, `opencensus-contrib-http-util-0.31.1.jar`) | Monitoring dan telemetry library untuk request ke Google API. |
| Jandex (`jandex-2.4.2.Final.jar`)                                                   | Digunakan Hibernate untuk indeks anotasi class Java.          |

</details>

<details>
  <summary>6. Runtime Environment</summary>

| Komponen   | Versi   | Fungsi                                                                                     |
| ---------- | ------- | ------------------------------------------------------------------------------------------ |
| **JDK 24** | Default | Versi utama Java yang digunakan untuk menjalankan aplikasi. Menyediakan semua API Java SE. |

</details>

## 💻 Cara Menggunakan Program

Program SEAMantau memiliki empat role utama pengguna, yaitu:
- Admin (Pengelola utama sistem)
- Pegawai (Verifikator dan pengelola laporan)
- User biasa (Pembuat laporan pengaduan)
- Nelayan (Pembuat laporan penangkapan dan pengaduan

Setiap role memiliki hak akses dan tampilan GUI yang berbeda. Berikut penjelasan lengkap mengenai cara penggunaan program untuk masing-masing role, disertai dengan tampilan GUI-nya.
### Halaman Utama ###
Menampilkan logo, judul, serta deskripsi singkat mengenai tujuan program untuk pemantauan ekosistem laut dan dilengkapi dengan tombol Sign Up dan Login sebagai navigasi awal.

<img width="784" height="478" alt="image" src="https://github.com/user-attachments/assets/f0f26ac9-89b9-483a-9089-fcfd7d193b6b" />


### Halaman Registrasi ###
Berisi kolom Nama, Username, Password, Email, Nomor Telepon, dan Alamat. Setelah mengisi data dengan lengkap, pengguna menekan tombol Buat Akun untuk registrasi. Selain itu, tersedia juga opsi “Login” bagi pengguna yang sudah memiliki akun.

<img width="815" height="490" alt="image" src="https://github.com/user-attachments/assets/d68addfa-bcc9-477a-a91a-6b4383ad1792" />


### Halaman Masuk ###
Pengguna mengisi username dan password, lalu menekan tombol Submit untuk melanjutkan ke dashboard sesuai peran masing-masing. Terdapat juga tautan “Sign Up” untuk pengguna baru.

<img width="784" height="457" alt="image" src="https://github.com/user-attachments/assets/cead5c8f-48fd-480c-a405-b07fcc4bbf4e" />


### 1. Admin ###
---
#### ↳ Menu ####
Setelah berhasil login, Admin akan diarahkan ke halaman utama yang menampilkan sambutan serta dua pilihan menu utama. Admin dapat memilih untuk membuka menu Manajemen Akun Pegawai atau Manajemen Wilayah Tangkap untuk melanjutkan pengelolaan data. Di bagian kanan atas juga terdapat tombol Keluar yang digunakan untuk logout dari sistem.

<img width="780" height="477" alt="image" src="https://github.com/user-attachments/assets/340a8c51-c41e-4097-aea6-c24b6a4ac44f" />


---
#### ↳ Manajemen Akun Pegawai ####
Pada halaman ini, Admin dapat mengelola data pegawai yang terdaftar di sistem. Formulir berisi kolom Nama, Username, Password, NIP, Email, Nomor Telepon, dan Alamat yang dapat diisi untuk menambahkan pegawai baru. Setelah data diisi dengan lengkap, Admin dapat menekan tombol Tambah untuk menyimpan data atau Edit untuk memperbarui data yang sudah ada. Daftar pegawai yang tersimpan akan muncul di tabel di bagian bawah halaman.

<img width="796" height="496" alt="image" src="https://github.com/user-attachments/assets/68b9db67-53ff-4bf9-b47a-e0322ff2edb9" />


---
#### ↳ Manajemen Wilayah Tangkap ####
Halaman ini digunakan Admin untuk menambahkan atau memperbarui data wilayah tangkap ikan. Admin dapat mengisi kolom Nama Wilayah Tangkap dan Titik Koordinat, lalu menekan tombol Tambah untuk menyimpan atau Edit untuk memperbarui data yang sudah ada. Setiap wilayah yang tersimpan akan ditampilkan pada tabel di bawah form input agar mudah dilihat dan dikelola.

<img width="796" height="498" alt="image" src="https://github.com/user-attachments/assets/e35db40c-82a7-4f53-a931-464662378a37" />



### 2. Pegawai ###
---
#### ↳ Menu ####
Setelah login, Pegawai akan diarahkan ke halaman utama yang menampilkan sambutan serta dua pilihan menu utama, yaitu Verifikasi Laporan dan Manajemen Akun Nelayan. Dari sini, Pegawai dapat memilih menu sesuai tugasnya. Terdapat juga tombol Profil untuk melihat atau memperbarui data pribadi, serta tombol Keluar untuk logout dari sistem.

<img width="781" height="478" alt="image" src="https://github.com/user-attachments/assets/bfb7790d-65f6-4771-9d33-8a6036bbae60" />


---
#### ↳ Profil Pegawai ####
Halaman ini menampilkan informasi pribadi pegawai yang sedang login dan berisi kolom Nama, Username, Password, Email, Nomor Handphone, Alamat, dan NIP. Pegawai dapat memperbarui datanya jika terjadi perubahan, lalu menekan tombol Simpan Perubahan untuk menyimpan hasilnya.

<img width="796" height="495" alt="image" src="https://github.com/user-attachments/assets/4f0a9c02-154e-438d-8273-9352aeba5e36" />


---
#### ↳ Verifikasi Laporan ####
Pada halaman ini, Pegawai dapat meninjau laporan dari Nelayan/User. Terdapat dua bagian utama: Laporan Pengaduan dan Laporan Penangkapan. Setiap laporan menampilkan detail seperti tanggal, deskripsi, titik koordinat, dan status. Pegawai dapat memperbarui status laporan atau menambahkan catatan hasil verifikasi.

<img width="796" height="494" alt="image" src="https://github.com/user-attachments/assets/6a3ec98e-1a43-417a-a661-45abc5a07e07" />


#### ↳ Verifikasi Laporan Pengaduan ####
Halaman ini digunakan untuk melihat atau mengverifikasi laporan pengaduan yang diajukan oleh nelayan maupun user biasa.
Formulirnya berisi kolom Nama, Deskripsi, Titik Koordinat, Catatan, Status, dan Bukti Foto. Pegawai juga dapat membuka file pendukung untuk memastikan validitas laporan sebelum melakukan pembaruan status.

<img width="766" height="499" alt="image" src="https://github.com/user-attachments/assets/12030c35-d924-4b0f-85fc-0696d692ea4f" />


#### ↳ Verifikasi Laporan Penangkapan ####
Halaman ini menampilkan laporan hasil tangkapan dari nelayan. Formulir berisi kolom Nama, Nama Pelabuhan, Titik Koordinat Wilayah Tangkap, Waktu Berangkat, Waktu Berlabuh, Alat Tangkap, Deskripsi, dan Catatan. Pegawai dapat membuka bukti foto yang dikirim nelayan dan memperbarui status laporan sesuai hasil pengecekan.

<img width="756" height="485" alt="image" src="https://github.com/user-attachments/assets/4c4f6f82-2794-4df3-bff2-43564245f70f" />


---
#### ↳ Manajemen Akun Nelayan ####
Halaman ini digunakan Pegawai untuk memverifikasi dan mengelola akun milik nelayan. Terdapat dua tabel utama, yaitu Daftar Akun Nelayan dan Daftar Kapal Nelayan. Pegawai dapat menekan tombol Acc untuk menyetujui pendaftaran atau Tolak jika data tidak valid. Selain itu, Tersedia juga tombol Open untuk melihat detail foto kapal yang diajukan.

<img width="795" height="497" alt="image" src="https://github.com/user-attachments/assets/312a34fd-4cee-4431-ba18-41d3a7297519" />

<img width="796" height="495" alt="image" src="https://github.com/user-attachments/assets/e9741b0c-990f-4c9e-9b09-e3689bd02d6c" />


### 3. User Biasa ###
---
#### ↳ Menu ####
Setelah berhasil login, User akan diarahkan ke halaman utama yang menampilkan dua menu pilihan, yaitu Buat Pengaduan dan Daftar Sebagai Nelayan. Dari halaman ini, User dapat memilih untuk melaporkan pengaduan baru atau mendaftar menjadi akun nelayan. Terdapat juga tombol Profil untuk mengubah data pribadi dan tombol Keluar untuk logout dari sistem.

<img width="782" height="474" alt="image" src="https://github.com/user-attachments/assets/8dce2be5-579b-4bba-b03e-db16f34bf914" />


---
#### ↳ Profil User ####
Halaman ini menampilkan data pribadi pengguna seperti Nama, Username, Password, Email, Nomor Handphone, dan Alamat.
User dapat memperbarui datanya jika ada perubahan, kemudian menekan tombol Simpan Perubahan untuk menyimpan hasil edit.
Tombol Kembali digunakan untuk kembali ke menu utama.

<img width="807" height="497" alt="image" src="https://github.com/user-attachments/assets/21a6beba-6715-47ad-8e1d-22d89afbe417" />


---
#### ↳ Form Laporan Pengaduan ####
Halaman ini digunakan User untuk membuat laporan pengaduan baru terkait kondisi lingkungan laut atau aktivitas pesisir.
Formulir pengaduan berisi kolom Nama, Titik Koordinat, Deskripsi, Catatan, Status, dan opsi untuk mengunggah bukti foto.
Setelah seluruh data diisi dengan benar, User dapat menekan tombol Simpan untuk mengirim laporan pengaduan.

<img width="794" height="496" alt="image" src="https://github.com/user-attachments/assets/78b07513-1f67-410a-8e53-1ef7bb4dd358" />


---
#### ↳ Daftar Sebagai Nelayan ####
Halaman ini digunakan User untuk mendaftar menjadi nelayan melalui sistem.
Formulir pendaftaran memuat kolom NIB, Nomor Registrasi Kapal, Nama Kapal, Kategori, Jenis Kapal, serta opsi Upload Foto Kapal.
Setelah data diisi lengkap, User menekan tombol Submit untuk mengirim permohonan pendaftaran sebagai nelayan.
Tombol Kembali di bagian atas dapat digunakan untuk kembali ke menu utama tanpa menyimpan data.

<img width="796" height="498" alt="image" src="https://github.com/user-attachments/assets/dd70d0c8-8bfb-42bf-afd1-01d7a94ca40d" />



### 4. Nelayan ###
---
#### ↳ Menu ####
Setelah berhasil login, Nelayan akan diarahkan ke halaman utama yang menampilkan dua menu utama, yaitu Buat Pengaduan dan Buat Laporan Penangkapan. Dari sini, Nelayan dapat memilih untuk mencatat aktivitas tangkapan ikan atau membuat laporan pengaduan terkait kegiatan di laut. Terdapat juga tombol Profil untuk mengelola data pribadi dan tombol Keluar untuk logout dari sistem.

<img width="780" height="478" alt="image" src="https://github.com/user-attachments/assets/4cc1dd1e-001a-4a4a-a832-c7c5a7b84360" />


---
#### ↳ Profil Nelayan ####
Halaman ini menampilkan informasi pribadi nelayan seperti Nama, Username, Password, Email, Nomor Handphone, Alamat, dan NIB.
Selain itu, pada bagian bawah terdapat tabel yang menampilkan daftar kapal milik nelayan. Nelayan dapat menekan tombol Tambah Kapal untuk mendaftarkan kapal baru, atau Hapus Kapal untuk menghapus kapal yang sudah tidak digunakan. Setelah melakukan perubahan, tombol Simpan digunakan untuk menyimpan semua pembaruan data.

<img width="806" height="496" alt="image" src="https://github.com/user-attachments/assets/e0232ae1-e12e-4895-958e-a12aada9a333" />


#### ↳ Form Tambah Kapal ####
Halaman ini digunakan untuk menambahkan data kapal baru yang dimiliki oleh nelayan. Formulir terdiri dari kolom Nomor Registrasi Kapal, Nama Kapal, Kategori, Jenis Kapal, serta opsi Upload Foto Kapal. Setelah data diisi lengkap, Nelayan dapat menekan tombol Submit untuk menyimpan kapal baru ke dalam sistem. Tombol Kembali di pojok kanan atas digunakan untuk kembali ke halaman profil tanpa menyimpan data.

<img width="795" height="497" alt="image" src="https://github.com/user-attachments/assets/31fed65b-a43e-4508-8cc9-16b9dbc1903e" />


---
#### ↳ Form Laporan Pengaduan ####
Pada halaman ini, Nelayan dapat membuat dan mengelola laporan pengaduan terkait aktivitas di laut. Formulir berisi kolom Jenis Pengaduan, Titik Koordinat, Deskripsi, dan Bukti Foto yang dapat diunggah langsung dari perangkat. Setelah data dimasukkan, Nelayan dapat menekan tombol Tambah untuk menyimpan pengaduan baru, Ubah untuk memperbarui laporan, atau Hapus jika laporan tidak diperlukan lagi. Semua data pengaduan yang dikirim akan muncul di tabel di bagian bawah halaman.

<img width="796" height="496" alt="image" src="https://github.com/user-attachments/assets/2a411702-3e8e-4eeb-9b2f-0b088673b9d7" />


---
#### ↳ Form Laporan Penangkapan ####
Halaman ini digunakan untuk mencatat kegiatan penangkapan ikan yang dilakukan oleh nelayan. Formulir terdiri dari kolom Nama Pelabuhan, Jenis Alat Tangkap, Kapal yang Digunakan, Wilayah Tangkap, Waktu Berangkat, Waktu Berlabuh, dan Foto Alat Tangkap.
Nelayan dapat menambahkan laporan baru menggunakan tombol Tambah, menyelesaikan laporan dengan tombol Selesaikan, atau menghapus laporan dengan tombol Hapus. Data laporan yang sudah dikirim akan tampil dalam tabel pada bagian bawah halaman.

<img width="777" height="495" alt="image" src="https://github.com/user-attachments/assets/2fe0660c-b2c1-48cc-8c77-d03181f8956e" />


