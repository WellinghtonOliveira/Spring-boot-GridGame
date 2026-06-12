package com.game.GridGame.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.GridGame.dto.ResponseMapa;
import com.game.GridGame.service.ServiceMap;

@RestController
@RequestMapping("/maps")
public class MapController {
    private ServiceMap serviceMaps;

    public MapController(ServiceMap serviceMaps) {
        this.serviceMaps = serviceMaps;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, String[][]>> obtemTodosMapas() {
        return ResponseEntity.ok(serviceMaps.obtemTodosMapas());
    }

    @GetMapping("/{idMapa}")
    public String[][] obtemMap(@PathVariable Integer idMapa) {
        return serviceMaps.obterMapa(idMapa);
    }

    @PostMapping("/salvarMapa")
    public ResponseEntity<Integer> salvarAtualizarMapas(@RequestBody ResponseMapa mapaRecebido) {
        Integer responseMap = serviceMaps.salvarAtualizarMapa(mapaRecebido);

        if (responseMap != null) return ResponseEntity.status(200).body(responseMap);
        
        return ResponseEntity.status(401).build();
    }
}