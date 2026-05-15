# User API

API REST desenvolvida com Java + Spring Boot para gerenciamento de usuários, telefones e endereços.

O projeto utiliza autenticação com JWT, integração com ViaCEP, documentação com Swagger/OpenAPI, banco PostgreSQL e arquitetura organizada em camadas.

---

# Tecnologias Utilizadas

* Java 17
* Spring Boot 4
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (JSON Web Token)
* PostgreSQL
* OpenFeign
* ViaCEP API
* Swagger / OpenAPI
* Docker
* Gradle
* Lombok

---

# Funcionalidades

* Cadastro de usuários
* Atualização de usuários
* Remoção de usuários
* Busca de usuários
* Cadastro de endereço
* Integração automática com ViaCEP
* Cadastro de telefones
* Autenticação com JWT
* Tratamento global de exceções
* Documentação da API com Swagger

---

# Estrutura do Projeto

````bash
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

````

---

# Configuração do Banco de Dados

Configure o arquivo:

```properties
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_api
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# Como Executar o Projeto

## 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/user-api.git
```

---

## 2. Entrar na pasta do projeto

```bash
cd user-api
```

---

## 3. Executar o PostgreSQL com Docker

```bash
docker-compose up -d
```

---

## 4. Rodar a aplicação

### Linux/Mac

```bash
./gradlew bootRun
```

### Windows

```bash
gradlew.bat bootRun
```

---

# Swagger / OpenAPI

Após iniciar a aplicação:

```bash
http://localhost:8080/swagger-ui.html
```

ou

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# Autenticação JWT

A API utiliza autenticação baseada em JWT.

Fluxo:

1. Fazer login
2. Receber token JWT
3. Enviar token no Authorization Header

Exemplo:

```http
Authorization: Bearer SEU_TOKEN
```

---

# Endpoints Principais

## Usuários

| Método | Endpoint       | Descrição             |
| ------ | -------------- | --------------------- |
| GET    | /usuarios      | Listar usuários       |
| GET    | /usuarios/{id} | Buscar usuário por ID |
| POST   | /usuarios      | Criar usuário         |
| PUT    | /usuarios/{id} | Atualizar usuário     |
| DELETE | /usuarios/{id} | Remover usuário       |

---

# Exemplo de JSON

## Criar Usuário

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

# Integração ViaCEP

Ao cadastrar um endereço utilizando apenas o CEP, a aplicação consulta automaticamente a API do ViaCEP para preencher:

* Rua
* Bairro
* Cidade
* Estado

---

# Segurança

O projeto possui:

* Spring Security
* Filtro JWT
* UserDetailsService customizado
* Rotas protegidas
* Tratamento de autenticação

---

# Docker

## Subir containers

```bash
docker-compose up -d
```

## Derrubar containers

```bash
docker-compose down
```

---

# Possíveis Melhorias Futuras

* Testes unitários
* Testes de integração
* Refresh Token
* Deploy na nuvem
* CI/CD com GitHub Actions
* Cache com Redis
* Paginação
* Logs centralizados

---

# Autor

Desenvolvido por Edson Vinicius.
