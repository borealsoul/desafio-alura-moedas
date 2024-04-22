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
    public Optional<String> RequisitaHTTP(Optional<String> URL, Optional<String> chaveAPI, Optional<String> moedaUsuario) {
        Optional<String> finalURL = Optional.of(Stream.of(chaveAPI, moedaUsuario).flatMap(Optional::stream).collect(Collectors.joining("/latest/")));
        try (HttpClient clienteHTTP = newHttpClient()) {
            HttpRequest pedidoHTTP = HttpRequest.newBuilder()
                    .uri(URI.create(
                            // https://stackoverflow.com/questions/46473098/concatenate-two-or-more-optional-string-in-java-8
                            Stream.of(
                                    URL,
                                    finalURL
                            ).flatMap(Optional::stream).collect(Collectors.joining())
                            // Stream.concat(URL.stream(), chaveAPI.stream(), moedaUsuario.stream()).collect(Collectors.joining())
                    )).build();
            clienteHTTP.sendAsync(pedidoHTTP, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();

            try {
                return Optional.of(clienteHTTP.sendAsync(
                        pedidoHTTP,
                        HttpResponse.BodyHandlers.ofString()
                ).thenApply(HttpResponse::body)
                        .get(5, TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException e) {
                return Optional.empty();
                //throw new RuntimeException(e);
            }
        } catch (TimeoutException e) {
            return Optional.empty();
        }
    }
}
