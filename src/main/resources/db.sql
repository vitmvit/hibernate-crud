DROP TABLE IF EXISTS "user";
DROP SEQUENCE IF EXISTS user_id_seq;
CREATE SEQUENCE user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."user"
(
    "id"        bigint DEFAULT nextval('user_id_seq') NOT NULL,
    "name"      character varying(50)                 NOT NULL,
    "last_name" character varying(50)                 NOT NULL,
    "age"       smallint                              NOT NULL,
    CONSTRAINT "user_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

TRUNCATE "user";
INSERT INTO "user" ("id", "name", "last_name", "age")
VALUES (1, 'Ivan', 'Ivanov', 5);
