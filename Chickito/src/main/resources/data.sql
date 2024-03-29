INSERT INTO ROLES (id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLES (id, name) VALUES (2, 'DIRECTOR');
INSERT INTO ROLES (id, name) VALUES (3, 'LEADER');
INSERT INTO ROLES (id, name) VALUES (4, 'WORKER');

INSERT INTO COUNTRIES (id, name) VALUES (111, 'Serbia');
INSERT INTO CITIES ( id, name, postal_code, country_id) VALUES (111, 'Subotica', '24000', 111);
INSERT INTO ADDRESSES (id, latitude, longitude, "number", street, city_id)
VALUES(111, 46.09984041288659, 19.6614071369724, 3, 'Ištvana Sečenjija', 111);

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,username,role_id, gender, available_vacation_days, old_vacation_days)
VALUES (111,true,false,'admin@gmail.com','Admin', 'Admin', null, '$2a$10$u09ug9/hMunP/jsz4aLIDOcXuJ9IPxaGLNjjU9ozwPQONPzHuYrv.', 'admin', 1, 1, 5, 0);

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,username,role_id, gender, available_vacation_days, vacation_days_per_year, old_vacation_days)
VALUES (222,true,false,'jelenahrnjak99@gmail.com','Janko', 'Ratkov', null, '$2a$10$I1rJ7EiiGwH/phEllVzAAelcVv3Rrbu7oXc2PfmEO6r/b88p9gd7S', 'direktor', 2, 1, 5, 25,2 );

INSERT INTO COMPANIES (id, establishment_date, name, pib, deleted, director_id)
VALUES (111, '2022-07-31 20:21:07.253468' , 'PP-TOPIKO' , '24124142', false, 222);

INSERT INTO public.buildings(id, deleted, head_office, address_id, company_id)
VALUES (111, false, true, 111, 111);

INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (111, 0 , 111, false);
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (222, 1 , 111, false);
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (333, 2 , 111, false);
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (444, 3 , 111, false);

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,username,role_id, gender,sector_id, available_vacation_days, vacation_days_per_year, old_vacation_days)
VALUES (333,true,false,'dragansaric@gmail.com','Dragan', 'Saric', null, '$2a$10$s4cBPBON540yp30i7i2ZQ.hybr3EX2x9GbVd2PzPUYcUyHFAlNWe2', 'rukovodilac', 3, 1, 111, 5,20, 0);

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,username,role_id, gender,sector_id, phone_number, available_vacation_days, vacation_days_per_year, old_vacation_days)
VALUES (411,true,false,'jovanperic@gmail.com','Jovan', 'Peric', null, '$2a$10$GQFIf0cvBL86aU4Awv2X4uTys9muPDeN/PPzU6tuo6IhuA7vUub0K', 'radnik1', 4, 0, 111, '0683293843', 5, 21, 3);

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,username,role_id, gender,sector_id, phone_number, available_vacation_days, vacation_days_per_year, old_vacation_days)
VALUES (422,true,false,'markosavic@gmail.com','Marko', 'Savić', null, '$2a$10$GQFIf0cvBL86aU4Awv2X4uTys9muPDeN/PPzU6tuo6IhuA7vUub0K', 'radnik2', 4, 1, 111, '063928384', 5, 25, 2);

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,username,role_id, gender,sector_id, phone_number, available_vacation_days, vacation_days_per_year, old_vacation_days)
VALUES (433,true,false,'draganatubic@gmail.com','Dragana', 'Tubić', null, '$2a$10$GQFIf0cvBL86aU4Awv2X4uTys9muPDeN/PPzU6tuo6IhuA7vUub0K', 'radnik3', 4, 0, 222, '0628231234', 5, 23, 2);

INSERT INTO ORDERS (id, approved, creation_date, price, author_id, reviewer_id)
VALUES (111, true, '2022-08-15 20:21:07.253468', 5000, 333, 222);
INSERT INTO ORDERS (id, approved, creation_date, price, author_id, reviewer_id)
VALUES (222, null, '2022-08-21 20:21:07.253468', 7000, 333, 222);

INSERT INTO DOCUMENTATIONS (id, work_Instructions, washing_instructions)
VALUES (111, 'Kapacitet: 500 litara.', 'Ne prati ventile.');

INSERT INTO SPARE_PARTS (id, stock_number, name, quantity, documentation_id)
VALUES (111, '324443', 'Ventil', 123, 111);

INSERT INTO MACHINES (id, active, name,model, price, quantity, serial_number, order_id, sector_id, technical_task)
VALUES (111, true, 'Injektor','MHM 280', 2000, 3, '12345', 111, 111, 'Broj obrtaja u minuti: 20');
INSERT INTO MACHINES (id, active, model, name, documentation_id, price, quantity, serial_number, order_id, sector_id)
VALUES (222, true, 'MKR 130', 'Tumbler', 111, 3000, 1, '1233', 111, 111);
INSERT INTO MACHINES (id, active, model, name, price, quantity, serial_number, order_id, sector_id)
VALUES (333, false, 'POP', 'Grebalica', 7000, 1, '12333', 222, 111);

INSERT INTO MACHINE_MAINTENANCES (id, year, author_id) VALUES (111, 2022, 333);
INSERT INTO MACHINE_MAINTENANCES (id, year, author_id) VALUES (222, 2022, 333);

INSERT INTO MACHINE_MAINTENANCE_ITEMS ( id, plan, machine_id, machine_maintenance_id)
VALUES (111, 'Voditi računa o pritisku ubrizgavanja.', 111, 111);
INSERT INTO MACHINE_MAINTENANCE_ITEMS ( id, plan, machine_id, machine_maintenance_id)
VALUES (222, 'Beležiti i pratiti ukupnog vremena i promeniti filter vakuumskog sistema.', 222, 222);

INSERT INTO WORKERS_ON_MACHINES (worker_id, machine_id, main_worker) VALUES (411, 111, true);

INSERT INTO REQUESTED_DAYS (id, date) VALUES (111, '2022-09-05');
INSERT INTO REQUESTED_DAYS (id, date) VALUES (222, '2022-09-06');
INSERT INTO REQUESTED_DAYS (id, date) VALUES (333, '2022-09-07');
INSERT INTO REQUESTED_DAYS (id, date) VALUES (444, '2022-09-08');

INSERT INTO VACATION_REQUESTS (id, approved, request_expiration_date, user_id, request_reviewer_id)
VALUES (111, null, '2022-09-05', 411, 222);

INSERT INTO VACATION_REQUESTS_DATES (vacation_request_id, dates_id) VALUES (111, 111);
INSERT INTO VACATION_REQUESTS_DATES (vacation_request_id, dates_id) VALUES (111, 222);
INSERT INTO VACATION_REQUESTS_DATES (vacation_request_id, dates_id) VALUES (111, 333);
INSERT INTO VACATION_REQUESTS_DATES (vacation_request_id, dates_id) VALUES (111, 444);