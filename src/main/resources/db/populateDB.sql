
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM vote;
DELETE FROM dish;
DELETE FROM menu;
DELETE FROM restaurant;

INSERT INTO users (id, name, email, password)
VALUES (0, 'Admin', 'admin@gmail.com', '{noop}admin'),
       (1, 'User1', 'user1@yandex.ru', '{noop}password1'),
       (2, 'User2', 'user2@yandex.ru', '{noop}password2');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 0),
       ('USER', 0);

INSERT INTO restaurant (id, name, address, phone)
VALUES (0, 'restaurant1', 'address1', '+375291111111'),
       (1, 'restaurant2', 'address2', '+375292222222');

INSERT INTO vote (id, restaurant_id, user_id, date)
VALUES (0, 0, 1, '2021-01-25'),
       (1, 0, 2, '2021-01-25'),
       (2, 0, 1, '2021-01-26'),
       (3, 1, 2, '2021-02-26');


INSERT INTO menu (id, restaurant_id, date)
VALUES (0, 0, '2021-01-25'),
       (1, 0, '2021-01-26'),
       (2, 0, now),
       (3, 1, '2021-01-25'),
       (4, 1, '2021-01-26'),
       (5, 1, now);

INSERT INTO  dish(id, menu_id, name, amount)
VALUES (0, 0, 'dish1', '10'),
       (1, 0, 'dish2', '11'),
       (2, 0, 'dish3', '12'),

       (3, 1, 'dish4', '20'),
       (4, 1, 'dish5', '21'),
       (5, 1, 'dish6', '22'),

       (6, 2, 'dish7', '30'),
       (7, 2, 'dish8', '31'),
       (8, 2, 'dish9', '32'),

       (9, 3, 'dish10', '40'),
       (10, 3, 'dish11', '41'),
       (11, 3, 'dish12', '42'),

       (12, 4, 'dish13', '50'),
       (13, 4, 'dish14', '51'),
       (14, 4, 'dish15', '52'),

       (15, 5, 'dish16', '60'),
       (16, 5, 'dish17', '61'),
       (17, 5, 'dish18', '62');



