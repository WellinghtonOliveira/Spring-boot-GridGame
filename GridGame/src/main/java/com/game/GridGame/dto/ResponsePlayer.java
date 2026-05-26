package com.game.GridGame.dto;

public class ResponsePlayer {
    String id;
    String nome;
    String cor;

    public ResponsePlayer(String id, String nome, String cor) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
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

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }
}
