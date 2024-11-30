package com.tabelao.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Grupo {

    private Deque<Equipe> equipes = new LinkedList<>();
    private String nomeGrupo;

    public Grupo(String nomeGrupo, List<String> nomesEquipes) {
        //this.equipes = new ArrayList<>();
        for(String nomeEquipe : nomesEquipes) {
            Equipe equipe = new Equipe(nomeEquipe);
            this.equipes.add(equipe);
        }
        this.nomeGrupo = nomeGrupo;
    }

    public Grupo(){

    }

    public Grupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public int getSize(){
        return equipes.size();
    }

    public void add(Equipe equipe){
        equipes.add(equipe);
    }

    public void addOrOverwrite(Equipe equipe){
        if(equipes.contains(equipe)){
            equipes.add(equipe);
        }
        else equipes.add(equipe);
    }

    public void concatenarEquipesDeOutroGrupo(Deque<Equipe> equipes){
        this.equipes.addAll(equipes);
    }

    public Deque <Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(Deque <Equipe> equipes) {
        this.equipes = equipes;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Equipe getEquipe(int index){
        return (Equipe) equipes.toArray()[index]; // Converte o deque para array para acessar pelo índice.
    }

    public void rotacionar(){

        if (equipes.size() > 1) {
            Equipe e = equipes.removeFirst();
            equipes.addFirst(equipes.removeLast());
            equipes.addFirst(e);
        }
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "equipes=" + equipes +
                ", nomeGrupo='" + nomeGrupo + '\'' +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "Grupo{" +
//                "nomeGrupo=" + nomeGrupo +
//                ", equipes=" + equipes + '\'' +
//                '}';
//    }
}
