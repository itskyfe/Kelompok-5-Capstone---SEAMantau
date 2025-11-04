package dao;

import model.Nelayan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;
import model.enums.StatusNelayan;
import org.hibernate.query.Query; 

public class NelayanDAO {

    public void save(Nelayan nelayan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(nelayan);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
            }
            throw e; 
        }
    }

    public void update(Nelayan nelayan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(nelayan);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public Nelayan findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT n FROM Nelayan n LEFT JOIN FETCH n.daftarKapal WHERE n.userId = :id",
                    Nelayan.class
            )
            .setParameter("id", id)
            .uniqueResult();
        }
    }

    public List<Nelayan> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Nelayan", Nelayan.class).list();
        }
    }

    public Nelayan findByNib(String nib) { 
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Nelayan WHERE nib = :nib", Nelayan.class)
                    .setParameter("nib", nib) 
                    .uniqueResult();
        }
    }

    public Nelayan findByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT n FROM Nelayan n LEFT JOIN FETCH n.daftarKapal WHERE n.userId = :userId",
                    Nelayan.class
            )
            .setParameter("userId", userId)
            .uniqueResult();
        }
    }
    
    
public void demoteNelayan(Nelayan nelayan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            
            Integer id = nelayan.getUserId(); 

            String sqlKapal = "DELETE FROM kapal WHERE nelayan_id = :id";
            session.createNativeQuery(sqlKapal)
                   .setParameter("id", id)
                   .executeUpdate();
                   
            String sqlNelayan = "DELETE FROM nelayan WHERE nelayan_id = :id";
            session.createNativeQuery(sqlNelayan)
                   .setParameter("id", id)
                   .executeUpdate();
                   
            String sqlUser = "UPDATE user SET role = NULL WHERE user_id = :id";
            session.createNativeQuery(sqlUser)
                   .setParameter("id", id)
                   .executeUpdate();
            
            tx.commit(); 
            
        } catch (RuntimeException e) {
            if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
            }
            throw e;
        }
    }
    
    public List<Nelayan> findByStatus(StatusNelayan status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Nelayan> query = session.createQuery(
                "FROM Nelayan n LEFT JOIN FETCH n.daftarKapal WHERE n.statusNelayan = :status", 
                Nelayan.class
            );
            query.setParameter("status", status);
            return query.list();
        }
    }
    
}