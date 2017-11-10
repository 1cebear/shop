DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM orderrows;
DELETE FROM items;
DELETE FROM orders;

ALTER TABLE users
  AUTO_INCREMENT = 1;

ALTER TABLE orders
  AUTO_INCREMENT = 1;

ALTER TABLE items
  AUTO_INCREMENT = 1;

ALTER TABLE orderrows
  AUTO_INCREMENT = 1;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_ADMIN', 2),
  ('ROLE_USER', 2);

INSERT INTO items (name, price, description) VALUES
  ('item 1', 100, '1st item'),
  ('item 2', 115, '2nd item'),
  ('item 3', 96, '3rd item');

INSERT INTO orders (name, commentary) VALUES
  ('1st order', ''),
  ('2nd order', '');

INSERT INTO orderrows (order_id, item_id, quantity, price, sum) VALUES
  (1, 1, 10, 100, 1000);