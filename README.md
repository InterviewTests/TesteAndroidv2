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
* Material Designer
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
