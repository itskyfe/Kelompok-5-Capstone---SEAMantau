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