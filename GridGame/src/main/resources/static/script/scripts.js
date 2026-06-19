const url = "http://localhost:8080/";

let idMapa = 0; 

export let jogador = {
    "id": "",
    "idMapa": idMapa,
    "nome": "PLAYER",
    "cor": "#e0e0e0"
}

const displayMinimapa = document.getElementById("display-grid-mapa");

let grid;
let tela;
let game;
let MAPAS = [];

let cameraX = 0;
let cameraY = 0;

const teclas = {
    "ArrowRight": false,
    "ArrowLeft": false,
}

document.addEventListener("DOMContentLoaded", () => {
    game = document.getElementById("game");
    grid = document.getElementById("grid");
    tela = document.getElementById("tela-fundo");

    async function init() {
        await carregarMapas();
        desenharGrid();
        desenharBlock();
        selecaoMapa();
    }

    init();
})

function selecaoMapa() {
    const select = document.getElementById("select-mapas");

    select.addEventListener("change", () => {
        idMapa = select.value;
        jogador.idMapa = idMapa;

        desenharGrid();
        desenharBlock();
    });
}

export async function confJogadorInit() {
    await geraJogador();
    if (window.jogadorOn) {
        observer();
        moveJogador();
        loop();
        isPlayerOff()
    }
}

function isPlayerOff() {
    setInterval(() => {
        client.publish({
            destination: "/app/ping",
            body: JSON.stringify({
                id: jogador.id
            })
        });
    }, 2000)
}

function loop() {
    if (teclas['ArrowRight']) {
        client.publish({
            destination: "/app/move",
            body: JSON.stringify({
                id: jogador.id,
                direcao: "ArrowRight",
            })
        });
    }

    if (teclas['ArrowLeft']) {
        client.publish({
            destination: "/app/move",
            body: JSON.stringify({
                id: jogador.id,
                direcao: "ArrowLeft",
            })
        });
    }

    requestAnimationFrame(loop);
}

function observer() {
    setInterval(() => {
        client.publish({
            destination: "/app/observer",
            body: JSON.stringify({
                id: jogador.id
            })
        });
    }, 16)
}

function moveJogador() {
    let podeClicar = false;

    document.addEventListener('keydown', (e) => {
        if (!window.jogadorOn) return;
        if (e.key == "ArrowRight") {
            teclas["ArrowLeft"] == false
            teclas[e.key] = true;
        }

        if (e.key == "ArrowLeft") {
            teclas["ArrowRight"] == false
            teclas[e.key] = true;
        }

        if (e.key == "ArrowUp") {
            if (e.repeat) return;
            client.publish({
                destination: "/app/move",
                body: JSON.stringify({
                    id: jogador.id,
                    direcao: "ArrowUp",
                })
            });
        }
    });

    document.addEventListener('keyup', (e) => {
        if (!window.jogadorOn) return;

        if (e.key === "ArrowRight" || e.key === "ArrowLeft") {
            teclas[e.key] = false;
            client.publish({
                destination: "/app/stop",
                body: e.key
            });
        }
    });
}

async function geraJogador() {
    let dataJogador;

    try {
        const inputId = await fetch(url + "player", { method: "GET" });
        dataJogador = await inputId.json();
        jogador.id = dataJogador.id;

        const inputDados = await fetch(url + "playerUpdata", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(jogador)
        });

        if (inputDados.ok && inputId.ok) {
            jogador.nome = await inputDados.text();
            window.jogadorOn = true;
        } else {
            alert("Erro ao iniciar\nVerifique seu nome")
        }
    } catch (error) {
        console.log("Error: " + error);
    }
}

export function desenharJogadoresMultplayer(jogadores) {

    const jogadoresAtivos = new Set();

    jogadores.forEach(elJogador => {
        if (elJogador.idMapa != idMapa) return;

        let player = document.getElementById(elJogador.nome);
        let boxName;

        jogadoresAtivos.add(elJogador.nome);

        if (player == null) {
            player = document.createElement("div");
            boxName = document.createElement("p");

            player.id = elJogador.nome;
            player.classList.add("player");
            player.style.background = `${elJogador.cor}`

            boxName.textContent = `${elJogador.nome}`;
            boxName.classList.add("playerName");

            player.appendChild(boxName);
            game.appendChild(player);
        } else {
            boxName = player.querySelector("p");
        }

        if (elJogador.nome === jogador.nome) {
            player.style.zIndex = "11";
            atualizarCamera(elJogador);
        }

        player.style.transform = `translate(${elJogador.x}px, ${elJogador.y}px)`;
    });

    apagaJogadorOffline(jogadoresAtivos);
}

function apagaJogadorOffline(jogadoresAtivos) {
    const jogadores = document.querySelectorAll(".player") || null;

    document.querySelectorAll(".player").forEach(player => {
        if (!jogadoresAtivos.has(player.id)) {
            player.remove();
        }
    });
}

function atualizarCamera(jogador) {
    const MARGIN = 7 * 40;

    const limitLeft = game.getBoundingClientRect().left - tela.getBoundingClientRect().left;
    const limitRight = game.getBoundingClientRect().right - tela.getBoundingClientRect().right;
    const limitUp = game.getBoundingClientRect().up - tela.getBoundingClientRect().up;
    const limitDown = game.getBoundingClientRect().down - tela.getBoundingClientRect().down;

    const screenW = tela.offsetWidth;
    const screenH = tela.offsetHeight;

    const playerScreenX = jogador.x - cameraX;
    const playerScreenY = jogador.y - cameraY;

    if (playerScreenX < MARGIN) { // esquerda
        if (limitLeft < 0) cameraX -= (MARGIN - playerScreenX);
    }

    if (playerScreenX > screenW - MARGIN) { // direita
        if (limitRight > 0) cameraX += (playerScreenX - (screenW - MARGIN));
    }

    if (playerScreenY < MARGIN) {
        if (limitUp > 0) cameraY -= (MARGIN - playerScreenY);
    }

    if (playerScreenY > screenH - MARGIN) {
        if (limitDown < 0) cameraY += (playerScreenY - (screenH - MARGIN));
    }

    game.style.transform = `translate(${-cameraX}px, ${-cameraY}px)`;
}

function desenharBlock() {
    const rows = MAPAS[idMapa].length;
    const cols = MAPAS[idMapa][0].length;

    for (let y = 0; y < rows; y++) {
        for (let x = 0; x < cols; x++) {

            if (MAPAS[idMapa][y][x] == "chao") {
                const index = y * cols + x;
                const cell = document.getElementById(`cell-num-${index}`);
                const cellMinimapa = document.getElementById(`cell-num-${index}-minimapa`);

                cellMinimapa.style.backgroundColor = '#664300';
                cellMinimapa.style.border = 'none';

                cell.style.backgroundColor = '#664300';
                cell.style.border = 'none';
            }
        }
    }
}

function desenharGrid() {

    const rows = MAPAS[idMapa].length;
    const cols = MAPAS[idMapa][0].length;

    displayMinimapa.style.gridTemplateColumns = `repeat(${cols}, 10px)`;
    displayMinimapa.style.gridTemplateRows = `repeat(${rows}, 10px)`;

    displayMinimapa.innerHTML = '';

    grid.style.gridTemplateColumns = `repeat(${cols}, 40px)`;
    grid.style.gridTemplateRows = `repeat(${rows}, 40px)`;

    grid.innerHTML = '';

    for (let i = 0; i < cols * rows; i++) {
        const cell = document.createElement('div');
        const cellMinimapa = document.createElement('div');

        const x = i % cols;
        const y = Math.floor(i / cols);

        if (y === 0) {
            cellMinimapa.style.borderTop = "1px solid rgba(255,255,255,0.3)";
            cell.style.borderTop = "1px solid rgba(255,255,255,0.3)";
        }

        if (x === 0) {
            cellMinimapa.style.borderLeft = "1px solid rgba(255,255,255,0.3)";
            cell.style.borderLeft = "1px solid rgba(255,255,255,0.3)";
        }

        cellMinimapa.id = `cell-num-${i}-minimapa`;
        cellMinimapa.classList.add("cellMinimapa");
        displayMinimapa.appendChild(cellMinimapa);

        cell.id = `cell-num-${i}`;
        cell.classList.add("cell");
        grid.appendChild(cell);
    }
}

async function carregarMapas() {

    const selectMapas = document.getElementById("select-mapas");

    try {
        const input = await fetch(url + `maps`);
        MAPAS = await input.json();

        let quantidade = Object.keys(MAPAS).length;

        if (quantidade > 1) {
            for (let i = 1; i < quantidade; i++) {
                const op = document.createElement("option");
                op.value = i;
                op.textContent = `MAPA ${i}`;

                selectMapas.appendChild(op);
            }
        }

    } catch (error) {
        console.log("Erro: " + error);
    }
}

// TODO o restart após o jogador sair
// TODO o checkpoint após o jogador morrer 