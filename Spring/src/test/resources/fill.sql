create table user_details (id bigint not null auto_increment, version datetime(6), authority varchar(255), email varchar(200) not null, password varchar(255) not null, username varchar(200) not null, primary key (id));
create table city (id bigint not null auto_increment, version datetime(6), city_name varchar(200) not null, latitude double precision not null, longitude double precision not null, post_code varchar(4) not null, primary key (id));
create table city_link (restaurant_id bigint not null, city_id bigint not null, primary key (restaurant_id, city_id));
create table reservation (id bigint not null auto_increment, version datetime(6), seat_number integer not null, time datetime(6) not null, city_id bigint, user_id bigint, restaurant_id bigint, primary key (id));
create table restaurant (id bigint not null auto_increment, version datetime(6), max_seats_number integer not null, name varchar(250) not null, primary key (id));


INSERT INTO city (id, version, post_code, city_name, longitude, latitude) VALUES (5, CURRENT_TIMESTAMP(), '3300','Eger', 20.37329, 47.90265);
INSERT INTO city (id, version, post_code, city_name, longitude, latitude) VALUES (6, CURRENT_TIMESTAMP(), '5400','Mezotur', 20.63333, 47);
INSERT INTO city (id, version,  post_code, city_name, longitude, latitude) VALUES (7, CURRENT_TIMESTAMP(), '5000','Szolnok', 20.2, 47.18333);
INSERT INTO city (id, version,  post_code, city_name, longitude, latitude) VALUES (8, CURRENT_TIMESTAMP(), '8621','Zamardi', 17.95366, 46.88488);

INSERT INTO restaurant (id, version, max_seats_number, name) VALUES (5, CURRENT_TIMESTAMP(), 40,'Heaven');
INSERT INTO restaurant (id, version, max_seats_number, name) VALUES (6, CURRENT_TIMESTAMP(), 20,'Erod Klub');
INSERT INTO restaurant (id, version, max_seats_number, name) VALUES (7, CURRENT_TIMESTAMP(), 20,'Boomerang');
INSERT INTO restaurant (id, version, max_seats_number, name) VALUES (8, CURRENT_TIMESTAMP(), 40,'Corner');

INSERT INTO user_details (id, version, authority, email, username, password) VALUES (5, CURRENT_TIMESTAMP(), 'ADMIN','admin@gmail.com','admin','admin');
INSERT INTO user_details (id, version, authority, email, username, password) VALUES (6, CURRENT_TIMESTAMP(), 'USER','user@gmail.com','user','user');
INSERT INTO user_details (id, version, authority, email, username, password) VALUES (7, CURRENT_TIMESTAMP(), 'USER','bandi@gamil.com','bandi1','bandi');

INSERT INTO city_link (restaurant_id, city_id) VALUES (6,5);
INSERT INTO city_link (restaurant_id, city_id) VALUES (7,7);
INSERT INTO city_link (restaurant_id, city_id) VALUES (8,5);

INSERT INTO reservation (id, version, seat_number, time, city_id, user_id, restaurant_id) VALUES (5, CURRENT_TIMESTAMP(), 20,'2020-10-01',7,7,8);