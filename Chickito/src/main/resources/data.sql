INSERT INTO ROLES (id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLES (id, name) VALUES (2, 'DIRECTOR');
INSERT INTO ROLES (id, name) VALUES (3, 'LEADER');
INSERT INTO ROLES (id, name) VALUES (4, 'WORKER');

INSERT INTO SECTORS (id, type) VALUES (1, 'ODRZAVANJE_I_ENERGETIKA');
INSERT INTO SECTORS (id, type) VALUES (2, 'KLANICA_I_PAKERAJ');
INSERT INTO SECTORS (id, type) VALUES (3, 'PRERADA');
INSERT INTO SECTORS (id, type) VALUES (4, 'KONTROLA_KVALITETA');

INSERT INTO USERS (id,active,deleted,email,first_name,last_name,last_password_reset_date,password,sector,username,role_id)
VALUES (1,1,0,'admin@gmail.com','Admin', 'Admin', null, '$2a$10$u09ug9/hMunP/jsz4aLIDOcXuJ9IPxaGLNjjU9ozwPQONPzHuYrv.', 0, 'admin', 1);
