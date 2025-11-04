package controller;
import dao.WilayahTangkapDAO;
import model.WilayahTangkap;
import java.util.List;

/**
 *
 * @author TUF
 */
public class WilayahController {
    private final WilayahTangkapDAO wilayahDAO = new WilayahTangkapDAO();


    public void tambahWilayah(String namaWilayah, String koordinat) throws Exception {
        if (namaWilayah == null || namaWilayah.trim().isEmpty() || 
            koordinat == null || koordinat.trim().isEmpty()) {
            throw new Exception("Nama Wilayah dan Koordinat tidak boleh kosong.");
        }
        if (namaWilayah.trim().length() < 5 || koordinat.trim().length() < 5) {
            throw new Exception("Nama Wilayah dan Koordinat harus minimal 5 karakter.");
        }
        
        if (wilayahDAO.findByNama(namaWilayah.trim()) != null) {
            throw new Exception("Nama Wilayah '" + namaWilayah.trim() + "' sudah terdaftar.");
        }

        WilayahTangkap wilayah = new WilayahTangkap();
        wilayah.setNamaWilayah(namaWilayah.trim());
        wilayah.setKoordinat(koordinat.trim());

        wilayahDAO.save(wilayah);
    }


    public void updateWilayah(WilayahTangkap wilayah) throws Exception {
        if (wilayah == null) {
            throw new Exception("Pilih wilayah terlebih dahulu!");
        }
        
        String nama = wilayah.getNamaWilayah();
        String koordinat = wilayah.getKoordinat();

        if (nama == null || nama.trim().isEmpty() || koordinat == null || koordinat.trim().isEmpty()) {
            throw new Exception("Nama Wilayah dan Koordinat tidak boleh kosong.");
        }
        if (nama.trim().length() < 5 || koordinat.trim().length() < 5) {
            throw new Exception("Nama Wilayah dan Koordinat harus minimal 5 karakter.");
        }

        WilayahTangkap cekDuplikat = wilayahDAO.findByNama(nama.trim());
        if (cekDuplikat != null && !cekDuplikat.getWilayahId().equals(wilayah.getWilayahId())) {
            throw new Exception("Nama Wilayah '" + nama.trim() + "' sudah dipakai oleh data lain.");
        }

        wilayahDAO.update(wilayah);
    }

    public List<WilayahTangkap> getAllWilayah() {
        return wilayahDAO.findAll();
    }
}