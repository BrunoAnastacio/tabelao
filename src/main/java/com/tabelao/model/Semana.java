package com.tabelao.model;

import com.tabelao.dto.DtoEquipe;
import com.tabelao.dto.DtoSemana;

import java.util.List;

public class Semana {
    int domingo, segunda, terca, quarta, quinta, sexta, sabado;

    public Semana(){

    }

    public Semana(List<DtoSemana> semana){
        domingo = semana.get(0).maxJogos();
        segunda = semana.get(1).maxJogos();
        terca = semana.get(2).maxJogos();
        quarta = semana.get(3).maxJogos();
        quinta = semana.get(4).maxJogos();
        sexta = semana.get(5).maxJogos();
        sabado = semana.get(6).maxJogos();
    }
}
