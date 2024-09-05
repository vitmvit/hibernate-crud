package jm.task.core.jdbc.singleton;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactorySingleton {

    private static volatile SessionFactorySingleton instance;
    private final SessionFactory sessionFactory;

    private SessionFactorySingleton() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class)
                .setProperty("hibernate.driver.class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/user_hibernate_crud")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.current_session.context_class", "thread");

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactorySingleton getInstance() {
        if (instance == null) {
            synchronized (SessionFactorySingleton.class) {
                if (instance == null) {
                    instance = new SessionFactorySingleton();
                }
            }
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}