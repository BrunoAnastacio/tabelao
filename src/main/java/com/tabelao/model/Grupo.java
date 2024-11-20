package com.tabelao.model;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

    private List<Equipe> equipes = new ArrayList<>();
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
            equipes.add(equipe.getId(),equipe);
        }
        else equipes.add(equipe);
    }

    public void concatenarEquipesDeOutroGrupo(List<Equipe> equipes){
        this.equipes.addAll(equipes);
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Equipe getEquipe(int i){
        return this.equipes.get(i);
    }

    public void rotacionar(){
        this.equipes.add(1,this.equipes.remove(this.equipes.size()-1));
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "equipes=" + equipes +
                ", nomeGrupo='" + nomeGrupo + '\'' +
                '}';
    }
}
