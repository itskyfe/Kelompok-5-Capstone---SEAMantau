package model;

import model.enums.StatusKapal;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kapal")
public class Kapal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_registrasi")
    private Integer noRegistrasi;

    @ManyToOne
    @JoinColumn(name = "nelayan_id", nullable = false)
    private Nelayan nelayan;

    @Column(name = "nama_kapal", nullable = false, length = 100)
    private String namaKapal;

    @Column(name = "kategori_kapal", nullable = false, length = 50)
    private String kategoriKapal;

    @Column(name = "jenis_kapal", nullable = false, length = 50)
    private String jenisKapal;

    @Column(name = "foto_kapal", nullable = false, length = 2048)
    private String fotoKapal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_kapal", nullable = false, columnDefinition = "ENUM('Aktif','Nonaktif') DEFAULT 'Nonaktif'")
    private StatusKapal statusKapal;



    /* ---------- getter & setter ---------- */
    public Integer getNoRegistrasi() { return noRegistrasi; }
    public void setNoRegistrasi(Integer noRegistrasi) { this.noRegistrasi = noRegistrasi; }

    public Nelayan getNelayan() { return nelayan; }
    public void setNelayan(Nelayan nelayan) { this.nelayan = nelayan; }

    public String getNamaKapal() { return namaKapal; }
    public void setNamaKapal(String namaKapal) { this.namaKapal = namaKapal; }

    public String getKategoriKapal() { return kategoriKapal; }
    public void setKategoriKapal(String kategoriKapal) { this.kategoriKapal = kategoriKapal; }

    public String getJenisKapal() { return jenisKapal; }
    public void setJenisKapal(String jenisKapal) { this.jenisKapal = jenisKapal; }

    public String getFotoKapal() { return fotoKapal; }
    public void setFotoKapal(String fotoKapal) { this.fotoKapal = fotoKapal; }

    public StatusKapal getStatusKapal() { return statusKapal; }
    public void setStatusKapal(StatusKapal statusKapal) { this.statusKapal = statusKapal; }
}