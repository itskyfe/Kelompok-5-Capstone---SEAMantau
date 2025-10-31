/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.UserDAO;
import model.Pegawai;
import model.enums.Role;
import org.mindrot.jbcrypt.BCrypt;
import javax.swing.JOptionPane;
import model.User;
/**
 *
 * @author TUF
 */
public class PegawaiController {
     private final UserDAO userDAO = new UserDAO();

    // CREATE
    public boolean tambahPegawai(String nama, String username, String password, String email,
                                 String noTelepon, String alamat, Integer nip) {
        try {
            if (nama.isBlank() || username.isBlank() || password.isBlank() || email.isBlank()) {
                JOptionPane.showMessageDialog(null, "Semua field wajib diisi!");
                return false;
            }

            // cek username/email udah dipakai belum
            if (userDAO.findByUsername(username) != null) {
                JOptionPane.showMessageDialog(null, "Username sudah digunakan!");
                return false;
            }
            if (userDAO.findByEmail(email) != null) {
                JOptionPane.showMessageDialog(null, "Email sudah digunakan!");
                return false;
            }

            // hash password
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

            // buat object Pegawai
            Pegawai pegawai = new Pegawai();
            pegawai.setNama(nama);
            pegawai.setUsername(username);
            pegawai.setPassword(hashed);
            pegawai.setEmail(email);
            pegawai.setNoHp(noTelepon);
            pegawai.setAlamat(alamat);
            pegawai.setNip(nip);
            pegawai.setRole(Role.Pegawai);

            // simpan ke DB
            userDAO.save(pegawai);

            JOptionPane.showMessageDialog(null, "Pegawai berhasil ditambahkan!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menambah pegawai: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePegawai(Pegawai pegawai) {
    try {
        if (pegawai == null) return false;

        // Cek duplikasi username/email/NIP selain diri sendiri
        User existingByUsername = userDAO.findByUsername(pegawai.getUsername());
        if (existingByUsername != null && !existingByUsername.getUserId().equals(pegawai.getUserId())) {
            JOptionPane.showMessageDialog(null, "Username sudah digunakan oleh pegawai lain!");
            return false;
        }

        User existingByEmail = userDAO.findByEmail(pegawai.getEmail());
        if (existingByEmail != null && !existingByEmail.getUserId().equals(pegawai.getUserId())) {
            JOptionPane.showMessageDialog(null, "Email sudah digunakan oleh pegawai lain!");
            return false;
        }

        User existingByNip = userDAO.findByNip(pegawai.getNip());
        if (existingByNip != null && !existingByNip.getUserId().equals(pegawai.getUserId())) {
            JOptionPane.showMessageDialog(null, "NIP sudah digunakan oleh pegawai lain!");
            return false;
        }

        userDAO.update(pegawai);
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal update pegawai: " + e.getMessage());
        return false;
    }
}

}
