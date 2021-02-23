DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE restaurant IF EXISTS;

CREATE TABLE users
(
    id               INTEGER IDENTITY PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL,
    enabled          BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id        INTEGER IDENTITY PRIMARY KEY,
    name      VARCHAR(255)  NOT NULL,
    address   VARCHAR(255)  NOT NULL,
    phone     VARCHAR(255)  NOT NULL,
    CONSTRAINT name_address_idx UNIQUE (name, address)
);

CREATE TABLE vote
(
    id                 INTEGER IDENTITY,
    restaurant_id      INTEGER       NOT NULL,
    user_id            INTEGER       NOT NULL,
    date               DATE DEFAULT now()   NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_unique_users_date_idx
    ON vote (user_id, date);

CREATE TABLE menu
(
    id            INTEGER IDENTITY,
    restaurant_id INTEGER NOT NULL,
    date          DATE DEFAULT now()   NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    CONSTRAINT restaurant_date_idx UNIQUE (restaurant_id, date)
);

CREATE TABLE dish
(
    id        INTEGER IDENTITY,
    menu_id   INTEGER       NOT NULL,
    name      VARCHAR(255)  NOT NULL,
    amount    INTEGER       NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

