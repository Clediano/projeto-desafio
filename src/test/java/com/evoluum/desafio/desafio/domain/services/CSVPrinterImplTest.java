package com.evoluum.desafio.desafio.domain.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CSVPrinterImplTest {

    @Autowired
    private Printer printer;

    @Test
    void testarGeracaoDeArquivoCSV() {
        String[] cabecalho = {"nome", "sobrenome"};
        List<List<String>> conteudo = Arrays.asList(List.of("clediano", "estefenon"));

        byte[] bytesDoArquivo = printer.gerarCSV(cabecalho, conteudo);
        assertTrue(bytesDoArquivo.length > 0);

        byte[] bytesDoArquivo2 = printer.gerarCSV(null, null);
        assertTrue(bytesDoArquivo2.length == 0);
    }

}
