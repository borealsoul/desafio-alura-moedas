package com.auroram.desafio_alura.logica;

import com.auroram.desafio_alura.logica.Conversao;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

import static java.util.Optional.empty;

public class InterfaceTerminal {
    Scanner leitor = new Scanner(System.in);
    public Optional<String> PerguntaMoedaOriginal() {
        System.out.println("Insira a moeda origem: ");
        return InterageUsuario();
    }

    public Optional<String> PerguntaMoedaDestino() {
        System.out.println("Insira a moeda de destino: ");
        return InterageUsuario();
    }

    public Optional<String> InterageUsuario() {

        System.out.println("""
                1 - Peso Argentino (ARS)
                2 - Real Brasileiro (BRL)
                3 - Dólar Estadunidense (USD)
                4 - Libra Esterlina (GBP)
                5 - Guaraní Paraguaio (PYG)
                6 - Euro (EUR)"""
        );

        try {
            return Optional.of(
                    switch (leitor.nextInt()) {
                        case 1 -> "ARS";
                        case 2 -> "BRL";
                        case 3 -> "USD";
                        case 4 -> "GBP";
                        case 5 -> "PYG";
                        case 6 -> "EUR";
                        default -> InterageUsuario().stream().findFirst().orElse("");
                    }
            );
        } catch (InputMismatchException e) {
            System.out.println("Valor inválido, não é possível continuar.");
            return Optional.empty();
        }
    }

    public OptionalDouble PerguntaValorOrigem() {
        System.out.println("Qual o valor que deseja converter?");
        try {
            return OptionalDouble.of(leitor.nextDouble());
        } catch (NumberFormatException | InputMismatchException e) {
            return OptionalDouble.empty();
        }
    }

    public void ExibeResultado(OptionalDouble valorOriginal,
                               Optional<String> moedaOriginal,
                               Optional<String> moedaDestino,
                               OptionalDouble resultado) {
        System.out.println("");
        System.out.println("O resultado de " + Optional.ofNullable(valorOriginal).get().orElse(0.0) + " " + Optional.ofNullable(moedaOriginal).get().orElse("ERRO") + " para " + Optional.ofNullable(moedaDestino).get().orElse("ERRO") + " é igual a: ");
        System.out.println(Optional.ofNullable(moedaDestino).get().orElse("ERRO") + " " + Optional.ofNullable(resultado).get().orElse(0.0));
    }
}
