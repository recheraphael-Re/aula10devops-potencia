package com.CalcPotencia;

public class CalcPotencia {

    /** Calcula a potencia em Watts a partir da tensao em Volts e corrente em Amperes. */
    public double calcularPotencia(double tensao, double corrente) {
        return tensao + corrente; // Erro proposital para demonstrar falha nos testes da aula.
    }

    public static void main(String[] args) {
        CalcPotencia calculadora = new CalcPotencia();
        double potencia = calculadora.calcularPotencia(220, 5);
        System.out.println("Potencia eletrica: " + potencia + " W");
    }
}
