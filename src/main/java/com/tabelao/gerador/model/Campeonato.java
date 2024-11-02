package com.tabelao.gerador.model;

import com.tabelao.gerador.algoritmos.RoundRobin;
import com.tabelao.gerador.enums.TiposDeTabela;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private String nomeCampeonato;
    private List<Grupo> grupos;
    private List<Rodada> rodadas;
    private Tabela tabela;
    private String tipoTabela;

    public Campeonato(String nomeCampeonato, List<Grupo> grupos){
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
