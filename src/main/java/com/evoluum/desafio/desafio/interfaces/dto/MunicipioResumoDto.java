package com.evoluum.desafio.desafio.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MunicipioResumoDto implements Serializable {
    private Integer idEstado;
    private String siglaEstado;
    private String regiaoNome;
    private String nomeCidade;
    private String nomeMesorregiao;
    private String nomeFormatado;

    public MunicipioResumoDto(
            @JsonProperty("idEstado") Integer idEstado,
            @JsonProperty("siglaEstado") String siglaEstado,
            @JsonProperty("regiaoNome") String regiaoNome,
            @JsonProperty("nomeCidade") String nomeCidade,
            @JsonProperty("nomeMesorregiao") String nomeMesorregiao,
            @JsonProperty("nomeFormatado") String nomeFormatado
    ) {
        this.idEstado = idEstado;
        this.siglaEstado = siglaEstado;
        this.regiaoNome = regiaoNome;
        this.nomeCidade = nomeCidade;
        this.nomeMesorregiao = nomeMesorregiao;
        this.nomeFormatado = nomeFormatado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public String getRegiaoNome() {
        return regiaoNome;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public String getNomeMesorregiao() {
        return nomeMesorregiao;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }
}
