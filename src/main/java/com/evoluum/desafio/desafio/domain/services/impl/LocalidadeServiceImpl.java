package com.evoluum.desafio.desafio.domain.services.impl;

import com.evoluum.desafio.desafio.domain.models.Municipio;
import com.evoluum.desafio.desafio.domain.repository.LocalidadeRepository;
import com.evoluum.desafio.desafio.domain.services.LocalidadeService;
import com.evoluum.desafio.desafio.domain.services.Printer;
import com.evoluum.desafio.desafio.domain.services.support.dto.MunicipioResumoDto;
import com.evoluum.desafio.desafio.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class LocalidadeServiceImpl implements LocalidadeService {

    private static final Logger LOG = Logger.getLogger(LocalidadeServiceImpl.class.getName());

    @Autowired
    private LocalidadeRepository localidadeRepository;

    @Autowired
    private Printer printer;

    public List<Municipio> buscarMunicipioPeloNome(String nomeMunicipio) {
        List<Municipio> municipios = localidadeRepository.buscarTodosOsMunicipios();
        List<Municipio> municipiosFiltrados = municipios
                .stream()
                .filter(mun -> mun.getNome().equalsIgnoreCase(nomeMunicipio)).collect(Collectors.toList());

        if (municipiosFiltrados.size() == 0) {
            LOG.warning("Nenhum município com o nome " + nomeMunicipio + " foi encontrado");
            throw new RecursoNaoEncontradoException("Nenhum município com este nome foi encontrado");
        }
        return municipiosFiltrados;
    }

    public List<Municipio> buscarMunicipiosPelaUF(String UF) {
        return localidadeRepository.buscarMunicipiosPorEstado(UF);
    }

    public byte[] gerarCSVDosMunicipiosPorUF(String UF) {
        List<Municipio> municipios = localidadeRepository.buscarMunicipiosPorEstado(UF);

        String[] cabecalho = {"idEstado", "siglaEstado", "regiaoNome", "nomeCidade", "nomeMesorregiao", "nomeFormatado"};
        List<List<String>> conteudo = new ArrayList<>();

        for (Municipio municipio : municipios) {
            MunicipioResumoDto resumo = MunicipioResumoDto.converterParaMunicipioResumoDto(municipio);
            conteudo.add(Arrays.asList(
                    resumo.getIdEstado().toString(),
                    resumo.getSiglaEstado(),
                    resumo.getRegiaoNome(),
                    resumo.getNomeCidade(),
                    resumo.getNomeMesorregiao(),
                    resumo.getNomeFormatado())
            );
        }
        return printer.gerarCSV(cabecalho, conteudo);
    }

}
