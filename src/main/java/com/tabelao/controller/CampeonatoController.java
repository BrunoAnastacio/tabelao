package com.tabelao.controller;

import com.tabelao.dto.DtoDadosCampeonato;
import com.tabelao.dto.DtoResponseCampeonatoCriado;
import com.tabelao.service.CampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.Calendar;

@RestController
@RequestMapping(value = "/campeonato", produces ="application/json", consumes = "application/json")
@CrossOrigin(origins = "http://localhost:63342")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @GetMapping("/gerar")
    @ResponseBody
    public DtoResponseCampeonatoCriado gerarCampeonato(@RequestBody DtoDadosCampeonato request){
        try{
            return campeonatoService.gerarCampeonato(request);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar campeonato", e);
        }
    }
}


