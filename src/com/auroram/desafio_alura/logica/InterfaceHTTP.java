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
    public Optional<String> RequisitaHTTP(Optional<String> URL, Optional<String> chaveAPI) throws TimeoutException {
        try (HttpClient clienteHTTP = newHttpClient()) {
            HttpRequest pedidoHTTP = HttpRequest.newBuilder()
                    .uri(URI.create(
                            Stream.concat(URL.stream(), chaveAPI.stream()).collect(Collectors.joining())
                    ))
                    .build();
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
                throw new RuntimeException(e);
            }
        }
    }
}
