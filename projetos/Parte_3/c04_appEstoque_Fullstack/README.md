# Controle Simples de Estoque com Spring Boot

## Descrição do Projeto

Este projeto é um sistema simples de gerenciamento de estoque desenvolvido com o Spring Boot e um banco de dados PostgreSQL. 

Ele permite a criação, atualização, leitura e exclusão de produtos no estoque por meio de requisições HTTP. Além disso, a API é documentada usando o Swagger para facilitar o entendimento e o uso por outros desenvolvedores.

Foi implementado uma interação básica com o Front-end, mais abaixo segue a Documentação do Front-end.

Segue abaixo o export do Report de testes:

[Clique Aqui para visualizar o Report de Testes.](https://htmlpreview.github.io/?https://github.com/carloslaczynskizup/ProjetoZero/blob/main/projetos/Parte_2/b05_appEstoque_Desafio_082023/htmlReport/index.html)



## Pré-requisitos

Certifique-se de ter os seguintes pré-requisitos instalados antes de executar o projeto:

- Java 11 ou superior;
- Maven;
- PostgreSQL (nome do banco de dados: gestaoestoque, porta: 5432). Ver configurações no arquivo application.properties;
- Postman ou outra ferramenta similar para testar as requisições;
- IDE IntelliJ para o back-end;
- VSCode e o plugin LiveServer ou Live Preview para o front-end.

## Como Executar o Projeto

1. Clone, copie ou faça download da pasta deste repositório/projeto.


2. Navegue até o diretório do projeto onde salvou.


3. Execute ou atualize na sua IDE o projeto usando o Maven:

```bash
mvn spring-boot:run
```

4. Acesse o Swagger para explorar a API:

```
http://localhost:8080/swagger-ui/index.html
```


## Uso da API

A API possui os seguintes endpoints:

```
Produtos:

POST /produtos: Cria um novo produto no estoque.

GET /produtos: Retorna a lista de todos os produtos no estoque.

GET /produtos/{id}: Retorna um produto específico com base no ID.

PUT /produtos/{id}: Atualiza os detalhes de um produto existente.

DELETE /produtos/{id}: Exclui um produto do estoque.

PUT /produtos/estoque/{id}: Atualiza a quantidade de estoque de um produto com base em uma entrada ou saída.

GET /produtos/dto: Retorna uma lista simplificada de produtos no estoque.


Fornecedores:

POST /fornecedores: Cria um novo fornecedor.

GET /fornecedores: Retorna a lista de todos os fornecedores.

GET /fornecedores/{id}: Retorna um fornecedor específico com base no ID.

PUT /fornecedores/{id}: Atualiza os detalhes de um fornecedor existente.

DELETE /fornecedores/{id}: Exclui um fornecedor.
```

Certifique-se de consultar a url do Swagger para obter detalhes sobre como usar cada endpoint.


## Frontend - Interação com o Backend

Este documento descreve como usar o frontend para interagir com o backend de um sistema de controle de produtos. 
O frontend é uma interface simples e amigável que permite realizar as seguintes funcionalidades:

## Funcionalidades

1. **Listar Todos os Produtos**

    - Acesse a página inicial para ver uma lista de todos os produtos disponíveis no estoque.
    - Os produtos serão exibidos em uma lista com detalhes, incluindo nome do produto, marca, preço de venda, quantidade em estoque e tipo de movimentação.

2. **Cadastrar um Novo Produto**

    - Acesse a página de cadastro para adicionar um novo produto ao estoque.
    - Preencha os campos obrigatórios, como nome do produto, marca, preço de venda e quantidade em estoque.
    - Clique no botão "Cadastrar Produto" para adicionar o produto ao estoque.


## Uso

A seguir, explicaremos como utilizar essas funcionalidades na interface do usuário:

### Listar Todos os Produtos

- Acesse a página inicial, arquivo index.html.
- Clique em Exibir Produtos Cadastrados.
- Os produtos serão listados automaticamente na página.

### Cadastrar um Novo Produto

- Acesse a página de cadastro.
- Clique em Cadastrar Produtos.
- Preencha os campos obrigatórios, como nome do produto, marca, preço de venda e quantidade em estoque.
- Clique no botão "Cadastrar Produto" para adicionar o produto ao estoque.
- Automaticamente será direcionado para a Listagem de Produtos Cadastrados.

---
Este documento fornece uma visão geral das funcionalidades do frontend e como usá-las para interagir com o sistema de controle de produtos.

---
**Nota:** Certifique-se de que o frontend esteja conectado ao backend e que o servidor backend esteja em execução para que as funcionalidades sejam acessíveis.

## Contribuindo

Sinta-se à vontade para contribuir com melhorias neste projeto. Crie um fork do repositório, faça suas alterações e envie uma solicitação de pull.

###### FIM