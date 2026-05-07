package com.game.GridGame.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.game.GridGame.entity.JogadorEntity;
import com.game.GridGame.service.ServiceJogador;

@Controller
public class PlayerSocketController {

    private ServiceJogador serviceJogador;

    public PlayerSocketController(ServiceJogador serviceJogador) {
        this.serviceJogador = serviceJogador;
    }

    @MessageMapping("/move")
    @SendTo("/topic/player")
    public JogadorEntity  mover(String direcao) {
        return serviceJogador.moverJogador(direcao);
    }

    @MessageMapping("/observer")
    @SendTo("/topic/player")
    public JogadorEntity  observador() {
        return serviceJogador.observerPlayer();
    }
}