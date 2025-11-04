# 🌊 SEAMantau
*Smart Ecosystem and Activity Monitoring for Marine Conservation*

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
  │   ├── WilayahTangkap.java
  │   └── Main.java
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
