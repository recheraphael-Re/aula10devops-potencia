package com.CalcPotencia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcPotenciaTest {

    private final CalcPotencia calculadora = new CalcPotencia();

    @Test
    void deveCalcularPotenciaDoExemplo() {
        assertEquals(1100.0, calculadora.calcularPotencia(220, 5), 0.000001);
    }

    @Test
    void deveCalcularPotenciaComValoresDecimais() {
        assertEquals(278.3, calculadora.calcularPotencia(121.0, 2.3), 0.000001);
    }

    @Test
    void deveRetornarZeroSemCorrente() {
        assertEquals(0.0, calculadora.calcularPotencia(220, 0), 0.000001);
    }

    @Test
    void deveRetornarZeroSemTensao() {
        assertEquals(0.0, calculadora.calcularPotencia(0, 5), 0.000001);
    }
}
