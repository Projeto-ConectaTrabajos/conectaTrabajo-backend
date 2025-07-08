CREATE DATABASE IF NOT EXISTS Conecta_Trabajo;
USE Conecta_Trabajo;

-- TABELA USUARIO (com todas as constraints necessárias)
CREATE TABLE IF NOT EXISTS Usuario (
    Id_Usuario INT AUTO_INCREMENT PRIMARY KEY,
    CPF VARCHAR(14) UNIQUE NOT NULL,
    RNE VARCHAR(20),
    Telefone VARCHAR(20) NOT NULL,
    Etnia ENUM('Branco', 'Pardo', 'Amarelo', 'Indigena', 'Negro') NOT NULL,
    PaisOrigem VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    Sobrenome VARCHAR(100) NOT NULL,
    Senha VARCHAR(100) NOT NULL,
    Data_Nascimento DATE NOT NULL,
    INDEX idx_email (Email),                     -- Índice para buscas por email
    INDEX idx_cpf (CPF)                          -- Índice para buscas por CPF
);

-- TABELA EMPRESA (com todas as constraints necessárias)
CREATE TABLE IF NOT EXISTS Empresa (
    Id_Empresa INT AUTO_INCREMENT PRIMARY KEY,
    CNPJ VARCHAR(18) UNIQUE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    Senha VARCHAR(100) NOT NULL,
    INDEX idx_email (Email),
    INDEX idx_cnpj (CNPJ)
);

-- TABELA VAGA (com FK corrigida e otimizada)
CREATE TABLE IF NOT EXISTS Vaga (
    Id_Vaga INT AUTO_INCREMENT PRIMARY KEY,
    Titulo VARCHAR(100) NOT NULL,
    Localizacao VARCHAR(255) NOT NULL,
    Competencias TEXT NOT NULL,
    DataPublicacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    Modalidade ENUM('Presencial', 'Híbrido', 'Remoto') NOT NULL,
    Requisitos TEXT NOT NULL,
    Beneficios TEXT,
    Responsabilidade TEXT NOT NULL,
    DescricaoEmpresa TEXT,
    Descricao TEXT NOT NULL,
    Salario DECIMAL(10, 2),
    Id_Empresa INT NOT NULL,
    FOREIGN KEY (Id_Empresa) REFERENCES Empresa(Id_Empresa) ON DELETE CASCADE,
    INDEX idx_empresa (Id_Empresa),              -- Índice para FK
    INDEX idx_data_publicacao (DataPublicacao)   -- Índice para ordenação
) ENGINE=InnoDB;

-- TABELA CANDIDATURA (com todas as FKs e otimizada)
CREATE TABLE IF NOT EXISTS Candidatura (
    Id_Candidatura INT AUTO_INCREMENT PRIMARY KEY,
    Data_Envio DATETIME DEFAULT CURRENT_TIMESTAMP,
    Descricao TEXT,
    Status ENUM('Enviada', 'Visualizada', 'Aceita', 'Rejeitada') DEFAULT 'Enviada',
    Id_Usuario INT NOT NULL,
    Id_Vaga INT NOT NULL,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario) ON DELETE CASCADE,
    FOREIGN KEY (Id_Vaga) REFERENCES Vaga(Id_Vaga) ON DELETE CASCADE,
    UNIQUE KEY uk_usuario_vaga (Id_Usuario, Id_Vaga),  -- Impede candidaturas duplicadas
    INDEX idx_vaga (Id_Vaga),
    INDEX idx_usuario (Id_Usuario)
) ENGINE=InnoDB;

-- TABELA ENDERECO (com otimizações)
CREATE TABLE IF NOT EXISTS Endereco (
    Id_Endereco INT AUTO_INCREMENT PRIMARY KEY,
    Rua VARCHAR(100) NOT NULL,
    Numero VARCHAR(10) NOT NULL,  -- Número pode ter letras (ex: "12A")
    Complemento VARCHAR(50),
    Bairro VARCHAR(50) NOT NULL,
    Cidade VARCHAR(50) NOT NULL,
    Estado CHAR(2) NOT NULL,
    Cep CHAR(8) NOT NULL,
    Id_Usuario INT,
    Id_Empresa INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario) ON DELETE CASCADE,
    FOREIGN KEY (Id_Empresa) REFERENCES Empresa(Id_Empresa) ON DELETE CASCADE,
    CONSTRAINT chk_usuario_empresa CHECK (
        (Id_Usuario IS NOT NULL AND Id_Empresa IS NULL) OR
        (Id_Usuario IS NULL AND Id_Empresa IS NOT NULL)
    ),
    INDEX idx_endereco_usuario (Id_Usuario),
    INDEX idx_endereco_empresa (Id_Empresa)
) ENGINE=InnoDB;