INSERT INTO city (post_code, city_name, longitude, latitude) VALUES ('3300','Eger', 20.37329, 47.90265);
INSERT INTO city (post_code, city_name, longitude, latitude) VALUES ('5400','Mezotur', 20.63333, 47);
INSERT INTO city (post_code, city_name, longitude, latitude) VALUES ('5000','Szolnok', 20.2, 47.18333);
INSERT INTO city (post_code, city_name, longitude, latitude) VALUES ('8621','Zamardi', 17.95366, 46.88488);

INSERT INTO restaurant (max_seats_number, name) VALUES (40,'Heaven');
INSERT INTO restaurant (max_seats_number, name) VALUES (20,'Erod Klub');
INSERT INTO restaurant (max_seats_number, name) VALUES (20,'Boomerang');
INSERT INTO restaurant (max_seats_number, name) VALUES (40,'Corner');

INSERT INTO _user (authority, email, username, password) VALUES ('ADMIN','admin@gmail.com','admin','admin');
INSERT INTO _user (authority, email, username, password) VALUES ('USER','user@gmail.com','user','user');
INSERT INTO _user (authority, email, username, password) VALUES ('USER','bandi@gamil.com','bandi1','bandi');

INSERT INTO city_link (restaurant_id, city_id) VALUES (2,1);
INSERT INTO city_link (restaurant_id, city_id) VALUES (3,4);

INSERT INTO reservation (seat_number, time, city_id, user_id, restaurant_id) VALUES (20,'2020-10-01 14:05:04.000000',3,3,4);