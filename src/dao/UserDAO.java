/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 *
 * @author TUF
 */
public class UserDAO {
     public void save(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
     
     public User findByNip(Integer nip) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Query<User> query = session.createQuery("FROM User WHERE nip = :nip", User.class);
        query.setParameter("nip", nip);
        return query.uniqueResult();
    }
}

     

        public boolean update(User user) {
                Transaction tx = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    tx = session.beginTransaction();
                    session.update(user);
                    tx.commit();
                    return true;
                } catch (Exception e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                    return false;
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
            return query.uniqueResult();
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
        return query.uniqueResult();
    }
}
    

    
}
