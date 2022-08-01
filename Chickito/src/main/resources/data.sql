INSERT INTO ROLES (id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLES (id, name) VALUES (2, 'DIRECTOR');
INSERT INTO ROLES (id, name) VALUES (3, 'LEADER');
INSERT INTO ROLES (id, name) VALUES (4, 'WORKER');

INSERT INTO COMPANIES (id, establishment_date, name, pib, deleted)
VALUES (1, '2022-07-31 20:21:07.253468' , 'PP-TOPIKO' , '24124142', false);

INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (1, 0 , 1, false);  -- INSERT INTO SECTORS (id, type,leader_id, company_id)
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (2, 1 , 1, false);
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (3, 2 , 1, false);
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (4, 3 , 1, false);

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,sector_id,username,role_id, gender)
VALUES (1,1,0,'admin@gmail.com','Admin', 'Admin', null, '$2a$10$u09ug9/hMunP/jsz4aLIDOcXuJ9IPxaGLNjjU9ozwPQONPzHuYrv.', 1, 'admin', 1, 1);

-- insert into countries (name) values (?)
-- insert into cities (country_id, name, postal_code) values (?, ?, ?)
-- insert into addresses (city_id, latitude, longitude, number, street) values (?, ?, ?, ?, ?)
-- insert into buildings (address_id, company_id, head_office) values (?, ?, ?)
