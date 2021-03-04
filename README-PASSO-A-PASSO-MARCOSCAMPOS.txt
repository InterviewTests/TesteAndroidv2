

1º passo:
Desenvolvimento da activity main xml, adicionei 1 image view, 2 edit text e 1 button (conforme layout padrão)

2º passo:

Conforme API , criei o recyclerview:
Criei o xml da segunda tela (onde está o recycler view) e depois criei o xml do detalhamento dos recibos. Com isso criei a classe "Recibos" e o View Holder com os atributos necessários.
Depois criei a classe Adapter e por padrão preciso dar sobrescrever as funções onCreateViewHolder (passando o inflate do layout detalhado do recibo, view holder), 
getItemCount (passando o tamanho da mutablelist das informações obtidas através da API), onBindViewHolder (onde indico o local que cada informação deve ficar no layout detalhado).


3º passo:
Criei a activity da segunda tela: "extrato activity"
Nela eu criei a função "montarLista" para finalização do recycler view, finalizei o adapter e o recycler.


4º passo:
No Postman retirei as informações necessárias para montar as classes UserAccountResponse (onde eu trato os dados do usuário) e StatementsResponse (onde eu trato os dados dos recibos).

5º passo:
Criei uma interface API (onde passei as informações do Retrofit e da URL)
Com isso mapeei as duas chamadas.


6º passo:
Criei na Main a função "setarTexto" onde fiz um Bundle para passar as informações para a "ExtratoActivity"
E com o bundle na "ExtratoAcitivity" peguei as informações e passei para o layout.
Fiz também a ação da imagem de retornar para a página inicial e para finalizar o código da "ExtratoActivity" fiz a requisição da segunda chamada através da função fun fazeSegundaChamada.


7º passo:
Na main activity, fiz a função para fazer a verificação dos requisitos da senha: fun "passwordValidation"
Fiz o sharedPreferences para salvar as informações do nome do usuário e a senha para quando abrir o aplicativo essas informações ficarem salvas.
Fiz a função "validacao" que verificar se o nome do usuario e a senha foram preenchidas e caso sim, faz a verificação se a senha atende todos os requisitos (validar
se a senha tem pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico) através da função "passwordValidation"
E caso atender todos os requisitos monto o body da minha chamada e faço e primeira chamada. Se der sucesso vai para a segunda tela.











