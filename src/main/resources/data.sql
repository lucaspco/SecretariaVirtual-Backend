-- Create Usuario table
CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    endereco VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(20),
    nome_responsavel VARCHAR(255),
    telefone_responsavel VARCHAR(20),
    cpf BIGINT UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Create Agendamento table
CREATE TABLE IF NOT EXISTS Agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_hora TIMESTAMP,
    usuario_nome VARCHAR(255),
    descricao VARCHAR(255),
    FOREIGN KEY (usuario_nome) REFERENCES Usuario(nome)
);

-- Create Pagamento table
CREATE TABLE IF NOT EXISTS Pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_pagamento TIMESTAMP,
    valor DOUBLE,
    metodo_pagamento VARCHAR(100),
    usuario_nome VARCHAR(255),
    agendamento_id INT,
    FOREIGN KEY (usuario_nome) REFERENCES Usuario(nome),
    FOREIGN KEY (agendamento_id) REFERENCES Agendamento(id)
);

-- Create Prontuario table
CREATE TABLE IF NOT EXISTS Prontuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prontuario TEXT,
    usuario_nome VARCHAR(255),
    FOREIGN KEY (usuario_nome) REFERENCES Usuario(nome)
);

-- Insert initial Usuario data
-- Senhas em texto simples (para uso com NoOpPasswordEncoder)
INSERT INTO Usuario (nome, endereco, email, telefone, nome_responsavel, telefone_responsavel, cpf, senha)
VALUES
    ('Lucas Paiva', 'Rua das Flores, 123', 'lucas@example.com', '1234567890', 'Maria Silva', '0987654321', 12345678901, 'senha123'),
    ('Fernanda Witkowski', 'Avenida Central, 456', 'ana@example.com', '2345678901', 'João Souza', '9876543210', 23456789012, 'borboleta123');

-- Insert initial Agendamento data
INSERT INTO Agendamento (data_hora, usuario_nome, descricao)
VALUES
    ('2024-05-30 09:00:00', 'Lucas Paiva', 'Primeira Consulta'),
    ('2024-06-01 10:00:00', 'Fernanda Witkowski', 'Primeira Consulta');

-- Insert initial Pagamento data
INSERT INTO Pagamento (data_pagamento, valor, metodo_pagamento, usuario_nome, agendamento_id)
VALUES
    ('2024-05-25 12:00:00', 100.0, 'Credit Card', 'Lucas Paiva', 1),
    ('2024-05-26 14:00:00', 200.0, 'Debit Card', 'Fernanda Witkowski', 2);

-- Insert initial Prontuario data
INSERT INTO Prontuario (prontuario, usuario_nome)
VALUES
    ('Primeira Consulta', 'Lucas Paiva'),
    ('Primeira Consulta', 'Fernanda Witkowski');
