package com.auroram.desafio_alura.logica;

import com.auroram.desafio_alura.modelos.MoedasJSON;

import java.util.Optional;
import java.util.OptionalDouble;

public class Conversao {
    private Optional<String> moedaOriginal;
    private Optional<String> moedaDestino;
    private OptionalDouble valorOriginal;

    public OptionalDouble getValorOriginal() {
        return valorOriginal;
    }

    public Optional<String> getMoedaOriginal() {
        return moedaOriginal;
    }

    public Optional<String> getMoedaDestino() {
        return moedaDestino;
    }

    public Conversao(OptionalDouble valorOriginal, Optional<String> moedaOriginal, Optional<String> moedaDestino) {
        this.valorOriginal = valorOriginal;
        this.moedaOriginal = moedaOriginal;
        this.moedaDestino = moedaDestino;
    }

    public OptionalDouble Converta(OptionalDouble quantidadeMoeda, Optional<MoedasJSON> moedasJSON) {
        double valorMoedaDestino = moedasJSON.map(
                json ->
                    switch (this.moedaDestino.stream().findFirst().orElse("")) {
                        case "ARS" -> json.getARS();
                        case "BRL" -> json.getBRL();
                        case "USD" -> json.getUSD();
                        case "GBP" -> json.getGBP();
                        case "PYG" -> json.getPYG();
                        case "EUR" -> json.getEUR();
                        default -> 0.0;
                    }
        ).stream().findFirst().orElse(0.0);

        return OptionalDouble.of(
                valorMoedaDestino * quantidadeMoeda.stream().findFirst().orElse(0.0)
        );

    }
}
