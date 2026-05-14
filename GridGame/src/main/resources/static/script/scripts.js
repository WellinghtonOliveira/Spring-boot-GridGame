const grid = document.querySelector("#grid");
const url = "http://localhost:8080/";

let MAPAS;
let posOldX;
let posOldY;

const teclas = {
    "ArrowRight": false,
    "ArrowLeft": false,
}

document.addEventListener("DOMContentLoaded", () => {
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
            body: "ArrowRight"
        });
    }

    if (teclas['ArrowLeft']) {
        client.publish({
            destination: "/app/move",
            body: "ArrowLeft"
        });
    }

    requestAnimationFrame(loop);
}

function observer() {
    setInterval(() => {
        client.publish({
            destination: "/app/observer"
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
                body: "ArrowUp"
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

    let x = dataJogador.x;
    let y = dataJogador.y;

    posOldX = x;
    posOldY = y;

    desenharJogador(dataJogador);
}

function desenharJogador(jogador) {
    const player = document.getElementById("player");

    player.style.transform = `translate(${jogador.x}px, ${jogador.y}px)`
    player.style.display = 'block';
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