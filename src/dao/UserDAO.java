package dao;
import model.User;
import model.Pegawai;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.util.List;

public class UserDAO {
    
    public void save(User user) throws Exception { 
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.getStatus().canRollback()) tx.rollback(); 
            throw e; 
        }
    }
    
    public void update(User user) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.getStatus().canRollback()) tx.rollback(); 
            throw e; 
        }
    }

    public Pegawai findByNip(String nip) { 
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pegawai> query = session.createQuery("FROM Pegawai WHERE nip = :nip", Pegawai.class);
            query.setParameter("nip", nip);
            return query.uniqueResult();
        }
    }

    public User findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public User findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            List<User> users = query.list();
            return (users != null && !users.isEmpty()) ? users.get(0) : null;
        }
    }

    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }
    
    public User findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            List<User> users = query.list();
            return (users != null && !users.isEmpty()) ? users.get(0) : null;
        }
    }
}