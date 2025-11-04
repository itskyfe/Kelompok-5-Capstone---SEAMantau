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
import java.util.regex.Pattern;

/**
 *
 * @author TUF
 */
public class AuthController {
    private final UserDAO userDAO = new UserDAO();
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PHONE_REGEX = Pattern.compile("^\\d{10,12}$"); 
    private static final Pattern NAME_USER_REGEX = Pattern.compile("^.{5,20}$"); 
    private static final Pattern PASS_REGEX = Pattern.compile("^.{5,20}$"); 

    

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

    public void signUpUser(String nama, String username, String password,
                           String email, String noTelepon, String alamat) throws Exception {
        
        if (nama.isBlank() || username.isBlank() || password.isBlank() || email.isBlank() || noTelepon.isBlank() || alamat.isBlank()) {
            throw new Exception("Semua field wajib diisi!");
        }
        if (!NAME_USER_REGEX.matcher(nama).matches()) {
            throw new Exception("Nama harus 5-20 karakter.");
        }
        if (!NAME_USER_REGEX.matcher(username).matches()) {
            throw new Exception("Username harus 5-20 karakter.");
        }
        if (!PASS_REGEX.matcher(password).matches()) {
            throw new Exception("Password harus 5-20 karakter.");
        }
        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new Exception("Format email tidak valid (contoh: user@gmail.com).");
        }
        if (!PHONE_REGEX.matcher(noTelepon).matches()) {
            throw new Exception("Nomor Telepon harus angka, 10-12 digit.");
        }

        if (userDAO.findByUsername(username) != null) {
            throw new Exception("Username '" + username + "' sudah digunakan!");
        }
        if (userDAO.findByEmail(email) != null) {
            throw new Exception("Email '" + email + "' sudah digunakan!");
        }

        User newUser = new User();
        newUser.setNama(nama);
        newUser.setUsername(username);
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        newUser.setPassword(hashed);
        newUser.setEmail(email);
        newUser.setNoHp(noTelepon);
        newUser.setAlamat(alamat);
        newUser.setRole(null);


        userDAO.save(newUser); 
    }
}

