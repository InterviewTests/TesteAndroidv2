# Show me the code

Esse repositório contem todo o material necessário para realizar o teste: 
- A especificação do layout está na pasta 'bank_app_layout' abrindo o index.html, utilizar os Styles do Android

- Os dados da Api estão mockados, os exemplos e a especificação dos serviços (login e statements) se encontram no arquivo BankApp.postman_collection.json ( é necessário instalar o postman e importar a colection https://www.getpostman.com/apps)

![Image of Yaktocat](https://github.com/SantanderTecnologia/TesteiOS/blob/new_test/telas.png)

### # DESAFIO:

Na primeira tela teremos um formulario de login, o campo user deve aceitar email ou cpf,
o campo password deve validar se a senha tem pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico.
Apos a validação, realizar o login no endpoint https://bank-app-test.herokuapp.com/api/login e exibir os dados de retorno na próxima tela.
O ultimo usuário logado deve ser salvo de forma segura localmente, e exibido na tela de login se houver algum salvo. 

Na segunda tela será exibido os dados formatados do retorno do login e será necessário fazer um segundo request para obter os lançamentos do usuário, no endpoint https://bank-app-test.herokuapp.com/api/statements/{idUser} que retornará uma lista de lançamentos

### # Avaliação

Você será avaliado pela usabilidade, por respeitar o design e pela arquitetura do app. É esperado que você consiga explicar as decisões que tomou durante o desenvolvimento através de commits.

Obrigatórios:

* Java ou Kotlin
* Material Design
* O app deve funcionar a partir do android 4.4
* Testes unitários, pode usar a ferramenta que você tem mais experiência, só nos explique o que ele tem de bom.
* Arquitetura a ser utilizada: Android Clean Code (https://github.com/kmmraj/android-clean-code && https://medium.com/@kmmraj/android-clean-code-part-1-c66da6551d1)
* Uso do git.

### # Observações gerais

Adicione um arquivo [README.md](http://README.md) com os procedimentos para executar o projeto.
Pedimos que trabalhe sozinho e não divulgue o resultado na internet.

Faça um fork desse desse repositório em seu Github e ao finalizar nos envie um Pull Request com o resultado, por favor informe por qual empresa você esta se candidatando.

# Importante: não há prazo de entrega, faça com qualidade!

# BOA SORTE!

1.       Kotlin

2.      Material design

3.      Arquitetura  MVP (? : é uma boa arquitetura, mas ainda deixa  a desejar, mas como tenho mais familiaridade com ela em projetos kotlin, achei mais adequado).

4.      Conceitos de SOLID

5.      Mesmo sendo projeto de exemplo, procurei dividir as telas, em activities e fragments, onde cada activities, pertence a um fragmente, assim respeitei a arquitetura e deixei a organização mais componentizado.

6.      Criei um pacote chamado de útil, para colocar as classes que utilitárias no projeto, mesmo sendo um app simples, a ideia é sempre imaginar que o projeto pode crescer e a manutenção pode ser feito por outra pessoa.

7.      Para a auxiliar na validação da tela de Login, utilizei a classe enum do kotlin, que é bastante útil, e utilizei  o método delegate, para informar ao usuário sobre algum tipo de aviso.

8.      Para a criação do banco de dados (interno), utilizei a biblioteca Room ("android.arch.persistence.room:compiler:1.1.1"), que facilita bastante na gerencia do banco de dados, nos dando a possibilidade de criar rotinas mais simples, tanto, para criar, recuperar ou excluir um determinado dado.

9.      Utilizei uma  biblioteca de terceiros ('com.amitshekhar.android:debug-db:1.0.4'), para a visualização do banco de dados (Sqlite) no navegador, com intuito de facilitar as consultas no bd.

10.  Para a requisição com a api, utilizei o Retrofit, que ao meu ver, além dele ser fácil de aprender, implementar, segundo um artigo do Medium, ele é considerado bem rápido tbm, em relação as outras bibliotecas (A exemplo, Volley).

11.  Para a conversão de dados, utilizei o gson, o qual também foi importando junto com a biblioteca do Retrofit.

12.   Utilizei  o git para controle de versão e duas branch para trabalhar na implementação (master e frandev)


14. PARA A REALIZAÇÃO DO TESTE - informe usuario ou email e  sua senha (deve conter, pelo menos  1 letra maiúscula, 1 alfanumérico e um caracter especial)

