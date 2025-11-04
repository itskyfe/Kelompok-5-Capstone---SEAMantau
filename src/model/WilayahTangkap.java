package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wilayah_tangkap")
public class WilayahTangkap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wilayah_id")
    private Integer wilayahId;

    @Column(name = "koordinat", nullable = false, length = 100)
    private String koordinat;

    @Column(name = "nama_wilayah", nullable = false, length = 50)
    private String namaWilayah;

    /* ---------- getter & setter ---------- */
    public Integer getWilayahId() { return wilayahId; }
    public void setWilayahId(Integer wilayahId) { this.wilayahId = wilayahId; }

    public String getKoordinat() { return koordinat; }
    public void setKoordinat(String koordinat) { this.koordinat = koordinat; }

    public String getNamaWilayah() { return namaWilayah; }
    public void setNamaWilayah(String namaWilayah) { this.namaWilayah = namaWilayah; }
    
    @Override
    public String toString() {
        return namaWilayah;
    }
}