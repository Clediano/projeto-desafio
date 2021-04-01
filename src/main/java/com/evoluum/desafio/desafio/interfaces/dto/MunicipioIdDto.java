package com.evoluum.desafio.desafio.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MunicipioIdDto implements Serializable {
    private Integer id;

    public MunicipioIdDto(@JsonProperty("id") Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
