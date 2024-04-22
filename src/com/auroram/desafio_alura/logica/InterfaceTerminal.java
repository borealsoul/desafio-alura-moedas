package com.auroram.desafio_alura.logica;

import java.util.Optional;

public class InterfaceTerminal {
    public Optional<String> PerguntaMoedaOriginal() {
        System.out.println("Insira a moeda origem: ");
        return InterageUsuario();
    }
    public Optional<String> InterageUsuario() {

        return "a";
    }
}
