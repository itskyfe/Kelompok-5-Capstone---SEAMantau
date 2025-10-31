<img width="609" height="609" alt="Image" src="https://github.com/user-attachments/assets/15ec61f0-38e6-4acb-a1a7-4e565aee11ae" />

# 🐟 SEAMantau — E-Logbook Perikanan
**Capstone Project — Sistem Basis Data (XAMPP) & Pemrograman Berorientasi Objek (NetBeans)**  

---

## 📘 Nama Program
**SEAMantau (Smart Ecosystem and Activity Monitoring for Marine Conservation)**  
Aplikasi e-logbook perikanan berbasis desktop untuk memantau aktivitas penangkapan ikan, melaporkan kondisi pesisir, serta memverifikasi laporan oleh pegawai secara digital.

---

## 🧾 Deskripsi Singkat Program
SEAMantau merupakan aplikasi yang dirancang untuk membantu proses pencatatan aktivitas nelayan dan verifikasi laporan perikanan oleh pihak pegawai dinas kelautan. Sistem ini mengintegrasikan data pengguna, kapal, wilayah tangkap, laporan aktivitas, dan pengaduan masyarakat ke dalam satu basis data terpusat menggunakan **MySQL (XAMPP)**.  
Aplikasi ini dibangun dengan bahasa **Java (NetBeans IDE)** menggunakan konsep **Pemrograman Berorientasi Objek (OOP)** agar modular, mudah dikembangkan, dan efisien.  

Terdapat tiga peran pengguna utama:
- **Admin** → mengelola data user, wilayah, dan kapal.  
- **Pegawai** → memverifikasi laporan dan menangani pengaduan.  
- **Nelayan** → membuat laporan aktivitas logbook dan laporan pengaduan.  

---

## ⚙️ Fitur Program
- 🔑 **Login & Manajemen Role** (Admin, Pegawai, Nelayan)
- 🚢 **CRUD Data Kapal**
- 🌊 **CRUD Data Wilayah Tangkap**
- 🗓️ **Pembuatan Laporan Aktivitas Harian**
- 📸 **Upload Foto Alat & Hasil Tangkap**
- 📢 **Laporan Pengaduan oleh Nelayan**
- ✅ **Verifikasi Laporan oleh Pegawai**
- 📊 **Rekap dan Statistik Aktivitas Perikanan**

---

## 🧩 Penerapan 5 Pilar OOP

| **Pilar OOP** | **Penerapan dalam Program SEAMantau** |
|----------------|----------------------------------------|
| **Encapsulation** | Atribut pada class seperti `User`, `Kapal`, dan `Laporan` dibuat `private` dengan getter/setter untuk melindungi data. |
| **Inheritance** | Class `User` menjadi superclass bagi `Nelayan`, `Pegawai`, dan `Admin` agar kode lebih efisien dan terstruktur. |
| **Abstraction** | Abstract class `BaseRepository` digunakan untuk menyederhanakan operasi CRUD yang digunakan oleh class DAO lain. |
| **Polymorphism** | Method `verifikasi()` diimplementasikan berbeda di class `Pegawai` dan `Admin` sesuai fungsi masing-masing. |
| **Interface** | Interface `IReportGenerator` dan `IDatabaseConnector` digunakan untuk memisahkan logika koneksi database dan pembuatan laporan. |

---

## 🏗️ Struktur Folder / Package
```

SEAMantau/
├─ src/
│  ├─ app/ → Main.java
│  ├─ config/ → DBConnection.java, IDatabaseConnector.java
│  ├─ model/ → User.java, Nelayan.java, Pegawai.java, Admin.java, Kapal.java, Laporan.java, LaporanPengaduan.java
│  ├─ dao/ → BaseRepository.java, KapalDAO.java, LaporanDAO.java, LaporanPengaduanDAO.java
│  ├─ service/ → ReportService.java, IReportGenerator.java
│  └─ ui/ → LoginForm.java, DashboardNelayan.java, DashboardPegawai.java, DashboardAdmin.java
├─ database/
│   └─ seamantau_db.sql
├─ docs/
│   ├─ Laporan_K5_Capstone.pdf
│   ├─ LogicalLASTO.png
│   ├─ RelationalLASTO.png
│   └─ Surat_Pengujian.pdf
└─ README.md

```

---

## 🧰 Library atau Framework yang Digunakan
- **Java SE (NetBeans IDE)**  
- **JDBC** untuk koneksi ke database MySQL  
- **MySQL / MariaDB (XAMPP)** sebagai DBMS  
- *(Opsional)* `HikariCP` sebagai connection pooling (nilai tambah)

---

## 🚀 Cara Menggunakan Program
1. Jalankan **XAMPP**, aktifkan *Apache* dan *MySQL*.  
2. Buka **phpMyAdmin**, buat database baru dengan nama `seamantau_db`.  
3. Import file SQL:  
```

database/seamantau_db.sql

````
4. Buka proyek **SEAMantau** di **NetBeans IDE**.  
5. Edit konfigurasi database pada file `config/DBConnection.java`:  
```java
private static final String URL = "jdbc:mysql://localhost:3306/seamantau_db";
private static final String USER = "root";
private static final String PASS = "";
````

6. Jalankan `Main.java`.
7. Login menggunakan akun contoh dari tabel `user`.

📷 **Tampilan GUI (Contoh):**

* Login Form
* Dashboard Nelayan (Input Laporan & Pengaduan)
* Dashboard Pegawai (Verifikasi Laporan)
* Dashboard Admin (Kelola Data Master)

---

## 🗺️ Dokumentasi dan Diagram Pendukung

* **ERD Logical:**

<img width="1614" height="799" alt="Image" src="https://github.com/user-attachments/assets/0725b64a-6a14-49f9-9e17-fa86e397ccd4" />

* **ERD Relasional:**

<img width="1614" height="836" alt="Image" src="https://github.com/user-attachments/assets/e79d50c3-9838-43bd-a48c-fb521b7d3131" />

* **Use Case Diagram:** [Use Case Diagram](https://drive.google.com/file/d/1rDLF2a2djfjGTg0d73RqV-xLF9lqe18J/view)

<img width="10000" height="7776" alt="Image" src="https://github.com/user-attachments/assets/17e58dbb-579a-4377-8a66-cb52915fa3bd" />

* **Flowchart Program:** [Flowchart Porgram](https://drive.google.com/file/d/1sEJ96vtfeM0TtixbL2fQUgLSqq7eXLEw/view)

<img width="11097" height="9621" alt="Image" src="https://github.com/user-attachments/assets/10d629eb-54cf-4a90-b092-3e205428dc52" />

* **Laporan SBD:** [Laporan_K5_Capstone.pdf](https://github.com/user-attachments/files/23248419/Laporan_K5_Capstone.pdf)

---

## 👥 Tim Pengembang

| **Nama**                    | **Peran**            | **Tanggung Jawab**                      |
| --------------------------- | -------------------- | ---------------------------------------- |
| Dilla Maharani              | -                    | -                                        |
| Sayid Rafi A’thaya          | -                    | -                                        |
| Muhammad Rizky Febrianto    | -                    | -                                        |
| Satria Rajwali Ektya Antara | -                    | -                                        |

---

## 📄 Lisensi & Pengumpulan

Proyek ini dikembangkan untuk memenuhi tugas **Capstone Project Sistem Basis Data & Pemrograman Berorientasi Objek.**
Seluruh file yang wajib dikumpulkan telah disertakan di dalam repository ini:

✅ `README.md` — Penjelasan lengkap program
✅ `database/seamantau_db.sql` — Struktur dan data awal basis data
✅ `docs/Laporan_K5_Capstone.pdf` — Laporan SBD sesuai template resmi
✅ `docs/Surat_Pengujian.pdf` — Scan surat pengujian dengan tanda tangan lengkap

Proyek ini bersifat **akademik dan non-komersial**, dikembangkan untuk mendukung pembelajaran implementasi OOP dan desain sistem basis data terintegrasi.

```
