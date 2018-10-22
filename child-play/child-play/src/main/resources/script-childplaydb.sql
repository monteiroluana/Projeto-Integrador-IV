create database childplaydb;

use childplaydb;

create table usuario(
    idUsuario		INTEGER NOT NULL AUTO_INCREMENT,
    nome		VARCHAR(255) NOT NULL,
    login		VARCHAR(30) NOT NULL,
    senha		VARCHAR(28) NOT NULL,
    funcao		VARCHAR(255),
    enable		BOOLEAN,	
   CONSTRAINT PK_USUARIO PRIMARY KEY (idUsuario)
);


create table cliente(
    idCliente           INTEGER NOT NULL AUTO_INCREMENT,
    nome		VARCHAR(255) NOT NULL,
    cpf			VARCHAR(11) NOT NULL,
    dataNasc		DATE,
    genero		VARCHAR(10),
    telefone		VARCHAR(15),
    email		VARCHAR(255),
    login		VARCHAR(255) NOT NULL,
    senha		VARCHAR(255) NOT NULL,
    enable		BOOLEAN,
    CONSTRAINT PK_USUARIO PRIMARY KEY (idCliente)
);


create table endereco(
    idCliente           INTEGER NOT NULL AUTO_INCREMENT,
    cep			VARCHAR(8),
    logradouro          VARCHAR(255),
    numero		VARCHAR(10),
    bairro		VARCHAR(255),
    cidade		VARCHAR(255),
    estado		VARCHAR(2),
    complemento		VARCHAR(255),
    enable		BOOLEAN,
    FOREIGN KEY(idCliente) REFERENCES cliente(idCliente)
);


create table cartao(
    idCliente           INTEGER NOT NULL AUTO_INCREMENT,
    nomeTitular		VARCHAR(255),
    tipoCartao		VARCHAR(20),
    numCartao		VARCHAR(30),
    codSeguranca	VARCHAR(30),
    validade		DATE,
    enable		BOOLEAN,
    FOREIGN KEY(idCliente) REFERENCES cliente(idCliente)
);


create table produto(
    idProduto           INTEGER NOT NULL AUTO_INCREMENT,
    nome		VARCHAR(255),
    marca		VARCHAR(255),
    descricao		VARCHAR(255),
    caracteristicas	VARCHAR(255),
    imagem              VARCHAR(255),
    alt                 VARCHAR(255),
    idade		INT,
    categoria		VARCHAR(255),
    preco		DECIMAL,
    estoque		INT,
    desconto		INT,
    enable              BOOLEAN,
   CONSTRAINT PK_PRODUTO PRIMARY KEY (idProduto)
);


create table pedido(
    idPedido            INTEGER NOT NULL AUTO_INCREMENT,
    idCliente           INT,
    idUsuario           INT,
    dataPedido          DATE,
    enderecoEntrega     VARCHAR(255),
    tipoPagamento       VARCHAR(255),
    status              CHAR,
    CONSTRAINT PK_PEDIDO PRIMARY KEY (idPedido),
    FOREIGN KEY(idCliente)REFERENCES cliente(idCliente),
    FOREIGN KEY(idUsuario)REFERENCES usuario(idUsuario)
);


create table item_pedido(
    idPedido            INT,
    idProduto           INT,
    quantidade          INT,
    preco               DECIMAL,
    FOREIGN KEY(idPedido) REFERENCES pedido(idPedido),
    FOREIGN KEY(idProduto) REFERENCES produto(idProduto)
);


INSERT INTO USUARIO (nome,login,senha,funcao,enable) VALUES
("Jonas Araujo","jonas.araujo","jonas","Gerente de Projeto",true),
("Luana Monteiro", "luana.monteiro", "luana","Dev.Back-end", true),
("Bruna Sayuri","bruna.sayuri","bruna","Analista",true),
("Rodrigo Normando","rodrigo.normando","rodrigo","Dev.Front-end",true);


INSERT INTO PRODUTO (nome, marca, descricao, caracteristicas, idade, categoria, preco, estoque, desconto, enable, imagem, alt) VALUES
("pato","marca do pato", "descricao pato", "pato de borracha", 3, "brinquedos pra nené", 19.99, 21, 0, true, "yj9v2sF.jpg", "Patinho de borracha"),
("cubo mágico ursal","marca do cubo", "descricao cubo mágico", "todos os lados iguais", 10, "estratégia", 30.00, 100, 0, true, "CfJJDDe.jpg", "Cubo mágido unicolor"),
("aviaozinho de papel","marca do avião", "descricao aviaozinho", "modelo alienigena para vc", 10, "brinquedo pra neném", 19.99, 30, 0, true, "CceQ45v.jpg", "Seu pai sabe fazer aviões de papel?");