/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.LaporanPengaduan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 * DAO untuk entitas LaporanPengaduan
 * @author TUF
 */
public class LaporanPengaduanDAO {

    public void save(LaporanPengaduan laporanPengaduan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(laporanPengaduan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void update(LaporanPengaduan laporanPengaduan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(laporanPengaduan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public LaporanPengaduan findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(LaporanPengaduan.class, id);
        }
    }

    public List<LaporanPengaduan> findByUserId(Integer userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LaporanPengaduan> query = session.createQuery(
                "FROM LaporanPengaduan WHERE user.userId = :id", LaporanPengaduan.class);
            query.setParameter("id", userId);
            return query.list();
        }
    }

    public List<LaporanPengaduan> findByStatus(String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LaporanPengaduan> query = session.createQuery(
                "FROM LaporanPengaduan WHERE status = :status", LaporanPengaduan.class);
            query.setParameter("status", status);
            return query.list();
        }
    }

    public List<LaporanPengaduan> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LaporanPengaduan", LaporanPengaduan.class).list();
        }
    }
}
