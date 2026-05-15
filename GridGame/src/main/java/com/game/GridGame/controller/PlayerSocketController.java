package com.game.GridGame.controller;

import java.util.Collection;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.game.GridGame.dto.MovimentoDTO;
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
    public void mover(MovimentoDTO movimento) {
        serviceJogador.moverJogador(movimento.getId(), movimento.getDirecao());
    }

    @MessageMapping("/observer")
    @SendTo("/topic/player")
    public Collection<JogadorEntity> observador(MovimentoDTO movimento) {
        serviceJogador.observerPlayer(movimento.getId());
        return serviceJogador.getJogadores();
    }
}