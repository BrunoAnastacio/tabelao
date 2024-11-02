package com.tabelao.gerador.model;

import java.util.ArrayList;
import java.util.List;

public class Tabela {

    private List<List<Rodada>> listaRodadas;

    public Tabela(){
        this.listaRodadas = new ArrayList<>();
    }

    public List<List<Rodada>> getListaRodadas() {
        return listaRodadas;
    }

    public void setListaRodadas(List<List<Rodada>> listaRodadas) {
        this.listaRodadas = listaRodadas;
    }

    public void add(List<Rodada> rodadas ){
        this.listaRodadas.addLast(rodadas);
    }

    @Override
    public String toString() {
        return "Tabela{" +
                "listaRodadas=" + listaRodadas +
                '}';
    }
}
