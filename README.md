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
