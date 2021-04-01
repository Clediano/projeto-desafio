package com.evoluum.desafio.desafio.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegiaoIntermediaria {
    private Integer id;
    private String nome;
    @JsonProperty("UF")
    private Estado UF;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getUF() {
        return UF;
    }
}
