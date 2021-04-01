package com.evoluum.desafio.desafio.domain.repository.ibge;

import com.evoluum.desafio.desafio.domain.models.Municipio;
import com.evoluum.desafio.desafio.domain.repository.LocalidadeRepository;
import com.evoluum.desafio.desafio.exception.RequisicaoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class LocalidadeRepositoryImpl implements LocalidadeRepository {

    private static final Logger LOG = Logger.getLogger(LocalidadeRepositoryImpl.class.getName());

    @Value("${ibge.base_url}")
    private String baseUrl;

    @Override
    @Cacheable("municipios")
    public List<Municipio> buscarTodosOsMunicipios() {
        try {
            HttpEntity httpEntity = new HttpEntity(null, new HttpHeaders());
            ResponseEntity<Municipio[]> response = new RestTemplate().exchange(baseUrl + "/municipios", HttpMethod.GET, httpEntity, Municipio[].class);
            if (response.getBody() != null) {
                return Arrays.asList(response.getBody());
            }
            return null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Exceção em buscarTodosOsMunicipios(): " + e);
            throw new RequisicaoException("Ocorreu um erro ao buscar os municípios na API do IBGE");
        }
    }

    @Override
    public List<Municipio> buscarMunicipiosPorEstado(String UF) {
        try {
            HttpEntity httpEntity = new HttpEntity(null, new HttpHeaders());
            ResponseEntity<Municipio[]> response = new RestTemplate().exchange(baseUrl + "/estados/" + UF + "/municipios", HttpMethod.GET, httpEntity, Municipio[].class);
            if (response.getBody() != null) {
                return Arrays.asList(response.getBody());
            }
            return null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Exceção em buscarMunicipiosPorEstado(): " + e);
            throw new RequisicaoException("Ocorreu um erro ao buscar os municípios na API do IBGE");
        }
    }
}
