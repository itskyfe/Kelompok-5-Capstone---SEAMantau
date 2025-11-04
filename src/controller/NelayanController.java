/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NelayanDAO;
import dao.KapalDAO;
import dao.UserDAO;
import model.*;
import model.enums.Role;
import util.GoogleDriveService;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query; 
import model.enums.StatusKapal;

import java.io.File;

/**
 *
 * @author TUF
 */
public class NelayanController {
    private final NelayanDAO nelayanDAO = new NelayanDAO();
    private final KapalDAO kapalDAO = new KapalDAO();
    private final UserDAO userDAO = new UserDAO();
    

    public void prosesPendaftaran(Nelayan nelayanForm, Kapal kapal, File fotoFile, User currentUser, String driveFolderId) throws Exception {

        if (nelayanDAO.findByNib(nelayanForm.getNib()) != null) {
            throw new Exception("NIB sudah terdaftar.");
        }


        if (kapalDAO.findByNoRegistrasi(kapal.getNoRegistrasi()) != null) {
            throw new Exception("No. Registrasi Kapal " + kapal.getNoRegistrasi() + " sudah terdaftar.");
        }


        String fotoUrl = GoogleDriveService.uploadFile(fotoFile.getAbsolutePath(), driveFolderId);
        kapal.setFotoKapal(fotoUrl);

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();


            User userFromDB = session.get(User.class, currentUser.getUserId());
            if (userFromDB == null) {
                throw new Exception("User tidak ditemukan.");
            }
            userFromDB.setRole(Role.Nelayan);
            session.update(userFromDB);


            String sql = "INSERT INTO nelayan (nelayan_id, nib, status_nelayan) " +
                         "VALUES (:userId, :nib, :status)";
            
            Query query = session.createNativeQuery(sql);
            query.setParameter("userId", userFromDB.getUserId());
            query.setParameter("nib", nelayanForm.getNib());
            query.setParameter("status", nelayanForm.getStatusNelayan().toString());
            
            query.executeUpdate();


            session.flush();
            session.clear();
            
            Nelayan managedNelayan = session.get(Nelayan.class, currentUser.getUserId());
            if (managedNelayan == null) {
                 throw new Exception("Gagal mengambil data nelayan setelah pendaftaran.");
            }

            kapal.setNelayan(managedNelayan);
            session.save(kapal);

            tx.commit();
            
        } catch (Exception e) {
            
            try {
                if (tx != null && tx.getStatus().canRollback()) {
                    tx.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("---!!! GAGAL MELAKUKAN ROLLBACK (Error E2) !!!---");
//                rollbackEx.printStackTrace();
                System.err.println("---!!! PENYEBAB ASLI (Error E1) ADA DI BAWAH !!!---");
            }
            
            throw e; 
        }
    }
    public void tambahKapalBaru(Kapal kapal, File fotoFile, String driveFolderId) throws Exception {
        
        if (kapalDAO.findByNoRegistrasi(kapal.getNoRegistrasi()) != null) {
            throw new Exception("No. Registrasi " + kapal.getNoRegistrasi() + " sudah terdaftar di sistem.");
        }
        
        String fotoUrl = GoogleDriveService.uploadFile(fotoFile.getAbsolutePath(), driveFolderId);
        kapal.setFotoKapal(fotoUrl);
        

        if (kapal.getNelayan() == null) {
            throw new Exception("Gagal menambah kapal: data Nelayan tidak terhubung.");
        }
        
        kapalDAO.save(kapal); 
    }
    public void hapusKapalSecaraLogis(Kapal kapal) throws Exception {
        if (kapal.getStatusKapal() == StatusKapal.Dihapus) {
            throw new Exception("Kapal ini sudah dihapus.");
        }
        
        kapal.setStatusKapal(StatusKapal.Dihapus);
        
        kapalDAO.update(kapal);
    }
}