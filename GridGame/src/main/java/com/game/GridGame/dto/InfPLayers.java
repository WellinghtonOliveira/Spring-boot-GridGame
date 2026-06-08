package com.game.GridGame.dto;

public class InfPLayers {
    private String id;
    private String nome;
    private String cor;
    private String direcao;

    private int idMapa;

    public InfPLayers() {}

    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }

    public Integer getIdMapa() {
        return idMapa;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
}
