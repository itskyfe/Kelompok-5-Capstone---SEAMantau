package dao;

import model.LaporanPengaduan;
import model.enums.StatusPengaduan; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.util.List;

public class LaporanPengaduanDAO {

    public void save(LaporanPengaduan laporanPengaduan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(laporanPengaduan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
//            e.printStackTrace();
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
//            e.printStackTrace();
        }
    }
    
        public void delete(LaporanPengaduan laporanPengaduan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(laporanPengaduan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
//            e.printStackTrace();
            throw e; 
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


    public List<LaporanPengaduan> findByStatus(StatusPengaduan status) { 
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LaporanPengaduan> query = session.createQuery(
                "FROM LaporanPengaduan WHERE statusPengaduan = :status", LaporanPengaduan.class); 
            query.setParameter("status", status); 
            return query.list();
        }
    }

    public List<LaporanPengaduan> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LaporanPengaduan", LaporanPengaduan.class).list();
        }
    }
    
    public List<LaporanPengaduan> findTugasPegawai(Integer pegawaiId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LaporanPengaduan> query = session.createQuery(
                "FROM LaporanPengaduan WHERE statusPengaduan = :menunggu OR (statusPengaduan = :diproses AND pegawai.userId = :pegawaiId)", 
                LaporanPengaduan.class
            );
            query.setParameter("menunggu", StatusPengaduan.Menunggu);
            query.setParameter("diproses", StatusPengaduan.Diproses);
            query.setParameter("pegawaiId", pegawaiId);
            return query.list();
        }
    }
}