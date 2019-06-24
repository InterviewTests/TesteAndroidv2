# Sobre o App

- Desenvolvido em JAVA;
- Padrão utilizado foi o MVP (Model View Presenter) que se encaixa bem no conceito modular do "Android Clean Code";
- Ferramenta escolhida para realizar os teste unitários foi o JUnit por ter mais familiaridade;
- Foi utilizado o Retrofit 2.5.0 junto com o Gson 2.5.0 para realizar as requisições na API;
- Os campos de login foram validados com base nos requerimentos pedidos;

# Utilização
- Para fazer login no app é obrigatório o usuário preencher todos os campos e estar conectado a uma rede de internet, requisitos para fazer login:
    Campo User = entrar com um e-mail ou cpf validos, caso seja digitado um e-mail ou cpf invalido o usuário será avisado por uma mensagem rápida na tela(Toast).
    Campo Password = a senha deve conter no mínimo um caractere maiúsculo, um especial e um alfanumérico, se a senha não obedecer esses critérios o usuário será avisado por uma mensagem rápida na tela(Toast).
    
- Passados os critérios acima o login será efetuado com sucesso;
- Ao fazer login os dados do login do usuário ficam salvos localmente de forma segura com o SharedPreferences, para quando ele abrir o app novamente, os campos de User e Password já estarão preenchidos com os dados do ultimo usuário logado;
- Na tela principal (Dashboard) o usuário vera suas informações junto com uma linha do tempo com suas ultimas movimentações da conta;
- Caso ele queira atualizar a lista basta ele arrastar o dedo de cima para baixo na tela e a lista será atualizada com novos dados caso tenha, esse é um recurso muito utilizado no padrão Material Design para atualizar uma pagina, o app do google chrome utiliza esse recurso por exemplo;
- No canto superior direito existe um ícone para sair da conta, ao clicar abrira uma caixa de dialogo perguntando se o usuário deseja realmente sair, ao clica em não nada acontece e o usuário continua na dashboard, caso clique em sim, ele volta para tela de login.
