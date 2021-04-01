package com.evoluum.desafio.desafio.domain.services;

import com.evoluum.desafio.desafio.domain.models.Municipio;
import com.evoluum.desafio.desafio.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LocalidadeServiceImplTest {

    private static final Integer CODIGO_MUN_MARAVILHA_SC = 4210506;

    @Autowired
    private LocalidadeService localidadeService;

    @Test
    void testarBuscarMunicipiosPeloNome() {
        List<Municipio> municipios = localidadeService.buscarMunicipioPeloNome("Maravilha");

        assertTrue(municipios.get(1).getId().equals(CODIGO_MUN_MARAVILHA_SC));
        assertThrows(RecursoNaoEncontradoException.class, () -> localidadeService.buscarMunicipioPeloNome("Nome de cidade que não existe"));
        assertThrows(RecursoNaoEncontradoException.class, () -> localidadeService.buscarMunicipioPeloNome(null));
    }

    @Test
    void testarBuscarResumoDosMunicipios() {
        List<Municipio> municipiosDeSC = localidadeService.buscarMunicipiosPelaUF("SC");
        assertTrue(municipiosDeSC.size() > 0);

        List<Municipio> municipiosNullos = localidadeService.buscarMunicipiosPelaUF(null);
        assertEquals(municipiosNullos.size(), 0);
    }

    @Test
    void testarGerarCSVDosMunicipiosPorUF() {
        byte[] arquivoComOsMunicipios = localidadeService.gerarCSVDosMunicipiosPorUF("SC");
        assertTrue(arquivoComOsMunicipios.length > 0);

        byte[] arquivoComCabecalhoApenas = localidadeService.gerarCSVDosMunicipiosPorUF(null);
        assertEquals(arquivoComCabecalhoApenas.length, 73); //bytes referentes ao cabeçalho do arquivo
    }
}
