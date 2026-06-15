package com.game.GridGame.service;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.game.GridGame.dto.ResponseMapa;

@Service
public class ServiceMap {
    private final Map<Integer, String[][]> mapas = new HashMap<>();
    private int countIds = 1;

    public ServiceMap() {
        String[][] mapaPadrao = {
                { "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "chao", "vazio", "vazio", "chao", "chao", "chao", "chao", "chao", "vazio", "chao", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "vazio", "chao", "vazio", "vazio", "chao", "chao", "chao", "chao", "chao", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "chao", "chao", "vazio", "chao", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "vazio", "chao",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "vazio", "chao",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "vazio", "chao", "vazio", "chao", "vazio", "vazio", "chao", "vazio", "chao", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "chao",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "chao", "chao", "chao", "chao", "chao", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "vazio", "chao", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "spawn", "chao",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" },
                { "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "chao", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",
                        "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio" }
        };
        mapas.put(0, mapaPadrao);
    }

    public String[][] obterMapa(Integer id) {
        if (id == null) id = 0;
        String[][] copiaMapa = new String[mapas.get(id).length][];

        for (int i = 0; i < mapas.get(id).length; i++) {
            copiaMapa[i] = mapas.get(id)[i].clone();
        }

        Point coordenadas = posRespawnMap(id);

        int posX = coordenadas.x;
        int posY = coordenadas.y;

        copiaMapa[posY][posX] = "vazio";

        return copiaMapa;
    }

    public Map<Integer, String[][]> obtemTodosMapas() {
        return mapas;
    }

    public Integer obterAltura(Integer id) {
        String[][] mapa = mapas.get(id);
        return mapa.length;     
    }

    public Integer obterLargura(Integer id) {
        return mapas.get(id)[0].length;
    }

    public Point posRespawnMap(Integer id) {
        for (int y = 0; y < obterAltura(id); y++) {
            for (int x = 0; x < obterLargura(id); x++) {
                if (mapas.get(id)[y][x].equals("spawn")) {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    public Point posVazioMap(Integer id) {
        for (int y = 0; y < obterAltura(id); y++) {
            for (int x = 0; x < obterLargura(id); x++) {
                if (mapas.get(id)[y][x].equals("vazio")) {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    public Integer salvarAtualizarMapa(ResponseMapa mapaRecebido) {
        String[][] mapaMatriz = mapaRecebido.getMatriz();

        int idMapa = mapaRecebido.getIdMapa();
        int countSpawn = 0;

        for (int y = 0; y < mapaMatriz.length; y++) {
            for (int x = 0; x < mapaMatriz[0].length; x++) {
                if (mapaMatriz[y][x].equals("spawn")) countSpawn++;
            }
        }
        
        if (countSpawn != 1) return null;

        if (idMapa < 0) {
            mapas.put(countIds, mapaMatriz);
            countIds++;
            return countIds - 1;
        } else {
            if (mapas.containsKey(idMapa)) {
                mapas.put(idMapa, mapaMatriz);
                return idMapa;
            }
            return null;
        }
    }
}
