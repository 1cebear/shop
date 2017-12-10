DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS orderrows;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS orders;


CREATE TABLE users
(
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(100) NOT NULL,
  email      VARCHAR(100) NOT NULL,
  password   VARCHAR(100) NOT NULL,
  registered TIMESTAMP           DEFAULT now(),
  enabled    BOOL                DEFAULT TRUE
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(100),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE orders
(
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id    INTEGER,
  name  VARCHAR(100) NOT NULL,
  email      VARCHAR(100) NOT NULL,
  commentary VARCHAR(100)
);

CREATE TABLE categories
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(100)
);

CREATE TABLE items
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  category_id INTEGER NOT NULL,
  name        VARCHAR(100) NOT NULL,
  price       DOUBLE              DEFAULT 0,
  description VARCHAR(100),
  FOREIGN KEY (category_id) REFERENCES categories (id)
    ON DELETE CASCADE
);

CREATE TABLE orderrows
(
  id       INTEGER PRIMARY KEY AUTO_INCREMENT,
  order_id INTEGER NOT NULL,
  item_id  INTEGER NOT NULL,
  quantity INTEGER             DEFAULT 0,
  price    DOUBLE              DEFAULT 0,
  sum      DOUBLE              DEFAULT 0,
  FOREIGN KEY (order_id) REFERENCES orders (id)
    ON DELETE CASCADE,
  FOREIGN KEY (item_id) REFERENCES items (id)
);

