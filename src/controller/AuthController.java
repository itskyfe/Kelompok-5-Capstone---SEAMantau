/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.UserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author TUF
 */
public class AuthController {
    private final UserDAO userDAO = new UserDAO();

    

//public User login(String username, String password) {
//    if (username == null || username.isBlank() || password == null) return null;
//
//    User user = userDAO.findByUsername(username);
//    if (user == null) return null;
//
//    // ✅ bandingkan password input dengan hash di database
//    if (!BCrypt.checkpw(password, user.getPassword())) {
//        return null;
//    }
//
//    return user;
//}


public User login(String username, String password) {
    if (username == null || username.isBlank() || password == null) return null;

    User user = userDAO.findByUsername(username);
    if (user == null) return null;

    String stored = user.getPassword();

    boolean match = false;

    if (stored != null && stored.startsWith("$2a$")) {
        match = BCrypt.checkpw(password, stored);
    } else {
        match = password.equals(stored);
    }

    if (!match) return null;
    
    return user;
}
    
        public boolean signUpUser(String nama, String username, String password,
                              String email, String noTelepon, String alamat) {
        if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Nama, Username, dan Password wajib diisi!", 
                "Input tidak lengkap", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // cek username udah dipakai belum
            Query<User> q = session.createQuery("FROM User WHERE username = :uname", User.class);
            q.setParameter("uname", username);
            if (!q.list().isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Username sudah digunakan!", 
                    "Gagal", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // bikin user baru
            User newUser = new User();
            newUser.setNama(nama);
            newUser.setUsername(username);
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            newUser.setPassword(hashed);
            newUser.setEmail(email);
            newUser.setNoHp(noTelepon);
            newUser.setAlamat(alamat);
            newUser.setRole(null); // default null

            session.save(newUser);
            tx.commit();
            JOptionPane.showMessageDialog(null, 
                "Akun berhasil dibuat! Silakan login.", 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Terjadi kesalahan saat membuat akun.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
