![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-brightgreen?style=for-the-badge\&logo=springboot)
![JWT](https://img.shields.io/badge/JWT-Authentication-red?style=for-the-badge\&logo=jsonwebtokens)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge\&logo=postgresql)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green?style=for-the-badge\&logo=swagger)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue?style=for-the-badge\&logo=docker)
![Gradle](https://img.shields.io/badge/Gradle-Build_Tool-black?style=for-the-badge\&logo=gradle)
![Lombok](https://img.shields.io/badge/Lombok-Java-orange?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:0F2027,50:203A43,100:2C5364&height=200&section=header&text=User%20API&fontSize=45&fontColor=ffffff&animation=fadeIn" />
</p>

<p align="center">
  <b>API REST desenvolvida com Spring Boot para gerenciamento de usuários, autenticação JWT e integração com ViaCEP.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/github/stars/ViniciusS4ntos/user-api?style=social" />
  <img src="https://img.shields.io/github/forks/ViniciusS4ntos/user-api?style=social" />
  <img src="https://img.shields.io/github/issues/ViniciusS4ntos/user-api" />
</p>

# User API

**User API** é uma API REST desenvolvida com **Java + Spring Boot** para gerenciamento de usuários, autenticação e cadastro de endereços.

O projeto utiliza **JWT** para autenticação, integração com a API do **ViaCEP** para preenchimento automático de endereços e persistência de dados utilizando **PostgreSQL**.

A aplicação também possui documentação com **Swagger/OpenAPI** e suporte para execução via **Docker**.

---

## Tecnologias Utilizadas

* Java 17 / Spring Boot
* Spring Security
* JWT (JSON Web Token)
* PostgreSQL
* OpenFeign
* ViaCEP API
* Swagger / OpenAPI
* Docker / Docker Compose
* Gradle
* Lombok

---

## Funcionalidades

* Cadastro de usuários
* Atualização e remoção de usuários
* Busca de usuários por ID
* Autenticação via JWT
* Criptografia de senhas
* Cadastro de telefone e endereço
* Integração automática com ViaCEP
* Documentação da API com Swagger
* Tratamento global de exceções
* Containerização com Docker

---

## Pré-requisitos

* Java 17 instalado
* Docker e Docker Compose instalados
* PostgreSQL configurado

---

## Rodando o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/user-api.git
cd user-api
```

---

### 2. Configure o `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_api
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3. Execute os containers

```bash
docker-compose up --build
```

---

### 4. Rodar a aplicação manualmente

#### Linux/Mac

```bash
./gradlew bootRun
```

#### Windows

```bash
gradlew.bat bootRun
```

---

## Swagger / OpenAPI

Após iniciar a aplicação:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

## Autenticação JWT

A API utiliza autenticação baseada em JWT.

Fluxo:

1. Realizar login
2. Receber token JWT
3. Enviar token no header Authorization

Exemplo:

```http
Authorization: Bearer SEU_TOKEN
```

---

## Endpoints Principais

### Usuários

* **GET /usuarios** – Lista todos os usuários
* **GET /usuarios/{id}** – Busca usuário por ID
* **POST /usuarios** – Cria um novo usuário
* **PUT /usuarios/{id}** – Atualiza um usuário
* **DELETE /usuarios/{id}** – Remove um usuário

---

## Exemplo de JSON

### Criar Usuário

```json
{
  "nome": "João",
  "email": "joao@email.com",
  "senha": "123456",
  "telefone": {
    "numero": "81999999999"
  },
  "endereco": {
    "cep": "50000000",
    "numero": "100"
  }
}
```

---

## Estrutura do Projeto

```text
Directory structure:
└── viniciuss4ntos-user-api/
    ├── docker-compose.yml
    ├── Dockerfile
    ├── gradlew
    ├── gradlew.bat
    ├── gradle/
    │   └── wrapper/
    │       └── gradle-wrapper.properties
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── vinicius/
    │   │   │           └── user_api/
    │   │   │               ├── UserApiApplication.java
    │   │   │               ├── business/
    │   │   │               │   ├── UsuarioService.java
    │   │   │               │   ├── ViaCepService.java
    │   │   │               │   ├── converter/
    │   │   │               │   │   └── UsuarioConverter.java
    │   │   │               │   └── dto/
    │   │   │               │       ├── EnderecoDTO.java
    │   │   │               │       ├── TelefoneDTO.java
    │   │   │               │       └── UsuarioDTO.java
    │   │   │               ├── controller/
    │   │   │               │   ├── GlobalExceptionHandler.java
    │   │   │               │   └── UsuarioController.java
    │   │   │               └── insfrastructure/
    │   │   │                   ├── clients/
    │   │   │                   │   ├── ViaCepClient.java
    │   │   │                   │   └── ViaCepDTO.java
    │   │   │                   ├── entity/
    │   │   │                   │   ├── Endereco.java
    │   │   │                   │   ├── Telefone.java
    │   │   │                   │   └── Usuario.java
    │   │   │                   ├── exception/
    │   │   │                   │   ├── ConflictException.java
    │   │   │                   │   ├── InvalidCepException.java
    │   │   │                   │   ├── ResourceNotFoundException.java
    │   │   │                   │   └── UnauthorizedException.java
    │   │   │                   ├── repository/
    │   │   │                   │   ├── EnderecoRepository.java
    │   │   │                   │   ├── TelefoneRepository.java
    │   │   │                   │   └── UsuarioRepository.java
    │   │   │                   └── security/
    │   │   │                       ├── JwtRequestFilter.java
    │   │   │                       ├── JwtUtil.java
    │   │   │                       ├── SecurityConfig.java
    │   │   │                       └── UserDetailsServiceImpl.java
    │   │   └── resources/
    │   │       └── application.properties
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── vinicius/
    │                   └── user_api/
    │                       └── UserApiApplicationTests.java
    └── .github/
        └── workflows/
            └── gradle.yml

```

---

## Integração ViaCEP

Ao cadastrar um endereço utilizando apenas o CEP, a aplicação consulta automaticamente a API do ViaCEP para preencher:

* Rua
* Bairro
* Cidade
* Estado

---

## Segurança

O projeto possui:

* Spring Security
* Filtro JWT
* Rotas protegidas
* Criptografia de senhas
* Tratamento de autenticação

---

## Docker

### Subir containers

```bash
docker-compose up -d
```

### Derrubar containers

```bash
docker-compose down
```

---

## Melhorias Futuras

* Testes unitários
* Testes de integração
* Refresh Token
* Deploy em nuvem
* CI/CD com GitHub Actions
* Cache com Redis
* Paginação
* Logs centralizados

---

## Autor

Desenvolvido por Edson Vinicius.
