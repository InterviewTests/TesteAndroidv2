# Explicando projeto

- Nesse projeto tento abordar como implementar os princípios de uma arquitetura limpa,
baseada em divisão de responsabilidades, assim como inversão de controle nas linhas abaixo detalharei,
como resolvi abordar tais problemas e como eles podem ser resolvidos usando recursos nativos
da linguagem Java e em que ponto devemos partir para bibliotecas e soluções empacotadas por 
algum grande fornecedor de ferramentas.

1 - Contexto de negócio deve ser prioritário a contexto técnico. Muitas vezes nossa organização de código
expressa o que usamos tecnicamente pra criar nosso app, mas não indica que problema estamos resolvendo.
Por exemplo dividir o app em pacotes view, model, data, etc. Demonstram que optamos por um tipo de abordagem 
arquitetural mas não o que o código faz. Pensando nisso procurei aplicar as seguintes ações sobre o que fdi construido.
	
	- Se dividir o app em modulos esses devem ter nomes das áreas de negógio que eles implementam.
	- Os pacotes devem ter como raiz a área que resolvem. Ex. as coisas de login devem estar no pacote de login. 
	As coisas de infra no pacote de infra etc. Mas toda área de negócio deve ter sua representação clara na divisão de pastas do sistema.
	- Constantes como Strings devem identiticar a área que resolvem, ou em que contexto estão envolvidos.
	Assim ao invés de ter uma string str_login, optei por usar coisas como login_button_label, statement_last_updates, que são mais minemônicos.
	- IDs de componentes dentro dos XMl devem focar novamente em minemônicos associados a negócio, contexto mais que componentes.
	Assim userET vira loginUserInput, passET tracamos por loginPasswordInput, e loginBt, por loginConfirmButton
	- Da mesma forma nomes de telas tem como prefixo o nome da área que resolvem e não necessariamente 
	devem ter sufixos associados ao tipo de classe que implementam (Embora não seja problema como sufixo).
	- Dentro dos pacotes raiz sim, podemos dividir os pacotes de acordo com a arquietura.
	- Coisas como Utils devem ser evitados, geralmente criamos esse tipo de abordagem quando deixamos escapar alguma abstração.
	Ao invés de colocar um formatador de String em StringUtil devemos pensar em criar uma classe CPFFomater,  que devolve o texto formatado para CPF.
	Um conversor ou até mesmo deixar dentro de uma classe de transformação.

2 - O uso indiscriminado de bibliotecas, desacelara o processo de matuação dos engenheiros de sistema, e muitas vezes coloca o projeto,
dependente de soluções que agregam mais dificuldades que valor ao projeto e ao negócio. Aumentam o débito técnico ao invés de melhorá-lo.
A princípio engenheiros de software devem ser capazes de entender as bibliotecas que usam, ser capazes de explicá-las e até de implementá-las.
O que levaria um engenheiro a usar uma lib ao invés de criar em meu ponto de vista são 2 casos;
	2.1 - A quantidade de esforço é alta para ser desenvolvida do zero e já existe solução de mercado plenamente desenvolvida.
	2.2 - O time não tem expertize para implementar aquela solução e precisa aceitar a biblioteca como verdade.

	Pensando nisso para trabalhar os prinpípios desenvolvi algumas implementações no projeto ao invés de acoplar uma dependência de biblioteca.

	a) Devemos depender de protocolos, abstrações e não de dependencias concretas. A comunicação com o server dependen de uma implementação mais simples,
	que é a que implementei mas esta pode ser facilmente substituida por uma mais robusta por que quem rege a dependencia é um contrato.
	Por exemplo LoginControl depende da interface LoginRepostitory que hoje funciona com uma implementação que usa 100% java nativo pra comunicar com o server,
	mas pode ser substutuida por uma versão que use a lib de preferência.

	b) A Injecão de views que é o processo de fazer o findViewById automático, pode ser feito sobrescrevendo o método setContentView, e está implementado na BaseView.

	c) Da mesma forma para evitar executar uma ação em um contexto que não está vivo após o fim de uma ação concorrente ou envento, 
	optei por mandar um lambda que valida se está indo pra um contexto vivo, ao invés de implementar todo um protocolo de reatividade. que em geral é bem mais
	difícil de assimilar e debugar. Dentro da Classe BaseView, o método runOnUiSafe executa uma ação na ThreadMais desde que esteja associada a um contexto ativo.

	d) O salvamento dos dados de forma segura foi executado através de uma abstração que hoje serailiza um arquivo criptografado com criptografia AES de tamanho 256.
	O salvamento da chave de criptografia pelo que vi da documentação do Android pode ser feito no repositório interno de chaves do Android, mas para esta versão a chave,
	ficou separada em um arquivo serializado (Serializable), dentro do projeto, e o valor é um numero randômico. Dessa forma assim que uma versão mais segura for desenvolvida
	pode substituir a versão atual. Para este caso acredito que pode-se considerar inclusive o uso de libs de terceiros, embora o Android tenha versões robustas para este fim
	que performam melhor a partir da versão 23 Android M.

	e) O Controle de ações concorrentes não deve ser comportamento padrão de bibliotecas de acesso a dados, arquivos etc. Executar uma ação de forma concorrente deve ser escolha
	da classe cliente, uma busca na web deve me devolver o resultado Http da requisição em não um CallBack. Muitas bibliotecas que suportam reatividade com Observáveis, Callback etc,
	também suportam o uso sincrono das chamadas. Nesse projeto a Classe Asynchronous permite executar um bloco de código de forma assincrona, evitando o uso de Callbacks, que em minha visão obscurecem o fluxo de processo que está sendo executado.
	Assim a Classe que precisar executar uma ação de forma paralela pedirá para a classe Asynchronous. Nesse projeto entendo que apenas em alguns momentos as telas precisam
	desse tipo de ação pois tem seu processo prejudicado por algumas ações de longa duração, ou por politicas de segurança do Android.

	Por exemplo LoginActivity executa o login passando user e password, através de uma solicitação ao controle de login, o resultado de sucesso dessa opecação ocorre na linha de código 
	logo abaixo sem necessidade de procurar por um método mágico, uma lib extra, deixando o processo claro.

	f) O princípio de inversão de controle quanto a injeção de dependencias pode ser implementado pelo uso da forma mais clássica com metodos acessores, porém nesse projeto optei por
	usar o padrão fabrica, para aquelas classes mais complexas como forma de proteger a arquiettura. 
	Para usar a fabrica seus objetos precisam ser do tipo instanciáveis apenas pela própria fábrica. Dessa forma os objetos precisam ter apenas um construtor e este deve ser privado. A Fabrica valida essa configuração e gera uma exceção caso este protocolo não seja atendido, no caso de sucesso um Objeto da classe solicitada é entregue.

	Em outras palavras com uso da fabrica evitamos instanciação direta de objetos em camadas diferentes do sistema, e temos um único ponto de entrega de objetos.
	Para esta implemtaçao usamos reflexáao     

	Também implementei um cash desse objetos que pode ser controlado pela fábrica no caso de executar release dos mesmos quando necessário, baseado em situaçoes de baixa de memória, ect.

3 - Desenho arquitetural
	O desenho do projeto levou em consideração a sugestão do projeto clean Architeture, porém como o clean se baseia em princípios de limpesa e divisão de tarefas e resposabilidades
	não usei o projeto como template para substituição de classes e implementação engessada de um conjunto de protocolos mas adequeia minha realidade de projeto.


	a) Dividi o projeto por negócio e abstração e não pela arquitetura, assim como já explicado no item 1 temos como raiz as áreas de negócio e não as camadas.
	b) Dentro das áreas de negócio Temos Telas(Screens), Business e Data. Esse modelo não é fechado mas atende ao projeto atualmente. 
	c) Importante que as telas não guardem estado entao quem guarda a sessao do user é o repositório. Se a tela for destruida e reconstruida ela 
	ainda conseguirá os dados da sessao porque está salvo em outro local.
	d) As telas tambem nao devem carregar as validações de senha e dados do usuário, estes foram feitas fora no LoginControl, assim essa regras independem da tela. 
	e) A persistencia de dados mesmo que dependa de contexto, nao deve passar o contexto da tela. A camada de persistencia está dentro do pacote de dados e é auxiliado
	pelo pacote de infra. Para a tela o tipo de persistencia está abstraido. A classe PersistenteContext é usada na camada de dados e precisa ser inicializada com o Context
	da Aplicação que tem ciclo de vida igual ao do App. Mesmo assim apenas algumas operaçoes sao disponibilizadas e nao todo o Contexto.
	A fim de evitar vazamento de responsabilidade e acoplamento entre as camadas.
	f) Os modelos da Api, Negócio e View não sao necessariamento o mesmo objeto, pode ocorrer operações de converssão entre eles.  Por exemplo o adapter da listagem de statements
	usa o UserAccountViewObject que recebe um UserAccount. Evita que operações de conversão sejam feitas no adapter. Este exibe apenas os dados convertidos para o usuário.
	g) O processo de negocio está dentro das telas nessa implementação por ser extremamente simples, mas pode ser representado por uma classe separada. 
	Mesmo assim uma tela não passa daados para outra através de Bundle. quem controla as fases do processo é o estado do controle.
	h) O tratamento de fluxo de exceção foi feito implementando o uso de exceçoes da plataforma, customizadas com estados.
	As abstraçoes e controle de processo primam por uma linearidade procedural, ao inves do uso de eventos ou reatividade. 

 4 - Features
 	- Como solicitado uma tela de login e uma de listagem de statements, assim como persistencia do usuário logado e validações de dados de entrada.
 	- Usei implementaçoes das classes padrão do Android pra Material Design como solicitado. 
 	- Imagens e icones foram os fornecidos.
 	- Algumas features como a do icone superior da tela de statements não tem definição de funcionalidade. Ficando o visual pronto
 	mas o comportamento a ser implementado após entendimento da feature adequada.
 	- O layout foi implementado para se aproximar ao máximo do que foi especificado, mas necessita de uma revisão final, com relação cores e proporções.
 	O ideal era conversar com a equipe de design pra apresentar dificuldades, principalmente na conversão de pixel para dp, que é unidade de tamanho 
 	para Android.
 	
 5 - Testes
 	- Implementamos testes básicos de unidade (UnitTests) para as duas áreas de negócio, basicamente subindo dentro 
 	das camadas, teste nos dados, teste nas regras de negócio, tambem fizemos testes muitos simples relacionados a
 	comunicaçao com o server ainda usando os testes de unidade. O bom que esses testes são bem rápidos de fazer
 	e muitos rapidos de testar.
 	- Apesar de nao ser discutível, já tive alguns problemas com mudança de serviço e gosto de fazer meus
 	testes de unidade pra validar pelo menos que as respostas nao mudaram. Depois da classe de comunicaçao estar pronta
 	esses testes são bem simples de fazer. Podem ser implemetados em pacotes separados e destacados das estatisticas
 	se assim achar necessário, mas considero uma forma muito boa de proteger o app de forma bem rápida.

 	Não implelemtamos mock para a classe de comunicação mas isso pode ser viabilizado pelo uso da fabrica, flavors ou
 	combinação de ambos.
 	- Implementamos um único teste instrumentado, LoginInstrumentedTest que basicamente valida no App se o usuário logado
 	é salvo corretamente no App.

6 - Considerações
	A implementação trás o uso de uma arquitetura que tenta resolver o problema proposto sem excesso de engenharia,
	baseado-se na arquietura limpa.
	O uso de bibliotecas foi bastante reduzido focando em abordar princípios, mas isso nao exclui o uso de libs em 
	projetos do mundo real. Foi escolha apenas para abordagem no projeto e trabalhar resoluçào de problemas.
	Princípios de programação como isolamento de responsabilidades e uso de abstrações foi priorizado.
	Linearidade do processo em detrimento de reatividade.
	
	Para executar o projeto acessar a pasta BankAndroid pelo Android Studio através do menu abir projeto existente.
	Para rodas os testes clicar com botao direito na pasta src/test/java e selecionar Run Tests in Java
	Para rodar os testes instrumentado executar o mesmo procedimento para os testes de unidade, porém sobre a pasta src/androidTest/java







