
# SISCOM – Sistema de Compras e Pedidos

## 📄 Descrição
O **SISCOM** é um sistema de compras e pedidos desenvolvido em **Java** e **MySQL** como banco de dados. 

O sistema permite o cadastro e login de usuários, tanto **clientes** quanto **fornecedores**. Após o login, os usuários podem:

- Visualizar produtos disponíveis;
- Realizar pedidos;
- Alterar senha.

O sistema também está preparado para expansões, como a implementação de funcionalidades específicas para fornecedores (gestão de estoque e pedidos recebidos).

---

## ✅ Funcionalidades
- 🔑 Login de usuários (cliente ou fornecedor)
- 📝 Cadastro de usuários (com e-mail, senha, endereço, CNPJ e cidade)
- 🔒 Alteração de senha
- 🛒 Visualização de itens disponíveis (para clientes)
- 📦 Realização de pedidos (seleção de produtos e quantidades)
- 🚀 Estrutura pronta para expansão com funcionalidades adicionais para fornecedores

---

## 📁 Estrutura de Arquivos
- `Main.java` — Tela inicial e roteamento das interfaces
- `Autenticacao.java` — Tela de login e cadastro de usuários
- `Menu.java` — Tela principal após login (interfaces diferenciadas para cliente e fornecedor)
- `UsuarioDAO.java` — Classe responsável pela conexão com o banco de dados e operações de usuários (login, cadastro e alteração de senha)

---

## 💻 Tecnologias Utilizadas
- **Linguagem:** Java 11
- **Interface Gráfica:** Swing
- **Banco de Dados:** MySQL
- **IDE:** IntelliJ IDEA (ou qualquer outra que suporte Java)

---

## 🗄️ Banco de Dados
### Script de Criação do Banco:
```sql
CREATE DATABASE IF NOT EXISTS SistemaPedidos;
USE SistemaPedidos;

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    login VARCHAR(50) UNIQUE,
    senha VARCHAR(50),
    email VARCHAR(100),
    endereco VARCHAR(200),
    cnpj VARCHAR(20),
    bairro VARCHAR(100),
    cidade VARCHAR(100)
);

CREATE TABLE Fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    login VARCHAR(50) UNIQUE,
    senha VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE Item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    tipo VARCHAR(50),
    tamanho VARCHAR(50),
    marca VARCHAR(50),
    valor DECIMAL(10,2),
    estoque INT,
    prazo_entrega VARCHAR(50),
    fornecedor_id INT,
    FOREIGN KEY (fornecedor_id) REFERENCES Fornecedor(id)
);

CREATE TABLE Pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    item_id INT,
    quantidade INT,
    forma_pagamento VARCHAR(20),
    status_pedido VARCHAR(30) DEFAULT 'Em processo',
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (item_id) REFERENCES Item(id)
);
```

---

## ⚙️ Configuração do Ambiente
1. Instale o **MySQL** e execute o script acima para criar o banco de dados.
2. Configure sua conexão no arquivo `UsuarioDAO.java`:
```java
String url = "jdbc:mysql://localhost:3306/SistemaPedidos";
String usuario = "root";
String senha = "sua_senha";
```
3. Adicione o driver JDBC (**mysql-connector-java**) ao seu projeto.
4. Compile e execute a classe `Main.java` para iniciar o sistema.

---

## 🚀 Como Executar
1. Clone ou baixe este repositório.
2. Abra o projeto no **VSCode**, **IntelliJ IDEA** ou qualquer IDE de sua preferência.
3. Configure corretamente a conexão com o banco de dados (`UsuarioDAO.java`).
4. Execute a classe `Main.java` para rodar o sistema.

---

## 📜 Licença
Projeto desenvolvido para **uso educacional**.

---
