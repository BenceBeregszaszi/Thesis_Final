ALTER DATABASE schedule CHARACTER SET utf8mb3 collate = utf8mb3_hungarian_ci ;

create table city (id bigint not null auto_increment, version datetime(6), city_name varchar(200) not null, post_code varchar(4) not null, primary key (id)) engine=InnoDB;
create table city_link (restaurant_id bigint not null, city_id bigint not null, primary key (restaurant_id, city_id)) engine=InnoDB;
create table reservation (id bigint not null auto_increment, version datetime(6), seat_number integer not null, time date not null, city_id bigint, user_id bigint, restaurant_id bigint, primary key (id)) engine=InnoDB;
create table restaurant (id bigint not null auto_increment, version datetime(6), max_seats_number integer not null, name varchar(250) not null, address varchar(200) not null, primary key (id)) engine=InnoDB;
create table user_details (id bigint not null auto_increment, version datetime(6), authority varchar(255), email varchar(200) not null, is_disabled bit not null, password varchar(255) not null, username varchar(200) not null, reminder varchar(255) not null, primary key (id)) engine=InnoDB;

INSERT INTO `city`(id, version, post_code, city_name) VALUES (6,'2020-09-17 14:02:14.000000','3300','Eger');
INSERT INTO `city`(id, version, post_code, city_name) VALUES (7,'2020-09-17 14:03:03.000000','5400','Mezotur');
INSERT INTO `city`(id, version, post_code, city_name) VALUES (8,'2020-09-17 14:03:15.000000','5000','Szolnok');
INSERT INTO `city`(id, version, post_code, city_name) VALUES (9,'2020-09-17 14:03:49.000000','8621','Zamardi');

INSERT INTO `restaurant` (id, version, max_seats_number, name, address) VALUES (6,'2020-09-17 14:07:29.000000',40,'Heaven', 'Eros ut 13');
INSERT INTO `restaurant` (id, version, max_seats_number, name, address) VALUES (7,'2020-09-17 14:08:24.000000',20,'Erod Klub', 'Nagy Istvan utca 56');
INSERT INTO `restaurant` (id, version, max_seats_number, name, address) VALUES (8,'2020-09-17 14:08:28.000000',20,'Boomerang', 'Kiss Peter utca 34');
INSERT INTO `restaurant` (id, version, max_seats_number, name, address) VALUES (9,'2020-09-17 14:08:30.000000',40,'Corner', 'Nagy Kazmer 45 3.em 4');

INSERT INTO `user_details` (id, version, authority, email, password, username, is_disabled, reminder) VALUES (3,'2020-09-17 14:05:42.000000','ADMIN','admin@gmail.com', MD5('admin'),'admin', false, 'admin reminder');
INSERT INTO `user_details` (id, version, authority, email, password, username, is_disabled, reminder) VALUES (4,'2020-09-17 14:06:08.000000','USER','user@gmail.com', MD5('user'),'user', true, 'user reminder');
INSERT INTO `user_details` (id, version, authority, email, password, username, is_disabled, reminder) VALUES (5,'2020-09-17 14:06:27.000000','USER','bandi@gamil.com', MD5('bandi1'),'bandi', false, 'bandi reminder');

INSERT INTO `city_link` (restaurant_id, city_id) VALUES (7,7),(6,9);

INSERT INTO `reservation` (id, version, seat_number, time, city_id, user_id, restaurant_id) VALUES (3,'2020-09-17 14:04:46.000000',20,'2020-10-01 14:05:04.000000',6,3,9);