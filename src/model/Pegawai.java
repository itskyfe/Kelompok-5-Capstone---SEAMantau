package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pegawai")
@PrimaryKeyJoinColumn(name = "pegawai_id")
public class Pegawai extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nip", nullable = false, unique = true)
    private Integer nip;

    /* ---------- Getter & Setter ---------- */
    public Integer getNip() {
        return nip;
    }

    public void setNip(Integer nip) {
        this.nip = nip;
    }

    @Override
    public String toString() {
        return "Pegawai{" +
                "userId=" + getUserId() +
                ", nama='" + getNama() + '\'' +
                ", nip=" + nip +
                '}';
    }
}
