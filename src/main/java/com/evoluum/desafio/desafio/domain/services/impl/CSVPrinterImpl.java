package com.evoluum.desafio.desafio.domain.services.impl;

import com.evoluum.desafio.desafio.domain.repository.ibge.LocalidadeRepositoryImpl;
import com.evoluum.desafio.desafio.domain.services.Printer;
import com.evoluum.desafio.desafio.exception.ErroInternoException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CSVPrinterImpl implements Printer {

    private static final Logger LOG = Logger.getLogger(LocalidadeRepositoryImpl.class.getName());

    public byte[] gerarCSV(String[] cabecalho, List<List<String>> conteudo) {
        StringBuilder csv = new StringBuilder();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(byteArray, StandardCharsets.UTF_8);

        this.setCSVHeader(cabecalho, csv);
        this.setCSVContent(conteudo, csv);

        try {
            writer.write(csv.toString());
            writer.flush();
            writer.close();
        } catch (IOException exception) {
            LOG.log(Level.SEVERE, "Exceção em gerarCSV(): " + exception);
            throw new ErroInternoException("Ocorreu um erro ao gerar o arquivo CSV");
        }
        return byteArray.toByteArray();
    }

    private void setCSVHeader(String[] header, StringBuilder csv) {
        if(header != null) {
            StringJoiner result = new StringJoiner(",");
            Arrays.asList(header).forEach(result::add);
            csv.append(result).append("\n");
        }
    }

    private void setCSVContent(List<List<String>> content, StringBuilder csv) {
        if(content != null) {
            StringBuilder result = new StringBuilder();

            for (List<String> line : content) {
                StringJoiner newLine = new StringJoiner(",");
                line.forEach(newLine::add);

                result.append(newLine).append("\n");
            }
            csv.append(result);
        }
    }
}
