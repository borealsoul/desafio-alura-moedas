package com.auroram.desafio_alura.logica;

import com.auroram.desafio_alura.modelos.MoedasJSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapeadorJSON {
    ObjectMapper mapeador = new ObjectMapper();

    // Mapeie(Optional<String> JSON) -> Optional<MoedaJSON
    // -- Mapeia o JSON em String e retorna já deserializado em MoedasJSON.
    public Optional<MoedasJSON> Mapeie(Optional<String> JSON) {
        try {
            return Optional.of(
                    mapeador.readValue(
                        Stream.of(JSON)                         // Converte Optional<String> para String.
                                .flatMap(Optional::stream)
                                .collect(Collectors.joining()),
                        MoedasJSON.class
                    )
            ); } catch (JsonProcessingException e) {
            return Optional.empty();
            // throw new RuntimeException(e);
        }
    }
}
