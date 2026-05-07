package com.game.GridGame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.GridGame.entity.JogadorEntity;
import com.game.GridGame.service.ServiceJogador;

@RestController
public class PlayerRestController {
    private ServiceJogador serviceJogador;

    public PlayerRestController(ServiceJogador serviceJogador) {
        this.serviceJogador = serviceJogador;
    }

    @GetMapping("/player")
    public JogadorEntity obtemJogadorInicial() {
        return serviceJogador.getJogador();
    }
}
