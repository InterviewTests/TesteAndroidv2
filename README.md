# Bank App

Para rodar o aplicativo é necessário apenas um Android (emulador ou device) com API >= 19. O aplicativo oferece uma tela de login onde o usuário pode logar com a sua conta para ver seu extrato bancário. Caso o usário digite uma senha ou login invaildos o apicativo exibie uma mensagem e monitora os campos, para quando o usuário corrigir os erros a mensagem desaparecer.

Apos o login ser concluido o usuário é levado à uma tela onde são apresentadas informações de sua conta e sua movimentação financeira. Caso alguém erro acontece no processo de baixar as moviemtações do usuário o aplicativo tenta cinco vzzes, em um intervalo de dois segundos refazer o request, caso todas as tentativas falhem uma mensagem de erro é exibida.

As credenciais do usuário são criptografadas e salvas em arquivo interno do aplicativo, caso o usuário desloge da aplicação o arquivo é deletado.

## Tecnologias
- [x] [RxJava2](https://github.com/ReactiveX/RxAndroid/tree/2.x)
- [x] [Koin](https://github.com/InsertKoinIO/koin)
- [x] [ViewModel/LiveData](https://developer.android.com/topic/libraries/architecture)
- [x] [Retrofit](https://square.github.io/retrofit/)
- [x] [Mockk](https://mockk.io/)

