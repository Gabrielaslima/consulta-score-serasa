-Usuarios de admin e user sao cadastrados automaticamente no banco na tabela REGULARUSER assim que a aplicaçao esta no ar

-Tabela de Score é cadastrada automaticamente no banco na tabela SCORE assim que a aplicaçao esta no ar

Usuario: testeAdmin
password: admin

Usuario: testeUser
password: user 
 
-Utilizar jdbc-url ao tentar se conectar no banco de dados H2 que esta no application.properties

-Utilizar endpoint de login no swagger para gerar o token de autenticaçao JTW que é retornado nos headers do response

-Cadastrar inicialmente uma pessoa atraves do Postman utilizando o endpoint POST /api/v1/serasa/score/pessoa

-Utilizar o Postman para consultar os serviços do SerasaController pois apenas o endpoint de Login para autenticaçao do token esta liberado no swagger

-Adicionar na aba Authorization do request o token de autenticacao por exemplo: Bearer + token

-Configuraçoes e chaves de autenticaçao tanto do banco H2 quanto da secret JWT estao no application.properties
