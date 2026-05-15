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
        int larguraMapa = mapaService.obterLargura();

        if (jogador.getX() < 2 && direcao.equals("ArrowLeft") ||
                jogador.getX() > (larguraMapa * 40) - 41 && direcao.equals("ArrowRight")) {
            jogador.setX(jogador.getX());
            return jogador;
        }

        switch (direcao) {
            case "ArrowRight":
                if (observerColisionDirections("direita")) {
                    jogador.setX(jogador.getX() + velocity);
                } else {
                    int xBloco = ((int)((jogador.getX() + 39 + jogador.getVelocityX()) / 40)) * 40;
                    jogador.setX(xBloco - 40);
                }

                break;
            case "ArrowLeft":
                if (observerColisionDirections("esquerda")) {
                    jogador.setX(jogador.getX() - velocity);
                } else {
                    int xBloco = ((int)((jogador.getX() + 39 + jogador.getVelocityX()) / 40)) * 40;
                    jogador.setX(xBloco - 40);
                }

                break;
            case "ArrowUp":
                if (observerColisionFoot()) {
                    jogador.setPulo(true);
                    jogador.setEmpuxo(false);
                }
                
                break;
        }

        return jogador;
    }

    public JogadorEntity observerPlayer() {
        if (jogador.getY() / 40 >= mapaService.obterAltura() - 1) {
            jogador.setY((mapaService.obterAltura() - 1) * 40);
            return jogador;
        }

        observerCanJump();
        boolean noChao = observerColisionFoot();

        if (!noChao && jogador.getEmpuxo()) {
            if (colisaoPosPuloFoot()) {
                int yBloco = ((int) ((jogador.getY() + 39 + jogador.getGravity()) / 40)) * 40;
                jogador.setY(yBloco - 40);
                jogador.setGravity(0);
            }
            gravidade();
        } else if (noChao) {
            jogador.setGravity(0);
        }

        return jogador;
    }

    public Boolean colisaoPosPuloFoot() {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) jogador.getX() / 40;
        int y = (int) (jogador.getY() / 40);

        int xEsquerda = (int) (jogador.getX() / 40);
        int xDireita = (int) ((jogador.getX() + 39) / 40);
        int yAbaixo = (int) ((jogador.getY() + 39 + jogador.getGravity()) / 40);

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

    public Boolean observerColisionDirections(String direction) {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) (jogador.getX() / 40);
        int y = (int) (jogador.getY() / 40);

        int xDireita = (int) ((jogador.getX() + 40) / 40);
        int xEsquerda = (int) ((jogador.getX() - 1) / 40);

        int yCima = (int) ((jogador.getY() + 1) / 40);
        int yBaixo = (int) ((jogador.getY() + 39) / 40);

        if (x >= 0 &&
                y + 1 < mapaService.obterAltura() &&
                x < mapaService.obterLargura()) {

            if (direction.equals("direita") &&
                    mapa[yCima][xDireita].equals("vazio") &&
                    mapa[yBaixo][xDireita].equals("vazio")) {
                return true;
            } else if (direction.equals("esquerda") &&
                    mapa[yCima][xEsquerda].equals("vazio") &&
                    mapa[yBaixo][xEsquerda].equals("vazio")) {
                return true;
            }
        }
        return false;
    }

    public Boolean observerColisionHead() {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) jogador.getX() / 40;
        int y = (int) (jogador.getY() / 40);

        int xEsquerda = (int) ((jogador.getX() + 1) / 40);
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

        int xEsquerda = (int) ((jogador.getX() + 1) / 40);
        int xDireita = (int) ((jogador.getX() + 39) / 40);
        int yAbaixo = (int) ((jogador.getY() + 40) / 40);

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

    public void observerCanJump() {
        if (jogador.getPulo()) {
            if (jogador.getQuantiaPulo() > 0) {
                if (observerColisionHead()) {

                    jogador.setY(jogador.getY() - 4);
                    jogador.setQuantiaPulo(jogador.getQuantiaPulo() - 1);

                } else {
                    jogador.setQuantiaPulo(0);
                }

            } else {
                jogador.setPulo(false);
                jogador.setEmpuxo(true);
                jogador.setQuantiaPulo(20);
            }
        }
    }

    public void gravidade() {
        jogador.setY(jogador.getY() + jogador.getGravity());

        if (jogador.getGravity() < 15) {
            jogador.setGravity(jogador.getGravity() + 1);
        }
    }

    public JogadorEntity getJogador() {
        return jogador;
    }
}
