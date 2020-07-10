# Overview sobre o projeto

Seguem algumas considerações sobre a arquitetura utilizada no projeto: 

Utilizei MVVMClean como arquitetura, separando as camadas em Fragment, ViewModel, Handler e Presenter.

Segue um resumo da responsabilidade de cada camada:
- No fragment ficam basicamente os observers dos objetos LiveData adicionados nas ViewModels e nos ViewPresenters, atualizando os dados da 
interface diretamente no xml, utilizando databinding.
- Na ViewModel ficam os objetos LiveData, os eventos de click de botão e as funções que controlam as ações do usuário, de acordo com ação de cada view.
Também ficam na ViewModel as requisições ao serviço, por meio da classe Handler.
- Por sua vez, a classe Handler é responsável por trabalhar com os dados requisitados pelo usuário. Sejam eles por meio de input de dados ou ações/requisições.
  - É nela onde são feitas as chamadas à classe Service, que por sua vez busca/envia os dados usando a classe Repository.
  - Também é nela onde enviam-se os dados trabalhados para objetos na classe Presenter, que por sua vez guarda dados de input, 
  estados das Views (selected, enabled), label texts, etc. e gerencia estados de componentes através de objetos LiveData.

Utilizei injeção de dependência com a biblioteca Koin, que também foi utilizada nas rotinas de teste, utilizando a classe KoinTest.
Para as reqisições à API utilizei Retrofit.

### # Observações gerais

Na tela de login, há a validação de usuário e senha, onde para habilitar o botão de login, é necessário digitar os dados de acordo com a validação
sugerida (nome de usuário utilizando email ou cpf, e senha com ao menos 1 número, 1 caractere especial e uma letra maiúscula).
Fiz a validação desta maneira e não após o toque no botão de Sign in pois acredito que neste cenário entende-se que o usuário á está cadastrado e sabe
das regras de cadastro de nome de usuário e senha.

*Para executar o projeto basta abrir no Android Studio, aguardar o gradle sincronizar as dependências e executar o mesmo.

# OBRIGADO PELA OPORTUNIDADE!

#MARLON

# Estou me candidatando através da empresa HProjekt. Recrutador/Contato: Luiz Pontes.
