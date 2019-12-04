INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '12345678901', 'Ona', 'Marija');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '22222222222', 'Jonas', 'Petras');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '33333333333', 'Jonas', 'Kazys');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '4444', 'A', 'D');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '5555', 'B', 'G');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '666', 'C', 'T');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '777', 'D', 'Y');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '88888', 'E', 'H');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '9', 'F', 'X');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '9', 'G', 'N');
INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '4545454', 'S', 'X');

INSERT INTO address(version, city) VALUES(0, "Babtai");
INSERT INTO address(version, city) VALUES(0, "Balbieriskis");
INSERT INTO address(version, city) VALUES(0, "Vilnius");

INSERT INTO driver_address(driver_id, address_id) VALUES(1, 1);
INSERT INTO driver_address(driver_id, address_id) VALUES(1, 3);
INSERT INTO driver_address(driver_id, address_id) VALUES(2, 2);

INSERT INTO radars(version, date, number, distance, time, driver_id) VALUES(0, '2019-01-01 23:59:00', 'AAA001', 5000, 120, 1);
INSERT INTO radars(version, date, number, distance, time, driver_id) VALUES(0, '2019-01-01 10:50:00', 'BBB222', 5000, 120, 1);
INSERT INTO radars(version, date, number, distance, time, driver_id) VALUES(0, '2019-01-02 14:59:00', 'AAA001', 5000, 115, 2);
INSERT INTO radars(version, date, number, distance, time) VALUES(0, '2019-01-03 10:59:00', 'CCC222', 5000, 122);

INSERT INTO comments(version, radar_id, date, comment) VALUES (0, 1, '2019-01-15 13:59:00', 'Ieškau savininko...');
INSERT INTO comments(version, radar_id, date, comment) VALUES (0, 1, '2019-01-16 10:00:00', 'Atrodo radau');
INSERT INTO comments(version, radar_id, date, comment) VALUES (0, 2, '2019-01-15 13:00:00', 'Nepilnametis vairuorojas');

INSERT INTO accounts(email, name, password, role) VALUES ('boss@b.lt', 'Jonas', 'q', 'BOSS');
INSERT INTO accounts(email, name, password, role) VALUES ('z@b.lt', 'Zose', 'q', 'MANAGER');
INSERT INTO accounts(email, name, password, role) VALUES ('a@b.lt', 'Aminas', 'q', 'ADMIN');
