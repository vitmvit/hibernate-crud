package jm.task.core.jdbc.constant;

public class Constant {

    public static final String SOLUTION_CONFIG = "application.yml";

    // query
    public static final String CREATE_TABLE = "CREATE TABLE public.users (id BIGSERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, lastname VARCHAR(50) NOT NULL, age SMALLINT NOT NULL);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS public.users";
    public static final String CLEAN_TABLE = "TRUNCATE TABLE public.users";
    public static final String SAVE_USER = "INSERT INTO public.users (name, lastname, age) VALUES (?,?,?)";
    public static final String REMOVE_USER = "DELETE FROM public.users WHERE id = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM public.users";

    // message
    public static final String CONNECTION_EXCEPTION = "Connection exception!";
}