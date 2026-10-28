# Aula 10 - Exercicio 2: Potencia eletrica

Projeto Maven com Java 21, Spring Boot e JUnit 5. A classe `CalcPotencia` implementa
`calcularPotencia(double tensao, double corrente)` usando a formula **P = V x I**.
Tensao em Volts (V), corrente em Amperes (A) e resultado em Watts (W).

## Executar

### API Spring Boot e Thunder Client

Inicie o servidor no terminal da pasta do projeto:

```powershell
mvn spring-boot:run
```

Mantenha esse terminal aberto. A API atende em `http://localhost:8080`.
Para encerrar, pressione `Ctrl+C`.

No VS Code, abra o Thunder Client e clique em **New Request**:

1. Selecione **GET**.
2. Informe `http://localhost:8080/api/potencia?tensao=220&corrente=5`.
3. Clique em **Send**. O resultado esperado é **200 OK**:

```json
{
  "tensao": 220.0,
  "corrente": 5.0,
  "potencia": 1100.0,
  "unidade": "W"
}
```

Para enviar os dados no corpo da requisição:

1. Selecione **POST** e use `http://localhost:8080/api/potencia`.
2. Em **Body > JSON**, informe:

```json
{
  "tensao": 220,
  "corrente": 5
}
```

3. Use o cabeçalho `Content-Type: application/json` e clique em **Send**.
   A resposta é a mesma do GET.

Os dois valores são obrigatórios e devem ser números finitos. Use ponto para
decimais (ex.: `2.3`). Campos ausentes, texto no lugar de números e resultados
infinitos retornam **400 Bad Request**. Zero e valores negativos são aceitos,
preservando a fórmula original.

Também é possível testar pelo PowerShell:

```powershell
Invoke-RestMethod 'http://localhost:8080/api/potencia?tensao=220&corrente=5'
Invoke-RestMethod -Method Post -Uri 'http://localhost:8080/api/potencia' -ContentType 'application/json' -Body '{"tensao":220,"corrente":5}'
```

Se a porta estiver ocupada, execute `mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"`
e troque a porta nas URLs. Se ocorrer conexão recusada, confira se o servidor
terminou de iniciar e se o terminal continua aberto.

Para gerar e executar o JAR com servidor embutido:

```powershell
mvn clean package
java -jar target/calc-potencia-1.0-SNAPSHOT.jar
```

### Programa original e testes

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

Os quatro testes originais verificam o exemplo do enunciado, valores decimais,
corrente zero e tensao zero. Outros quatro testes verificam a API GET/POST,
campos ausentes e valores inválidos. Resultado esperado: 8 testes, sem falhas ou erros.
Relatorios ficam em `target/surefire-reports`.

## GitHub Actions

O workflow fica em `.github/workflows/testes.yml`, na raiz deste repositorio.
Executa `mvn clean test` em pushes e pull requests, e permite execucao manual.
Depois de publicar as alteracoes, consulte a aba **Actions** do GitHub para
acompanhar a pipeline e obter o print solicitado pelo professor.
