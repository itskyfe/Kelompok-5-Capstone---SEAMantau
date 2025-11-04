/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.NelayanDAO;
import dao.UserDAO;
import model.Nelayan;
import model.User;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

/**
 *
 * @author TUF
 */
public class UserController {
    private final UserDAO userDAO = new UserDAO();
    private final NelayanDAO nelayanDAO = new NelayanDAO();
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PHONE_REGEX = Pattern.compile("^\\d{10,12}$");
    private static final Pattern NAME_USER_REGEX = Pattern.compile("^.{5,20}$");
    private static final Pattern PASS_REGEX = Pattern.compile("^.{5,20}$");


    public User getUserById(int id) {
        return userDAO.findById(id);
    }


 public void updateProfil(User updatedUser, String newPassword) throws Exception {
        
        if (updatedUser.getNama().isBlank() || updatedUser.getUsername().isBlank() || 
            updatedUser.getEmail().isBlank() || updatedUser.getNoHp().isBlank() || 
            updatedUser.getAlamat().isBlank()) {
            throw new Exception("Semua field wajib diisi!");
        }
        if (!NAME_USER_REGEX.matcher(updatedUser.getNama()).matches()) {
            throw new Exception("Nama harus 5-20 karakter.");
        }
        if (!NAME_USER_REGEX.matcher(updatedUser.getUsername()).matches()) {
            throw new Exception("Username harus 5-20 karakter.");
        }
        if (!newPassword.isEmpty() && !PASS_REGEX.matcher(newPassword).matches()) {
            throw new Exception("Password baru harus 5-20 karakter (atau biarkan kosong).");
        }
        if (!EMAIL_REGEX.matcher(updatedUser.getEmail()).matches()) {
            throw new Exception("Format email tidak valid (contoh: user@gmail.com).");
        }
        if (!PHONE_REGEX.matcher(updatedUser.getNoHp()).matches()) {
            throw new Exception("Nomor Telepon harus angka, 10-12 digit.");
        }

        // 1. Cek duplikat username
        User existingUser = userDAO.findByUsername(updatedUser.getUsername());
        if (existingUser != null && !existingUser.getUserId().equals(updatedUser.getUserId())) {
            throw new Exception("Username '" + updatedUser.getUsername() + "' sudah digunakan!");
        }
        
        // 2. Cek duplikat email
        existingUser = userDAO.findByEmail(updatedUser.getEmail());
         if (existingUser != null && !existingUser.getUserId().equals(updatedUser.getUserId())) {
            throw new Exception("Email '" + updatedUser.getEmail() + "' sudah digunakan!");
        }

        userDAO.update(updatedUser);
    }
    
    public boolean sudahDaftarNelayan(int userId) {
        Nelayan nelayan = nelayanDAO.findByUserId(userId);
        return nelayan != null;
    }
}