package com.evoluum.desafio.desafio.interfaces.converter;

import com.evoluum.desafio.desafio.domain.models.*;
import com.evoluum.desafio.desafio.interfaces.dto.MunicipioIdDto;
import com.evoluum.desafio.desafio.interfaces.dto.MunicipioResumoDto;

public class Converter {

    public static MunicipioResumoDto converterParaMunicipioResumoDto(Municipio municipio) {
        Estado estado = municipio.getMicrorregiao().getMesorregiao().getUF();
        RegiaoImediata regiaoImediata = municipio.getRegiaoImediata();
        Mesorregiao mesorregiao = municipio.getMicrorregiao().getMesorregiao();

        return new MunicipioResumoDto(estado.getId(), estado.getSigla(), regiaoImediata.getNome(), municipio.getNome(), mesorregiao.getNome(), municipio.toString());
    }

    public static MunicipioIdDto converterParaMunicipioIdDto(Municipio municipio) {
        return new MunicipioIdDto(municipio.getId());
    }
}
