package com.game.GridGame.service;

import org.springframework.stereotype.Service;
import com.game.GridGame.entity.JogadorEntity;

@Service
public class ServiceJogador {
    private JogadorEntity jogador;
    private ServiceMap mapaService;

    public ServiceJogador() {
        this.jogador = new JogadorEntity(null);
        this.mapaService = new ServiceMap();
    }

    public JogadorEntity moverJogador(String direcao) {
        double velocity = jogador.getVelocityX();

        String[][] mapa = mapaService.obterMapa(null);

        int larguraMapa = mapaService.obterLargura();

        if (jogador.getX() < 2 && direcao.equals("ArrowLeft") ||
                jogador.getX() > (larguraMapa * 40) - 41 && direcao.equals("ArrowRight")) {
            jogador.setX(jogador.getX());
            return jogador;
        }

        if (jogador.getY() < 1) {
            return jogador;
        }

        int posX = (int) (jogador.getX() / 40);
        int posY = (int) (jogador.getY() / 40);
        switch (direcao) {
            case "ArrowRight":
                if (mapa[posY][posX + 1].equals("vazio")) {
                    jogador.setX(jogador.getX() + velocity);
                }
                break;
            case "ArrowLeft":
                if (mapa[posY][posX].equals("vazio")) {
                    jogador.setX(jogador.getX() - velocity);
                }
                break;
            case "ArrowUp":
                if (jogador.getPulo()) {
                    int alturaDesejada = 120;
                    int movido = 0;

                    while (movido < alturaDesejada) {
                        int proximoY = (int) ((jogador.getY() - 40) / 40);
                        int atualX = (int) (jogador.getX() / 40);

                        if (proximoY >= 0 &&
                            mapa[proximoY][atualX].equals("vazio") &&
                            observerColisionHead()) {
                    
                            jogador.setY(jogador.getY() - 40);
                            movido += 40;
                        } else {
                            break;
                        }
                    }

                    jogador.setPulo(false);
                }
                break;
        }

        return jogador;
    }

    public JogadorEntity observerPlayer() {
        int y = (int) (jogador.getY() / 40);

        if (y >= mapaService.obterAltura() - 1) {
            jogador.setY((mapaService.obterAltura() - 1) * 40);
            jogador.setPulo(true);
            return jogador;
        }

        if (observerColisionFoot()) {
            jogador.setPulo(true);
            return jogador;
        }

        jogador.setPulo(false);
        jogador.setY(jogador.getY() + (jogador.getVelocityY() * jogador.getGravity()));
        return jogador;
    }

    public Boolean observerColisionHead() {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) jogador.getX() / 40;
        int y = (int) (jogador.getY() / 40);

        int xEsquerda = (int) (jogador.getX() / 40);
        int xDireita = (int) ((jogador.getX() + 39) / 40);
        int yCima = (int) ((jogador.getY() - 1) / 40);

        if (y + 1 < mapaService.obterAltura() &&
                x >= 0 &&
                x < mapaService.obterLargura()) {
                    
            if (mapa[yCima][xEsquerda].equals("vazio") &&
                    mapa[yCima][xDireita].equals("vazio")) {
                return true;
            }
        }
        return false;
    }

    public Boolean observerColisionFoot() {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) jogador.getX() / 40;
        int y = (int) (jogador.getY() / 40);

        int xEsquerda = (int) (jogador.getX() / 40);
        int xDireita = (int) ((jogador.getX() + 39) / 40);
        int yAbaixo = (int) ((jogador.getY() + 41) / 40);

        if (y + 1 < mapaService.obterAltura() &&
                x >= 0 &&
                x < mapaService.obterLargura()) {

            if (mapa[yAbaixo][xEsquerda].equals("chao") ||
                    mapa[yAbaixo][xDireita].equals("chao")) {
                return true;
            }
        }
        return false;
    }

    public JogadorEntity getJogador() {
        return jogador;
    }
}
