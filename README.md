# Projeto Final Java - Sistema de Farmácia

Sistema desktop em Java (Swing) para gerenciamento de farmácia, com autenticação de usuários, cadastro e controle de estoque de medicamentos integrado a banco de dados relacional MySQL via JDBC.

## 📁 Estrutura do Repositório

```text
ProjetoFinalJava/
└── Farmacia/
    ├── lib/                          # Driver MySQL Connector/J
    ├── src/                          # Código-fonte Java padronizado
    │   ├── ConexaoDB.java            # Gerenciador de conexão JDBC
    │   ├── Usuario.java              # Modelo de Usuário
    │   ├── UsuarioDAO.java           # Acesso a dados de Usuário
    │   ├── Remedio.java              # Modelo de Remédio
    │   ├── RemedioDAO.java           # Acesso a dados de Remédios (CRUD)
    │   ├── TelaLogin.java            # Interface gráfica de autenticação
    │   ├── TelaCadastro.java         # Interface de cadastro de usuários
    │   ├── TelaFarmacia.java         # Menu principal
    │   ├── TelaCrudRemedios.java     # Gerenciamento de estoque de remédios
    │   └── Main.java                 # Ponto de entrada da aplicação
    ├── db.properties                 # Configurações de conexão MySQL
    └── schema.sql                    # Script DDL de criação do banco e tabelas
```

## ⚙️ Pré-requisitos

- **Java JDK 17+**
- **MySQL Server 8.0+**
- **MySQL Workbench** ou cliente SQL equivalente

## 🗄️ Configuração do Banco de Dados

1. Certifique-se de que o serviço MySQL está ativo.
2. Execute o script contido em `Farmacia/schema.sql` no MySQL Workbench ou terminal:
   ```sql
   CREATE DATABASE IF NOT EXISTS farmacia_db;
   USE farmacia_db;
   ```
3. O banco já virá com o usuário padrão para teste:
   - **Login:** `admin`
   - **Senha:** `123`
4. Ajuste as credenciais no arquivo `Farmacia/db.properties` se a sua senha do MySQL for diferente:
   ```properties
   db.user=root
   db.password=SuaSenhaAqui
   ```

## 🚀 Como Executar

### Via Linha de Comando (Terminal / PowerShell)

1. Navegue até a pasta da Farmácia:
   ```powershell
   cd c:\dev-projetos\ProjetoFinalJava\Farmacia
   ```

2. Compile os arquivos:
   ```powershell
   javac -encoding UTF-8 -cp "lib\mysql-connector-j-26.7.0.jar;src" -d out\production\Farmacia src\*.java
   ```

3. Execute a aplicação:
   ```powershell
   java -cp "out\production\Farmacia;lib\mysql-connector-j-26.7.0.jar" Main
   ```

### Via IDE (IntelliJ IDEA / Eclipse / VS Code)

- Abra a pasta `Farmacia` na IDE.
- Certifique-se de que a biblioteca `lib/mysql-connector-j-26.7.0.jar` está configurada no Classpath / Dependencies.
- Execute a classe principal `src/Main.java`.
