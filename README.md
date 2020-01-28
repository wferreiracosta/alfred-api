# Loja

## Requisitos

* Java JDK >= 8
* Maven
* Git

## Banco de Dados

### Banco de Dados de Teste

O banco de dados para teste é o H2, é possivel sua utilização por causa da dependência a seguir:

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

No arquivo *application-test.properties* podemos ver a configuração usada para acessar o banco de dados.

#### Acessar Banco de Dados de Teste

Para acessar banco de dados de teste e necessario colocar a seguinte url no navegar e usar as informações de acesso no arquivo *application-test.properties*.

    http://localhost:8080/h2-console

### Banco de Dados de Desenvolvimento

O banco de dados para desenvolvimento é MySQL instalado na maquina local.

No arquivo *application-dev.properties* podemos ver a configuração usada para acessar o banco de dados.

## Endpoints

### Produtos

Endpoint  | Descrição
--------- | ------
produtos/ | Retorna todos os produtos cadastrados
produtos/{ID} | Retorna o produto com a ID espeficada
produtos/?nome={Letra que deve conter no nome do produto}&categorias={ID da categoria} | Retorna o produto que contenha as letras e categoria espeficadas no parametro, retorna paginado o resultado

### Categorias

Endpoint  | Descrição
--------- | ------
categorias/ | Retorna todos as categorias cadastradas
categorias/{ID} | Retorna a categoria com a ID espeficada
categorias/page/ | Retorna todas categorias paginadas
