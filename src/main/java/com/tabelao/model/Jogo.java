package com.tabelao.model;

import java.util.Date;

public class Jogo {

    private Equipe mandante;
    private Equipe visitante;
    private int rodada;
    private Date data;
    private Local local;

    public Jogo(Equipe mandante, Equipe visitante, int rodada, Date data, Local local) {
        this.mandante = mandante;
        this.visitante = visitante;
        this.rodada = rodada;
        this.data = data;
        this.local = local;
    }

    public Jogo(Equipe mandante, Equipe visitante, int rodada){
        this.mandante = mandante;
        this.visitante = visitante;
        this.rodada = rodada;
        this.data = null;
        this.local = null;
    }

    public Jogo(){

    }

    public Jogo(Object jogo, Date novaData) {
    }

    public Equipe getMandante() {
        return mandante;
    }

    public void setMandante(Equipe mandante) {
        this.mandante = mandante;
    }

    public Equipe getVisitante() {
        return visitante;
    }

    public void setVisitante(Equipe visitante) {
        this.visitante = visitante;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "mandante=" + mandante +
                ", visitante=" + visitante +
                ", rodada=" + rodada +
                ", data=" + data +
                ", local=" + local +
                '}';
    }
}
