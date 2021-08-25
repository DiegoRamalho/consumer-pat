# Consumer CardPAT

Projeto de teste para seleção de candidatos a vagas de desenvolvimento

## Sobre o projeto

Esse serviço tem o objetivo de gerenciar os dados e realizar transações de clientes que possuam cartões de benefícios,
como vale alimentação, refeição e combustivel.

### Requisitos para executar o projeto

- JDK 11
- Maven 3+

## Orientações

Faça um fork do projeto, clone o mesmo, abra na sua IDE de preferência e execute na sua máquina local. Depois leia as
classes(exceto as do pacote config) e verifique se tem algo que você faria diferente. Se encontrar, faça as alterações
necessárias. Ao fim, commit e no envie um Pull Request.

### Executando

<p>Ao executar a aplicação você poderá acessa-la em: http://localhost:8080/</p>
<p>Documentação da API: http://localhost:8080/swagger-ui/ <br/>
Banco de Dados da API: http://localhost:8080/h2-console/ <br/></p>

***Atenção: Não inclua nenhuma lib ou framework***  

### Melhorias futuras
Incluir sistema de autenticação (usando [Spring Security](https://spring.io/projects/spring-security) e [JWT](https://jwt.io/))
Melhoria na rastreabilidade (usando [Spring Sleuth](https://spring.io/projects/spring-cloud-sleuth) e [Logbook](https://github.com/zalando/logbook))
Utilizar o [MapStruct](https://mapstruct.org/)
Testes de performance
