# Alfred API

Alfred - Projeto de uma API de loja desenvolvido com Spring Boot.

## Tecnologias Utilizadas

*   **Java 8**
*   **Spring Boot 2.7.18**
*   **Spring Security & JWT**
*   **JPA / Hibernate**
*   **Maven**
*   **MySQL** (Banco de Dados de Desenvolvimento)
*   **H2** (Banco de Dados de Teste)

---

## Arquitetura do Projeto

O projeto utiliza o padrão de **Arquitetura em Camadas (Layered Architecture)**, que organiza o código em camadas lógicas com responsabilidades bem definidas. Isso promove a separação de conceitos, facilita a manutenção e melhora a testabilidade.

O fluxo de dependência é unidirecional: uma camada só pode interagir com a camada imediatamente abaixo dela.

**`Controllers` → `Services` → `Repositories`**

As camadas do projeto são:

*   **`controllers` (Camada de Apresentação):** Responsável por expor os endpoints da API, receber requisições HTTP e retornar as respostas. É a porta de entrada da aplicação e não contém lógica de negócio.
*   **`services` (Camada de Serviço):** Contém a lógica de negócio principal, regras e orquestração das operações. É chamada pelos controllers e utiliza os repositories para acessar os dados.
*   **`repositories` (Camada de Acesso a Dados):** Interface para a comunicação com o banco de dados. Abstrai a lógica de persistência (CRUD) para que a camada de serviço não precise se preocupar com a implementação do banco.
*   **`entities` (Modelo de Dados):** Classes que mapeiam as tabelas do banco de dados (Entidades JPA).
*   **`dto` (Data Transfer Object):** Objetos que modelam os dados transferidos entre o cliente e a API, garantindo que a estrutura interna do banco de dados não seja exposta.

---

## Como Executar o Projeto

### Pré-requisitos

*   **Java JDK 8** ou superior.
*   **Maven 3.6** ou superior.
*   **Git**.
*   Um cliente **MySQL** para o ambiente de desenvolvimento.

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/alfred-api.git
cd alfred-api
```

### 2. Configure o Banco de Dados (Desenvolvimento)

1.  Acesse seu cliente MySQL e crie um novo banco de dados.
    ```sql
    CREATE DATABASE alfred;
    ```
2.  Abra o arquivo `src/main/resources/application-dev.properties`.
3.  Configure as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` de acordo com as suas credenciais do MySQL.

### 3. Execute a Aplicação

Você pode executar a aplicação usando o Maven. O perfil `dev` será ativado por padrão.

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## Perfis de Banco de Dados

O projeto está configurado com dois perfis do Spring:

*   **dev**: Perfil padrão. Utiliza o banco de dados **MySQL** configurado em `application-dev.properties`.
*   **test**: Perfil para testes. Utiliza um banco de dados em memória **H2** e é ativado automaticamente ao rodar os testes. As configurações estão em `application-test.properties`. Para acessar o console do H2, navegue para `http://localhost:8080/h2-console` enquanto a aplicação estiver rodando com o perfil de teste.

---

## Documentação da API

### Categorias

| Método HTTP | Endpoint              | Descrição                                                               | Acesso |
|-------------|-----------------------|-------------------------------------------------------------------------|--------|
| `GET`       | `/categorias`         | Retorna uma lista com todas as categorias.                              | Público|
| `GET`       | `/categorias/{id}`    | Busca uma categoria específica pelo seu ID.                             | Público|
| `GET`       | `/categorias/page`    | Retorna as categorias de forma paginada. Aceita parâmetros de paginação. | Público|
| `POST`      | `/categorias`         | Cria uma nova categoria.                                                | Admin  |
| `PUT`       | `/categorias/{id}`    | Atualiza uma categoria existente.                                       | Admin  |
| `DELETE`    | `/categorias/{id}`    | Deleta uma categoria.                                                   | Admin  |

### Produtos

| Método HTTP | Endpoint                                  | Descrição                                                                                             | Acesso |
|-------------|-------------------------------------------|-------------------------------------------------------------------------------------------------------|--------|
| `GET`       | `/produtos`                               | Retorna todos os produtos cadastrados.                                                                | Público|
| `GET`       | `/produtos/{id}`                          | Retorna o produto com o ID especificado.                                                              | Público|
| `GET`       | `/produtos/search?nome=...&categorias=...`| Busca produtos por nome e/ou categorias. O resultado é paginado.                                      | Público|

*(Nota: A documentação dos endpoints de Produtos foi baseada na estrutura existente e pode ser expandida.)*
