package com.tabelao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tabelao.dto.DtoEquipe;
import com.tabelao.dto.DtoSemana;

import java.util.List;

public class Semana {

    @JsonProperty("semana")
    private int[] maxJogosPorSemana;

    public Semana(int[] semana) {
        this.maxJogosPorSemana = semana;
    }

    public int size() {
        return maxJogosPorSemana.length;
    }
//    private int domingo, segunda, terca, quarta, quinta, sexta, sabado;
//
//    public Semana(){
//
//    }
//
//    public Semana(List<DtoSemana> semana){
//        this.domingo = semana.get(0).maxJogos();
//        segunda = semana.get(1).maxJogos();
//        terca = semana.get(2).maxJogos();
//        quarta = semana.get(3).maxJogos();
//        quinta = semana.get(4).maxJogos();
//        sexta = semana.get(5).maxJogos();
//        sabado = semana.get(6).maxJogos();
//    }
//
//    @Override
//    public String toString() {
//        return "Semana{" +
//                "domingo=" + domingo +
//                ", segunda=" + segunda +
//                ", terca=" + terca +
//                ", quarta=" + quarta +
//                ", quinta=" + quinta +
//                ", sexta=" + sexta +
//                ", sabado=" + sabado +
//                '}';
//    }
}
