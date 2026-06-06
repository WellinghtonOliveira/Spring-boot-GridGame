package com.game.GridGame.service;

import java.awt.Point;
import org.springframework.stereotype.Service;

@Service
public class ServiceMap {
    private final String[][] mapaPadrao = {
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao"},
        {"chao", "spawn", "chao", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao"},
        {"chao", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "chao", "chao", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "chao", "chao"},
        {"chao", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "chao", "chao", "chao", "vazio", "chao", "vazio", "chao", "chao", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao"},
        {"chao", "chao", "chao", "chao", "chao", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "chao", "chao"},
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao"},
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "vazio", "vazio", "chao", "vazio", "chao", "chao", "vazio", "chao", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "vazio", "chao"},
        {"chao", "vazio", "chao", "chao", "chao", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "chao"},
        {"chao", "vazio", "chao", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "chao", "vazio", "vazio", "chao"},
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao"},
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "chao", "vazio", "chao"},
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao"},
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio"},
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao"}
    };
    
    public String[][] obterMapa(Integer id) {
        if (id == null) {
            return mapaPadrao;
        }
        return new String[][] {};
    }

    public Integer obterAltura() {
        return mapaPadrao.length;
    }

    public Integer obterLargura() {
        return mapaPadrao[0].length;
    }

    public Point posRespawnMap() {
        for (int y = 0; y < obterAltura(); y++) {
            for (int x = 0; x < obterLargura(); x++) {
                if (mapaPadrao[y][x].equals("spawn")) {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    public Point posVazioMap() {
        for (int y = 0; y < obterAltura(); y++) {
            for (int x = 0; x < obterLargura(); x++) {
                if (mapaPadrao[y][x].equals("vazio")) {
                    return new Point(x, y);
                }
            }
        }   
        return null;
    }
}
