package jm.task.core.jdbc.constant;

public class Constant {

    public static final String SOLUTION_CONFIG = "application.yml";

    // query
    public static final String CREATE_TABLE = "CREATE TABLE public.user (id BIGSERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, age SMALLINT NOT NULL);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS public.user";
    public static final String CLEAN_TABLE = "TRUNCATE TABLE public.user";
    public static final String SAVE_USER = "INSERT INTO public.user (name, last_name, age) VALUES (?,?,?)";
    public static final String REMOVE_USER = "DELETE FROM public.user WHERE id = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM public.user";

    // message
    public static final String CONNECTION_EXCEPTION = "Connection exception!";
}