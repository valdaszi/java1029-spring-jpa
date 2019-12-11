INSERT INTO drivers(version, pid, first_name, last_name) VALUES (0, '12345678901', 'Onė', 'Marija');
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

INSERT INTO privileges(name, description) VALUES ("DRIVER_READ", "Can read driver info");
INSERT INTO privileges(name, description) VALUES ("DRIVER_WRITE", "Can create, update and delete driver");
INSERT INTO privileges(name, description) VALUES ("RADAR_READ", "Radar read");
INSERT INTO privileges(name, description) VALUES ("RADAR_WRITE", "Radar write");

INSERT INTO roles(name, description) VALUES ("ADMIN", "System administrator");
INSERT INTO roles(name, description) VALUES ("MANAGER", "manager");
INSERT INTO roles(name, description) VALUES ("BOSS", ":)");
INSERT INTO roles(name, description) VALUES ("EDITOR", "Editor");

INSERT INTO roles_privileges(role_id, privilege_id) VALUES ("MANAGER", "DRIVER_READ");
INSERT INTO roles_privileges(role_id, privilege_id) VALUES ("MANAGER", "DRIVER_WRITE");
INSERT INTO roles_privileges(role_id, privilege_id) VALUES ("MANAGER", "RADAR_READ");
INSERT INTO roles_privileges(role_id, privilege_id) VALUES ("MANAGER", "DRIVER_WRITE");

INSERT INTO roles_privileges(role_id, privilege_id) VALUES ("BOSS", "DRIVER_READ");
INSERT INTO roles_privileges(role_id, privilege_id) VALUES ("BOSS", "RADAR_READ");

INSERT INTO accounts(email, name, password) VALUES ('boss@b.lt', 'Jonas', '$2a$10$7aKGODZ1Hc.zXvzTrQvIOufW.1AbfBLeNj5s3IsJptOR.2NmwnECS'); -- , 'USER');
INSERT INTO accounts(email, name, password) VALUES ('z@b.lt', 'Zosė', 'q'); -- , 'MANAGER');
INSERT INTO accounts(email, name, password) VALUES ('a@b.lt', 'Aminas', '$2a$10$jjG1dZc8HwEZmP5nM3CLqO0qGqc7yiIvI78l5DQnEEtoqG0mLg9WG'); -- , 'ADMIN');

INSERT INTO users_roles(account_id, role_id) VALUES(1, 'BOSS');
INSERT INTO users_roles(account_id, role_id) VALUES(2, 'MANAGER');
INSERT INTO users_roles(account_id, role_id) VALUES(3, 'MANAGER');
INSERT INTO users_roles(account_id, role_id) VALUES(3, 'ADMIN');
