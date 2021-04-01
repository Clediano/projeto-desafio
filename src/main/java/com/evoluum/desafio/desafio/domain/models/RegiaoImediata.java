package com.evoluum.desafio.desafio.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegiaoImediata {
    private Integer id;
    private String nome;
    @JsonProperty("regiao-intermediaria")
    private RegiaoIntermediaria regiaoIntermediaria;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public RegiaoIntermediaria getRegiaoIntermediaria() {
        return regiaoIntermediaria;
    }
}
