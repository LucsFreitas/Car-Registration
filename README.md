# Visão Geral
O projeto é uma aplicação back-end responsável por fazer o cadastro de usuários e carros, demonstrando a utilização do framework [Spring Boot](https://projects.spring.io/spring-boot), [Spring Data](http://projects.spring.io/spring-data), realização de testes com [Mockito](https://site.mockito.org/) e documentação da API com [Swagger-UI](https://swagger.io/tools/swagger-ui/).

## Tecnologias
- [Spring Boot](https://projects.spring.io/spring-boot) é uma ferramenta que simplifica a configuração e execução de aplicações Java stand-alone,  com conceitos de dependências “starters”, auto configuração e servlet container embutidos é proporcionado uma grande produtividade desde o start-up da aplicação até sua ida a produção.
 
- [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html) é um framework que auxilia no desenvolvimento de aplicação web, fornecendo mecanismos simplificados para a criação de APIs RESTful através de anotações, e além disso, também possui recursos de serialização e deserialização de objetos de forma transparente .
 
- [Spring Data](http://projects.spring.io/spring-data/) é um framework que abstrai o acesso ao modelo de dados, independente a tecnologia de base de dados. Deste modo, permite maior flexibilidade ao unificar interfaces de diversos frameworks, e aumenta a produtividade oferecendo implementações básicas de CRUD já prontas e permitindo algumas customizações apenas alterando a assinatura do método.

- [Mockito](https://site.mockito.org/) é um framework de testes desenvolvido em Java, que permite isolar as responsabilidades a serem testadas através de "mocks". Isso permite realizar testes cada vez mais granularizados e forma transparente, sem alterar nada do código a ser testado por causa do teste.

- [Swagger-UI](https://swagger.io/tools/swagger-ui/) permite que qualquer pessoa visualize e interaja com os recursos da API sem ter nenhuma lógica de implementação em prática. É gerado automaticamente através de anotações, e fornece uma documentação visual facilitando a implementação do back-end e o consumo do cliente final.

## API

O projeto utiliza o padrão Rest de comunicação, produzindo e consumindo arquivos no formato JSON disponibiliza a API em 2 contextos diferentes: Users e Cars. Como a API utiliza autenticação de usuário, os endpoints de carros são protegidos, e só podem ser acessados após autenticação.

#### Login
- /login (POST) - Realiza login
- /me (GET) - Busca as informações do usuário logado

#### Users
- /users (GET) - Lista todos os usuários
- /users (POST) - Cadastra um novo usuário
- /users/{id} (GET) - Busca um usuário pelo id
- /users/{id} (PUT) - Atualiza um usuário pelo id
- /users/{id} (DELETE) - Remove um usuário pelo id

#### Cars
- /cars (GET) - Lista todos os carro do usuário logado
- /cars (POST) - Cadastra um novo carro para o usuário logado
- /cars/{id} (GET) - Busca um carro do usuário pelo id
- /cars/{id} (PUT) - Atualiza um carro do usuário pelo id
- /cars/{id} (DELETE) - Remove um carro do usuário logado pelo id

É possível utilizar a api através do Swagger-UI, disponibilizado [aqui](https://car-registration-api.herokuapp.com/api/swagger-ui.html). Lembrando que apenas os endpoints de usuários são liberados para uso sem autenticação. Para se autenticar e conseguir utilizar os demais endpoints, é necessário:

- Criar um usuário válido através do POST /users.
- Realizar o login através do POST /login.
- Após realizar o login, copiar o token de acesso, exibido no header Authorization na resposta da requisição de login.
- Após copiar, basta clicar no botão "Authorize" no inicio da página, colar o token de acesso e em seguida clicar em "Authorize".
