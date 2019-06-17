# Sobre o aplicativo desenvolvido

O Aplicativo foi desenvolvido respeitando a arquitetura Clean conforme requisitado, 
sendo assim, divide os papéis e responsabilidades em classes, facilitando a leitura 
do código e tornando o aplicativo testável.

Foi utilizado JUnit para realizar o teste dos métodos de validação.

Para consumir a api disponibilizada, foi utilizado a lib http Retrofit da Square, que considero uma lib leve,
muito utilizada pelos desenvolvedores, fácil de trabalhar, inserir interceptors e modificar requisições como headers,
timeout e entre outros.

O Aplicativo salva localmente o login do último usuário logado, por ser uma pequena quantidade
de dados, optei pela SharedPreferences, pois não vi necessidade de criar um database (SQLite) para esse caso.

Optei por desenhar os layouts com ConstraintLayout, recomendado pelo Google e um dos últimos lançados, que funciona
muito bem com diversos tamanhos de tela, além de também ter utilizado RelativeLayout que agrupa os componentes um relativo ao outro.

Seguindo os conceitos da arquitetura Clean, foram criadas Interfaces para comunicação entre as classes. A Activity recebe uma ação 
do usuário, o Interactor é delegado de buscar os dados localmente ou na web através do Repositório e retornar os dados brutos para 
o Presenter, que apresenta os dados formatados e validados para a Activity.

# Observações
O Aplicativo está dentro da pasta AppTesteAndroid
