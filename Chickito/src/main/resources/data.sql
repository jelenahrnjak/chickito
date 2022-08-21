INSERT INTO ROLES (id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLES (id, name) VALUES (2, 'DIRECTOR');
INSERT INTO ROLES (id, name) VALUES (3, 'LEADER');
INSERT INTO ROLES (id, name) VALUES (4, 'WORKER');

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,username,role_id, gender)
VALUES (222,true,false,'direktor@gmail.com','Janko', 'Ratkov', null, '$2a$10$I1rJ7EiiGwH/phEllVzAAelcVv3Rrbu7oXc2PfmEO6r/b88p9gd7S', 'direktor', 2, 1);


INSERT INTO COMPANIES (id, establishment_date, name, pib, deleted, director_id)
VALUES (111, '2022-07-31 20:21:07.253468' , 'PP-TOPIKO' , '24124142', false, 222);

INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (111, 0 , 111, false);  -- INSERT INTO SECTORS (id, type,leader_id, company_id)
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (222, 1 , 111, false);
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (333, 2 , 111, false);
INSERT INTO SECTORS (id, type, company_id, deleted) VALUES (444, 3 , 111, false);

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,username,role_id, gender)
VALUES (111,true,false,'admin@gmail.com','Admin', 'Admin', null, '$2a$10$u09ug9/hMunP/jsz4aLIDOcXuJ9IPxaGLNjjU9ozwPQONPzHuYrv.', 'admin', 1, 1);

-- insert into countries (name) values (?)
-- insert into cities (country_id, name, postal_code) values (?, ?, ?)
-- insert into addresses (city_id, latitude, longitude, number, street) values (?, ?, ?, ?, ?)
-- insert into buildings (address_id, company_id, head_office) values (?, ?, ?)
