package jm.task.core.jdbc.dao.impl;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.exception.OperationException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jm.task.core.jdbc.constant.Constant.*;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;
    private final Logger log = Logger.getLogger(UserDaoHibernateImpl.class.getName());

    public UserDaoHibernateImpl() {
        sessionFactory = new Util().getSessionFactory();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            log.log(Level.INFO, "UserDaoHibernateImpl: " + name + " add to database");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "UserDaoHibernateImpl saveUser: " + ex);
            throw new OperationException("Save user error: " + ex);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();
            log.log(Level.INFO, "UserDaoHibernateImpl: remove user with id=" + id);
        } catch (Exception ex) {
            log.log(Level.SEVERE, "UserDaoHibernateImpl removeUserById: " + ex);
            throw new OperationException("Remove user error: " + ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> userList = session.createQuery(GET_ALL_USERS_HQL, User.class).list();
            session.getTransaction().commit();
            for (var item : userList) {
                log.log(Level.INFO, "UserDaoHibernateImpl: find user " + item.toString());
            }
            return userList;
        } catch (Exception ex) {
            log.log(Level.SEVERE, "UserDaoHibernateImpl getAllUsers: " + ex);
            throw new OperationException("Find users error: " + ex);
        }
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
            log.log(Level.INFO, "UserDaoHibernateImpl: create table");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "UserDaoHibernateImpl createUsersTable: " + ex);
            throw new OperationException("Create table error: " + ex);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
            log.log(Level.INFO, "UserDaoHibernateImpl: drop table");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "UserDaoHibernateImpl dropUsersTable: " + ex);
            throw new OperationException("Drop table error: " + ex);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery(CLEAN_TABLE_HQL).executeUpdate();
            session.getTransaction().commit();
            log.log(Level.INFO, "UserDaoHibernateImpl: clean table");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "UserDaoHibernateImpl cleanUsersTable: " + ex);
            throw new OperationException("Clean table error: " + ex);
        }
    }
}