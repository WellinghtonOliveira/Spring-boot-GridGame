const url = "http://localhost:8080/";

//TODO salvar os dados do player como um objeto id, nome, cor, vida.

let id = "";
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

    document.getElementById("game").style.display = "none";
    document.getElementById("button-comecar").addEventListener("click", () => observer());

    async function init() {
        await carregarMapas();
        desenharGrid();
        desenharBlock();
        await geraJogador();
    }

    init();
    moveJogador();
    loop();
})

function loop() {
    if (teclas['ArrowRight']) {
        client.publish({
            destination: "/app/move",
            body: JSON.stringify({
                id: id,
                direcao: "ArrowRight",
            })
        });
    }

    if (teclas['ArrowLeft']) {
        client.publish({
            destination: "/app/move",
            body: JSON.stringify({
                id: id,
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
                id: id
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
                    id: id,
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
        const input = await fetch(url + "player");
        dataJogador = await input.json();
    } catch (error) {
        console.log("Error: " + error);
    }
    id = dataJogador.id;
}

function desenharJogadoresMultplayer(jogadores) {
    jogadores.forEach(jogador => {
        let player = document.getElementById(jogador.id);

        if (!player) {
            player = document.createElement("div");
            player.id = jogador.id;
            player.classList.add("player");
            game.appendChild(player);
        }

        if (jogador.id === id) {
            atualizarCamera(jogador);
        }

        player.style.transform = `translate(${jogador.x}px, ${jogador.y}px)`;
    });
}

function atualizarCamera(jogador) {
    const MARGIN = 120;

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