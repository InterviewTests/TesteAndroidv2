
### # Especificações do projeto:
No projeto foi utilizado: 

- Linguagem de programação Java;
- SDK Minimo: Android 4.4 KitKat(API 19);
-Arquitetura Clean Code MVP(Model-View-Presenter);
- Para os teste unitários, foi usado o JUnit4 e Mockito, testei os métodos de validação de e-mail, validação de CPF e validação de senha que estavam presentes na LoginPresenter;
-Para a consumação da API, foi utilizado o Retrofit, Gson, HTTPLoggingInterceptor e OkHTTP3;
-Utilização do git.


### # Execução do projeto:
Para executar o projeto é preciso:

*Clonar este repositório;
*Abrir o projeto no Android Studio;
*Executar a aplicação.

Para entrar no aplicativo será pedido ao usuário um e-mail ou CPF juntamente com a senha. Caso o login e senha estejam corretos, o usuário será encaminhado a uma segunda tela, onde será exibido nome do usuário, conta bancária, agência bancária, saldo total e todas as transações feitas. Feito isso, se o usuário quiser sair da aplicação, no canto superior direito está localizado um icone para sair da aplicação, que quando clicada, é exibida uma caixa de diálogo, onde o usuário escolhe se irá sair ou não, se a opção escolhida for para sair, o usuário sera levado para a tela de Login novamente para voltar a inserir os dados. 
