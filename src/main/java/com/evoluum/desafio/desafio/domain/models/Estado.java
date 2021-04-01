package com.evoluum.desafio.desafio.domain.models;

public class Estado {

    private Integer id;
    private String sigla;
    private String nome;
    private Regiao regiao;

    public Integer getId() {
        return id;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public Regiao getRegiao() {
        return regiao;
    }
}
