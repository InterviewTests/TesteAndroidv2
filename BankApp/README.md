# Bank App

### Testes Unitários com JUnit
Para a execução dos testes unitários via gradle: `./gradlew testDebugUnitTest`

### Testes Instrumentados
- Conectar um device em modo debug
- Desabilitar as animações e transições (dependência do Espresso)
- Executar a task com `./gradlew connectedAndroidTest`

### Cobertura de testes com Jacoco
* Para executar o relatório de cobertura de testes, basta executar:
`./gradlew createDebugCoverageReport`

O relatório será gerado em `BankApp/app/build/reports/coverage/debug/index.html`


