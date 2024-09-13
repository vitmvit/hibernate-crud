package jm.task.core.jdbc.util;

import jm.task.core.jdbc.config.DatabaseConfig;
import jm.task.core.jdbc.connection.ConnectionSingleton;
import jm.task.core.jdbc.connection.DatabaseConnector;

import java.sql.Connection;
import java.util.Map;
import java.util.Optional;

/**
 * Класс для создания и получения соединения с базой данных.
 */
public class Util {

    /**
     * Создает соединение с базой данных.
     */
    public void createConnection() {
        if (ConnectionSingleton.getInstance().getConnection().isEmpty()) {
            DatabaseConfig configReader = new DatabaseConfig();
            Map<String, String> configMap = configReader.createConfigMap();
            Connection connection = new DatabaseConnector().connection(
                    configMap.get("driver"),
                    configMap.get("url"),
                    configMap.get("username"),
                    configMap.get("password")
            );
            ConnectionSingleton.setInstance(connection == null ? Optional.empty() : Optional.of(connection));
        }
    }

    /**
     * Возвращает соединение с базой данных.
     *
     * @return Соединение с базой данных.
     */
    public Optional<Connection> getConnection() {
        if (ConnectionSingleton.getInstance().getConnection().isEmpty()) {
            createConnection();
        }
        return ConnectionSingleton.getInstance().getConnection();
    }
}