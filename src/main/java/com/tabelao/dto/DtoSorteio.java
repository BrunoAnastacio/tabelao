package com.tabelao.dto;

import com.tabelao.model.Equipe;
import com.tabelao.model.Grupo;

import java.util.List;
public record DtoSorteio(
        List<Equipe> equipes,
        int qtdeGrupos     // Quantidade de grupos
) {}
