/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.WilayahTangkapDAO;
import model.WilayahTangkap;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author TUF
 */
public class WilayahController {
    private final WilayahTangkapDAO wilayahDAO = new WilayahTangkapDAO();

    public void tambahWilayah(String namaWilayah, String koordinat) {
        if (namaWilayah.isEmpty() || koordinat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field wajib diisi!");
            return;
        }

        WilayahTangkap wilayah = new WilayahTangkap();
        wilayah.setNamaWilayah(namaWilayah);
        wilayah.setKoordinat(koordinat);

        wilayahDAO.save(wilayah);
        JOptionPane.showMessageDialog(null, "Wilayah berhasil ditambahkan!");
    }

    public void updateWilayah(WilayahTangkap wilayah) {
        if (wilayah == null) {
            JOptionPane.showMessageDialog(null, "Pilih wilayah terlebih dahulu!");
            return;
        }

        wilayahDAO.update(wilayah);
        JOptionPane.showMessageDialog(null, "Wilayah berhasil diperbarui!");
    }

    public List<WilayahTangkap> getAllWilayah() {
        return wilayahDAO.findAll();
    }
}
