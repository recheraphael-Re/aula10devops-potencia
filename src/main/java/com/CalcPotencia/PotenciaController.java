package com.CalcPotencia;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/potencia")
public class PotenciaController {
    private final CalcPotencia calculadora = new CalcPotencia();

    @GetMapping
    public PotenciaResposta calcular(@RequestParam double tensao, @RequestParam double corrente) {
        double potencia = calculadora.calcularPotencia(tensao, corrente);
        if (!Double.isFinite(tensao) || !Double.isFinite(corrente) || !Double.isFinite(potencia)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe valores finitos para o calculo.");
        }
        return new PotenciaResposta(tensao, corrente, potencia, "W");
    }

    @PostMapping
    public PotenciaResposta calcular(@Valid @RequestBody PotenciaRequisicao requisicao) {
        return calcular(requisicao.tensao(), requisicao.corrente());
    }

    public record PotenciaRequisicao(@NotNull Double tensao, @NotNull Double corrente) { }

    public record PotenciaResposta(double tensao, double corrente, double potencia, String unidade) { }
}
