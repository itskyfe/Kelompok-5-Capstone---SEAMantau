package dao;
import model.Laporan;
import model.enums.StatusLaporan; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.util.List;

public class LaporanDAO {
    
    public void save(Laporan laporan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(laporan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
//            e.printStackTrace();
        }
    }

    public void update(Laporan laporan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(laporan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
//            e.printStackTrace();
        }
    }

    public void delete(Laporan laporan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(laporan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
//            e.printStackTrace();
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
                "FROM Laporan WHERE nelayan.userId = :id", Laporan.class);
            query.setParameter("id", nelayanId);
            return query.list();
        }
    }


    public List<Laporan> findByStatus(StatusLaporan status) { 
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Laporan> query = session.createQuery(
                "FROM Laporan WHERE statusLaporan = :status", Laporan.class); 
            query.setParameter("status", status); 
            return query.list();
        }
    }
    

    public List<Laporan> findTugasPenangkapan() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Laporan> query = session.createQuery(
                "FROM Laporan WHERE statusLaporan = :menunggu OR statusLaporan = :berlayar", 
                Laporan.class
            );
            query.setParameter("menunggu", StatusLaporan.Menunggu);
            query.setParameter("berlayar", StatusLaporan.Berlayar);
            return query.list();
        }
    }
    
    public List<Laporan> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Laporan", Laporan.class).list();
        }
    }
}