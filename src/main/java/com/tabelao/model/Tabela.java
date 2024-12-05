package com.tabelao.model;

import java.util.ArrayList;
import java.util.List;

public class Tabela {

    private List<Jogo> jogos;
    private int qtdeRodadas;

    public Tabela(){}

    public Tabela(List<Jogo> jogos, int qtdeRodadas) {
        this.jogos = jogos;
        this.qtdeRodadas = qtdeRodadas;
    }

    public Tabela(int qtdeRodadas) {
        this.jogos = new ArrayList<>();
        this.qtdeRodadas = qtdeRodadas;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public void addJogo(Jogo jogo){
        this.jogos.add(jogo);
    }

    public void addJogos(List<Jogo> jogos){
        this.jogos.addAll(jogos);
    }

    public int getQtdeRodadas() {
        return qtdeRodadas;
    }

    public void setQtdeRodadas(int qtdeRodadas) {
        this.qtdeRodadas = qtdeRodadas;
    }

    public void addUmaRodada(){
        this.qtdeRodadas ++;
    }

}
