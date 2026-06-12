package com.game.GridGame.dto;

public class ResponseMapa {
    private int idMapa;
    private String[][] matriz;

    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }

    public void setMatriz(String[][] matriz) {
        this.matriz = matriz;
    }

    public int getIdMapa() {
        return idMapa;
    }

    public String[][] getMatriz() {
        return matriz;
    }
}
