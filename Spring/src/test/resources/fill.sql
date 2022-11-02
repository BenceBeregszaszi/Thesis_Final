create table user_details (id bigint not null auto_increment, version datetime(6), authority varchar(255), email varchar(200) not null, password varchar(255) not null, username varchar(200) not null, is_disabled bit not null, reminder varchar(255) not null, primary key (id));
create table city (id bigint not null auto_increment, version datetime(6), city_name varchar(200) not null, post_code varchar(4) not null, primary key (id));
create table city_link (restaurant_id bigint not null, city_id bigint not null, primary key (restaurant_id, city_id));
create table reservation (id bigint not null auto_increment, version datetime(6), seat_number integer not null, time date not null, city_id bigint, user_id bigint, restaurant_id bigint, primary key (id));
create table restaurant (id bigint not null auto_increment, version datetime(6), max_seats_number integer not null, name varchar(250) not null, address varchar(200) not null, primary key (id));


INSERT INTO city (id, version, post_code, city_name) VALUES (5, CURRENT_TIMESTAMP(), '3300','Eger');
INSERT INTO city (id, version, post_code, city_name) VALUES (6, CURRENT_TIMESTAMP(), '5400','Mezotur');
INSERT INTO city (id, version,  post_code, city_name) VALUES (7, CURRENT_TIMESTAMP(), '5000','Szolnok');
INSERT INTO city (id, version,  post_code, city_name) VALUES (8, CURRENT_TIMESTAMP(), '8621','Zamardi');

INSERT INTO restaurant (id, version, max_seats_number, name, address) VALUES (5, CURRENT_TIMESTAMP(), 40,'Heaven', 'Erős út 13');
INSERT INTO restaurant (id, version, max_seats_number, name, address) VALUES (6, CURRENT_TIMESTAMP(), 20,'Erod Klub', 'Nagy István útca 56');
INSERT INTO restaurant (id, version, max_seats_number, name, address) VALUES (7, CURRENT_TIMESTAMP(), 60,'Boomerang', 'Kiss Péter utca 34');
INSERT INTO restaurant (id, version, max_seats_number, name, address) VALUES (8, CURRENT_TIMESTAMP(), 40,'Corner', 'Nagy Kázmér 45 3.em 4');

INSERT INTO user_details (id, version, authority, email, username, password, is_disabled, reminder) VALUES (5, CURRENT_TIMESTAMP(), 'ADMIN','admin@gmail.com','admin','admin', false, 'admin reminder');
INSERT INTO user_details (id, version, authority, email, username, password, is_disabled, reminder) VALUES (6, CURRENT_TIMESTAMP(), 'USER','user@gmail.com','user','user', true, 'user reminder');
INSERT INTO user_details (id, version, authority, email, username, password, is_disabled, reminder) VALUES (7, CURRENT_TIMESTAMP(), 'USER','bandi@gamil.com','bandi1','bandi', false, 'bandi reminder');

INSERT INTO city_link (restaurant_id, city_id) VALUES (6,5);
INSERT INTO city_link (restaurant_id, city_id) VALUES (7,7);
INSERT INTO city_link (restaurant_id, city_id) VALUES (8,5);

INSERT INTO reservation (id, version, seat_number, time, city_id, user_id, restaurant_id) VALUES (5, CURRENT_TIMESTAMP(), 20,'2020-10-01',7,7,8);