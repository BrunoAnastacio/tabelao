package com.tabelao.dto;

import com.tabelao.model.Grupo;
import com.tabelao.model.Rodada;
import com.tabelao.model.Tabela;

import java.util.List;

public record DtoResponseCampeonatoCriado(
        List<Grupo> grupos,
        List<Rodada> rodadas
) {
}
