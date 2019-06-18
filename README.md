# Executar o projeto

Apenas dar o clone e abrir no android studio, baixar as dependencias , ir em file -> settings -> Instant Run -> e desmarcar o opção enable Instant Run caso estive marcada.
Apos isso, sera necessario apenas executar a aplicação em um emulador.

# Como funciona

O Aplicativo foi construido utilizando a arquitetura VIPER, que alem de ser fortemente baseada no clean architecture tambem é fortemente baseada nos conceitos do SOLID, foi implementada utilizando os ports and adapters, e o dagger2 para o conceito de DIP (Injeção de dependencia), foi adaptado uma ViewModel para garantir o fluxo de dados sem quebrar o conceito da arquitetura e foi adaptado ao interactor um dataManeger para chamadas internas da aplicação e um remote para chamadas internas, tambem foi utilizado o conceito de SingleActivity para garantir melhor estabilidade do aplicativo, e utilizado o Navigation Architecture Components para o fluxo de navegação entre Fragments garantindo menos chances de vazamento de memoria nas trocas de tela, foi utilizado o databinding para garantir a reatividade da aplicação, e o livedata juntamente com o viewmodel da jetpack, para que os dados fornecidos pelo usuario ou consultados nao sejam perdidos no caso de uma reconstrução do lifecycle do fragment, e tambem foi utilizado o RecyclerView para garantir uma boa performace, juntamente com o constraintLayout que tambem teve o papel de garantir a performação da aplicação.  

O aplicativo salva os dados da conta do usuario em um banco cifrado onde utilizei o room persistem e criei um camada de repository para poder ter um controle sobre a execução de cada thread, evitando que haja duas chamadas simultâneas no banco, utilizei o Hawk para salvar o usuario, pois como o usuario e um dado pequeno e um dado bastante sensível o Hawk garante um boa segurança utilizando o conceal criado pelo facebook para criptografia segura do dados, e utiliza preferencias cifradas. 

O aplicativo utiliza o retrofit2, com chamadas sincronas para realizar os testes unitarios e chamadas assincronas para o fluxo da aplicação, garantido assim que não haja chamadas de request na Thread Principal.

# Recursos para os testes unitarios

decidi utilizar o rebolectric para inicializar a activity, e os mockitos para a comparação dos metodos a serem chamados, ja que a arquitetura é baseada em port and adapters, tambem estou utilizando o Junit para os testes unitarios juntamente com o Coverage.


