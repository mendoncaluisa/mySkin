/* INSERINDO CARACTERÍSTICAS */
INSERT INTO characteristic (description) VALUES ('mista');

INSERT INTO characteristic (description) VALUES ('oleosa');

INSERT INTO characteristic (description) VALUES ('seca');

INSERT INTO characteristic (description) VALUES ('normal');

INSERT INTO characteristic (description) VALUES ('sensível');

INSERT INTO characteristic (description) VALUES ('acneica');



/* INSERINDO ROLES */

INSERT INTO role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO role (authority) VALUES ('ROLE_USER');


/* TABELA DE USUARIOS (SEM SENHA)*/
INSERT INTO users (username, name, email, birth_date) VALUES ('jdoe', 'John Doe', 'john.doe@example.com', '1990-05-15');

INSERT INTO users (username, name, email, birth_date) VALUES ('asmith', 'Alice Smith', 'alice.smith@example.com', '1985-08-23');

INSERT INTO users (username, name, email, birth_date) VALUES ('bwhite', 'Bob White', 'bob.white@example.com', '1992-12-01');

INSERT INTO users (username, name, email, birth_date) VALUES ('cgreen', 'Carol Green', 'carol.green@example.com', '1988-03-10');

INSERT INTO users (username, name, email, birth_date) VALUES ('djames', 'David James', 'david.james@example.com', '1995-07-07');

INSERT INTO users (username, name, email, birth_date) VALUES ('ewilson', 'Emma Wilson', 'emma.wilson@example.com', '1991-01-19');

INSERT INTO users (username, name, email, birth_date) VALUES ('ffrank', 'Frank Frankson', 'frank.frankson@example.com', '1987-09-30');

INSERT INTO users (username, name, email, birth_date) VALUES ('gking', 'Grace King', 'grace.king@example.com', '1993-06-25');

INSERT INTO users (username, name, email, birth_date) VALUES ('hlee', 'Henry Lee', 'henry.lee@example.com', '1989-11-11');

INSERT INTO users (username, name, email, birth_date) VALUES ('ikim', 'Ivy Kim', 'ivy.kim@example.com', '1994-02-28');

INSERT INTO users (username, name, email, birth_date, password) VALUES ('Poliana', 'Poliana Cristina', 'poliana@gmail.com', '2000-01-17', '$2a$10$mqiuBJ35YDMXLKHnZ2YDFedly5HmMdx.vjJ5SMVsTtaQVpKjQ1FF2');

INSERT INTO users (username, name, email, birth_date, password) VALUES ('Maria', 'Maria Luisa', 'maria@gmail.com', '2000-12-25', '$2a$10$mqiuBJ35YDMXLKHnZ2YDFedly5HmMdx.vjJ5SMVsTtaQVpKjQ1FF2');

/* INSERINDO RELACIONAMENTO DE PAPEIS COM USUARIOS */
INSERT INTO user_role (user_id, role_id) VALUES (1,1);

INSERT INTO user_role (user_id, role_id) VALUES (2,1);

INSERT INTO user_role (user_id, role_id) VALUES (2,2);

INSERT INTO user_role (user_id, role_id) VALUES (11,1);

INSERT INTO user_role (user_id, role_id) VALUES (12,1);



INSERT INTO product (name, description, company, size) VALUES ('Creme Hidratante', 'Hidrata profundamente a pele seca', 'BelezaNatural', 200.0);

INSERT INTO product (name, description, company, size) VALUES ('Protetor Solar FPS 50', 'Proteção UVA/UVB de longa duração', 'SunCare', 100.0);

INSERT INTO product (name, description, company, size) VALUES ('Shampoo Revitalizante', 'Fortalece e dá brilho aos cabelos', 'HairLux', 250.0);

INSERT INTO product (name, description, company, size) VALUES ('Condicionador Nutritivo', 'Nutre e desembaraça os fios', 'HairLux', 250.0);

INSERT INTO product (name, description, company, size) VALUES ('Sabonete Facial', 'Limpeza suave para peles sensíveis', 'SkinSoft', 150.0);



/* INSERINDO REVIEWS */
INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Ótimo atendimento', 'Demora na entrega', 4.5, 1, 1);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Produto de qualidade', 'Preço um pouco alto', 4.0, 2, 1);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Fácil de usar', 'Manual pouco claro', 3.8, 3, 2);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Entrega rápida', 'Embalagem danificada', 3.5, 4, 3);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Excelente custo-benefício', 'Cor diferente do anúncio', 4.2, 5, 2);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Bom suporte', 'Interface complicada', 3.9, 6, 4);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Design bonito', 'Material frágil', 3.7, 7, 5);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Muito confortável', 'Poucas opções de cor', 4.1, 8, 3);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Tamanho perfeito', 'Chegou atrasado', 3.6, 9, 1);

INSERT INTO review (positive, negative, rate, user_id, product_id) VALUES ('Excelente performance', 'Bateria acaba rápido', 4.3, 10, 2);



INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Ácido Hialurônico', 'Molécula hidratante que retém água na pele', 'Hidratante', 'Origem biotecnológica', true, false, false, 'Polissacarídeo linear', 'Retenção de umidade', 'Ligação covalente');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Vitamina C', 'Antioxidante que ajuda na síntese de colágeno', 'Antioxidante', 'Natural (frutas cítricas)', true, false, true, 'Ácido ascórbico', 'Doação de elétrons', 'Ligação iônica e covalente');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Óleo de Coco', 'Agente emoliente que suaviza a pele', 'Emoliente', 'Origem vegetal', true, true, false, 'Triglicerídeo', 'Hidratação oclusiva', 'Ligação éster');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Niacinamida', 'Forma ativa da vitamina B3 com ação clareadora', 'Clareador', 'Origem sintética', true, false, false, 'Amida', 'Regulação da melanina', 'Ligação covalente');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Ácido Salicílico', 'Beta hidroxiácido com ação esfoliante', 'Esfoliante', 'Origem vegetal (casca de salgueiro)', true, false, true, 'Ácido aromático', 'Queratinólise', 'Ligação covalente');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Manteiga de Karité', 'Agente nutritivo para peles secas', 'Emoliente', 'Origem vegetal', true, true, false, 'Mistura de ésteres e ácidos graxos', 'Hidratação', 'Ligação éster');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Retinol', 'Derivado da vitamina A que estimula renovação celular', 'Renovador celular', 'Origem sintética', true, false, true, 'Polienos', 'Ativação de receptores nucleares', 'Ligação covalente');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Ácido Glicólico', 'AHA com efeito esfoliante', 'Esfoliante', 'Origem vegetal (cana-de-açúcar)', true, false, true, 'Ácido carboxílico', 'Esfoliação química', 'Ligação covalente');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Extrato de Camomila', 'Calmante e anti-inflamatório natural', 'Calmante', 'Origem vegetal', true, false, false, 'Flavonoides', 'Inibição de citocinas inflamatórias', 'Ligação hidrogênio e covalente');

INSERT INTO ingredient (nome, descricao, funcao, origem, seguro, comedogenico, pot_irritante, estrutura, funcao_quimica, ligacao_quimica) VALUES ('Pantenol', 'Pró-vitamina B5 que melhora hidratação da pele', 'Hidratante', 'Origem sintética', true, false, false, 'Álcool poli-hidroxilado', 'Atração de moléculas de água', 'Ligação hidrogênio');


-- Produto id 1 (Creme Hidratante) com ingredientes 1 e 2
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (1, 1);
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (1, 2);

-- Produto id 2 (Protetor Solar FPS 50) com ingredientes 2 e 3
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (2, 2);
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (2, 3);

-- Produto id 3 (Shampoo Revitalizante) com ingredientes 3 e 4
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (3, 3);
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (3, 4);

-- Produto id 4 (Condicionador Nutritivo) com ingredientes 4 e 5
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (4, 4);
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (4, 5);

-- Produto id 5 (Sabonete Facial) com ingredientes 1 e 5
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (5, 1);
INSERT INTO product_ingredient (product_id, ingredient_id) VALUES (5, 5);


