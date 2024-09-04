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
        configuration.addAnnotatedClass(User.class)
                .setProperty("hibernate.driver.class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/user_hibernate_crud")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.current_session.context_class", "thread");
        return configuration.buildSessionFactory();
    }
}