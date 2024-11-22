package com.tabelao.dto;

import com.tabelao.model.Equipe;

import java.util.List;
public record DtoSorteio(
        List<Equipe> equipes,
        int qtdeGrupos     // Quantidade de grupos
) {}
