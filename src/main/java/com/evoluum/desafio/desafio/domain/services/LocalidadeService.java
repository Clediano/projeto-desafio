package com.evoluum.desafio.desafio.domain.services;

import com.evoluum.desafio.desafio.domain.models.Municipio;

import java.util.List;

public interface LocalidadeService {
    List<Municipio> buscarMunicipioPeloNome(String nomeMunicipio);

    List<Municipio> buscarMunicipiosPelaUF(String UF);

    byte[] gerarCSVDosMunicipiosPorUF(String UF);
}
