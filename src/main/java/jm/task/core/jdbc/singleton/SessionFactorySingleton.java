package jm.task.core.jdbc.singleton;

import jm.task.core.jdbc.config.DatabaseConfig;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactorySingleton {

    private static volatile SessionFactorySingleton instance;
    private final SessionFactory sessionFactory;

    private SessionFactorySingleton() {
        Configuration configuration = new Configuration();
        var configMap = new DatabaseConfig().createConfigMap();

        configuration.addAnnotatedClass(User.class)
                .setProperty("hibernate.driver.class", configMap.get("driver"))
                .setProperty("hibernate.connection.url", configMap.get("url"))
                .setProperty("hibernate.connection.username", configMap.get("username"))
                .setProperty("hibernate.connection.password", configMap.get("password"))
                .setProperty("hibernate.dialect", configMap.get("dialect"))
                .setProperty("hibernate.show_sql", configMap.get("show_sql"))
                .setProperty("hibernate.current_session.context_class", configMap.get("context_class"));
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