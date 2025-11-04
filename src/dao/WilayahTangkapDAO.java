package dao;
import model.WilayahTangkap;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;
import org.hibernate.query.Query;

/**
 *
 * @author TUF
 */
public class WilayahTangkapDAO {
    
     public void save(WilayahTangkap wilayah) throws Exception { 
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(wilayah);
            tx.commit();
        } catch (Exception e) { 
            try {
                if (tx != null && tx.getStatus().canRollback()) {
                    tx.rollback();
                }
            } catch (Exception rollbackEx) { 
                System.err.println("---!!! GAGAL ROLLBACK (Error E2) !!!---");
                // rollbackEx.printStackTrace(); 
                System.err.println("---!!! PENYEBAB ASLI (Error E1) ADA DI BAWAH !!!---");
            }
            throw e; 
        }
    }

    public void update(WilayahTangkap wilayah) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(wilayah);
            tx.commit();
        } catch (Exception e) { 
             try {
                if (tx != null && tx.getStatus().canRollback()) {
                    tx.rollback();
                }
            } catch (Exception rollbackEx) { 
                System.err.println("---!!! GAGAL ROLLBACK (Error E2) !!!---");
                System.err.println("---!!! PENYEBAB ASLI (Error E1) ADA DI BAWAH !!!---");
            }
            throw e; 
        }
    }

    public List<WilayahTangkap> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM WilayahTangkap", WilayahTangkap.class).list();
        }
    }

    public WilayahTangkap findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(WilayahTangkap.class, id);
        }
    }
    
    public WilayahTangkap findByNama(String namaWilayah) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<WilayahTangkap> query = session.createQuery(
                "FROM WilayahTangkap w WHERE w.namaWilayah = :nama", WilayahTangkap.class
            );
            query.setParameter("nama", namaWilayah);
            return query.uniqueResult();
        } catch (Exception e) {
//            e.printStackTrace(); 
            return null;
        }
    }
}