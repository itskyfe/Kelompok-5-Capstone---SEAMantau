package dao;

import model.Nelayan;
import model.Kapal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class NelayanDAO {

    public void save(Nelayan nelayan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(nelayan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // ✅ FIX: fetch join daftarKapal biar gak LazyInitializationException
    public Nelayan findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT n FROM Nelayan n LEFT JOIN FETCH n.daftarKapal WHERE n.id = :id",
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

    public void addKapal(Nelayan nelayan, Kapal kapal) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            nelayan.getDaftarKapal().add(kapal);
            kapal.setNelayan(nelayan);
            session.update(nelayan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // ✅ FIX: versi by userId dengan fetch daftarKapal juga
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
}
