

Bom dia,

    Como vai você? Meu nome é Carlos Nicolau Galves, moro em Curitiba, sou desenvolvedor de Android Nativo nas linguagens
    Java Android e Kotlin Android a uns 6 anos trabalhando no mercado. Tenho 29 anos, e estou sempre procurando melhorar meus
conhecimentos nesta área. Os aplicativos que trabalhei vão de: Fitness, Medicina, Agricultura, Banco digital, Rede Social, etc.


    Também já trabalhei um pouco com iOS, desenvolvi um aplicativo para o Albert Einstein em Swift.

    Nesta prova, coloquei alguns dos meus conhecimentos, infelizmente eu não tive 100% do meu dia-a-dia para desenvolver então
isso envolveu desenvolver essa prova depois do meu trabalho ou nos finais de semana. Anyway comentando sobre a prova:

    Aqui onde trabalho atualmente usamos o MVVM, e usamos uma lib particular nossa para as chamadas. Para o exemplo da prova
foi usado o RxJava, RxAndroid + Retrofit. Devo admitir que não tenho muita prática nestas tecnologias uma vez que não são usadas
no dia-a-dia. Porém nada de absurdo que visualizar como é escrito o código de vocês eu não vá entender.

   Aqui no meu trabalho não estamos atualmente desenvolvendo em Kotlin, então aproveitei para praticar meus conhecimentos
sobre a tecnologia. Pode-ser que aja algumas maneiras durante o código de fazer com funcionalidades melhores, a exemplo
coroutines para as chamadas mas visto que meu tempo era curto e queria mostrar também este conhecimento no momento não implementei.

   Segui o tutorial que vocês recomendaram, peguei uns 3 dias no final de semana antes de começar e dei uma pesquisada.
Achei interessante mais uma maneira de se implementar, esta é a 4a maneira de se organizar o seu código que vejo (MVC, MVP, MVVM e
esta agora )

   Bom, implementei o Room como pediram e vi que era para salvar o usuário local de maneira segura, para isto criei uma classe
CryptoFakeUtils, apenas para indicar que se fosse um projeto de produção provavelmente usariamos uma lib particular nossa ( De
qualquer maneira na hora de salvar o usuário local teria que ser com a cryptografia na senha e não a real né?)

   Vi também que pediram algumas validações na hora de digitar, testei a princípio manualmente com email e a senha batendo com
os pedidos. Caso queira-se melhorar o exemplo, de novo, ressaltando que fiz este esforço pós trabalho, seria interessante por uma
mascara que reconhece-se no input do user name.

Caso você erre vai subir um popzinho mostrando o que falta.

    Caso você se logue com outro usuário, ele vai salvar o último, lá mostro como usar a query do Room. E de qualquer forma qualquer
usuário passado vai retornar o mesmo do servidor visto que ta mockado provavelmente.

    Acredito que a maneira de organização das classes em Presentation, Domain e Data se encaixam legal, não estou afirmando que
é a melhor maneira, visto que esta é uma demonstração de conhecimento ( Pretendo absorver melhores ideias com pessoas que
provavelmente aí já praticam isto no dia-a-dia)

    Sobre os testes, fiz alguns apenas pra demonstrativo, demorei um bom tempo para me organizar nas ideias e acredito que
posso melhorar este exemplo mas ai seria criando task e substask de uma maneira que isto demoraria mais tempo.

    O exemplo de login ele é bem extenso, existem N casos de erros e cuidados que devemos ter por isso ficou um pouco extenso
talvez o teste e fazer os testes.

Ouve outros imprevistos, o roboletric não é usado onde trabalho então esta foi minha primeira experiência. Com o androidx
ele começou a dar uns problemas mas acredito que ta ok.

    Mas, como meu conhecimento sobre o roboeletronic é menor ( Não dizendo que limitado ) Teria que ter mais tempo para
estudar com mais calma, ver exemplos em prática e isto é pois na hora de verificar alguns métodos ele chama o Room, e ai como
eu precisava apenas fazer um UnitTest, fiz um "xunxo" que ao estar no flavor UnitTest, ele tem o retrofitFake, databaseFake, etc.

Tentei usar o mockito e o powermockito para solucionar esses problemas mas não cheguei a uma solução ainda que gostasse de fato.
Então preferi não poluir.

Na segunda parte do app, escrevi em MVVM, um pouco mais rápido no meu entender, provalvemente para seguir esta arquitetura
criaria um template para criar as classes e pacotes já predefinidos pelo nome que criar. Igual quando criamos uma Activity e já vem
o xml e o AndroidManifesto escrito.

Bom,

Fiz com a máxima dedicação possível. Você pode rodar o app, ele vai funcionar.

Sobre melhorias que gostaria com ( Tempo ) de fazer e no caso, uma vez contratado né? hehe

Seriam: Colocar injeção de dependência, melhorar os singletons, debater qual é cada lugar melhor ( Sempre estou aberto
a entender o outro ponto de vista)

E acredito que é isso. Estou sendo indicado pela IBM ( andrea.hosne@ibm.com )  e pela Juliana (juliana.ribeiro@zup.com.br)

Estou sempre interessado em evoluir e gosto de ter profissionais ainda mais capacitados ao meu redor que me estimulem a procurar
as melhores soluções ao máximo que conseguirmos.


Obrigado e espero que possamos nos falar, abraço.






