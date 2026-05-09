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

        switch (direcao) {
            case "ArrowRight":
                jogador.setX(jogador.getX() + velocity);
                break;
            case "ArrowLeft":
                jogador.setX(jogador.getX() - velocity);
                break;
            case "ArrowUp":
                if (jogador.getPulo()) {
                    int alturaDesejada = 120; 
                    int movido = 0;

                    while (movido < alturaDesejada) {
                        int proximoY = (int) ((jogador.getY() - 40) / 40);
                        int atualX = (int) (jogador.getX() / 40);

                        if (proximoY >= 0 && mapa[proximoY][atualX].equals("vazio")) {
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
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) jogador.getX() / 40;
        int y = (int) (jogador.getY() / 40);

        if (y >= mapaService.obterAltura() - 1) {
            jogador.setY((mapaService.obterAltura() - 1) * 40);
            jogador.setPulo(true);
            return jogador;
        }

        if (y + 1 < mapaService.obterAltura() && x >= 0 && x < mapaService.obterLargura()) {
            if (mapa[y + 1][x].equals("chao")) {
                jogador.setPulo(true);
                jogador.setY(y * 40);
                return jogador;
            }
        }

        jogador.setPulo(false);
        jogador.setY(jogador.getY() + (jogador.getVelocityY() * jogador.getGravity()));
        return jogador;
    }

    public JogadorEntity getJogador() {
        return jogador;
    }
}
