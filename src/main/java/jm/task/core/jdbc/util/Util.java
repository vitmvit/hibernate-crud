package jm.task.core.jdbc.util;

import jm.task.core.jdbc.connection.DbConnection;
import jm.task.core.jdbc.singleton.SessionFactorySingleton;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.Optional;


public class Util {

    public Optional<Connection> getConnection() {
        return new DbConnection().getConnection();
    }

    public SessionFactory getSessionFactory() {
        return SessionFactorySingleton.getInstance().getSessionFactory();
    }
}