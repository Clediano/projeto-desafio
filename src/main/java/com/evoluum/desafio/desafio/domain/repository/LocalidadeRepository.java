package com.evoluum.desafio.desafio.domain.repository;

import com.evoluum.desafio.desafio.domain.models.Municipio;

import java.util.List;

public interface LocalidadeRepository {
    List<Municipio> buscarTodosOsMunicipios();
    List<Municipio> buscarMunicipiosPorEstado(String UF);
}
