const url = "http://localhost:8080/";

export let jogador = {
    "id": "",
    "nome": "PLAYER",
    "cor": "#e0e0e0"
}

let grid;
let tela;
let game;
let MAPAS;

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
    }

    init();
})

export async function confJogadorInit() {
    await geraJogador();
    if (window.jogadorOn) {
        observer();
        moveJogador();
        loop();
    }
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
            teclas[e.key] = true;
        }

        if (e.key == "ArrowLeft") {
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

        if (inputDados.ok) {
            window.jogadorOn = true;
        }
    } catch (error) {
        console.log("Error: " + error);
    }
}

export function desenharJogadoresMultplayer(jogadores) {

    jogadores.forEach(elJogador => {
        let player = document.getElementById(elJogador.nome);
        let boxName;

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
    const rows = MAPAS.length;
    const cols = MAPAS[0].length;

    for (let y = 0; y < rows; y++) {
        for (let x = 0; x < cols; x++) {

            if (MAPAS[y][x] == "chao") {
                const index = y * cols + x;
                const cell = document.getElementById(`cell-num-${index}`);

                cell.style.backgroundColor = '#664300';
                cell.style.border = 'none';
            }
        }
    }
}

function desenharGrid() {
    const rows = MAPAS.length;
    const cols = MAPAS[0].length;

    grid.style.gridTemplateColumns = `repeat(${cols}, 40px)`;
    grid.style.gridTemplateRows = `repeat(${rows}, 40px)`;

    grid.innerHTML = '';

    for (let i = 0; i < cols * rows; i++) {
        const cell = document.createElement('div');
        const x = i % cols;
        const y = Math.floor(i / cols);

        if (y === 0) {
            cell.style.borderTop = "1px solid rgba(255,255,255,0.3)";
        }

        if (x === 0) {
            cell.style.borderLeft = "1px solid rgba(255,255,255,0.3)";
        }

        cell.id = `cell-num-${i}`;
        cell.classList.add("cell");
        grid.appendChild(cell)
    }
}

async function carregarMapas() {
    try {

        const input = await fetch(url + "maps");
        MAPAS = await input.json();

    } catch (error) {
        console.log("Erro: " + error);
    }
}