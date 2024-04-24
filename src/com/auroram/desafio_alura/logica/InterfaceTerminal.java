package com.auroram.desafio_alura.logica;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

// As funções "Pergunta*" todas ligam ao InterageUsuario().
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

    // InterageUsuario() -> Optional<String>
    // Imprime a lista de moedas,
    // E pede para o usuário digitar um número correspondente à moeda desejada.
    // Caso seja escolhido outro número, ele chama a função de novo.
    // E caso o usuário não digite um número, ele retorna Optional<Empty>.
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

    // PerguntaValorOrigem() -> OptionalDouble
    // Imprime ao usuário a pergunta de qual valor ele deseja para a moeda original.
    // Se houver qualquer erro na inserção de valor, retorna OptionalDouble<Empty>
    public OptionalDouble PerguntaValorOrigem() {
        System.out.println("Qual o valor que deseja converter?");
        try {
            return OptionalDouble.of(leitor.nextDouble());
        } catch (NumberFormatException | InputMismatchException e) {
            return OptionalDouble.empty();
        }
    }

    // ExibeResultado(OptionalDouble valorOriginal,
    //                Optional<String> moedaOriginal,
    //                Optional<String> moedaDestino,
    //                OptionalDouble resultado)
    //
    // Imprime a concatenação de todos esses parâmetros no formato:
    // O resultado de 10 BRL para USD é igual a:
    // USD 10
    public void ExibeResultado(OptionalDouble valorOriginal,
                               Optional<String> moedaOriginal,
                               Optional<String> moedaDestino,
                               OptionalDouble resultado) {
        System.out.println();
        System.out.println(
                "O resultado de "
                + Optional.ofNullable(valorOriginal).get().orElse(0.0)
                + " " + Optional.ofNullable(moedaOriginal).get().orElse("ERRO")
                + " para " + Optional.ofNullable(moedaDestino).get().orElse("ERRO")
                + " é igual a: "
        );
        System.out.println(
                Optional.ofNullable(moedaDestino).get().orElse("ERRO")
                        + " " + Optional.ofNullable(resultado).get().orElse(0.0)
        );
    }
}
