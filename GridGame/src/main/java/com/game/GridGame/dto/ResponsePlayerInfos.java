package com.game.GridGame.dto;

public class ResponsePlayerInfos {
    String nome;
    String cor;

    int idMapa;
    int vida;

    double x;
    double y;

    public ResponsePlayerInfos(String nome, String cor, int idMapa, int vida, double x, double y) {
        this.nome = nome;
        this.cor = cor;
        this.idMapa = idMapa;
        this.vida = vida;
        this.x = x;
        this.y = y;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getIdMapa() {
        return idMapa;
    }

    public int getVida() {
        return vida;
    }    

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
