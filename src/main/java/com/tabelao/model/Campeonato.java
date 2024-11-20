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
    private List<Grupo> grupos;
    private List<Rodada> rodadas;
    private Tabela tabela;
    private String tipoTabela;
    private int qtdeGrupos;
    private int qtdeTurnosDentro;
    private int qtdeTurnosFora;

    //-----outros metodos
    //sortearGrupos()
    //


    public Campeonato(){
        this.tabela = new Tabela();
        this.grupos = new ArrayList<>();
    }

    public Campeonato(String nomeCampeonato, List<Grupo> grupos, int qtdeGrupos, int qtdeTurnosDentro, int qtdeTurnosFora){
        this.nomeCampeonato = nomeCampeonato;
        this.grupos = grupos; //verificar se funciona
        this.qtdeGrupos = qtdeGrupos;
        this.qtdeTurnosDentro = qtdeTurnosDentro;
        this.qtdeTurnosFora = qtdeTurnosFora;
    }

    public Campeonato(String nomeCampeonato, List<Grupo> grupos){
        this.nomeCampeonato = nomeCampeonato;
        this.grupos = new ArrayList<>();
        this.rodadas = new ArrayList<>();
        this.tabela = new Tabela();
        this.grupos.addAll(grupos);
    }

    public List<Grupo> criarGrupos(int qtdeGrupos){
       List<Grupo> gruposCriados = new ArrayList<>();
        for (int i = 0; i < qtdeGrupos; i++){
            Grupo grupo = new Grupo("Grupo " + (i+1));
            gruposCriados.add(grupo);
        }
        return gruposCriados;
    }

    public Equipe escolherEquipe(List<Equipe> equipes){
        return equipes.get((int) (random() * equipes.size()));
    }


    public List<Grupo> sortearGrupos(List<Equipe> equipes, int qtdeGrupos){
        //List<Grupo> grupo --> receber lista com um grupo contendo todos os times e (imagine os 16 times do paulistao)
        //List<Grupo> gruposCriados --> receber lista com os grupos criados, porem vazios (imagine os grupos A, B, C, D, porem vazios)
        //sortear aleatoriamente um time do grupo completo e colocar na primeira posição do primeiro grupo
        //seguir essa logica até completar o primeiro grupo
        //seguir essa logica até completar todos os grupos
        //retornar a lista de grupos preenchidas

        List<Grupo> gruposCriados = criarGrupos(qtdeGrupos);
        int tamanhoDeCadaGrupo = equipes.size()/qtdeGrupos;

//        System.out.println("Numero de times passados: " + equipes.size());
//        System.out.println("Numero de grupos solicitados: "+ qtdeGrupos);
//        System.out.println(tamanhoDeCadaGrupo);

        if (tamanhoDeCadaGrupo >=3 ){
            for (Grupo grupoCriado:gruposCriados) {
                for(int i=0; i < tamanhoDeCadaGrupo; i++){
                    Equipe equipe = escolherEquipe(equipes);
                    grupoCriado.addOrOverwrite(equipe);
                    equipes.remove(equipe);
                }
            }
        } else{
            //erro de 'tamanho de cgrupo incondizente com sorteio'
            // melhorar lancamento do erro
            throw new RuntimeException("Numero de times incondizente com numero de grupos");

        }
        return gruposCriados;
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

    public Tabela gerarTabela(TiposDeTabela tipo, List <Grupo> grupos){ //passar tipos de tabela:
        switch(tipo.getNumero()){
            case 1:
                for (Grupo grupo:grupos) {
                    tabela.add(turnoUnicoDentroDoGrupo(grupo));
                }
                break;
            case 2:
                for (Grupo grupo:grupos) {
                    tabela.add(turnoReturnoDentroDoGrupo(grupo));
                }
                break;
            case 3:
                Grupo grupaoUnico = new Grupo();
                for (Grupo grupo:grupos){
                    grupaoUnico.concatenarEquipesDeOutroGrupo(grupo.getEquipes());
                }
                tabela.add(turnoUnicoDentroDoGrupo(grupaoUnico));
                break;
            case 4:
                Grupo grupaoTurnoReturno = new Grupo();
                for (Grupo grupo:grupos){
                    grupaoTurnoReturno.concatenarEquipesDeOutroGrupo(grupo.getEquipes());
                }
                for (Grupo grupo:grupos) {
                    tabela.add(turnoReturnoDentroDoGrupo(grupo));
                }
                break;
            case 5:
                for (Grupo grupo:grupos) {
                    tabela.add(turnoUnicoDentroDoGrupo(grupo));
                }
                Grupo grupaoTurnoReturnoII = new Grupo();
                for (Grupo grupo:grupos){
                    grupaoTurnoReturnoII.concatenarEquipesDeOutroGrupo(grupo.getEquipes());
                }
                tabela.add(turnoReturnoDentroDoGrupo(grupaoTurnoReturnoII));
                break;
        }

        return tabela;
    }

    public List <Rodada> turnoUnicoDentroDoGrupo(Grupo grupo){
        return RoundRobin.gerarRodadas(grupo, false);
    }

    public List <Rodada> turnoReturnoDentroDoGrupo(Grupo grupo){
        List <Rodada> rodadas = RoundRobin.gerarRodadas(grupo, false);
        List <Rodada> returno = RoundRobin.gerarRodadas(grupo, true);
        rodadas.addAll(returno);
        return rodadas;

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
