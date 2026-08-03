package com.game.GridGame.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.awt.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.game.GridGame.dto.InfPLayers;
import com.game.GridGame.dto.ResponsePlayerInfos;
import com.game.GridGame.dto.ResponsePlayerId;
import com.game.GridGame.entity.JogadorEntity;

@Service
public class ServiceJogador {
    private final Map<String, JogadorEntity> jogadores = new ConcurrentHashMap<>();

    private ServiceMap mapaService;

    private int countReqs = 1;

    public ServiceJogador(ServiceMap mapaService) {
        this.mapaService = mapaService;
    }

    public void moverJogador(String id, String direcao) {
        JogadorEntity jogador = jogadores.get(id);

        if (jogador == null)
            return;

        double velocity = jogador.getVelocityX();

        switch (direcao) {
            case "ArrowRight":
                if (((jogador.getX() + 40) / 40) > mapaService.obterLargura(jogador.getIdMapa()) + 1)
                    return;
                if (observerColisionDirections("direita", jogador)) {
                    jogador.setX(jogador.getX() + velocity);
                } else {
                    int xBloco = ((int) ((jogador.getX() + 39 + jogador.getVelocityX()) / 40)) * 40;
                    jogador.setX(xBloco - 40);
                }

                break;
            case "ArrowLeft":
                if (((jogador.getX() - 1) / 40) < 0)
                    return;
                if (observerColisionDirections("esquerda", jogador)) {
                    jogador.setX(jogador.getX() - velocity);
                } else {
                    int xBloco = ((int) ((jogador.getX() + 39 + jogador.getVelocityX()) / 40)) * 40;
                    jogador.setX(xBloco - 40);
                }

                break;
            case "ArrowUp":
                if (observerColisionFoot(jogador)) {
                    jogador.setPulo(true);
                    jogador.setEmpuxo(false);
                }

                break;
        }
    }

    public Collection<ResponsePlayerInfos> getJogadores() {
        return jogadores.values()
                .stream()
                .filter(jogador -> jogador.getPass())
                .map(jogador -> new ResponsePlayerInfos(
                    jogador.getNome(),
                    jogador.getCor(),
                    jogador.getIdMapa(),
                    jogador.getX(),
                    jogador.getY()))
                .toList();
    }

    public void observerPlayer(String id) {
        if (id == null) return;
        JogadorEntity jogador = jogadores.get(id);
        if (jogador == null) return;
        
        observerCanJump(jogador);
        boolean noChao = observerColisionFoot(jogador);
        
        
        if (!noChao && jogador.getEmpuxo()) {
            if (colisaoPosPuloFoot(jogador)) {
                int yBloco = ((int) ((jogador.getY() + 39 + jogador.getGravity()) / 40)) * 40;
                jogador.setY(yBloco - 40);
                jogador.setGravity(0);
            }
            gravidade(jogador);
        } else if (noChao) {
            jogador.setGravity(0);
        }
        observerVida(jogador);
    }

    public Boolean colisaoPosPuloFoot(JogadorEntity jogador) {
        String[][] mapa = mapaService.obterMapa(jogador.getIdMapa());

        int x = (int) jogador.getX() / 40;
        int y = (int) (jogador.getY() / 40);

        int xEsquerda = (int) (jogador.getX() / 40);
        int xDireita = (int) ((jogador.getX() + 39) / 40);
        int yAbaixo = (int) ((jogador.getY() + 39 + jogador.getGravity()) / 40);

        if (y + 1 < mapaService.obterAltura(jogador.getIdMapa()) &&
            x >= 0 &&
            x < mapaService.obterLargura(jogador.getIdMapa())) {

            if (yAbaixo >= mapaService.obterAltura(jogador.getIdMapa())) {
                return true;
            }

            if (mapa[yAbaixo][xEsquerda].equals("letal") ||
                mapa[yAbaixo][xDireita].equals("letal")) {

                jogador.setVida(0);
                return true;
            }

            if (mapa[yAbaixo][xEsquerda].equals("chao") ||
                    mapa[yAbaixo][xDireita].equals("chao")) {
                return true;
            }
        }

        return false;
    }

    public Boolean observerColisionDirections(String direction, JogadorEntity jogador) {
        String[][] mapa = mapaService.obterMapa(jogador.getIdMapa());

        int x = (int) (jogador.getX() / 40);

        int xDireita = (int) ((jogador.getX() + 40) / 40);
        int xEsquerda = (int) ((jogador.getX() - 1) / 40);

        int yCima = (int) ((jogador.getY() + 1) / 40);
        int yBaixo = (int) ((jogador.getY() + 39) / 40);

        if (x >= 0) {
            if (direction.equals("direita") && xDireita < mapaService.obterLargura(jogador.getIdMapa()) &&
                mapa[yCima][xDireita].equals("letal") &&
                mapa[yBaixo][xDireita].equals("letal")) {

                jogador.setVida(0);
            } else if (direction.equals("esquerda") &&
                mapa[yCima][xEsquerda].equals("letal") &&
                mapa[yBaixo][xEsquerda].equals("letal")) {

                jogador.setVida(0);
                return true;
            }

            if (direction.equals("direita") && xDireita < mapaService.obterLargura(jogador.getIdMapa()) &&
                mapa[yCima][xDireita].equals("vazio") &&
                mapa[yBaixo][xDireita].equals("vazio")) {
                return true;
            } else if (direction.equals("esquerda") &&
                mapa[yCima][xEsquerda].equals("vazio") &&
                mapa[yBaixo][xEsquerda].equals("vazio")) {
                return true;
            }
        }
        return false;
    }

    public Boolean observerColisionHead(JogadorEntity jogador) {
        String[][] mapa = mapaService.obterMapa(jogador.getIdMapa());

        int xEsquerda = (int) ((jogador.getX() + 1) / 40); 
        int xDireita = (int) ((jogador.getX() + 39) / 40);
        int yCima = (int) ((jogador.getY() - 1) / 40);

        if (((jogador.getY() - 1) / 40) >= 0) { 
            if (mapa[yCima][xEsquerda].equals("letal") ||
                mapa[yCima][xDireita].equals("letal")) {

                jogador.setVida(0);
            }
            
            if (mapa[yCima][xEsquerda].equals("vazio") &&
                mapa[yCima][xDireita].equals("vazio")) {
                return true;
            }
        }
        return false;
    }

    public Boolean observerColisionFoot(JogadorEntity jogador) {
        String[][] mapa = mapaService.obterMapa(jogador.getIdMapa());

        int x = (int) jogador.getX() / 40;

        int xEsquerda = (int) ((jogador.getX() + 5) / 40);
        int xDireita = (int) ((jogador.getX() + 35) / 40);
        int yAbaixo = (int) ((jogador.getY() + 40) / 40);

        if (yAbaixo >= mapaService.obterAltura(jogador.getIdMapa())) return true;

        if (x >= 0 &&
            x < mapaService.obterLargura(jogador.getIdMapa())) {
            if (mapa[yAbaixo][xEsquerda].equals("letal") ||
                mapa[yAbaixo][xDireita].equals("letal")) {

                jogador.setVida(0);
                return true;
            }

            if (mapa[yAbaixo][xEsquerda].equals("chao") ||
                mapa[yAbaixo][xDireita].equals("chao")) {
                return true;
            }
        }
        return false;
    }

    public Boolean findNamePlayers(String id, String nome) {
        for (JogadorEntity j : jogadores.values()) {
            if (j.getNome().equals(nome)) {
                return true;
            }
        }

        return false;
    }

    public void observerVida(JogadorEntity jogador) {
        int vidaJogador = jogador.getVida();
        
        if (vidaJogador == 0) {
            System.out.println("Jogador: " + jogador.getNome() + " --- Morto");
            deletePlayer(jogador.getId());
        }
    }

    public void observerCanJump(JogadorEntity jogador) {
        if (jogador.getPulo()) {
            if (jogador.getQuantiaPulo() > 0) {
                if (observerColisionHead(jogador)) {

                    jogador.setY(jogador.getY() - 5);
                    jogador.setQuantiaPulo(jogador.getQuantiaPulo() - 1);

                } else {
                    jogador.setQuantiaPulo(0);
                }

            } else {
                jogador.setPulo(false);
                jogador.setEmpuxo(true);
                jogador.setQuantiaPulo(16);
            }
        }
    }

    public void gravidade(JogadorEntity jogador) {
        jogador.setY(jogador.getY() + jogador.getGravity());

        if (jogador.getGravity() < 15) {
            jogador.setGravity(jogador.getGravity() + 1);
        }

    }

    public void deletePlayer(String id) {
        for (JogadorEntity j : jogadores.values()) {
            if (j.getId().equals(id))
                jogadores.remove(id);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void verificarPlayerOff() {
        long agora = System.currentTimeMillis();

        for (JogadorEntity j : jogadores.values()) {
            if (agora - j.getUltimoPing() > 15000) {
                deletePlayer(j.getId());
                System.out.println("Jogador: " + j.getNome() + "  ---  Deletado");
            }
        }
    }

    public void atualizaUltimoPing(String id) {
        JogadorEntity jogador = jogadores.get(id);

        if (jogador != null) {
            jogador.setUltimoPing(System.currentTimeMillis());
        }
    }

    public ResponseEntity<String> updatePlayer(InfPLayers data) {
        if (data.getId() == null) return ResponseEntity.status(404).build();
        JogadorEntity jogador = jogadores.get(data.getId());

        int idMapa = data.getIdMapa();

        String nome = data.getNome();
        String cor = data.getCor();

        if (findNamePlayers(data.getId(), nome)) {
            deletePlayer(data.getId());
            return ResponseEntity.status(404).body("Nome de usuario em uso");
        }

        jogador.setIdMapa(idMapa);
        jogador.setPass(true);
        jogador.setUltimoPing(System.currentTimeMillis());

        Point coordenadas = mapaService.posRespawnMap(jogador.getIdMapa());

        if (coordenadas != null) {
            double spawnX = coordenadas.x;
            double spawnY = coordenadas.y;

            jogador.setX(spawnX * 40);
            jogador.setY(spawnY * 40);
        } else {
            Point spawnVazioProximo = mapaService.posVazioMap(jogador.getIdMapa());

            double spawnX = spawnVazioProximo.x;
            double spawnY = spawnVazioProximo.y;

            jogador.setX(spawnX * 40);
            jogador.setY(spawnY * 40);
        }

        if (nome != null && !nome.isBlank() && nome.length() <= 15) {
            jogador.setNome(nome);
        }

        if (cor != null && !cor.isBlank()) {
            jogador.setCor(cor);
        }

        return ResponseEntity.status(200).body(jogador.getNome());
    }

    public ResponsePlayerId criarJogadorAddId() {
        JogadorEntity jogador = new JogadorEntity("jogador " + countReqs++);
        jogadores.put(jogador.getId(), jogador);

        return new ResponsePlayerId(jogador.getId());
    }
}