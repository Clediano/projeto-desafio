package com.evoluum.desafio.desafio.domain.services;

import java.util.List;

public interface Printer {
    byte[] gerarCSV(String[] cabecalho, List<List<String>> conteudo);
}
