/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
/**
 *
 * @author TUF
 */
public class AttemptLimiter extends LoginLimiter {
    @Override
    public void onFailedAttempt() {
        int sisa = MAX_LOGIN_ATTEMPTS - failedAttempts;
        String message = String.format("Login gagal. Sisa percobaan: %d", sisa);
        
        JOptionPane.showMessageDialog(
            null, 
            message, 
            "Login Gagal", 
            JOptionPane.WARNING_MESSAGE
        );
    }

    @Override
    public void onLimitReached() {
        String message = "Anda sudah gagal 3x. Aplikasi akan ditutup.";
        
        JOptionPane.showMessageDialog(
            null, 
            message, 
            "Batas Percobaan Habis", 
            JOptionPane.ERROR_MESSAGE
        );
        
        System.exit(0); 
    }
}
