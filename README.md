# Aplicação Bank

A aplicação 'Bank' possui as seguintes características:
- API mínima: 19
- Desenvolvida em Java
- Utilizada arquitetura 'Android Clean Code'
- Testes Unitários desenvolvidos em Kotlin:
  - Foi utilizada a biblioteca de testes **Mockk** (https://mockk.io/), pois oferece recursos bem interessantes e facilita o desenvolvimento de código em Kotlin
  - Como essa biblioteca apresentou algumas instabilidades ao testar as *WeakReferences* do Presenter, para esse componente foi utilizado o **Mockito** (https://site.mockito.org/), uma biblioteca bem difundida na comunidade para testes unitários 
