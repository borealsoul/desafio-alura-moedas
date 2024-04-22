package com.auroram.desafio_alura.logica;

import com.auroram.desafio_alura.modelos.MoedasJSON;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class Conversao {
    private String moedaOriginal;
    private String moedaDestino;

    public Conversao(String moedaOriginal, String moedaDestino) {
        this.moedaOriginal = moedaOriginal;
        this.moedaDestino = moedaDestino;
    }

    public OptionalDouble Converta(double quantidadeMoeda, Optional<MoedasJSON> moedasJSON) {
        double valorMoedaDestino = moedasJSON.map(
                json ->
                    switch (this.moedaDestino) {
                        case "ARS" -> json.getARS();
                        case "BRL" -> json.getBRL();
                        case "USD" -> json.getUSD();
                        case "GBP" -> json.getGBP();
                        case "PYG" -> json.getPYG();
                        case "EUR" -> json.getEUR();
                        default -> 0.0;
                    }
        ).stream().findFirst().orElse(0.0);

        return OptionalDouble.of(valorMoedaDestino * quantidadeMoeda);

    }
}
