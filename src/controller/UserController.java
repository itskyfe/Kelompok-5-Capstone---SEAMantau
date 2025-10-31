/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.NelayanDAO;
import dao.UserDAO;
import model.Nelayan;
import model.User;

/**
 *
 * @author TUF
 */
public class UserController {
     private final UserDAO userDAO = new UserDAO();
     private final NelayanDAO nelayanDAO = new NelayanDAO();


    public User getUserById(int id) {
        return userDAO.findById(id);
    }

    public boolean updateProfil(User updatedUser) {
        // cek username duplikat tapi abaikan kalau itu user yang sama
        User existingUser = userDAO.findByUsername(updatedUser.getUsername());
        if (existingUser != null && existingUser.getUserId()!= updatedUser.getUserId()) {
            System.out.println("❌ Username sudah digunakan oleh user lain.");
            return false;
        }

        // lanjut update kalau aman
        return userDAO.update(updatedUser);
    }
public boolean sudahDaftarNelayan(int userId) {
    Nelayan nelayan = nelayanDAO.findByUserId(userId);
    return nelayan != null;
}


}
