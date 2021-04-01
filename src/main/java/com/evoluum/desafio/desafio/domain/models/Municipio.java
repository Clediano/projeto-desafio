package com.evoluum.desafio.desafio.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Municipio {
    private Integer id;
    private String nome;
    private Microrregiao microrregiao;
    @JsonProperty("regiao-imediata")
    private RegiaoImediata regiaoImediata;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Microrregiao getMicrorregiao() {
        return microrregiao;
    }

    public RegiaoImediata getRegiaoImediata() {
        return regiaoImediata;
    }

    @Override
    public String toString() {
        return nome + "/" + microrregiao.getMesorregiao().getUF().getSigla();
    }
}
