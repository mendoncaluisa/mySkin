/* INSERINDO PELES */
INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (1, true, false, true);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (2, false, false, false);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (3, true, true, true);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (4, false, true, false);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (5, false, false, true);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (1, true, false, false);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (2, true, true, false);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (3, false, false, true);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (4, true, false, true);

INSERT INTO tb_skin (tipo, melasma, rosacea, sensivel) VALUES (5, false, true, true);


/* INSERINDO ROLES */

INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_role (authority) VALUES ('ROLE_USER');

INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');

/* TABELA DE USUARIOS (SEM SENHA)*/
INSERT INTO tb_user (username, name, email, birth_date) VALUES ('jdoe', 'John Doe', 'john.doe@example.com', '1990-05-15');

INSERT INTO tb_user (username, name, email, birth_date) VALUES ('asmith', 'Alice Smith', 'alice.smith@example.com', '1985-08-23');

INSERT INTO tb_user (username, name, email, birth_date) VALUES ('bwhite', 'Bob White', 'bob.white@example.com', '1992-12-01');

INSERT INTO tb_user (username, name, email, birth_date) VALUES ('cgreen', 'Carol Green', 'carol.green@example.com', '1988-03-10');

INSERT INTO tb_user (username, name, email, birth_date) VALUES ('djames', 'David James', 'david.james@example.com', '1995-07-07');

INSERT INTO tb_user (username, name, email, birth_date, id_skin) VALUES ('ewilson', 'Emma Wilson', 'emma.wilson@example.com', '1991-01-19', 5);

INSERT INTO tb_user (username, name, email, birth_date, id_skin) VALUES ('ffrank', 'Frank Frankson', 'frank.frankson@example.com', '1987-09-30', 4);

INSERT INTO tb_user (username, name, email, birth_date, id_skin) VALUES ('gking', 'Grace King', 'grace.king@example.com', '1993-06-25', 3);

INSERT INTO tb_user (username, name, email, birth_date, id_skin) VALUES ('hlee', 'Henry Lee', 'henry.lee@example.com', '1989-11-11', 2);

INSERT INTO tb_user (username, name, email, birth_date, id_skin) VALUES ('ikim', 'Ivy Kim', 'ivy.kim@example.com', '1994-02-28',1);

/* INSERINDO RELACIONAMENTO DE PAPEIS COM USUARIOS */
INSERT INTO tb_user_role (user_id, role_id) VALUES (1,1);

INSERT INTO tb_user_role (user_id, role_id) VALUES (2,1);

INSERT INTO tb_user_role (user_id, role_id) VALUES (2,2);
