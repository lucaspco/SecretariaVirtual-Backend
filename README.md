
# Secretaria Virtual Backend

Projeto de aplicação backend para uma Secretaria Virtual desenvolvido em Java com o framework Spring Boot. Esta aplicação oferece funcionalidades de gestão de usuários, agendamentos, pagamentos e prontuários, além de autenticação com JWT (JSON Web Token).

## Funcionalidades

- **Gestão de Usuários**: Criação, atualização, exclusão e listagem de usuários.
- **Agendamentos**: Criação e consulta de agendamentos de atendimento para usuários.
- **Pagamentos**: Gestão de pagamentos, associando-os a agendamentos e usuários.
- **Prontuários**: Cadastro e consulta de prontuários de usuários.
- **Autenticação com JWT**: Permite segurança de endpoints utilizando tokens JWT para autenticar e autorizar requisições.
- **Console do H2**: Banco de dados H2 em memória para facilitar o desenvolvimento e os testes, acessível via `/h2-console`.

## Configuração

### Pré-requisitos

- Java 17+
- Maven 3.6+
- Postman (para testar os endpoints)

### Configuração do Banco de Dados

O projeto usa H2 como banco de dados em memória para facilitar o desenvolvimento. O acesso ao console do H2 está habilitado na aplicação e pode ser acessado em `http://localhost:8080/h2-console`.

Configurações do banco no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=lucas
spring.datasource.password=1234
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
server.port=8080
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Configuração do JWT

A aplicação usa JWT para autenticação. O segredo para geração de tokens está no arquivo `JwtUtil.java`.

## Instalação e Execução

Para rodar o projeto localmente após cloná-lo, siga os passos abaixo:

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/lucaspco/SecretariaVirtual-Backend.git
   cd SecretariaVirtual
   ```

2. **Instale as dependências**:
   No diretório do projeto, rode o seguinte comando para instalar todas as dependências definidas no `pom.xml`:
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação**:
   Depois de instalar as dependências, você pode iniciar a aplicação com:
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints Principais

### Autenticação

- `POST /login`: Autenticação com JWT, requer `username` e `password` no corpo da requisição.

### Usuários

- `POST /usuarios/registro`: Registra um novo usuário.
- `GET /usuarios/{nome}`: Consulta um usuário específico.
- `PUT /usuarios/{nome}`: Atualiza informações do usuário.
- `DELETE /usuarios/{nome}`: Exclui um usuário.

### Agendamentos

- `POST /agendamentos`: Cria um novo agendamento.
- `GET /agendamentos`: Lista todos os agendamentos.
- `GET /agendamentos/usuario/{nome}`: Lista agendamentos por usuário.

### Pagamentos

- `POST /pagamentos`: Registra um pagamento.
- `GET /pagamentos`: Lista todos os pagamentos.
- `GET /pagamentos/usuario/{nome}`: Lista pagamentos por usuário.

### Prontuários

- `POST /prontuarios`: Cria um prontuário.
- `GET /prontuarios`: Lista todos os prontuários.
- `GET /prontuarios/usuario/{nome}`: Consulta prontuários de um usuário.

## Estrutura do Projeto

- `controller/`: Controladores REST que expõem os endpoints da API.
- `service/`: Lógica de negócio.
- `repository/`: Acesso ao banco de dados.
- `dto/`: Objetos de Transferência de Dados (DTO).
- `model/`: Classes de entidade.
- `config/`: Configurações de segurança e JWT.

## Segurança

A configuração de segurança utiliza o Spring Security com autenticação JWT. Apenas as rotas de `/login` e `/usuarios/registro` estão abertas ao público.

## Banco de Dados H2

Acesse o console do banco de dados H2 em `http://localhost:8080/h2-console` para ver e manipular os dados diretamente.
