INSERT INTO restaurant (id, version, name, maxSeatsNumber) VALUES (1, 1, "Buona Sera", 30);
INSERT INTO restaurant (id, version, name, maxSeatsNumber) VALUES (2, 1, "Dabo Döner", 20);
INSERT INTO restaurant (id, version, name, maxSeatsNumber) VALUES (3, 1, "Hippolit terrace", 10);
INSERT INTO restaurant (id, version, name, maxSeatsNumber) VALUES (4, 1, "Erőd Klub", 50);
INSERT INTO restaurant (id, version, name, maxSeatsNumber) VALUES (5, 1, "Corner", 30);

INSERT INTO city (id, version, postCode, restaurant) VALUES (1, 1, 3300, 1);
INSERT INTO city (id, version, postCode, restaurant) VALUES (2, 1, 5400, 3);
INSERT INTO city (id, version, postCode, restaurant) VALUES (3, 1, 5000, 1);
INSERT INTO city (id, version, postCode, restaurant) VALUES (4, 1, 5012, 2);
INSERT INTO city (id, version, postCode, restaurant) VALUES (5, 1, 4356, 5);

INSERT INTO user (id, version, username, password, email, authority) VALUES (1, 1, "admin", "admin", "admin@gmail.com", "ADMIN");
INSERT INTO user (id, version, username, password, email, authority) VALUES (1, 1, "user", "user", "user@gmail.com", "USER");

INSERT INTO reservation (id, version, userId, seatNumber, cityId, restaurantId, time) VALUES (1, 1, 1, 4, 4, 2, CURRENT_TIMESTAMP);
INSERT INTO reservation (id, version, userId, seatNumber, cityId, restaurantId, time) VALUES (1, 2, 1, 8, 1, 1, CURRENT_TIMESTAMP);