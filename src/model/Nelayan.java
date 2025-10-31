package model;

import model.enums.StatusNelayan;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nelayan")
@PrimaryKeyJoinColumn(name = "nelayan_id")
public class Nelayan extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nib", nullable = false, unique = true)
    private Integer nib;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_nelayan", nullable = false,
            columnDefinition = "ENUM('Aktif','Nonaktif') DEFAULT 'Nonaktif'")
    private StatusNelayan statusNelayan;

    // 🔹 Relasi One-to-Many ke Kapal
    @OneToMany(mappedBy = "nelayan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kapal> daftarKapal = new ArrayList<>();

    /* ---------- Getter & Setter ---------- */
    public Integer getNib() {
        return nib;
    }

    public void setNib(Integer nib) {
        this.nib = nib;
    }

    public StatusNelayan getStatusNelayan() {
        return statusNelayan;
    }

    public void setStatusNelayan(StatusNelayan statusNelayan) {
        this.statusNelayan = statusNelayan;
    }

    public List<Kapal> getDaftarKapal() {
        return daftarKapal;
    }

    public void setDaftarKapal(List<Kapal> daftarKapal) {
        this.daftarKapal = daftarKapal;
    }

    // 🔹 Helper method biar aman manipulasi relasi dua arah
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
