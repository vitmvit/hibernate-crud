package jm.task.core.jdbc.config;

import jm.task.core.jdbc.exception.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static jm.task.core.jdbc.constant.Constant.SOLUTION_CONFIG;

/**
 * Класс конфигурации базы данных.
 */
public class DatabaseConfig {

    /**
     * Создает карту конфигурации со значениями драйвера, URL, имени пользователя и пароля базы данных.
     *
     * @return Карта конфигурации.
     */
    public Map<String, String> createConfigMap() {
        Properties properties;
        try {
            properties = loadProperties(SOLUTION_CONFIG);
        } catch (IOException e) {
            throw new NotFoundException(SOLUTION_CONFIG + " not found!");
        }
        Map<String, String> map = new HashMap<>(7);
        map.put("driver", (String) properties.get("driver"));
        map.put("url", (String) properties.get("url"));
        map.put("username", (String) properties.get("username"));
        map.put("password", (String) properties.get("password"));
        map.put("dialect", (String) properties.get("dialect"));
        map.put("show_sql", (String) properties.get("show_sql"));
        map.put("context_class", (String) properties.get("context_class"));
        return map;
    }

    private Properties loadProperties(String propertyFile) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream(propertyFile);
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }
}