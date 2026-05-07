package com.game.GridGame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.GridGame.service.ServiceMap;

@RestController
@RequestMapping("/maps")
public class MapController {
    private ServiceMap serviceMaps = new ServiceMap();

    @GetMapping
    public char[][] obtemMaps() {
        return serviceMaps.obterMapa(null);
    }

}