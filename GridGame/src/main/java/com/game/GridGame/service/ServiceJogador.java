package com.game.GridGame.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.game.GridGame.dto.InfPLayers;
import com.game.GridGame.dto.ResponsePlayer;
import com.game.GridGame.entity.JogadorEntity;

@Service
public class ServiceJogador {
    private final Map<String, JogadorEntity> jogadores = new ConcurrentHashMap<>();
    private int countReqs = 1;
    private ServiceMap mapaService;

    public ServiceJogador(ServiceMap mapaService) {
        this.mapaService = mapaService;
    }

    public void moverJogador(String id, String direcao) {
        JogadorEntity jogador = jogadores.get(id);

        if (jogador == null) return;

        double velocity = jogador.getVelocityX();

        switch (direcao) {
            case "ArrowRight":
                if (((jogador.getX() + 40) / 40) > mapaService.obterLargura() + 1) return;
                if (observerColisionDirections("direita", jogador)) {
                    jogador.setX(jogador.getX() + velocity);
                } else {
                    int xBloco = ((int) ((jogador.getX() + 39 + jogador.getVelocityX()) / 40)) * 40;
                    jogador.setX(xBloco - 40);
                }

                break;
            case "ArrowLeft":
                if (((jogador.getX() - 1) / 40) < 0) return;
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

    public Collection<ResponsePlayer> getJogadores() {
        return jogadores.values()
        .stream()
        .filter(jogador -> jogador.getPass())
        .map(jogador -> new ResponsePlayer(
            jogador.getNome(),
            jogador.getCor(),
            jogador.getX(),
            jogador.getY()
        ))
        .toList();
    }

    public void observerPlayer(String id) {
        if (id == null) return;
        JogadorEntity jogador = jogadores.get(id);
        if (jogador == null) return;

        if (jogador.getY() / 40 >= mapaService.obterAltura() - 1) {
            jogador.setY((mapaService.obterAltura() - 1) * 40);
        }

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
    }

    public Boolean colisaoPosPuloFoot(JogadorEntity jogador) {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) jogador.getX() / 40;
        int y = (int) (jogador.getY() / 40);

        int xEsquerda = (int) (jogador.getX() / 40);
        int xDireita = (int) ((jogador.getX() + 39) / 40);
        int yAbaixo = (int) ((jogador.getY() + 39 + jogador.getGravity()) / 40);

        if (y + 1 < mapaService.obterAltura() &&
                x >= 0 &&
                x < mapaService.obterLargura()) {

            if (mapa[yAbaixo][xEsquerda].equals("chao") ||
                    mapa[yAbaixo][xDireita].equals("chao")) {
                return true;
            }
        }

        return false;
    }

    public Boolean observerColisionDirections(String direction, JogadorEntity jogador) {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) (jogador.getX() / 40);

        int xDireita = (int) ((jogador.getX() + 40) / 40);
        int xEsquerda = (int) ((jogador.getX() - 1) / 40);

        int yCima = (int) ((jogador.getY() + 1) / 40);
        int yBaixo = (int) ((jogador.getY() + 39) / 40);

        if (x >= 0) {
            if (direction.equals("direita") && xDireita < mapaService.obterLargura() &&
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
        String[][] mapa = mapaService.obterMapa(null);

        int xEsquerda = (int) ((jogador.getX() + 1) / 40);
        int xDireita = (int) ((jogador.getX() + 39) / 40);
        int yCima = (int) ((jogador.getY() - 1) / 40);

        if (((jogador.getY() - 1) / 40) >= 0) {
            if (mapa[yCima][xEsquerda].equals("vazio") &&
                    mapa[yCima][xDireita].equals("vazio")) {
                return true;
            }
        }
        return false;
    }

    public Boolean observerColisionFoot(JogadorEntity jogador) {
        String[][] mapa = mapaService.obterMapa(null);

        int x = (int) jogador.getX() / 40;

        int xEsquerda = (int) ((jogador.getX() + 5) / 40);
        int xDireita = (int) ((jogador.getX() + 35) / 40);
        int yAbaixo = (int) ((jogador.getY() + 40) / 40);

        if (x >= 0 &&
            x < mapaService.obterLargura()) {

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

    public void observerCanJump(JogadorEntity jogador) {
        if (jogador.getPulo()) {
            if (jogador.getQuantiaPulo() > 0) {
                if (observerColisionHead(jogador)) {

                    jogador.setY(jogador.getY() - 4);
                    jogador.setQuantiaPulo(jogador.getQuantiaPulo() - 1);

                } else {
                    jogador.setQuantiaPulo(0);
                }

            } else {
                jogador.setPulo(false);
                jogador.setEmpuxo(true);
                jogador.setQuantiaPulo(20);
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
            if (j.getId().equals(id)) jogadores.remove(id);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void verificarPlayerOff() {
        long agora = System.currentTimeMillis();
        
        for (JogadorEntity j : jogadores.values()) {
            if (agora - j.getUltimoPing() > 15000) {
                deletePlayer(j.getId());
                System.out.println("Jogador " + j.getNome() + "  ---  Deletado");
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

        String nome = data.getNome();
        String cor = data.getCor();

        if (findNamePlayers(data.getId(), nome)) {
            deletePlayer(data.getId());
            return ResponseEntity.status(404).body("Nome de usuario em uso");
        }

        jogador.setPass(true);
        
        if (nome != null && !nome.isBlank() && nome.length() <= 15) {
            jogador.setNome(nome);
        }
        
        if (cor != null && !cor.isBlank()) {
            jogador.setCor(cor);
        }
        

        return ResponseEntity.status(200).body(jogador.getNome());
    }

    public ResponsePlayer criarJogador() {
        JogadorEntity jogador = new JogadorEntity("jogador " + countReqs++);
        jogadores.put(jogador.getId(), jogador);

        jogador.setUltimoPing(System.currentTimeMillis());

        return new ResponsePlayer(
            jogador.getId(),
            jogador.getX(), 
            jogador.getY()
        );
    }
}