package com.game.GridGame.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePlayer {
    String id;
    String nome;
    String cor;

    double x;
    double y;

    public ResponsePlayer(String id) {
        this.id = id;
    }

    public ResponsePlayer(String nome, String cor, double x, double y) {
        this.nome = nome;
        this.cor = cor;
        this.x = x;
        this.y = y;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
