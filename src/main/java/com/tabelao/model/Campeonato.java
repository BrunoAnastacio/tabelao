package com.tabelao.model;

import com.tabelao.util.algoritmos.RoundRobin;
import com.tabelao.util.enums.TiposDeTabela;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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

    public Campeonato(String nomeCampeonato, List<Equipe> equipes, int qtdeGrupos, int qtdeTurnosDentro, int qtdeTurnosFora){
        this.nomeCampeonato = nomeCampeonato;
        this.qtdeGrupos = qtdeGrupos;
        this.qtdeTurnosDentro = qtdeTurnosDentro;
        this.qtdeTurnosFora = qtdeTurnosFora;
        this.equipes = equipes;
        this.tabela = new Tabela();
        this.grupos = new ArrayList<>();
        this.rodadas = new ArrayList<>();
    }

    public Campeonato(String nomeCampeonato, List<Grupo> grupos){
        this.nomeCampeonato = nomeCampeonato;
        this.grupos = new ArrayList<>();
        this.rodadas = new ArrayList<>();
        this.tabela = new Tabela();
        this.grupos.addAll(grupos);
    }

    //REGRAS DE NEGOCIO - REFATORAR?

    public List<Grupo> criarGrupos(){
       List<Grupo> gruposCriados = new ArrayList<>();
        for (int i = 0; i < qtdeGrupos; i++){
            Grupo grupo = new Grupo("Grupo " + (i+1));
            gruposCriados.add(grupo);
        }
        return gruposCriados;
    }

    public Equipe escolherEquipe(){
        return equipes.get((int) (random() * equipes.size()));
    }

    public List<Grupo> sortearGrupos(){

        List<Grupo> gruposCriados = criarGrupos();
        int tamanhoDeCadaGrupo = equipes.size()/qtdeGrupos;

        if (tamanhoDeCadaGrupo >=3 ){
            for (Grupo grupoCriado:gruposCriados) {
                for(int i=0; i < tamanhoDeCadaGrupo; i++){
                    Equipe equipe = escolherEquipe();
                    grupoCriado.addOrOverwrite(equipe);
                    equipes.remove(equipe);
                }
            }
        } else{
            throw new RuntimeException("Numero de equipes precisa ser: 1) Um multiplo do numero de grupos; " +
                    "2) Suficiente para preencher cada grupo com 3 equipes ou mais");
        }

        grupos = gruposCriados;

        return gruposCriados;
    }

    public List<Rodada> gerarTabela(){
        gerarJogosDentroDoGrupo();
        gerarJogosEntreGruposDiferentes();
        return rodadas;
    }


    public void gerarJogosDentroDoGrupo(){
        if (qtdeTurnosDentro > 0) {
            for(int i = 0; i < qtdeTurnosDentro; i++){
                for (Grupo grupo : grupos) {
                    boolean inverterMando = (i % 2) == 1;
                    rodadas.addAll(RoundRobin.gerarRodadas(grupo, inverterMando, rodadas.size()));
                }
            }
        }
    }

    public void gerarJogosEntreGruposDiferentes(){
        Grupo grupao = new Grupo();
        if (qtdeTurnosFora >0) {
            for(int i = 0; i < qtdeTurnosFora; i++) {
                for(Grupo grupo :  grupos) {
                    grupao.concatenarEquipesDeOutroGrupo(grupo.getEquipes());
                    boolean inverterMando = (i % 2) == 1;
                    rodadas.addAll(RoundRobin.gerarRodadas(grupao, inverterMando, rodadas.size()));
                }
            }
        }
    }

//GETTERS E SETTERS

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

    @Override
    public String toString() {
        return "Campeonato{" +
                "nomeCampeonato='" + nomeCampeonato + '\'' +
                ", grupos=" + grupos +
                ", rodadas=" + rodadas +
                '}';
    }
}
