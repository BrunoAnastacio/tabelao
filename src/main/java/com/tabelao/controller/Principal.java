package com.tabelao.controller;

import com.tabelao.dto.DtoSorteio;
import com.tabelao.model.Grupo;
import com.tabelao.util.enums.TiposDeTabela;
import com.tabelao.model.Campeonato;
import com.tabelao.model.Tabela;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/campeonato", produces ="application/json", consumes = "application/json")
public class Principal {

    @PostMapping("/gerar")
    @ResponseBody
    public Tabela gerarTabela(@RequestBody Campeonato campeonato){
        return campeonato.gerarTabela(
                TiposDeTabela.valueOf(campeonato.getTipoTabela()),
                campeonato.getGrupos());
    }

    @PostMapping("/sortear")
    @ResponseBody
    public List<Grupo> sortear(@RequestBody DtoSorteio request){

        Campeonato campeonato = new Campeonato();
        return campeonato.sortearGrupos(request.equipes(), request.qtdeGrupos());
    }
}


