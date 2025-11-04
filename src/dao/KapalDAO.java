package dao;

import model.Kapal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;
import model.enums.StatusKapal;
import model.enums.StatusNelayan;
import org.hibernate.query.Query;

/**
 *
 * @author TUF
 */
public class KapalDAO {
    
    public void save(Kapal kapal) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(kapal);
            tx.commit();
        } catch (RuntimeException e) {
            try {
                if (tx != null && tx.getStatus() != null) {
                    tx.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw e;
        }
    }
    
    public Kapal findByNoRegistrasi(String noRegistrasi) { // Ganti jadi String
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Kapal.class, noRegistrasi); // Kirim String
        }
    }


    public void update(Kapal kapal) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(kapal);
            tx.commit();
        } catch (RuntimeException e) {
             if (tx != null && tx.getStatus().canRollback()) { 
                tx.rollback();
            }
            throw e;
        }
    }

    public void delete(Kapal kapal) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(kapal);
            tx.commit();
        } catch (RuntimeException e) {
             if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
            }
            throw e;
        }
    }
    

    public List<Kapal> findAllByStatusAndNelayanStatus(StatusKapal statusKapal, StatusNelayan statusNelayan) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Kapal> query = session.createQuery(
                "FROM Kapal k JOIN FETCH k.nelayan n WHERE k.statusKapal = :sk AND n.statusNelayan = :sn", 
                Kapal.class
            );
            query.setParameter("sk", statusKapal);
            query.setParameter("sn", statusNelayan);
            return query.list();
        }
    }
    
}