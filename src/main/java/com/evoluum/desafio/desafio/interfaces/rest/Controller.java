package com.evoluum.desafio.desafio.interfaces.rest;

import com.evoluum.desafio.desafio.domain.models.Municipio;
import com.evoluum.desafio.desafio.domain.services.LocalidadeService;
import com.evoluum.desafio.desafio.exception.RequisicaoException;
import com.evoluum.desafio.desafio.interfaces.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/v1")
public class Controller {

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());

    @Autowired
    private LocalidadeService localidadeService;

    @GetMapping("/cidades")
    public ResponseEntity<?> buscarCidadePorNome(@RequestParam String nomeCidade) {
        List<Municipio> municipios = localidadeService.buscarMunicipioPeloNome(nomeCidade);
        return new ResponseEntity<>(municipios.stream().map(Converter::converterParaMunicipioIdDto), HttpStatus.OK);
    }

    @GetMapping("/estados/{UF}/cidades/resumo/json")
    public ResponseEntity<?> buscarDadosJson(@PathVariable String UF) {
        this.validarSeUFInformadoExiste(UF);
        List<Municipio> municipios = localidadeService.buscarMunicipiosPelaUF(UF);
        return new ResponseEntity<>(municipios.stream().map(Converter::converterParaMunicipioResumoDto), HttpStatus.OK);
    }

    @GetMapping(value = "/estados/{UF}/cidades/resumo/csv")
    public ResponseEntity<?> buscarDadosCsv(@PathVariable String UF) {
        this.validarSeUFInformadoExiste(UF);
        byte[] arquivoCSV = localidadeService.gerarCSVDosMunicipiosPorUF(UF);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=municipios.csv");
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        return new ResponseEntity<>(arquivoCSV, headers, HttpStatus.OK);
    }

    private void validarSeUFInformadoExiste(String UF) {
        Stream<String> UFS = Stream.of(
                "RO","AC","AM","RR","PA","AP","TO","MA","PI","CE","RN","PB","PE","AL",
                "SE","BA","MG","ES","RJ","SP","PR","SC","RS","MS","MT","GO","DF");
        if(UFS.noneMatch(uf -> uf.equalsIgnoreCase(UF))) {
            LOG.warning("Estado com UF igual a " + UF + " não foi encontrado");
            throw new RequisicaoException("UF '" + UF + "' não existe. Verifique a sigla do estado e tente novamente");
        }
    }
}
