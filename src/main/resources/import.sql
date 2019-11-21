INSERT INTO drivers(pid, first_name, last_name) VALUES ('12345678901', 'Ona', 'Marija');
INSERT INTO drivers(pid, first_name, last_name) VALUES ('22222222222', 'Jonas', 'Petras');
INSERT INTO drivers(pid, first_name, last_name) VALUES ('33333333333', 'Jonas', 'Kazys');

INSERT INTO address(city) VALUES("Babtai");
INSERT INTO address(city) VALUES("Balbieriskis");
INSERT INTO address(city) VALUES("Vilnius");

INSERT INTO driver_address(driver_id, address_id) VALUES(1, 1);
INSERT INTO driver_address(driver_id, address_id) VALUES(1, 3);
INSERT INTO driver_address(driver_id, address_id) VALUES(2, 2);

INSERT INTO radars(date, number, distance, time, driver_id) VALUES('2019-01-01 23:59:00', 'AAA001', 5000, 120, 1);
INSERT INTO radars(date, number, distance, time, driver_id) VALUES('2019-01-01 10:50:00', 'BBB222', 5000, 120, 1);
INSERT INTO radars(date, number, distance, time, driver_id) VALUES('2019-01-02 14:59:00', 'AAA001', 5000, 115, 2);
INSERT INTO radars(date, number, distance, time) VALUES('2019-01-03 10:59:00', 'CCC222', 5000, 122);

INSERT INTO comments(radar_id, date, comment) VALUES (1, '2019-01-15 13:59:00', 'Ieškau savininko...');
INSERT INTO comments(radar_id, date, comment) VALUES (1, '2019-01-16 10:00:00', 'Atrodo radau');
INSERT INTO comments(radar_id, date, comment) VALUES (2, '2019-01-15 13:00:00', 'Nepilnametis vairuorojas');

