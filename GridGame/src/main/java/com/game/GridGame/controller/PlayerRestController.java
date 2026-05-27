package com.game.GridGame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.GridGame.dto.InfPLayers;
import com.game.GridGame.dto.ResponsePlayer;
import com.game.GridGame.service.ServiceJogador;

@RestController
public class PlayerRestController {
    private ServiceJogador serviceJogador;

    public PlayerRestController(ServiceJogador serviceJogador) {
        this.serviceJogador = serviceJogador;
    }

    @GetMapping("/player")
    public ResponsePlayer obtemJogadorInicial() {
        return serviceJogador.criarJogador();
    }

    @PostMapping("/playerUpdata")
    public void atualizaPlayer(@RequestBody InfPLayers data) {
        serviceJogador.updatePlayer(data);
    }
}