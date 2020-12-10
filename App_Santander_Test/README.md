# appBank_Santander

Teste realizado por Ivan Costa em 10 de dezembro de 2020;

A arquitetura utilizada para desenvolvimento do App foi uma arquitetura limpa/hexagonal, baseada em MVP.

A estrutura do app está divida em alguns pacotes principais, sendo eles:

- Adapter - que contém uma classe adapter para o objeto Statement que será exibido na tela por meio de uma recycler view
- Api - Que contém boa parte da arquitetura, sendo responsável por configurar e estabelecer a conexão com as Apis;
- Dagger - Onde estão as classes responsáveis por implementar toda parte de programação Reativa utilizando RxAndroid.
- Database - Importante para a manutenção e Persistência de Dados utilizando o Realm;
- Infraestrutura - Com uma classe WorkerOperator que instancia um Observable para testes;
- Model - Criação de modelos de objetos que são consumidos dentro do app, como User e Statement;
- Utils - Classes utilizadas para formatação de campos e validação de força de senha;
- View - Package onde estão contidas as classes referentes a camada de UI da aplicação.

Para testar o funcionamento das chamadas de API, utilizei os dados que estavam pré-cadastrados no json enviado e adicionei mais alguns.
Para testar, utilizar o usuário test_user com senha Test@123

A validação de senha também foi feita. Para que o usuário consiga se logar, solicito uma senha com pelo menos 8 caracteres, uma letra maíuscula e um caracter especial. Sem isso,
é retornado um toast informando que é necessário adicionar uma senha forte.

Fiz uma validação para campo vazio. Caso o usuário não preencha um dos campos, é retornado para ele a necessidade de preencher esses campos.

O preenchimento dos dados vindos da api de Statements é feito por meio de um recycler view. O adapter recebe os dados que vem da api por meio de uma chamada
ao Retrofit e seta esses dados na tela num fragment que foi criado (uma espécie de card).

Nas chamadas ao Retrofit, também utilizei Clean Architeture. As chamadas são feitas em background e os objetos são setados na tela
sem oneração ao processamento do App. As chamadas estão sendo realizadas na classe BreedApiService, que está
dentro do repositório Api.

Com relação aos testes, implementei os testes unitários do próprio Android, além do Junit, o Espresso, o Mockito e o Hamcrest.
