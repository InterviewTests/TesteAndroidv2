
# Android Clean Architecture

A estrutura deste projeto foi desenvolvida usando abordagem MVP Clean Architecture.
## Introdução

![](/captures/screenshot_login.jpg?raw=true "Login Screen")  ![](/captures/screenshot_statements.jpg?raw=true "Statement Screen")

A Clean Architecture definida por Robert Martin em 2012, possui dois principais princípios:
1. **Separação de camadas** -  Divisão da aplicativo em três camadas
   - **data** (modulo android library)
   - **domain** (modulo java library) - lógica de negócios
   - **presentation** (modulo android application).

2. **Inversão de Controle** - De acordo com este princípio, a camada de domínio não deve depender das camadas externas. Ou seja, as classes das camadas externas não devem ser usadas na camada de domínio. A interação com camadas externas é implementada por meio de interfaces. A declaração das interfaces contém na camada de domínio e sua implementação contém nas camadas externas.

## Camada de domínio (domain)
Esta camada contém a regras de negócio e deve ser completamente independente das demais camadas. É nela onde são criadas nas interfaces dos repositórios com a qual o UseCase trabalha. Ela descreve quais dados o UseCase deseja obter de camadas externas.

## Camada de dados (data)
Essa camada contém tudo sobre armazenamento e gerenciamento de dados (banco de dados, SharedPreferences, rede)

O source dos dados foram divididos em:
- Local - dados armazanamedo localmente, seja em um banco de dados ou SharedPreferences.
- Remoto - dados provenitentes da internet.
## Camada de apresentação (presentation)

A camada de apresentação contém todos os componentes da interface do usuário, como CustomView, Activity, fragment, etc.

### View
**View** é responsável por como os dados serão mostrados ao usuário. Além disso, a View informa ao presenter sobre a interação do usuário, como o clique do botão ou a entrada de texto.

### Presenter
O presenter reage às ações do usuário que a View notificou (como pressionar um botão, clicar no item da lista ou inserir texto) e decidir o que fazer a seguir.

## Organização de pacotes

```
com.santander
|
|----data
|     |---- di
|     |---- exception
|     |---- mapper
|     |---- repository
|     |---- source
|     |     |---- local
|     |     |     |---- preferences
|     |     |---- remote
|     |     |     |---- entity
|     |     |     |     |---- response
|     |     |     |     |---- request
|     |---- util
|     |     |---- network
|     |     |     |---- preferences
|     |     |---- security
|     |     |     |---- crypto
|
|---- domain
|     |---- di
|     |---- entity
|     |     |---- business
|     |     |---- input
|     |---- exception
|     |---- repository
|     |---- usecase
|     |     |---- core
|     |     |---- impl
|
|---- presentation
|     |---- core
|     |     |---- di
|     |     |---- ui
|     |     |     |---- base
|     |     |     |---- widget
|     |     |---- util
|     |     |     |---- extension
|     |     |     |---- validation
|     |---- feature
|     |     |---- login
|     |     |---- statements
```

# Testes

Para construção dos testes unitários foi usado o [Spek](https://spekframework.org/) e o [Mockito](https://github.com/nhaarman/mockito-kotlin). O Spek framework oferece uma DSL mais expressiva para escrever testes em Kotlin.

## Libraries:

- Androidx - for backwards-compatibility
- Material Components
- RxJava 2 - for reactive programming
- Koin 2 - for dependency injection
- Retrofit 2 - for Networking
- Gson - for parsing the JSON responses
- Spek e Mockito - for tests