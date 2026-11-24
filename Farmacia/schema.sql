-- Script de Criação do Banco de Dados para o Sistema de Farmácia

CREATE DATABASE IF NOT EXISTS farmacia_db;
USE farmacia_db;

-- Tabela de Usuários para autenticação
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL
);

-- Inserção de usuário padrão para testes
INSERT IGNORE INTO usuarios (login, senha) VALUES ('admin', '123');

-- Tabela de Medicamentos / Remédios
CREATE TABLE IF NOT EXISTS remedios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    quantidade INT NOT NULL
);
