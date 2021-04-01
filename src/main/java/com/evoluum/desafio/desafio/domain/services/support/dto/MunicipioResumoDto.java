package com.evoluum.desafio.desafio.domain.services.support.dto;

import com.evoluum.desafio.desafio.domain.models.Estado;
import com.evoluum.desafio.desafio.domain.models.Mesorregiao;
import com.evoluum.desafio.desafio.domain.models.Municipio;
import com.evoluum.desafio.desafio.domain.models.RegiaoImediata;

public class MunicipioResumoDto {
    private Integer idEstado;
    private String siglaEstado;
    private String regiaoNome;
    private String nomeCidade;
    private String nomeMesorregiao;
    private String nomeFormatado;

    public MunicipioResumoDto(Integer idEstado, String siglaEstado, String regiaoNome, String nomeCidade, String nomeMesorregiao, String nomeFormatado) {
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

    public static MunicipioResumoDto converterParaMunicipioResumoDto(Municipio municipio) {
        Estado estado = municipio.getMicrorregiao().getMesorregiao().getUF();
        RegiaoImediata regiaoImediata = municipio.getRegiaoImediata();
        Mesorregiao mesorregiao = municipio.getMicrorregiao().getMesorregiao();

        return new MunicipioResumoDto(estado.getId(), estado.getSigla(), regiaoImediata.getNome(), municipio.getNome(), mesorregiao.getNome(), municipio.toString());
    }
}
