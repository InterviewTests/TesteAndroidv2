# Bank App

Um projeto montado utilizando Android Nativo e, especificamente a linguagem Kotlin, para agregar os últimos gastos de uma determinada conta bancária de forma simples e intuitiva.

[![version](https://img.shields.io/badge/Kotlin-v1.3.72-red.svg)](https://semver.org) 

## Instruções
Para rodar o aplicativo, é necessário ter o Android Studio e uma SDK Android; a versão de SDK mínima para rodar este app é a 19, e a máxima é a 29. Ap

 - Para rodar o aplicativo, é necessário ter o Android Studio e uma SDK Android; a versão de SDK mínima para rodar este app é a 19, e a máxima é a 29. 
 - Após ter os requisitos mínimos e rodar pela primeira vez, será promptada uma tela de login, onde será necessário ter um usuário de acesso e uma senha; Como os conteúdos de API estão mockados, não há problema algum em inserir um e-mail ou CPF qualquer, desde que ambos sejam válidos no mundo real. A senha deve possuir pelo menos uma letra maiúscula para ser aceita.
 - Entrando na página principal com os últimos gastos, será possível fazer scroll dos itens e, se for desejável sair, há um botão de logout no canto superior direito, que irá redirecionar o usuário de volta para a tela de login.
## Pacotes
 - [Koin](https://insert-koin.io/): Pacote utilizado para injeção de dependências, utilizado principalmente pela facilidade de integração com ViewModels;
 - [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) : Pacote que visa deixar requisições assíncronas em Kotlin mais leves e com um nível de concorrência mais sofisticado;
 - [JUnit](https://junit.org/junit5/): Framework de testes unitários que é a base de testagem de aplicativos desenvolvidos em Android Nativo;
 - [MockK](https://mockk.io/): Ferramenta complementar de testes unitários para mockagem de dados e chamadas assíncronas, utilizadas neste projeto por sua integração nativa com Coroutines;
 - [OkHttp](https://square.github.io/okhttp/): Utilizado para facilitar o controle de chamadas HTTP e mockagem de servidores dentro do aplicativo;
 - [Retrofit](https://square.github.io/retrofit/): Cliente de chamadas HTTP, notável pela facilidade em sua montagem e desserialização de objetos;
 - [Gson](https://github.com/google/gson): Ferramenta que, através de anotações e reflections dentro do Android, permite serializar e desserializar entre objetos e arquivos JSON.
