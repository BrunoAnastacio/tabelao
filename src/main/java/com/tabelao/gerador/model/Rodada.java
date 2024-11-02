package com.tabelao.gerador.model;

import java.util.ArrayList;
import java.util.List;

public class Rodada {
    int numeroRodada;
    private List<Jogo> jogos;

    public Rodada(int numeroRodada, List<Jogo> jogos) {
        this.numeroRodada = numeroRodada;
        this.jogos = jogos;
    }

    public Rodada(int numeroRodada){
        this.numeroRodada = numeroRodada;
        this.jogos = new ArrayList<>();
    }

    public void add(Jogo jogo){
        this.jogos.add(jogo);
    }

    public int getNumeroRodada() {
        return numeroRodada;
    }

    public void setNumeroRodada(int numeroRodada) {
        this.numeroRodada = numeroRodada;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    @Override
    public String toString() {
        return "Rodada{" +
                "numeroRodada=" + numeroRodada +
                ", jogos=" + jogos +
                '}';
    }
}
