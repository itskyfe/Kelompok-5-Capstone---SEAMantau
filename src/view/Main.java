/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import com.google.api.client.json.gson.GsonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import util.*;
import model.*;

/**
 *
 * @author TUF
 */
public class Main {
    
    public static void main(String[] args) throws IOException, GeneralSecurityException {   
                java.awt.EventQueue.invokeLater(() -> new LandingPage().setVisible(true));

//        SessionFactory factory = HibernateUtil.getSessionFactory();
//
//        Session session = factory.openSession();
//
//        if (session.isConnected()) {
//            System.out.println("Session Hibernate aktif dan terkoneksi ke database!");
//        } else {
//            System.out.println("Gagal membuka session Hibernate!");
//        }
//        System.out.println("\n Daftar entity yang dikenali Hibernate:");
//        factory.getMetamodel().getEntities().forEach(entity -> {
//            System.out.println(" - " + entity.getName());
//        });
//        
//    System.out.println("\n Data dari tabel User:");
//    List<User> users = session.createQuery("FROM User", User.class).list();
//
//    if (users.isEmpty()) {
//        System.out.println("️ Tidak ada data di tabel User!");
//    } else {
//        for (User u : users) {
//            System.out.println("ID: " + u.getUserId() +
//                               " | Nama: " + u.getNama() +
//                               " | Username: " + u.getUsername() +
//                               " | Role: " + u.getRole());
//        }
//    }
//
//        // Tutup session
//        session.close();
//
//        // Tutup SessionFactory
//        HibernateUtil.shutdown();
//
//        // Menampilkan form LandingPage
////        LandingPage landingPage = new LandingPage();
////        landingPage.setVisible(true);
////        
//            MenuUser gg = new MenuUser();
//                  gg.setVisible(true);
//
////             MenuNelayan gg = new MenuNelayan();
////                  gg.setVisible(true);
//                  
////            MenuPegawai gg = new MenuPegawai();
////                             gg.setVisible(true);
//
////            MenuAdmin gg = new MenuAdmin();
////                  gg.setVisible(true);
    }
}
