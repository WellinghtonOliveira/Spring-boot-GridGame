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
                }
                break;
            case "ArrowLeft":
                if (observerColisionDirections("esquerda")) {
                    jogador.setX(jogador.getX() - velocity);
                }
                break;
            case "ArrowUp":
                if (observerColisionHead()) {
                    if (jogador.getQuantiaPulo() <= 0) {
                        jogador.setEmpuxo(true);
                        jogador.setQuantiaPulo(80);
                        System.out.println(jogador.getQuantiaPulo());
                        break;
                    }

                    jogador.setEmpuxo(false);
                    jogador.setY(jogador.getY() - 2);
                    jogador.setQuantiaPulo(jogador.getQuantiaPulo() - 2);
                }
                break;
        }

        return jogador;
    }

    public JogadorEntity observerPlayer() {
        int y = (int) (jogador.getY() / 40);

        if (y >= mapaService.obterAltura() - 1) {
            jogador.setY((mapaService.obterAltura() - 1) * 40);
            return jogador;
        }

        if (!observerColisionFoot() && jogador.getEmpuxo()) {
            jogador.setY((jogador.getY() + jogador.getGravity()));
        }

        return jogador;
    }

    public Boolean observerColisionDirections(String direction) {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) (jogador.getX() / 40);
        int y = (int) (jogador.getY() / 40);

        int xDireita = (int) ((jogador.getX() + 41) / 40);
        int xEsquerda = (int) ((jogador.getX() - 1) / 40);

        int yCima = (int) ((jogador.getY() + 2) / 40);
        int yBaixo = (int) ((jogador.getY() + 38) / 40);

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
