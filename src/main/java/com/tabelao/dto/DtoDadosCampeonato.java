package com.tabelao.dto;

import com.tabelao.model.Equipe;

import java.time.LocalDate;
import java.util.List;

public record DtoDadosCampeonato (
        String nomeCampeonato,
        List<DtoEquipe> equipes,
        String cidadeTime,
        String cluster,
        boolean sortear,
        int qtdeGrupos, //0 e 1 para grupo unico, 2 ou mais para multiplos gruops, de acordo com a qtde informada
        int turnosDentroDoGrupo,
        int turnosEntreGrupos,
        boolean tabelaOtimizada,
        int maxJogosPorDia, //tirar. requisito atendido pela variavel "semana"
        int minDiasDescanso,
        LocalDate diaDeInicio,
        List<DtoSemana> semana
) {}
