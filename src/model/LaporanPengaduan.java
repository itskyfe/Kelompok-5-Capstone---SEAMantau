package model;

import model.enums.JenisPengaduan;
import model.enums.StatusPengaduan;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "laporan_pengaduan")
public class LaporanPengaduan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pengaduan_id")
    private Integer pengaduanId;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pegawai_id")
    private Pegawai pegawai;

    @Column(name = "tanggal_pengaduan", nullable = false)
    private LocalDateTime tanggalPengaduan;

    @Enumerated(EnumType.STRING)
    @Column(name = "jenis_pengaduan", nullable = false, columnDefinition = "ENUM('Pelanggaran','Kerusakan','Sistem')")
    private JenisPengaduan jenisPengaduan;


    @Column(name = "deskripsi", nullable = false, columnDefinition = "TEXT")
    private String deskripsi;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pengaduan", nullable = false, columnDefinition = "ENUM('Menunggu','Diproses','Selesai') DEFAULT 'Menunggu'")
    private StatusPengaduan statusPengaduan;


    @Column(name = "dokumentasi", nullable = false, length = 2048)
    private String dokumentasi;

    @Column(name = "titik_koordinat", nullable = false, length = 100)
    private String titikKoordinat;

    @Column(name = "catatan", columnDefinition = "TEXT")
    private String catatan;

    /* ---------- getter & setter ---------- */
    public Integer getPengaduanId() { return pengaduanId; }
    public void setPengaduanId(Integer pengaduanId) { this.pengaduanId = pengaduanId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Pegawai getPegawai() { return pegawai; }
    public void setPegawai(Pegawai pegawai) { this.pegawai = pegawai; }

    public LocalDateTime getTanggalPengaduan() { return tanggalPengaduan; }
    public void setTanggalPengaduan(LocalDateTime tanggalPengaduan) { this.tanggalPengaduan = tanggalPengaduan; }

    public JenisPengaduan getJenisPengaduan() { return jenisPengaduan; }
    public void setJenisPengaduan(JenisPengaduan jenisPengaduan) { this.jenisPengaduan = jenisPengaduan; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public StatusPengaduan getStatusPengaduan() { return statusPengaduan; }
    public void setStatusPengaduan(StatusPengaduan statusPengaduan) { this.statusPengaduan = statusPengaduan; }

    public String getDokumentasi() { return dokumentasi; }
    public void setDokumentasi(String dokumentasi) { this.dokumentasi = dokumentasi; }

    public String getTitikKoordinat() { return titikKoordinat; }
    public void setTitikKoordinat(String titikKoordinat) { this.titikKoordinat = titikKoordinat; }

    public String getCatatan() { return catatan; }
    public void setCatatan(String catatan) { this.catatan = catatan; }
}