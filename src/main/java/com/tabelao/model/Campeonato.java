package com.tabelao.model;

import com.tabelao.util.algoritmos.GeradorRoundRobin;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.random;

public class Campeonato {
    private String nomeCampeonato;
    private List<Equipe> equipes;
    private List<Grupo> grupos;
    private List<Rodada> rodadas;
    private Tabela tabela;
    private String tipoTabela;
    private int qtdeGrupos;
    private int qtdeTurnosDentro;
    private int qtdeTurnosFora;

    //CONSTRUTORES
    public Campeonato(){
        this.tabela = new Tabela();
        this.grupos = new ArrayList<>();
    }

    public Campeonato(String nomeCampeonato, List<Equipe> equipes, int qtdeGrupos, int qtdeTurnosDentro, int qtdeTurnosFora) {
        this.nomeCampeonato = nomeCampeonato;
        this.qtdeGrupos = qtdeGrupos;
        this.qtdeTurnosDentro = qtdeTurnosDentro;
        this.qtdeTurnosFora = qtdeTurnosFora;
        this.equipes = equipes;
        this.tabela = new Tabela();
        this.grupos = new ArrayList<>();
        this.rodadas = new ArrayList<>();
    }

    public Campeonato(String nomeCampeonato, List<Grupo> grupos) {
        this.nomeCampeonato = nomeCampeonato;
        this.grupos = new ArrayList<>();
        this.rodadas = new ArrayList<>();
        this.tabela = new Tabela();
        this.grupos.addAll(grupos);

    }

    public List<Grupo> getGrupos(){
        return grupos;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Rodada> getRodadas() {
        return rodadas;
    }

    public void setRodadas(List<Rodada> rodadas) {
        this.rodadas = rodadas;
    }

    public void addRodadas(List<Rodada> rodadas) {
        this.rodadas.addAll(rodadas);
    }

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public String getTipoTabela() {
        return tipoTabela;
    }

    public void setTipoTabela(String tipoTabela) {
        this.tipoTabela = tipoTabela;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public int getQtdeGrupos() {
        return qtdeGrupos;
    }

    public void setQtdeGrupos(int qtdeGrupos) {
        this.qtdeGrupos = qtdeGrupos;
    }

    public int getQtdeTurnosDentro() {
        return qtdeTurnosDentro;
    }

    public void setQtdeTurnosDentro(int qtdeTurnosDentro) {
        this.qtdeTurnosDentro = qtdeTurnosDentro;
    }

    public int getQtdeTurnosFora() {
        return qtdeTurnosFora;
    }

    public void setQtdeTurnosFora(int qtdeTurnosFora) {
        this.qtdeTurnosFora = qtdeTurnosFora;
    }

//    public int[] getSemana() {
//        return semana;
//    }
//
//    public void setSemana(int[] semana) {
//        this.semana = semana;
//    }

    @Override
    public String toString() {
        return "Campeonato{" +
                "nomeCampeonato='" + nomeCampeonato + '\'' +
                ", grupos=" + grupos +
                ", rodadas=" + rodadas +
                '}';
    }
}
