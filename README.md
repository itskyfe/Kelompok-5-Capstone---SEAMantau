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

## 1. Encapsulation

Encapsulation merupakan pilar utama dalam Pemrograman Berorientasi Objek (PBO) yang bertujuan untuk **melindungi data dari akses langsung dari luar kelas**. Dalam sistem SEAMantau, prinsip ini diterapkan pada setiap kelas entitas seperti `User`, `Nelayan`, `Pegawai`, `Admin`, `Kapal`, `WilayahTangkap`, `Laporan`, dan `LaporanPengaduan`.

Setiap atribut kelas bersifat **`private`**, dan hanya dapat diakses melalui **getter** dan **setter**. Hal ini menjaga integritas data agar perubahan hanya dapat dilakukan dengan cara yang terkontrol.

Sebagai contoh implementasi, berikut penerapan pada kelas `Laporan`:

```java
package model;

import model.enums.StatusLaporan;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "laporan")
public class Laporan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laporan_id")
    private Integer laporanId;

    @ManyToOne
    @JoinColumn(name = "id_nelayan", nullable = false)
    private Nelayan nelayan;

    @ManyToOne
    @JoinColumn(name = "id_pegawai")
    private Pegawai pegawai;

    @ManyToOne
    @JoinColumn(name = "id_kapal", nullable = false)
    private Kapal kapal;

    @ManyToOne
    @JoinColumn(name = "id_wilayah", nullable = false)
    private WilayahTangkap wilayah;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_laporan", nullable = false, columnDefinition = "ENUM('Driverifikasi','Menunggu','Berlayar','Ditolak') DEFAULT 'Menunggu'")
    private StatusLaporan statusLaporan;

    @Column(name = "nama_pelabuhan", nullable = false, length = 100)
    private String namaPelabuhan;

    @Column(name = "waktu_berangkat", nullable = false)
    private LocalDateTime waktuBerangkat;

    @Column(name = "alat_tangkap", nullable = false, length = 100)
    private String alatTangkap;

    @Column(name = "foto_alat_tangkap", nullable = false, length = 2048)
    private String fotoAlatTangkap;

    @Column(name = "waktu_berlabuh")
    private LocalDateTime waktuBerlabuh;

    @Column(name = "catatan", columnDefinition = "TEXT")
    private String catatan;

    /* ---------- getter & setter ---------- */
    public Integer getLaporanId() { return laporanId; }
    public void setLaporanId(Integer laporanId) { this.laporanId = laporanId; }

    public Nelayan getNelayan() { return nelayan; }
    public void setNelayan(Nelayan nelayan) { this.nelayan = nelayan; }

    public Pegawai getPegawai() { return pegawai; }
    public void setPegawai(Pegawai pegawai) { this.pegawai = pegawai; }

    public Kapal getKapal() { return kapal; }
    public void setKapal(Kapal kapal) { this.kapal = kapal; }

    public WilayahTangkap getWilayah() { return wilayah; }
    public void setWilayah(WilayahTangkap wilayah) { this.wilayah = wilayah; }

    public StatusLaporan getStatusLaporan() { return statusLaporan; }
    public void setStatusLaporan(StatusLaporan statusLaporan) { this.statusLaporan = statusLaporan; }

    public String getNamaPelabuhan() { return namaPelabuhan; }
    public void setNamaPelabuhan(String namaPelabuhan) { this.namaPelabuhan = namaPelabuhan; }

    public LocalDateTime getWaktuBerangkat() { return waktuBerangkat; }
    public void setWaktuBerangkat(LocalDateTime waktuBerangkat) { this.waktuBerangkat = waktuBerangkat; }

    public String getAlatTangkap() { return alatTangkap; }
    public void setAlatTangkap(String alatTangkap) { this.alatTangkap = alatTangkap; }

    public String getFotoAlatTangkap() { return fotoAlatTangkap; }
    public void setFotoAlatTangkap(String fotoAlatTangkap) { this.fotoAlatTangkap = fotoAlatTangkap; }

    public LocalDateTime getWaktuBerlabuh() { return waktuBerlabuh; }
    public void setWaktuBerlabuh(LocalDateTime waktuBerlabuh) { this.waktuBerlabuh = waktuBerlabuh; }

    public String getCatatan() { return catatan; }
    public void setCatatan(String catatan) { this.catatan = catatan; }
}
```

Dalam sistem SEAMantau, prinsip Encapsulation diterapkan pada setiap kelas entitas seperti Laporan.java.
Semua atribut bersifat private dan hanya dapat diakses melalui metode getter dan setter.
Contohnya, kelas Laporan menyimpan data seperti id_laporan, status_laporan, dan nama_pelabuhan yang tidak dapat diubah secara langsung oleh kelas lain.
Penerapan ini menjaga integritas data dan memastikan perubahan hanya dilakukan melalui metode resmi yang telah ditentukan.

---

## 2. Inheritance

**Inheritance** atau pewarisan adalah salah satu pilar utama dalam Pemrograman Berorientasi Objek (PBO) yang memungkinkan suatu kelas (child/subclass) untuk **mewarisi atribut dan metode dari kelas lain (parent/superclass)**.
Tujuan utamanya adalah untuk **menghindari duplikasi kode**, menjaga keteraturan struktur program, dan memudahkan pengembangan.

Dalam sistem **SEAMantau**, konsep *inheritance* diterapkan pada entitas pengguna sistem, di mana **kelas `User` bertindak sebagai superclass**, dan tiga kelas lainnya — **`Admin`, `Pegawai`, serta `Nelayan`** — menjadi subclass-nya.
Masing-masing subclass mewarisi atribut dasar seperti `id_user`, `nama`, `username`, `password`, dan `email`, tetapi juga menambahkan atribut serta perilaku khusus sesuai peran masing-masing.

---

### 🔹 Superclass — `User.java`

Kelas `User` berfungsi sebagai **kelas induk** yang berisi informasi umum dari semua jenis pengguna sistem.
Kelas ini juga menggunakan anotasi `@Inheritance(strategy = InheritanceType.JOINED)` dari JPA (Hibernate), yang berarti setiap subclass akan disimpan dalam tabel berbeda, namun terhubung dengan tabel `user` melalui kolom *primary key* yang sama.

```java
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "nama", nullable = false, length = 100)
    private String nama;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "no_hp", nullable = false, length = 20)
    private String noHp;

    @Column(name = "alamat", nullable = false, columnDefinition = "TEXT")
    private String alamat;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = true,
            columnDefinition = "ENUM('Admin','Pegawai','Nelayan') DEFAULT 'NULL'")
    private Role role;

    // Getter dan Setter
}
```

Kelas ini mendefinisikan atribut identitas dasar bagi seluruh pengguna sistem seperti nama, username, email, dan role (peran).
Selain itu, atribut `role` membantu sistem membedakan tipe pengguna secara otomatis ketika proses login dan otorisasi dilakukan.

---

### 🔹 Subclass 1 — `Admin.java`

Kelas `Admin` merupakan **turunan langsung dari `User`** dan memiliki atribut tambahan `status` yang menunjukkan apakah akun admin sedang aktif atau nonaktif.
Admin memiliki hak akses penuh terhadap seluruh data pengguna dan laporan di sistem SEAMantau.

```java
@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "admin_id")
public class Admin extends User implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,
        columnDefinition = "ENUM('Aktif','Nonaktif') DEFAULT 'Aktif'")
    private StatusAdmin status;

    public StatusAdmin getStatus() { return status; }
    public void setStatus(StatusAdmin status) { this.status = status; }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + getUserId() +
                ", nama='" + getNama() + '\'' +
                ", status=" + status +
                '}';
    }
}
```

🧭 **Fungsi khusus admin:**

* Mengelola data pengguna (aktif/nonaktif)
* Mengontrol dan menghapus data laporan
* Melihat statistik laporan dan pengaduan

---

### 🔹 Subclass 2 — `Pegawai.java`

Kelas `Pegawai` juga mewarisi `User`, namun menambahkan atribut khusus yaitu `nip` sebagai nomor identitas pegawai.
Pegawai berperan sebagai **verifikator laporan**, yang bertugas memvalidasi laporan dan pengaduan nelayan di lapangan.

```java
@Entity
@Table(name = "pegawai")
@PrimaryKeyJoinColumn(name = "pegawai_id")
public class Pegawai extends User implements Serializable {

    @Column(name = "nip", nullable = false, unique = true, length = 18)
    private String nip;

    public String getNip() { return nip; }
    public void setNip(String nip) { this.nip = nip; }

    @Override
    public String toString() {
        return "Pegawai{" +
                "userId=" + getUserId() +
                ", nama='" + getNama() + '\'' +
                ", nip=" + nip +
                '}';
    }
}
```

🧭 **Fungsi khusus pegawai:**

* Memverifikasi laporan nelayan
* Mengubah status laporan menjadi *Diverifikasi*, *Ditolak*, atau *Menunggu*
* Menambahkan catatan hasil pemeriksaan lapangan

---

### 🔹 Subclass 3 — `Nelayan.java`

Kelas `Nelayan` adalah turunan dari `User` yang berperan sebagai **pelapor utama** dalam sistem SEAMantau.
Nelayan memiliki atribut tambahan seperti `nib` (Nomor Identitas Berlayar) dan `statusNelayan`, serta relasi *one-to-many* terhadap entitas `Kapal`.

```java
@Entity
@Table(name = "nelayan")
@PrimaryKeyJoinColumn(name = "nelayan_id")
public class Nelayan extends User implements Serializable {

    @Column(name = "nib", nullable = false, unique = true, length = 13)
    private String nib;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_nelayan", nullable = false,
            columnDefinition = "ENUM('Aktif','Nonaktif') DEFAULT 'Nonaktif'")
    private StatusNelayan statusNelayan;

    @OneToMany(mappedBy = "nelayan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kapal> daftarKapal = new ArrayList<>();

    // Getter, Setter, dan Method Tambahan
    public void tambahKapal(Kapal kapal) {
        daftarKapal.add(kapal);
        kapal.setNelayan(this);
    }

    public void hapusKapal(Kapal kapal) {
        daftarKapal.remove(kapal);
        kapal.setNelayan(null);
    }

    @Override
    public String toString() {
        return "Nelayan{" +
                "userId=" + getUserId() +
                ", nama='" + getNama() + '\'' +
                ", nib=" + nib +
                ", statusNelayan=" + statusNelayan +
                ", jumlahKapal=" + (daftarKapal != null ? daftarKapal.size() : 0) +
                '}';
    }
}
```

🧭 **Fungsi khusus nelayan:**

* Membuat laporan aktivitas melaut
* Melaporkan pengaduan kerusakan ekosistem laut
* Mengelola data kapal yang dimiliki

---

### 🔹 **Struktur Pewarisan di Sistem**

Secara konseptual, relasi antar kelas dapat digambarkan sebagai berikut:

```
            ┌──────────────┐
            │     User     │  ← Superclass
            └──────┬───────┘
       ┌───────────┼───────────┐
       │           │           │
 ┌─────▼─────┐ ┌───▼──────┐ ┌───▼──────┐
 │  Admin    │ │ Pegawai  │ │ Nelayan  │
 └───────────┘ └──────────┘ └──────────┘
```

💬 Semua subclass (`Admin`, `Pegawai`, `Nelayan`) mewarisi atribut umum dari `User`, namun masing-masing memiliki atribut tambahan yang mencerminkan peran unik mereka di dalam sistem.

---

### 🔹**Keuntungan Penerapan Inheritance di SEAMantau**

1. **Efisiensi kode:** Atribut umum cukup ditulis sekali pada kelas `User`, tidak perlu diulang di subclass.
2. **Kemudahan pemeliharaan:** Jika ada perubahan pada struktur data pengguna (misalnya penambahan `no_hp`), cukup ubah di `User.java`.
3. **Relasi database yang jelas:** Dengan strategi `@Inheritance(strategy = InheritanceType.JOINED)`, data di tiap subclass memiliki tabel sendiri, tetapi tetap saling terhubung ke tabel utama `user`.
4. **Fleksibilitas sistem:** Developer dapat menambahkan jenis pengguna baru (misalnya `PetugasLapangan`) tanpa mengubah arsitektur inti sistem.

---

## 3. Abstraction

**Abstraction (Abstraksi)** merupakan pilar penting dalam Pemrograman Berorientasi Objek (PBO) yang berfungsi untuk **menyembunyikan detail implementasi** dan hanya menampilkan struktur penting atau perilaku umum yang harus dimiliki oleh turunan kelas tersebut.
Dengan menerapkan abstraksi, sistem menjadi lebih terorganisir, karena setiap kelas hanya fokus pada fungsinya masing-masing tanpa perlu mengetahui detail teknis kelas lain.

---

### 🔹 Penerapan Abstraction dalam SEAMantau

Kita bikin `` `abstract class` `` untuk nanganin logika batas 3x gagal login.

**`controller/LoginLimiter.java` (Abstract Class - Induk)**

Kelas ini *abstrak*. Dia *tau* logikanya (ada `` `MAX_LOGIN_ATTEMPTS = 3` `` dan *method* `` `recordFailedLogin()` `` yang ngitung kegagalan).
Tapi, dia *nggak tau* apa yang harus dilakuin pas gagal. Dia cuman nyediain "lubang" kosong:
Contoh penerapan:

```java
public abstract class LoginLimiter {
    protected final int MAX_LOGIN_ATTEMPTS = 3;
    protected int failedAttempts = 0;

    // Method ini nyembunyiin kerumitan logic
    public void recordFailedLogin() {
        if (!hasReachedLimit()) {
            failedAttempts++;
            onFailedAttempt(); // Panggil "lubang" 1
        }
        if (hasReachedLimit()) {
            onLimitReached(); // Panggil "lubang" 2
        }
    }

    // --- "LUBANG" (Method Abstract) ---
    // Induknya nggak tau ini mau diisi apa.
    public abstract void onFailedAttempt();
    public abstract void onLimitReached();
}
```

---

### 🔹 Implementasi Abstraction oleh Subclass

**`controller/AttemptLimiterGUI.java` (Abstract Class - anak)**
Kelas ini yang ngisi lubang itu. Dia `extends LoginLimiter` dan ngasih tau cara nanganin kegagalan (pake `JOptionPane` dan `System.exit(0)`).

#### ✅ `Admin.java`

```java
public class AttemptLimiterGUI extends LoginLimiter {

    @Override // Nimpah "lubang" 1
    public void onFailedAttempt() {
        int sisa = MAX_LOGIN_ATTEMPTS - failedAttempts;
        JOptionPane.showMessageDialog(null, "Login gagal. Sisa percobaan: " + sisa);
    }

    @Override // Nimpah "lubang" 2
    public void onLimitReached() {
        JOptionPane.showMessageDialog(null, "Anda sudah gagal 3x. Aplikasi akan ditutup.");
        System.exit(0);
    }
}
```

---

## 4. Polymorphism

Polymorphism merupakan salah satu pilar penting dalam Pemrograman Berorientasi Objek (PBO) yang memungkinkan suatu objek memiliki banyak bentuk dan berperilaku berbeda tergantung pada konteks pemanggilannya.

### 🔹 B. Polymorphism - Overriding (Menimpah Method)

Ini terjadi kalo *class anak* ngasih implementasi baru buat *method* yang nama dan parameternya **sama persis** kayak *class induk*-nya (ditandai `` `@Override` ``).

**Contoh 1: `AttemptLimiterGUI.java`**
* `LoginLimiter` (Induk) punya `abstract void onFailedAttempt()`.
* `AttemptLimiterGUI` (Anak) nimpah method itu:
    ```java
    @Override
    public void onFailedAttempt() {
        // Ini adalah implementasi BARU (versi GUI)
        JOptionPane.showMessageDialog(...); 
    }
    ```

**Contoh 2: `WilayahTangkap.java` (di Model)**
* Semua class di Java mewarisi `Object` (Induk), yang punya `public String toString()`.
* Lo nimpah method itu di `WilayahTangkap` (Anak):
    ```java
    @Override
    public String toString() {
        // Implementasi baru: balikin nama, bukan "model.WilayahTangkap@123abc"
        return namaWilayah;
    }
    ```
* **Hasil:** `` `drpWilayah` `` (JComboBox) lo otomatis nampilin nama wilayah, bukan kode aneh.

---

### 🔹 C. Polymorphism - Overloading (Nama Sama, Parameter Beda)

Ini terjadi di dalem **satu class** yang punya method (atau constructor) dengan **nama sama** tapi **parameter beda**.

**Contoh 1: `GoogleDriveService.java` (Method Overloading)**
Kita punya dua method `` `uploadFile` `` dengan nama sama:
```java
// Method 1: Cuman 1 parameter (default)
public static String uploadFile(String filePath) throws ... { ... }

// Method 2: Nama sama, tapi 2 parameter (spesifik)
public static String uploadFile(String filePath, String folderId) throws ... { ... }
```
**Contoh 2: `MenuPegawai.java` (*Constructor Overloading*) Kita punya dua cara buat bikin MenuPegawai:
```
// Constructor 1: Kosongan
public MenuPegawai() {
    initComponents();
}
    
// Constructor 2: Nama sama, tapi ada parameter
public MenuPegawai(Pegawai pegawai) {
    initComponents();
    this.pegawaiLogin = pegawai;
}
```
**Contoh 2: `ImageViewerDialog.java` (*Constructor Overloading*) KKita bikin ini biar bisa dipanggil dari `JFrame` atau `JDialog`:
```
// Constructor 1: Nerima Frame
public ImageViewerDialog(Frame parent, String googleDriveUrl) { ... }

// Constructor 2: Nama sama, tapi nerima JDialog
public ImageViewerDialog(JDialog parent, String googleDriveUrl) { ... }
```


---

## 5. Interface

Interface merupakan kontrak atau blueprint dalam Pemrograman Berorientasi Objek (PBO) yang mendefinisikan sekumpulan metode atau nilai konstan tanpa implementasi langsung. Kelas atau enumerasi yang mengimplementasikan atau menggunakan interface harus menyediakan perilaku spesifik sesuai kontrak tersebut.

Dalam sistem **SEAMantau**, konsep interface diterapkan melalui penggunaan **`enum` seperti `StatusAdmin`**, yang berperan sebagai *interface-like structure* untuk membatasi dan mendefinisikan nilai yang boleh digunakan oleh atribut tertentu pada kelas `Admin.java`.

Sebagai contoh:

```java
@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "admin_id")
public class Admin extends User implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,
        columnDefinition = "ENUM('Aktif','Nonaktif') DEFAULT 'Aktif'")
    private StatusAdmin status;

    public StatusAdmin getStatus() {
        return status;
    }

    public void setStatus(StatusAdmin status) {
        this.status = status;
    }
}
```

Dan berikut kode **`StatusAdmin.java`** sebagai bentuk *interface-kontraktual*:

```java
package model.enums;

public enum StatusAdmin {
    Aktif,
    Nonaktif
}
```

Pada struktur di atas, `StatusAdmin` bertindak layaknya **interface enumerasi** yang memastikan atribut `status` pada entitas `Admin` hanya dapat memiliki dua nilai yang valid, yaitu `Aktif` atau `Nonaktif`.
Dengan begitu, sistem tidak dapat memberikan nilai sembarangan pada status admin, menjaga konsistensi dan keamanan data.

Selain itu, konsep interface juga digunakan pada **lapisan DAO (Data Access Object)** — seperti `LaporanDAO` dan `UserDAO` — di mana setiap kelas memiliki struktur metode yang mirip (misalnya `save()`, `update()`, `delete()`, `findAll()`), namun dengan implementasi berbeda sesuai jenis data yang diolah.
Penerapan prinsip ini membuat kode **lebih modular, mudah diuji, dan dapat dikembangkan tanpa mengubah kode utama**.

---

## 🎯 **Kesimpulan Penerapan Lima Pilar OOP pada Sistem SEAMantau**

Penerapan **Object-Oriented Programming (OOP)** pada sistem **SEAMantau** berperan penting dalam membangun arsitektur aplikasi yang **terstruktur, aman, dan mudah dikembangkan**. Kelima pilar OOP—**Encapsulation, Inheritance, Abstraction, Polymorphism, dan Interface**—telah diterapkan secara konsisten di seluruh komponen sistem.

Berikut kesimpulan penerapannya:

### 1. **Encapsulation**

Diterapkan pada setiap kelas model seperti `Laporan`, `User`, `Nelayan`, dan `Pegawai`, dengan penggunaan **modifier `private`** untuk atribut dan **getter-setter** untuk akses data. Hal ini menjaga keamanan data dan memastikan setiap perubahan atribut dilakukan secara terkontrol, sehingga mencegah manipulasi langsung dari luar kelas.

### 2. **Inheritance**

Kelas `User` berperan sebagai **superclass** bagi `Admin`, `Pegawai`, dan `Nelayan`. Melalui pewarisan (`extends`), seluruh atribut umum seperti `nama`, `username`, `email`, dan `alamat` diwarisi oleh subclass, yang kemudian menambahkan atribut spesifik masing-masing. Hal ini mengurangi duplikasi kode dan mempermudah pengelolaan entitas yang memiliki karakteristik mirip.

### 3. **Abstraction**

Kelas `User` juga berfungsi sebagai **abstraksi umum** bagi seluruh tipe pengguna. Dengan pendekatan ini, kode yang berhubungan dengan entitas pengguna dapat menggunakan `User` sebagai tipe referensi umum tanpa memerlukan detail spesifik subclass-nya. Abstraksi ini menyederhanakan logika bisnis dan menjaga fleksibilitas sistem.

### 4. **Polymorphism**

Melalui kelas `LaporanDAO`, konsep polymorphism diwujudkan dalam bentuk metode dengan **nama sama tetapi perilaku berbeda**, seperti `save()`, `update()`, dan `findById()`. Setiap metode beroperasi terhadap objek yang sama (`Laporan`), namun memiliki fungsi yang berbeda sesuai konteksnya. Hal ini memungkinkan sistem menangani berbagai operasi dengan efisien dan dinamis.

### 5. **Interface**

Konsep interface diterapkan melalui penggunaan **enum dan struktur DAO** yang berperan sebagai kontrak perilaku. Misalnya, `StatusAdmin` dan `StatusLaporan` memastikan nilai status hanya terbatas pada pilihan valid, sedangkan pola DAO memastikan setiap kelas data memiliki metode yang konsisten seperti `save()`, `update()`, dan `delete()`. Dengan pendekatan ini, sistem menjadi modular dan mudah dikembangkan.

---

💡 **Secara keseluruhan**, penerapan kelima pilar OOP pada SEAMantau menciptakan sistem yang:

* **Modular dan efisien**, karena setiap komponen memiliki tanggung jawab spesifik,
* **Aman dan konsisten**, berkat encapsulation dan interface,
* **Fleksibel dan mudah dikembangkan**, melalui pewarisan, abstraksi, dan polymorphism.

Dengan arsitektur OOP yang kuat ini, SEAMantau dapat terus dikembangkan tanpa harus mengubah struktur utama, menjadikannya **sistem pelaporan kelautan yang handal, scalable, dan maintainable.**

---

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

<img width="797" height="496" alt="Screenshot 2025-11-04 231645" src="https://github.com/user-attachments/assets/bc192689-7b9e-4cae-a0b0-5fab6857a979" />



---
#### ↳ Manajemen Wilayah Tangkap ####
Halaman ini digunakan Admin untuk menambahkan atau memperbarui data wilayah tangkap ikan. Admin dapat mengisi kolom Nama Wilayah Tangkap dan Titik Koordinat, lalu menekan tombol Tambah untuk menyimpan atau Edit untuk memperbarui data yang sudah ada. Setiap wilayah yang tersimpan akan ditampilkan pada tabel di bawah form input agar mudah dilihat dan dikelola.

<img width="796" height="496" alt="Screenshot 2025-11-04 232149" src="https://github.com/user-attachments/assets/b801ab86-8e5a-4d79-a9c6-feaf1fe8aabb" />




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

<img width="757" height="486" alt="Screenshot 2025-11-04 232008" src="https://github.com/user-attachments/assets/d564715d-7c8c-4413-be67-d7fda7097bd1" />


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


