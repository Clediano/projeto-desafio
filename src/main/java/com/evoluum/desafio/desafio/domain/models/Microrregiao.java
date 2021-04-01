package com.evoluum.desafio.desafio.domain.models;

public class Microrregiao {
    private Integer id;
    private String nome;
    private Mesorregiao mesorregiao;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Mesorregiao getMesorregiao() {
        return mesorregiao;
    }
}
