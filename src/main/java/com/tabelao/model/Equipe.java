package com.tabelao.model;

public class Equipe {

    private int id;
    private String nome;
    private String cidade;
    private Double coordenadaX;
    private Double coordenadaY;
    private String cluster;
    private String grupo;

    public Equipe(int id, String nome, String cidade, Double coordenadaX, Double coordenadaY, String cluster) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.cluster = cluster;
    }

    public Equipe(){

    }
    public Equipe(String nome) {
        this.id = 0;
        this.nome = nome;
        this.cidade = "N/A";
        this.coordenadaX = 0.0;
        this.coordenadaY = 0.0;
        this.cluster = "N/A";
        System.out.println(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", coordenadaX=" + coordenadaX +
                ", coordenadaY=" + coordenadaY +
                ", cluster='" + cluster + '\'' +
                '}';
    }
}
