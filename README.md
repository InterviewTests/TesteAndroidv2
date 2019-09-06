# Teste Android v2 - Desenvolvido por Rodrigo Nogueira Costa - Digão(digaot.info@gmail.com)

Este repositório contém o codigo fonte referente ao desenvolvimento referente ao projeto Teste Android v2(https://github.com/SantanderTecnologia/TesteAndroidv2)

## Dependencies
- recyclerview-v7;
- cardview-v7;
- okhttp

## Tela Login
- REST(https://bank-app-test.herokuapp.com/api/login) com o método POST de login passando passando os parametros login e senha:
	- Login com a validação de CPF ou E-mail;
	- Senha deve conter caracteres alfanuméricos, caracteres especiais e pelo menos um letra maiúscula;
- Styles do Android;
- SQLite para salvar os dados do último usuário logado e manter como sujestão toda a vez que o usuário entrar na tela de login


## Tela de Lançamentos
- Informa o nome do usuário logado, agência, conta e saldo final dos lançamentos;
- REST(https://bank-app-test.herokuapp.com/api/statements/{idUser}) com método GET passando o ID do usuário logado e lista dos lançamentos utilizando CardView e RecyclerView com o titulo, data, descrição e valor dos lançamentos
