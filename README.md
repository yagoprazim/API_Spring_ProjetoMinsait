# 🚀 API_ProjetoMinsait (Controle de Contatos)

Trata-se de uma **API Rest** desenvolvida com o **Spring Boot Framework 3.0.12 (JAVA)**, com o objetivo de implementar um _controle de contatos_, de forma que, temos 2 entidades que se relacionam: **Pessoa** e **Contato**. Podemos _cadastrar, visualizar, atualizar e deletar_ pessoas e seus contatos, ou seja, um _CRUD_ completo através dos métodos HTTP.
- Para saber sobre detalhes estruturais e técnicos, assim como de algumas dependências implementadas, como: _Lombok_ (para limpeza de código)  e _MapStruct_ (Mapeamento de Entidades x Dto's), há um tópico detalhando toda a estrutura caso tenha interesse! =)
    
# <img src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" width="40" height="40" alt="Spring Boot"> Back-End 
### :pushpin: Requerimentos
- Java JDK - version 17.0.8
- Banco de Dados MySQL - version 8.0.33
- Maven
- Ambiente de Desenvolvimento - IDE (Recomendo o Intellij, pois ele já possui o Maven embutido)
------------------------------------------------------------------------------------------------------
### :computer: Instalação e Execução
1. **Na sua IDE, clone o repositório e acesse a pasta principal:**
   - git clone https://github.com/yagoprazim/API_Spring_ProjetoMinsait.git
   - cd .\API_Spring_ProjetoMinsait\

2. **Configure o banco de dados a partir do arquivo **'application.properties'** em src/main/resources:**
   - Antes de criar o banco, verifique se o: **username=root, password=root, mysql://localhost:3306/** correspondem ao seu ambiente MySQL (você também pode alterá-los caso queira).
   - Nome do banco: **db_controle_contatos** - você também pode alterá-lo.
   - Utilizando um gerenciador ou o próprio Prompt, crie o banco de dados seguindo o que você deixou definido no 'application.properties'.

3. **Baixando as dependências e executando a aplicação, no terminal:**
   - mvn spring-boot:run
   - Por padrão, ele irá rodar no: http://localhost:8080/
     
4. **Após a aplicação rodar corretamente, verifique se foram criadas as tabelas no seu banco:**
   - _tb_pessoa_ e _tb_contato_

------------------------------------------------------------------------------------------------------
### <img src="https://user-images.githubusercontent.com/25181517/186711335-a3729606-5a78-4496-9a36-06efcc74f800.png" width="40" height="40" alt="Swagger"> Swagger 
- Para acessar o Swagger e testar os endpoints - Com o seu projeto rodando e o banco de dados corretamente configurado, acesse: http://localhost:8080/swagger-ui/index.html
- Nele você encontrará uma breve descrição sobre a funcionalidade de cada endpoint. Contudo, caso queira saber os detalhes principais sobre a estrutura do projeto, continue a leitura abaixo.
------------------------------------------------------------------------------------------------------
### :open_file_folder: Resumo da estrutura do projeto:
1. __MODELS__:
   - Contém as Entidades da API: **PessoaModel e ContatoModel**. Elas se relacionam de forma que _uma pessoa pode ter vários contatos_.
   - Além das declaração dos atributos/propriedades de cada model, foi implementado o Lombok para não termos que criar métodos getters, setters, etc manualmente, para isso, usamos algumas annotations, como: @Data.
   - Além disso, usamos annotations que vão servir para mapear cada model como Entidade JPA, para que possam ser criadas as tabelas no banco de dados a partir de cada Entidade. Para isso, utilizamos o Hibernate/JPA para que, ao executarmos a aplicação, sejam geradas automaticamente as tabelas no banco.

2. __DTO's__:
   - Para encapsulamento dos dados e mais liberdade para a definição correta dos dados que precisam ser passados no _body_ das _requests_, como também daqueles que são recebidos no _body_ das _responses_, foram implementados os dto's.
   - Foram criados 2 _packages_: Request e Response. O motivo fica mais claro com um exemplo: para criar um contato, o que é preciso passar na requisição é diferente do que é preciso ser retornado (a quantidade e alguns tipos de dados mudam), portanto,
     na requisição, preciso apenas de:
     ```json
     //REQUEST
     {
      "tipoContato": 0,
      "contato": "string"
      }
     //O id da pessoa é passado na url, e o id do contato é auto incremental
     ```
     Enquanto que na resposta, quero que retorne:
      ```json
     //RESPONSE
     {
      "id": 0,
      "tipoContato": 0,
      "contato": "string",
      "pessoa": {
        "id": 0,
        "nome": "string",
        "endereco": "string",
        "cep": "string",
        "cidade": "string",
        "uf": "string"
      }
     ```
     --> Com o uso de DTO's, conseguimos controlar bem isso, por isso foram criados DTO's para Response e outros para Request.
   - Além disso, foram criados outros DTO's para casos específicos: o que é usado na implementação da 'mala-direta' (endpoint que concatena os dados de endereço de uma pessoa); e outro para personalizar as respostas do tratamento dos erros 400.

  3. __MAPPERS__:
     - Para facilitar o mapeamento e conversão dos dados entre DTO's e Entidades, utilizei o MapStruct, o qual, dentre os mapeadores que existem, ele possui bastante opções de controle e boa performace. 
     - Foram criadas: PessoaMapper e ContatoMapper - em resumo, essas 2 Interfaces servem para sempre que precisarmos, chamarmos métodos para converter e mapear os dados automaticamente, seja de uma Model para um DTO, vice-versa.

  4. __REPOSITORIES__:
     - Temos as interfaces: PessoaRepository e ContatoRepository, servindo para herdarmos de JpaRepository e termos acesso aos métodos para realizar queries com o banco de dados.

  5. __SERVICES__:
     - Temos: PessoaService e ContatoService, servindo para criar as regras de negócio e utilizar do mapeamento para lidar com os dados entre Entidades e Dto's; implementando também os repositories para chamamento dos métodos de CRUD que serão utilizados pelos Controllers.
     - Em PessoaService, temos os métodos para: listarTodasPessoas; listarPessoaPorId; registrarPessoa; atualizarPessoa; deletarPessoa; listarMalaDiretaPorId; construirMalaDiretaDto; listarContatosPessoa; adicionarContatoPessoa.
     - Em ContatoService, temos os métodos para: listarContatoPorId, atualizarContato, deletarContato.
     - Perceba que alguns métodos como o de criação de contatos e listar todos os contatos de uma pessoa, resolvi deixar dentro de PessoaService, devido a estrutura que escolhi seguir.
     - Optei pela escrita verbosa dos métodos para facilitar o entendimento do que cada um faz.
  
  6. __CONTROLLERS__:
     - Basicamente, implementa tudo que criamos nos services, tornando tudo funcional através de métodos HTTP (Get, Post, Put, Delete)... Para facilitar, deixei os nomes de cada método dos controllers iguais aos dos services, seguindo um padrão. Eles estão bem simples e com poucas linhas de código, justamente por causa da forma como o projeto foi estruturado, para não colocar tanta informação nos controllers.
     - Inclusive, optei por usar @RequiredArgsConstructor ao invés de @Autowired.
     - Tratamentos básicos, como: para verificar se uma pessoa existe ou não, ou se determinado dado está nulo ou não, etc, não passei isso no controller, ficando ele responsável apenas pelo chamamento dos services e ResponseEntity de forma simples - deixei essas responsabilidades de tratamento nos packages de: services + exceptions + validations. Enquanto no controller apenas passei os statuscode de sucesso (200, 201, 204)...
  
  7. __EXCEPTIONS e VALIDATIONS__:
     - Tratam-se de 2 packages que se complementam. Em Exceptions, tratei as exceções básicas, de forma que no caso de erro 404, poderei personalizar as respostas de erro a partir das mensagens que coloquei nos services.
     - Em casos de erro 400, utilizei o dto 'ErrosListaDto' para gerarmos uma lista de erros tratados a partir do uso das annotations (@NotBlank, @NotNull, etc), de forma que na lista de erros, são retornadas mensagens personalizadas que eu defini. É aí que entra a Package validation, pois ela serve para criarmos annotations personalizadas, fiz isso para tratar o 'tipoContato', para que seu valor só possa ser '0' ou '1' (com mensagem personalizada que é adicionada na lista de erros das annotations padrão), então penso como uma boa forma de escalar o projeto e criar annotations que possam ser implementadas direto no tratamento de erros 400.

  8. __CONFIGURATIONS__:
     - Contém o arquivo de configurações do Swagger. Contudo, irá servir para coisas complementares no futuro, como arquivo de configuração de CORS para caso eu implemente um Front-End, etc.
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------






