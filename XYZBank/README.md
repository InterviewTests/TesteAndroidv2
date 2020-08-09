Estudo de Caso para uma tela de login de usuario e accesso a uma conta de um banco imaginario.    
    
Empresa R. M. Sistemas Web e Mobiles    
    
1 - Analize do problema.    
      
1.1 - preparado a plataforma de trabalho para obter o acesso para a API.    
1.1.1 - Instalacao e configuracao do Postman.    
1.1.2 - Teste dos diversos snipets de requisicao para a API oferecidos pela Collection Mockada via Postmam.    
1.1.3 - Escolhido o Framework OkHttp Client como snipet para as requisicoes junto a API remota.    
    
2 - Criacao de um prototipo inicial.    
    
2.1 - Escolhido o template "Login Activity" do Android Studio.    
2.2 - Escolhido o teste Espresso Unit Test (JUnit) por ja ser embutido no template do Android Studio e por ser    
      mais facil de manejar diretamente os dados da GUI.    
2.3 - Implementado o parser para receber os resultados da requisicao POST para a API.    
2.4 - Modificado a tela do template para parecer o maximo possivel ao do design proposto no estudo de caso.    
2.5 - Implementado o teste expresso para os diversos complentes clicaveis e editaveis da UI.    
2.6 - Implementado o metodo para a receber os dados da requsicao GET para a API.    
2.7 - Testado a requisicao remota.    
2.8 - Criado o lyout para receber os dados enviados pela API devidamente tratados e formatados.    
2.9 - Criado o adapter para exibir os referidos dados em uma lista conforme o design proposto no estudode caso.    
2.10 - Testado o envio da requiscao e os controles da UI.    
2.11 - Testes finalizados e todos funcionando a contento.   
    
3 - Implementado e Melhorado a GUI    
    
3.1 - Melhorado a Vizualizacao dos dados na UI.    
3.2 - Criado uma versao em portugues do Brasil para as mensagens de tela que por padrao foram configuradas em ingles.    
3.3 - Criado uma simples tela de splash screen.    
3.4 - projeto postado no github como um pull request numa branch de nome "XYZBankApp".    
    
O projeto XYZBank foi desenvolvido em Kotlin versao "1.3.72" com Android Studio "4.2 Canary 7" e com a mais    
alta versao do gradle "build:gradle:4.2.0-alpha07".    
    
Estao sendo enviados como pull request, a pasta "XYZBank" contendo:     
   - arquivos de configuracao do Android Studio, um screencast video do programa em execucao;    
    
   - a pasta "app" contendo:    
     os fontes "*.kt" do pacote '../src/main/java/com.xyzbank.xyzbankapp/* e os testes    
     os arquivos de layout e resources de tela "*.xml", na pasta '../src/main/res/*';   
     os fontes "*.kt" dos testes nas saus respectivas pastas    
    
   - a pasta gradle para referencia local.    
    
   - a pasta 'outputs/apk/', com um pacote de bytecodes no formato "debug.apk"    
    
XYZBank/    
app/*    
gradle/*    
outputs/apk/*    
build.gradle    
gradle.properties    
gradlew    
gradlew.bat    
local.properties    
README.md - this file    
settings.gradle    
XYZBank.webm    
    
    
Conforme proposto no estudo de caso, o app foi criado para Android a partir da versao 4.4 (19 - KitKat)    
Para compilar uma versao desse app:    
   - atualize o Android Studio para a ultima versao.    
   - Atualize as entradas do Android SDK desde a versao 19 ate a "major" versao real: 30.    
   - Atualize oplugin do Kotlin para a ultima versao.    
   - Atualize o gradle no projeto para a ultima versao.    
