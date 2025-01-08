# AgilStore Project / Projeto AgilStore
[English](#english) | [Português](#português)

Disclaimer: by default, i put a seed in the database with some categories and products, you can change this in the docker-compose.yml file and init.sql file.

Observação: por padrão, eu coloquei um seed no banco de dados com algumas categorias e produtos, você pode alterar isso no arquivo docker-compose.yml e no arquivo init.sql.



# English

## Overview
The **AgilStore** project is a product management application built with **Java**, **Spring Boot**, **Maven**, and **SQL**. It enables CRUD operations (Create, Read, Update, and Delete) for products and categories.

## Technologies
- **Java**
- **Spring Boot**
- **Maven**
- **H2 Database** (testing environment)
- **PostgreSQL**

## Setup Instructions
The project is configured to use PostgreSQL as the default database. To use H2 (in-memory database), simply change `spring.profiles.active` in the `application.properties` file to `test`.

```bash
# Clone the repository
git clone https://github.com/JulianoL13/agilstore-challenger.git

# Navigate to project directory
cd agilstore-challenger

# Edit .env file with desired settings

# Start the application with Docker Compose
docker-compose up
# or 
docker-compose up -d # to run in background
```

[View full English documentation](#full-english-documentation)

---

# Português

## Descrição
O projeto **AgilStore** é uma aplicação de gerenciamento de produtos desenvolvida com **Java**, **Spring Boot**, **Maven** e **SQL**. A aplicação permite realizar operações CRUD (Criar, Ler, Atualizar e Deletar) em produtos e categorias.

## Tecnologias Utilizadas
- **Java**
- **Spring Boot**
- **Maven**
- **H2 Database** (ambiente de teste)
- **PostgreSQL**

## Como Configurar
O projeto está configurado para utilizar PostgreSQL como banco de dados padrão. Para usar o H2 (banco de dados em memória), basta alterar o `spring.profiles.active` no arquivo `application.properties` para `test`.

```bash
# Clonar o repositório
git clone https://github.com/JulianoL13/agilstore-challenger.git

# Entrar no diretório do projeto
cd agilstore-challenger

# Editar o arquivo .env com as configurações desejadas

# Iniciar a aplicação com o Docker Compose
docker-compose up
# ou 
docker-compose up -d # para rodar em segundo plano
```

[Ver documentação completa em português](#documentação-completa-em-português)

---

# Full English Documentation

## Property Profiles
For exploring the project without database concerns, it comes pre-configured with H2 database for the test profile. To use it, change `spring.profiles.active` in `application.properties` to `test`.

Property files are located in the `src/main/resources` directory.

### Property Files Details:

#### `application.properties`
Contains default application settings:
```properties
spring.profiles.active=dev # or test
spring.jpa.hibernate.ddl-auto=update
```

#### `application-test.properties`
Test profile settings with H2 database configuration:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Access H2 console at: `http://localhost:8080/h2-console`

#### `application-dev.properties`
Development profile settings for PostgreSQL:
```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
## JPA/Hibernate settings
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```
These settings are intentionally empty as variables are passed directly through Docker Compose.

## Project Structure

### Entities

#### `Product`
Represents a product with attributes:
- `id` (UUID)
- `name`
- `amount`
- `price`
- `category`

The `Category` entity represents product categories with:
- `id`
- `name`
- `description`

## API Endpoints

### Products

#### List Products
- **Method:** GET
- **Endpoint:** `/products`
- **Description:** Returns a list of all products with optional filters
- **Parameters:**
    - `name` (optional): Filter by product name
    - `categoryId` (optional): Filter by category
    - `minPrice` (optional): Filter by minimum price
    - `maxPrice` (optional): Filter by maximum price
    - `minAmount` (optional): Filter by minimum amount
    - `maxAmount` (optional): Filter by maximum amount
    - `page` (optional): Page number
    - `size` (optional): Page size
    - `sort` (optional): Sort by specific field

**Curl Example:**
```bash
curl -X GET http://localhost:8080/products
# or
curl -X GET http://localhost:8080/products?name=Product
```

#### Get Product by ID
- **Method:** GET
- **Endpoint:** `/products/{id}`
- **Description:** Returns a specific product by ID

**Curl Example:**
```bash
curl -X GET http://localhost:8080/products/{id}
```

#### Create Product
- **Method:** POST
- **Endpoint:** `/products`
- **Description:** Creates a new product

**Curl Example:**
```bash
curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d '{
    "name": "Product A", 
    "amount": 10, 
    "price": 99.99, 
    "category": {
        "id": "category-id"
    }
}'
```

#### Update Product
- **Method:** PUT
- **Endpoint:** `/products/{id}`
- **Description:** Updates an existing product

**Curl Example:**
```bash
curl -X PUT http://localhost:8080/products/{id} -H "Content-Type: application/json" -d '{
    "name": "Product A", 
    "amount": 20, 
    "price": 89.99, 
    "category": {
        "id": "category-id"
    }
}'
```

#### Delete Product
- **Method:** DELETE
- **Endpoint:** `/products/{id}`
- **Description:** Deletes a product by ID

**Curl Example:**
```bash
curl -X DELETE http://localhost:8080/products/{id}
```

## Local Development
To run the application locally using Maven:

```bash
mvn spring-boot:run
```

---

# Documentação Completa em Português

## Perfis de Propriedades
Caso deseje explorar o projeto sem se preocupar com o banco de dados, o projeto já vem configurado com o banco de dados H2 para o perfil de teste. Para isso, basta alterar o `spring.profiles.active` no arquivo `application.properties` para `test`.

Os arquivos de propriedades estão localizados no diretório `src/main/resources`.

### Detalhes dos Arquivos de Propriedades:

#### `application.properties`
Contém as configurações padrão da aplicação:
```properties
spring.profiles.active=dev # ou test
spring.jpa.hibernate.ddl-auto=update
```

#### `application-test.properties`
Configurações do perfil de teste com banco H2:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Acesse o console H2 em: `http://localhost:8080/h2-console`

#### `application-dev.properties`
Configurações do perfil de desenvolvimento para PostgreSQL:
```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
## Configurações JPA/Hibernate
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```
Estas configurações estão vazias pois as variáveis são passadas diretamente pelo Docker Compose.

## Estrutura do Projeto

### Entidades

#### `Product`
Representa um produto com os atributos:
- `id` (UUID)
- `name` (nome)
- `amount` (quantidade)
- `price` (preço)
- `category` (categoria)

A entidade `Category` representa as categorias de produtos com:
- `id`
- `name` (nome)
- `description` (descrição)

## Endpoints da API

### Produtos

#### Listar Produtos
- **Método:** GET
- **Endpoint:** `/products`
- **Descrição:** Retorna uma lista de todos os produtos com filtros opcionais
- **Parâmetros:**
    - `name` (opcional): Filtra por nome do produto
    - `categoryId` (opcional): Filtra por categoria
    - `minPrice` (opcional): Filtra por preço mínimo
    - `maxPrice` (opcional): Filtra por preço máximo
    - `minAmount` (opcional): Filtra por quantidade mínima
    - `maxAmount` (opcional): Filtra por quantidade máxima
    - `page` (opcional): Número da página
    - `size` (opcional): Tamanho da página
    - `sort` (opcional): Ordena por campo específico

**Exemplo com Curl:**
```bash
curl -X GET http://localhost:8080/products
# ou
curl -X GET http://localhost:8080/products?name=Produto
```

#### Obter Produto por ID
- **Método:** GET
- **Endpoint:** `/products/{id}`
- **Descrição:** Retorna um produto específico pelo ID

**Exemplo com Curl:**
```bash
curl -X GET http://localhost:8080/products/{id}
```

#### Criar Produto
- **Método:** POST
- **Endpoint:** `/products`
- **Descrição:** Cria um novo produto

**Exemplo com Curl:**
```bash
curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d '{
    "name": "Produto A", 
    "amount": 10, 
    "price": 99.99, 
    "category": {
        "id": "categoria-id"
    }
}'
```

#### Atualizar Produto
- **Método:** PUT
- **Endpoint:** `/products/{id}`
- **Descrição:** Atualiza um produto existente

**Exemplo com Curl:**
```bash
curl -X PUT http://localhost:8080/products/{id} -H "Content-Type: application/json" -d '{
    "name": "Produto A", 
    "amount": 20, 
    "price": 89.99, 
    "category": {
        "id": "categoria-id"
    }
}'
```

#### Deletar Produto
- **Método:** DELETE
- **Endpoint:** `/products/{id}`
- **Descrição:** Deleta um produto pelo ID

**Exemplo com Curl:**
```bash
curl -X DELETE http://localhost:8080/products/{id}
```

## Desenvolvimento Local
Para executar a aplicação localmente usando Maven:

```bash
mvn spring-boot:run
```