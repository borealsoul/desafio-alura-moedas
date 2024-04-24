package com.auroram.desafio_alura.logica;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.net.http.HttpClient.newHttpClient;

public class InterfaceHTTP {

    // RequisitaHTTP(Optional<String> URL,
    //               Optional<String> chaveAPI,
    //               Optional<String> moedaUsuario) -> Optional<String>
    //
    public Optional<String> RequisitaHTTP(Optional<String> URL,
                                          Optional<String> chaveAPI,
                                          Optional<String> moedaUsuario) {
        if (moedaUsuario.isPresent()) {                             // Se moedaUsuario estiver presente
            Optional<String> finalURL = Optional.of(
                    Stream.of(chaveAPI, moedaUsuario)               // Concatena chaveAPI e moedaUsuario
                            .flatMap(Optional::stream)
                            .collect(Collectors
                                    .joining("/latest/"))   // Com o delimitador "/latest/"
            );

            try (HttpClient clienteHTTP = newHttpClient()) {            // Tente criar um cliente HTTP em clienteHTTP / Caso não consiga (TimeoutException), retorna Optional<Empty>
                HttpRequest pedidoHTTP = HttpRequest.newBuilder()       // Peça em HTTP o retorno de
                        .uri(URI.create(Stream.of(URL, finalURL)        // Concatena a URL: https://stackoverflow.com/questions/46473098/concatenate-two-or-more-optional-string-in-java-8
                                        .flatMap(Optional::stream)
                                        .collect(Collectors.joining())
                        )).build();

                return Optional.of(
                    clienteHTTP.sendAsync(pedidoHTTP,               // Envia o pedidoHTTP em Async pelo clienteHTTP
                                    HttpResponse
                                            .BodyHandlers
                                            .ofString())
                            .thenApply(HttpResponse::body)
                            .get(5, TimeUnit.SECONDS));     // Solta um TimeoutException se a resposta demorar mais de 5sec.

            } catch (TimeoutException | ExecutionException | InterruptedException e) {
                System.out.println("Ocorreu um erro " + e + "com a interfaceHTTP.java.");
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
