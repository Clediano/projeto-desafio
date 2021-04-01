package com.evoluum.desafio.desafio.interfaces;

import com.evoluum.desafio.desafio.domain.models.Municipio;
import com.evoluum.desafio.desafio.domain.repository.LocalidadeRepository;
import com.evoluum.desafio.desafio.interfaces.converter.Converter;
import com.evoluum.desafio.desafio.interfaces.dto.MunicipioIdDto;
import com.evoluum.desafio.desafio.interfaces.dto.MunicipioResumoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConverterTest {

    private static final Integer CODIGO_MUN_MARAVILHA_SC = 4210506;

    @Autowired
    private LocalidadeRepository repository;

    @Test
    void testarConverterParaMunicipioResumoDto() {

        Municipio municipio = this.buscarMunicipioDeMaravilha();

        if (municipio != null) {
            MunicipioResumoDto municipioMaravilha = Converter.converterParaMunicipioResumoDto(municipio);
            MunicipioResumoDto municipioModelo = this.criarMunicipioResumoDto();

            assertEquals(municipioMaravilha.getIdEstado(), municipioModelo.getIdEstado());
            assertEquals(municipioMaravilha.getSiglaEstado(), municipioModelo.getSiglaEstado());
            assertEquals(municipioMaravilha.getRegiaoNome(), municipioModelo.getRegiaoNome());
            assertEquals(municipioMaravilha.getNomeCidade(), municipioModelo.getNomeCidade());
            assertEquals(municipioMaravilha.getNomeMesorregiao(), municipioModelo.getNomeMesorregiao());
            assertEquals(municipioMaravilha.getNomeFormatado(), municipioModelo.getNomeFormatado());
        }
    }

    @Test
    void testarConverterParaMunicipioIdDto() {
        Municipio municipio = this.buscarMunicipioDeMaravilha();

        if (municipio != null) {
            MunicipioIdDto municipioMaravilha = Converter.converterParaMunicipioIdDto(municipio);
            MunicipioIdDto municipioModelo = this.criarMunicipioIdDto();

            assertEquals(municipioMaravilha.getId(), municipioModelo.getId());
        }
    }

    private MunicipioResumoDto criarMunicipioResumoDto() {
        return new MunicipioResumoDto(
                42,
                "SC",
                "Maravilha",
                "Maravilha",
                "Oeste Catarinense",
                "Maravilha/SC"
        );
    }

    private MunicipioIdDto criarMunicipioIdDto() {
        return new MunicipioIdDto(CODIGO_MUN_MARAVILHA_SC);
    }

    private Municipio buscarMunicipioDeMaravilha() {
        List<Municipio> municipios = repository.buscarMunicipiosPorEstado("SC");
        Optional<Municipio> municipio = municipios
                .stream()
                .filter(mun -> mun.getNome().equalsIgnoreCase("Maravilha"))
                .findFirst();

        return municipio.orElse(null);
    }
}
