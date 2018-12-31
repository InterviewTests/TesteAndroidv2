# Procedimentos
No projeto, foi implementado a arquitetura Android Clean Code, conforme solicitado. Ao meu entendimento, essa arquitetura divide o código em diversas camadas
e classes, o que facilita a testabilidade, deixa o código mais organizado e de melhor entendimento, além de facilitar em manutenções futuras.
Foi utilizado a biblioteca Retrofit para consumir os serviços web.
Os dados do último usuário logado foram armazenados em SharedPreferences, pois como é uma pequena quantidade de valores, não vi necessidade de salva-los em 
um banco de dados (sqlite). 
Também foi utilizado DataBinding para vinculação das views com as classes, evitando repetições de criar no código diversos "findById" para cada elemento,
deixando o código mais limpo.
