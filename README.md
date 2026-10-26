# Aula 10 - Exercicio 2: Potencia eletrica

Projeto Maven com Java 21 e JUnit 5. A classe `CalcPotencia` implementa
`calcularPotencia(double tensao, double corrente)` usando a formula **P = V x I**.
Tensao em Volts (V), corrente em Amperes (A) e resultado em Watts (W).

## Executar

No terminal, dentro de `aula10devops_potencia`:

```powershell
mvn clean test
mvn package
java -cp target/classes com.CalcPotencia.CalcPotencia
```

Saida do programa para 220 V e 5 A:

```text
Potencia eletrica: 1100.0 W
```

Os quatro testes verificam o exemplo do enunciado, valores decimais,
corrente zero e tensao zero. Resultado esperado: 4 testes, sem falhas ou erros.
Relatorios ficam em `target/surefire-reports`.

## GitHub Actions

O workflow fica em `.github/workflows/testes.yml`, na raiz deste repositorio.
Executa `mvn clean test` em pushes e pull requests, e permite execucao manual.
Depois de publicar as alteracoes, consulte a aba **Actions** do GitHub para
acompanhar a pipeline e obter o print solicitado pelo professor.
