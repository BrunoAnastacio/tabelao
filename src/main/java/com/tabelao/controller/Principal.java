package com.tabelao.controller;

import com.tabelao.dto.DtoDadosCampeonato;
import com.tabelao.dto.DtoEquipe;
import com.tabelao.dto.DtoResponseCampeonatoCriado;
import com.tabelao.dto.DtoSorteio;
import com.tabelao.model.*;
import com.tabelao.util.enums.TiposDeTabela;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/campeonato", produces ="application/json", consumes = "application/json")
public class Principal {

    @PostMapping("/gerar")
    @ResponseBody
    public DtoResponseCampeonatoCriado gerarCampeonato(@RequestBody DtoDadosCampeonato request){
        List<Equipe> equipes = new ArrayList<>();
        for (DtoEquipe dtoEquipe:request.equipes()) {
            Equipe equipe = new Equipe(dtoEquipe);
            equipes.add(equipe);
        }
        Campeonato campeonato = new Campeonato(
                request.nomeCampeonato(),
                equipes,
                request.qtdeGrupos(),
                request.turnosDentroDoGrupo(),
                request.turnosEntreGrupos()
        );

        //gerando campeonato
        List<Grupo> grupos = campeonato.sortearGrupos();
        List<Rodada> rodadas = campeonato.gerarTabela();
        DtoResponseCampeonatoCriado campeonatoCriado = new DtoResponseCampeonatoCriado(grupos, rodadas);

        return campeonatoCriado;

    }

    @PostMapping("/sortear")
    @ResponseBody
    public List<Grupo> sortear(@RequestBody DtoSorteio request){
        Campeonato campeonato = new Campeonato();
        return campeonato.sortearGrupos();
    }
}


