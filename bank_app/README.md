# Instruções

Para gerar o APK da aplicação, executar na pasta do aplicativo o comando abaixo:

```
gradlew assembleDebug
```

Para gerar o APK da aplicação e instalar no dispositivo ou emulador conectado, executar na pasta do aplicativo o comando abaixo:

```
gradlew installDebug
```

# Bibliotecas

## Testes Unitários

Para os testes unitários foi utilizado o JUnit e o MockK.

O MockK foi escolhido por ter sido construído para o Kotlin, permitindo utilizar características da linguagem, que outras bibliotecas não permitem.

## Testes de instrumentação

Para os testes de instrumentação a biblioteca utilizada foi o Espresso, que é a ferramenta que vem por padrão nos projetos criados pelo Android Studio, e não precisa de nenhuma configuração adicional.
