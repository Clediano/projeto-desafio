package com.evoluum.desafio.desafio.interfaces;

import com.evoluum.desafio.desafio.domain.repository.LocalidadeRepository;
import com.evoluum.desafio.desafio.exception.RequisicaoException;
import com.evoluum.desafio.desafio.interfaces.dto.MunicipioIdDto;
import com.evoluum.desafio.desafio.interfaces.dto.MunicipioResumoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    private static final Integer CODIGO_MUN_MARAVILHA_SC = 4210506;
    private static final Integer CODIGO_MUN_MARAVILHA_AL = 2704609;

    @Autowired
    private LocalidadeRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void buscarCidadePorNome() {
        ResponseEntity<MunicipioIdDto[]> responseEntity = this.buscarCidadeDeMaravilhaPorNome();
        List<MunicipioIdDto> responseBody = Arrays.asList(Objects.requireNonNull(responseEntity.getBody(),
                "Corpo da requisição não pode ser null"));

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseBody.get(0).getId(), CODIGO_MUN_MARAVILHA_AL);
        assertEquals(responseBody.get(1).getId(), CODIGO_MUN_MARAVILHA_SC);
    }

    @Test
    void buscarDadosJson() {
        ResponseEntity<MunicipioResumoDto[]> responseEntity = this.buscarResumoDosMunicipiosDeSC();
        List<MunicipioResumoDto> responseBody = Arrays.asList(Objects.requireNonNull(responseEntity.getBody(),
                "Corpo da requisição não pode ser null"));

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertTrue(responseBody.size() > 0);
    }

    @Test
    void buscarDadosCsv() {
        ResponseEntity<byte[]> responseEntity = this.buscarResumoDosMunicipiosDeSCEmCSV();

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    private ResponseEntity<MunicipioIdDto[]> buscarCidadeDeMaravilhaPorNome() {
        try {
            return restTemplate.exchange("/v1/cidades?nomeCidade=maravilha", HttpMethod.GET, null, MunicipioIdDto[].class);
        } catch (Exception e) {
            throw new RequisicaoException("Ocorreu um erro ao buscar os municípios");
        }
    }

    private ResponseEntity<MunicipioResumoDto[]> buscarResumoDosMunicipiosDeSC() {
        try {
            return restTemplate.exchange("/v1/estados/SC/cidades/resumo/json", HttpMethod.GET, null, MunicipioResumoDto[].class);
        } catch (Exception e) {
            throw new RequisicaoException("Ocorreu um erro ao buscar os dados");
        }
    }

    private ResponseEntity<byte[]> buscarResumoDosMunicipiosDeSCEmCSV() {
        try {
            return restTemplate.exchange("/v1/estados/SC/cidades/resumo/csv", HttpMethod.GET, null, byte[].class);
        } catch (Exception e) {
            throw new RequisicaoException("Ocorreu um erro ao buscar os dados");
        }
    }
}
