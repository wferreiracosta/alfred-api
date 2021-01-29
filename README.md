# alfred-api

[![codecov](https://codecov.io/gh/wferreiracosta/alfred-api/branch/master/graph/badge.svg?token=GQ6V6PGTBN)](https://codecov.io/gh/wferreiracosta/alfred-api)
![Workflow Homolog](https://github.com/wferreiracosta/alfred-api/workflows/Workflow%20Homolog/badge.svg?branch=homolog)

API de e-commerce feito com Spring Boot com as principais atividades de uma loja.

## Projeto

### Estrutura

![Alt text](https://i.ibb.co/QP8hG2y/estrutura.png "Optional title")

- Controladores REST (resource/controller): Faz o controle dos endpoints e suas disponibilidades
- Camada de serviço (service): Serviços para os controladores REST
- Camada de acesso a dados (repository): Camada que conversa com o banco de dados

### Iniciar projeto através de command line

```
mvn spring-boot:run
```

### Banco de dados

Especificações do banco de dados utilizado na API

#### Banco de Dados de Teste

O banco de dados para teste é o H2, é possivel sua utilização por causa da dependência a seguir:

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

No arquivo *application-test.properties* podemos ver a configuração usada para acessar o banco de dados.

#### Banco de Dados de Desenvolvimento

O banco de dados para desenvolvimento é MySQL instalado na maquina local através do XAMPP.

No arquivo *application-dev.properties* podemos ver a configuração usada para acessar o banco de dados.
