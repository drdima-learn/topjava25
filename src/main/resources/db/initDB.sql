DROP TABLE IF EXISTS MEALS;
DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS USERS;

DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ START 100000;

CREATE TABLE USERS
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    name            VARCHAR,
    email           VARCHAR NOT NULL,
    password        VARCHAR NOT NULL,
    registered      TIMESTAMP DEFAULT now(),
    enabled         BOOL    DEFAULT true,
    calories_per_day  INTEGER
);
CREATE UNIQUE INDEX unique_email ON USERS (email);

CREATE TABLE USER_ROLES
(
    user_id     INTEGER NOT NULL,
    role        VARCHAR,
    CONSTRAINT  user_roles_idx  UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE MEALS
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    dateTime        TIMESTAMP,
    description     VARCHAR NOT NULL ,
    calories        INTEGER,
    user_id         INTEGER NOT NULL,
    CONSTRAINT  meals_user_idx  UNIQUE (user_id, dateTime),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);