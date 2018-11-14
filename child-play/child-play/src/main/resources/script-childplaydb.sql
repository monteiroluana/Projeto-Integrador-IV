create database childplaydb;

use childplaydb;

create table usuario(
    idUsuario		INTEGER NOT NULL AUTO_INCREMENT,
    nome		VARCHAR(255),
    login		VARCHAR(30),
    senha		VARCHAR(255),
    funcao		VARCHAR(255),
    enable		BOOLEAN,	
    CONSTRAINT PK_USUARIO PRIMARY KEY (idUsuario),
    CONSTRAINT UC_USUARIO UNIQUE (idUsuario,login)
   
);


create table cliente(
    idCliente           INTEGER NOT NULL AUTO_INCREMENT,
    nome		VARCHAR(255),
    cpf			VARCHAR(11),
    dataNasc		DATE,
    genero		VARCHAR(10),
    telefone		VARCHAR(15),
    email		VARCHAR(255),
    login		VARCHAR(255),
    senha		VARCHAR(255),
    token               VARCHAR(255),
   
    cep			VARCHAR(8),
    logradouro          VARCHAR(255),
    numero		VARCHAR(10),
    bairro		VARCHAR(255),
    cidade		VARCHAR(255),
    uf                  VARCHAR(2),
    complemento		VARCHAR(255),
    enable		BOOLEAN,
    CONSTRAINT PK_CLIENTE PRIMARY KEY (idCliente),
    CONSTRAINT UC_CLIENTE UNIQUE (idCliente,login)
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
    idade		INT,
    categoria		VARCHAR(255),
    preco		DOUBLE,
    estoque		INT,
    desconto		INT,
    enable              BOOLEAN,
   CONSTRAINT PK_PRODUTO PRIMARY KEY (idProduto)
);

create table imagem(
    idImagem            INTEGER NOT NULL AUTO_INCREMENT,
    idProduto           INT,
    imagem              VARCHAR(255),
    alt                 VARCHAR(255),
    CONSTRAINT PK_PRODUTO PRIMARY KEY (idImagem),
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto)
);


create table pedido(
    idPedido            INTEGER NOT NULL AUTO_INCREMENT,
    protocolo           VARCHAR(20),
    idCliente           INT,
    idUsuario           INT,
    dataPedido          DATE,
    tipoPagamento       VARCHAR(255),
    status              VARCHAR(30),
    valorTotal          DOUBLE,
    valorFrete          DOUBLE,

    cep			VARCHAR(8),
    logradouro            VARCHAR(255),
    numero		VARCHAR(10),
    bairro		VARCHAR(255),
    cidade		VARCHAR(255),
    uf                  VARCHAR(2),
    complemento		VARCHAR(255),
    CONSTRAINT PK_PEDIDO PRIMARY KEY (idPedido),
    CONSTRAINT UC_PEDIDO UNIQUE (idPedido,protocolo),
    FOREIGN KEY(idCliente)REFERENCES cliente(idCliente),
    FOREIGN KEY(idUsuario)REFERENCES usuario(idUsuario)
);


create table itemPedido(
    idItem              INTEGER NOT NULL AUTO_INCREMENT,
    idPedido            INT,
    idProduto           INT,
    quantidade          INT,
    preco               DOUBLE,
    CONSTRAINT PK_PEDIDO PRIMARY KEY (idItem),
    FOREIGN KEY(idPedido) REFERENCES pedido(idPedido),
    FOREIGN KEY(idProduto) REFERENCES produto(idProduto)
);


create table endereco(
    idCliente           INTEGER NOT NULL AUTO_INCREMENT,
    cep			VARCHAR(8),
    logradouro          VARCHAR(255),
    numero		VARCHAR(10),
    bairro		VARCHAR(255),
    cidade		VARCHAR(255),
    uf		VARCHAR(2),
    complemento		VARCHAR(255),
    enable		BOOLEAN,
    FOREIGN KEY(idCliente) REFERENCES cliente(idCliente)
);


INSERT INTO USUARIO (nome,login,senha,funcao,enable) VALUES
("Jonas Araujo","jonas.araujo","jonas","Gerente de Projeto",true),
("Luana Monteiro", "luana.monteiro", "luana","Dev.Back-end", true),
("Bruna Sayuri","bruna.sayuri","bruna","Analista",true),
("Rodrigo Normando","rodrigo.normando","rodrigo","Dev.Front-end",true);


INSERT INTO PRODUTO (nome, marca, descricao, caracteristicas, idade, categoria, preco, estoque, desconto, enable) VALUES
("pato","marca do pato", "descricao pato", "pato de borracha", 3, "brinquedos pra nené", 19.99, 21, 0, true),
("cubo mágico ursal","marca do cubo", "descricao cubo mágico", "todos os lados iguais", 10, "estratégia", 30.00, 100, 0, true),
("aviaozinho de papel","marca do avião", "descricao aviaozinho", "modelo alienigena para vc", 10, "brinquedo pra neném", 19.99, 30, 0, true),
("Ursinho","marca do Ursinho", "descricao do Ursinho", "ursinho de apertar", 3, "brinquedos pra nené", 17.99, 21, 0, true),
("Skate","marca do Skate", "descricao do Skate", "Skate radical", 10, "brinquedos e esportes", 129.99, 21, 0, true),
("Bike infantil","marca da Bike", "descricao da Bike", "Bike radical", 10, "brinquedos e esportes", 129.99, 21, 0, true);


INSERT INTO IMAGEM (idProduto, imagem, alt) VALUES
(1,"https://i.imgur.com/yj9v2sF.jpg", "Patinho de borracha"),
(1,"https://i.imgur.com/xqe6RBd.jpg", "Patinho de borracha"),
(1,"https://i.imgur.com/LNJh1W9.jpg", "Patinho de borracha"),

(2,"https://i.imgur.com/CfJJDDe.jpg", "Cubo mágido unicolor"),
(2,"https://i.imgur.com/dpXbmkH.jpg", "Cubo mágido unicolor"),
(2,"https://i.imgur.com/nzc40We.jpg", "Cubo mágido unicolor"),

(3,"https://i.imgur.com/CceQ45v.jpg", "Seu pai sabe fazer aviões de papel?"),
(3,"https://i.imgur.com/5VQVCyx.jpg", "Seu pai sabe fazer aviões de papel?"),
(3,"https://i.imgur.com/oVbYcO8.jpg", "Seu pai sabe fazer aviões de papel?"),

(4,"https://i.imgur.com/DOBgyJa.jpg", "Ursinho"),
(4,"https://i.imgur.com/dVIQjEG.jpg", "Ursinho"),
(4,"https://i.imgur.com/dg6aFRJ.jpg", "Ursinho"),

(5,"https://i.imgur.com/tvJnTaA.jpg", "Skate"),
(5,"https://i.imgur.com/BDCsVAg.jpg", "Skate"),
(5,"https://i.imgur.com/72xOay5.jpg", "Skate"),

(6,"https://i.imgur.com/4LDYycI.jpg", "Bike"),
(6,"https://i.imgur.com/oLmrawq.jpg", "Bike"),
(6,"https://i.imgur.com/rYemDSt.jpg", "Bike");

INSERT INTO `cliente`(`nome`, `email`, `login`, `senha`, `enable`) VALUES ('aoi', 'aoi@aoi.com', 'kunieda', 'kunieda',true);

/*
INSERT INTO cliente (nome, cpf, dataNasc, genero, telefone, email, login, senha, enable) VALUES ('Benício Kevin José das Neves','40049341847','16/04/1996','masculino','7135421150','beniciokevinjosedasneves@cheryamur.com.br','beniciokevinjosedasneves','PMrln1f7GX',true);
INSERT INTO cartao (idCliente, nomeTitular, tipoCartao, numCartao, codSeguranca, validade, enable) VALUES (2,'','cartão de crédito','5184 6943 2861 0388','882','08/10/2019', true);
INSERT INTO endereco (idCliente, logradouro, numero, cep, complemento, bairro, cidade, uf, enable) VALUES (2,'3ª Travessa Carneiro da Rocha','576','40335335','','Pero Vaz','Salvador','BA',true);
*/