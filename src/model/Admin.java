package model;

import model.enums.StatusAdmin;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "admin_id")
public class Admin extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,
        columnDefinition = "ENUM('Aktif','Nonaktif') DEFAULT 'Aktif'")
    private StatusAdmin status;

    /* ---------- Getter & Setter ---------- */
    public StatusAdmin getStatus() {
        return status;
    }

    public void setStatus(StatusAdmin status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + getUserId() +
                ", nama='" + getNama() + '\'' +
                ", status=" + status +
                '}';
    }
}
