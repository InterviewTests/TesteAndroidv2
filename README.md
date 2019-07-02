# Projeto Santander

-	Neste projeto usei android4.4, foi utilizado linguagem Java.
-	Criei um layout conforme solicitado, uma tela de login e uma tela Histórico.
-	Na minha tela login está exatamente o que foi proposto para fazer o teste, 2 campos, “user” e “password” e o ” batão” para fazer login.
-	No campo user segue a regra de negócio que foi proposta no desafio, somente aceitar user que conter um email ou um CPF, caso contrário aparece uma mensagem, informando que está incorreto campo user.
-	No campo password, segue a seguinte regra de negócio, para validar password tem que conter maiúsculo minúsculo, caracteres especiais e números.
-	Tela histórico criei em duas constraint, coloquei campos textView para mostrar, nome, conta e saldo do cliente.
-	No lado superior a direita fiz um botão que ao clicar te envia para tela de login, trazendo o último email ou CPF inserido.
-	Usei um recycleView para trazer as informações da API do cliente.
-	Criei adapter hisórico, para jogar as informações que irei puxar na API e mostrar no recycleView.
-	Na minha model, está todas as classes que preciso para consumir a API, que seriam: Error, RequestLogin, StatementList, TransactionList, UserAccount.
-	No meu pacote service, criei meu RetrofitConfing que é o responsável por direcionar o caminho da API e tenho uma interface UdacityService que pego meu @POST @GET puxados da API.
-	Para realizar os testes implementei a biblioteca mockito.



### # DESAFIO:

-	Acredito que desafio proposto foi concluído com sucesso, obrigado.                                                                       - Gostei bastante de realizar este teste, espero ter uma oportunidade de poder trabalhar com a família Santander.
