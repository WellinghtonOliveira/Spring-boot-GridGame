package com.game.GridGame.controller;

import java.util.Collection;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.game.GridGame.dto.InfPLayers;
import com.game.GridGame.dto.ResponsePlayerInfos;
import com.game.GridGame.service.ServiceJogador;

@Controller
public class PlayerSocketController {

    private ServiceJogador serviceJogador;

    public PlayerSocketController(ServiceJogador serviceJogador) {
        this.serviceJogador = serviceJogador;
    }

    @MessageMapping("/move")
    public void mover(InfPLayers infPlayer) {
        serviceJogador.moverJogador(infPlayer.getId(), infPlayer.getDirecao());
    }

    @MessageMapping("/ping")
    public void verificarPlayerOff(InfPLayers infPLayers) {
        serviceJogador.atualizaUltimoPing(infPLayers.getId());
    }

    @MessageMapping("/observer")
    @SendTo("/topic/player")
    public Collection<ResponsePlayerInfos> observador(InfPLayers infPlayer) {
        serviceJogador.observerPlayer(infPlayer.getId());
        return serviceJogador.getJogadores();
    }
}