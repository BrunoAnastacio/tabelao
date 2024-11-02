package com.tabelao.gerador.resources;

import com.tabelao.gerador.enums.TiposDeTabela;
import com.tabelao.gerador.model.Campeonato;
import com.tabelao.gerador.model.Tabela;
import org.springframework.web.bind.annotation.*;

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
}


