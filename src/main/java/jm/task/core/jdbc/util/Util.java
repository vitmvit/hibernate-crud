package jm.task.core.jdbc.util;

import jm.task.core.jdbc.connection.DbConnection;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.util.Optional;


public class Util {

    public Optional<Connection> getConnection() {
        return new DbConnection().getConnection();
    }

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
}