package com.game.GridGame.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceMap {
    private final String[][] mapaPadrao = {
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "chao", "chao", "chao", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"},
        {"chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio"}
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
}
