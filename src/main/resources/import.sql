/* INSERINDO PELES */
INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (1, true, false, true);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (2, false, false, false);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (3, true, true, true);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (4, false, true, false);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (5, false, false, true);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (1, true, false, false);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (2, true, true, false);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (3, false, false, true);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (4, true, false, true);

INSERT INTO skin (tipo, melasma, rosacea, sensivel) VALUES (5, false, true, true);


/* INSERINDO ROLES */

INSERT INTO role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO role (authority) VALUES ('ROLE_USER');

INSERT INTO role (authority) VALUES ('ROLE_CLIENT');

/* TABELA DE USUARIOS (SEM SENHA)*/
INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('jdoe', 'John Doe', 'john.doe@example.com', '1990-05-15', 10);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('asmith', 'Alice Smith', 'alice.smith@example.com', '1985-08-23', 9);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('bwhite', 'Bob White', 'bob.white@example.com', '1992-12-01', 8);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('cgreen', 'Carol Green', 'carol.green@example.com', '1988-03-10', 7);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('djames', 'David James', 'david.james@example.com', '1995-07-07', 6);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('ewilson', 'Emma Wilson', 'emma.wilson@example.com', '1991-01-19', 5);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('ffrank', 'Frank Frankson', 'frank.frankson@example.com', '1987-09-30', 4);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('gking', 'Grace King', 'grace.king@example.com', '1993-06-25', 3);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('hlee', 'Henry Lee', 'henry.lee@example.com', '1989-11-11', 2);

INSERT INTO users (username, name, email, birth_date, id_skin) VALUES ('ikim', 'Ivy Kim', 'ivy.kim@example.com', '1994-02-28',1);

/* INSERINDO RELACIONAMENTO DE PAPEIS COM USUARIOS */
INSERT INTO user_role (user_id, role_id) VALUES (1,1);

INSERT INTO user_role (user_id, role_id) VALUES (2,1);

INSERT INTO user_role (user_id, role_id) VALUES (2,2);

/* INSERINDO REVIEWS */
INSERT INTO review (positive, negative, rate, user_id) VALUES ('Ótimo atendimento', 'Demora na entrega', 4.5, 1);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Produto de qualidade', 'Preço um pouco alto', 4.0, 2);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Fácil de usar', 'Manual pouco claro', 3.8, 3);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Entrega rápida', 'Embalagem danificada', 3.5, 4);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Excelente custo-benefício', 'Cor diferente do anúncio', 4.2, 5);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Bom suporte', 'Interface complicada', 3.9, 6);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Design bonito', 'Material frágil', 3.7, 7);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Muito confortável', 'Poucas opções de cor', 4.1, 8);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Tamanho perfeito', 'Chegou atrasado', 3.6, 9);

INSERT INTO review (positive, negative, rate, user_id) VALUES ('Excelente performance', 'Bateria acaba rápido', 4.3, 10);

