# Descrição

O aplicativo foi escrito em kotlin e o projeto está localizado na pasta **BankApp**.
Há uma apk para download na parte de releases.
Para executar via Android Studio, basta clonar o repositório e executar.

### Pré-requisitos:
```
Android 4.4 ou superior.
```

### Segurança
No arquivo 'gradle.properties' é definida uma string *KEY_PASSWORD* para criptografar dados sensíveis do usuário ao  salvar localmente no aparelho.

### Testes
Foram implementados testes validando os retornos de chamadas de endpoints.

### Bibliotecas
* [Retrofit](https://square.github.io/retrofit/) - Consumo dos endpoints
* [Gson](https://github.com/google/gson/) - Conversão de JSON para objetos
* [Mockito](https://github.com/nhaarman/mockito-kotlin) - Testes unitários
* [Lottie](https://github.com/airbnb/lottie-android) - Animações
* [Logger](https://github.com/orhanobut/logger) - Otimização da visualização de logs
* [Fast-Android-Networking](https://github.com/amitshekhariitbhu/Fast-Android-Networking) - Auxilio de requisições web
