package com.tabelao.model;

import com.tabelao.util.algoritmos.RoundRobin;
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
        int qtdeRodadasCriadas = 0;
        rodadas.addAll(gerarJogosDentroDoGrupo(qtdeRodadasCriadas));
        rodadas.addAll(gerarJogosEntreGruposDiferentes());
        return rodadas;
    }

    public List<Rodada> gerarJogosDentroDoGrupo(int qtdeRodadasCriadas){
        List<Rodada> r = new ArrayList<>();
        if (qtdeTurnosDentro > 0) {
            for(int i = 0; i < qtdeTurnosDentro; i++){
                for (Grupo grupo : grupos) {
                    boolean inverterMando = (i % 2) == 1;
                    if (i > 0) qtdeRodadasCriadas = rodadas.size();
                    r.addAll(RoundRobin.gerarRodadas(grupo, inverterMando, qtdeRodadasCriadas));
                }
            }
        }
        return r;
    }

    public List<Rodada> gerarJogosEntreGruposDiferentes(){
        List<Rodada> r = new ArrayList<>();
        int qtdeRodadasCriadas = 0;
        if(qtdeTurnosFora > 0){
            Deque<Grupo> filaGrupos = new LinkedList<>();
            filaGrupos.addAll(grupos);
            for(int i = 0; i < qtdeTurnosFora; i++){
                boolean inverterMando = (i % 2) == 1;
                qtdeRodadasCriadas = rodadas.size();
                if(i == 0 && !rodadas.isEmpty())
                    qtdeRodadasCriadas = rodadas.size()/filaGrupos.size();
                r.addAll(RoundRobin.cruzarEntreGrupos(filaGrupos, inverterMando, qtdeRodadasCriadas, false));
            }
        }
        return r;
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
