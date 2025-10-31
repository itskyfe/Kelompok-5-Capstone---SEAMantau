/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Laporan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 *
 * @author TUF
 */
public class LaporanDAO {
    public void save(Laporan laporan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(laporan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Laporan findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Laporan.class, id);
        }
    }

    public List<Laporan> findByNelayanId(Integer nelayanId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Laporan> query = session.createQuery(
                "FROM Laporan WHERE nelayan.id = :id", Laporan.class);
            query.setParameter("id", nelayanId);
            return query.list();
        }
    }

    public List<Laporan> findByStatus(String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Laporan> query = session.createQuery(
                "FROM Laporan WHERE status = :status", Laporan.class);
            query.setParameter("status", status);
            return query.list();
        }
    }

    
}
