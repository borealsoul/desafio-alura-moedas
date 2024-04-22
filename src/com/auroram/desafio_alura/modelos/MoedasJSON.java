package com.auroram.desafio_alura.modelos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoedasJson {
    private double ARS;
    private double BRL;
    private double USD;
    private double GBP;
    private double PYG;
    private double EUR;

    @JsonProperty("conversion_rates")
    private void desempacotaValores(Map<String, String> conversion_rates) {
        ARS = Double.parseDouble(conversion_rates.get("ARS"));
        BRL = Double.parseDouble(conversion_rates.get("BRL"));
        USD = Double.parseDouble(conversion_rates.get("USD"));
        GBP = Double.parseDouble(conversion_rates.get("GBP"));
        PYG = Double.parseDouble(conversion_rates.get("PYG"));
        EUR = Double.parseDouble(conversion_rates.get("EUR"));
    }
}
