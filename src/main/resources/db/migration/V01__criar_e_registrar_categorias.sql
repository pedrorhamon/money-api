CREATE TABLE categoria
(
   codigo BIGINT (20) PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR (100) NOT NULL
)

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Supermercado');
INSERT INTO categoria (nome) VALUES ('Farmácia');
INSERT INTO categoria (nome) VALUES ('outros');