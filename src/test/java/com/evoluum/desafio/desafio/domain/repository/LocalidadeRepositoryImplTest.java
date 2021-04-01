package com.evoluum.desafio.desafio.domain.repository;

import com.evoluum.desafio.desafio.domain.models.Municipio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LocalidadeRepositoryImplTest {

    @Autowired
    private LocalidadeRepository repository;

    @Test
    void testarABuscaDosMunicipios() {
        List<Municipio> municipios = repository.buscarTodosOsMunicipios();
        assertTrue(municipios.size() > 0);
    }

    @Test
    void testarABuscaDeMunicipiosPorEstado() {
        List<Municipio> municipiosDeSC = repository.buscarMunicipiosPorEstado("SC");
        assertTrue(municipiosDeSC.size() > 0);

        List<Municipio> municipiosNaoExistentes = repository.buscarMunicipiosPorEstado("AA");
        assertTrue(municipiosNaoExistentes.isEmpty());
    }
}
