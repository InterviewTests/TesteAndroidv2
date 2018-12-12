## TesteSantander

Projeto TesteSantander projeto desenvolvido em Kotlin, onde é feito uma requisição para um endpoint e posteriormente exibido uma lista de lançamentos e dados de um usuário teste.

## Instalação
Está anexado neste projeto o arquivo apk. A apk pode ser baixado clicando 
[aqui](https://github.com/0tavi0/TesteAndroidv2/blob/master/apk/app-release.apk). 

# ScreenShots
<p align="center">
  <img src="Screenshots/tela1.png" width="200" alt="accessibility text">
  <img src="Screenshots/tela2.png" width="200" alt="accessibility text">
</p>

## Projeto
Na primeira tela tem um formulario de login, o campo user aceita email ou cpf,
o campo password valida se a senha tem pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico.
Após a validação, é realizadp o login no endpoint https://bank-app-test.herokuapp.com/api/login e é exibido os dados de retorno na próxima tela.
O ultimo usuário logado deve é salvo de forma segura localmente, e é exibido na tela de login algum salvo. 
Na segunda tela é exibido os dados do retorno do login no endpoint https://bank-app-test.herokuapp.com/api/statements/{idUser} que retorna uma lista de lançamentos

## Arquitetura
Neste projeto utilizei a arquitetura MVP pela facilidade de manutenção e futuras implementações.

## Bibliotecas
- [Retrofit](https://square.github.io/retrofit/): Biblioteca escolhida para requisições das APIs.
- [Gson](https://github.com/google/gson): Biblioteca da Google, para deserializar o retorno da API.
- [Hawk](https://github.com/orhanobut/hawk): A proposta da lib Hawk é fornecer uma maneira simples de persistir qualquer tipo de dado utilizando uma interface pública no modelo "chave-valor". Os dados são por padrão criptografados para atender a uma outra meta da API, segurança.
- [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin): Uma pequena biblioteca que fornece funções auxiliares para trabalhar com Mockito em Kotlin.

##Testes

- Foi feito pequenos testes usando o [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin) e Junit 

# Author
- Otávio Augusto - otavio.le@gmail.com </br>
 
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Otávio%20Augusto-blue.svg)](https://www.linkedin.com/in/otavio-augusto-776861116/)