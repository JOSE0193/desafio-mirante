
drop table if exists Cidade;

CREATE TABLE Cidade(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    capital int NOT NULL
);
