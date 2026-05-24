package com.game.GridGame.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.GridGame.dto.InfPLayers;
import com.game.GridGame.entity.JogadorEntity;
import com.game.GridGame.service.ServiceJogador;

@RestController
public class PlayerRestController {
    private ServiceJogador serviceJogador;

    public PlayerRestController(ServiceJogador serviceJogador) {
        this.serviceJogador = serviceJogador;
    }

    @PostMapping("/player")
    public JogadorEntity obtemJogadorInicial(@RequestBody InfPLayers dados) {
        System.out.println(dados);
        return serviceJogador.criarJogador();
    }
}