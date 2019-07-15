# Teste-Accenture

Aplicativo baseado em um sistema de banco.
* Desenvolvido na linguagem kotlin.
* Possui as telas (Login e Principal), após autenticar os dados na tela de Login, o aplicativo direcionará para a tela Principal.
* Nessa tela (Principal), será executado um endpoint para baixar os dados de Statement. Uma vez baixado, os dados serão exibidos na tela.
* Criado teste unitário (JUnitTest) para validar os dados inseridos nos campos User e Password.
* Os Dados são armazenados em SharedPreferences.
  * Escolhi esta opção:
    1. Rápido acesso aos dados.
    2. Possíbilidade de criptografia dos dados.

## Utilizado
```python
implementation 'com.squareup.retrofit2:retrofit:2.3.0'
implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
implementation 'com.pnikosis:materialish-progress:1.7'
implementation 'com.android.support:cardview-v7:28.0.0'
implementation 'com.android.support:recyclerview-v7:28.0.0'
```

## Clone
git clone https://github.com/ebdjesus/Teste-Accenture.git

#### Candidatura por: Accenture / Cunha&Petreche
