CREATE database javaVeiculos;
USE javaVeiculos;
 
CREATE TABLE veiculo (
    id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    modelo varchar(25) Not NUll,
    fabricante varchar(25) Not NUll,
    cor varchar(20) Not NUll,
    ano int,
    preco double
 )
 
CREATE TABLE vendas (
    id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    idVeiculo int NUll,
    dataHora datetime DEFAUT current_timestamp()	
 )
