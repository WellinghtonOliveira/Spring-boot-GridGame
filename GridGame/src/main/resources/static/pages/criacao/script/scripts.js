let grid;

let rows;
let cols;

// Blocos existentes
const blocos = {
    "chao": "#664300",
    "spawn": "#acc95f"
}

document.addEventListener("DOMContentLoaded", () => {
    desenharGrid()

    document.getElementById("button-copiar").addEventListener("click", () => {
        copyMapa();
    })

    document.getElementById("button-gerar").addEventListener("click", () => {
        acrecentaNaGrid()
    })
})

async function copyMapa() {

    verificardorSpawn();

    const matriz = obterMatrizMapa();

    const linhasFormatadas = matriz.map(linha => {
        const conteudoLinha = linha.map(item => `"${item}"`).join(", ");
        return `    {${conteudoLinha}}`;
    });

    const mapaFormatado = `{\n${linhasFormatadas.join(",\n")}\n};`;

    try {
        await navigator.clipboard.writeText(mapaFormatado);
        alert("Estrutura do mapa copiada no formato customizado!");
    } catch (error) {
        console.error("Erro ao copiar:", error);
    }
}

function verificardorSpawn() {
    const container = document.getElementById("grid-container");
    const cells = container.querySelectorAll("div");

    let countSpawn = 0;

    cells.forEach((cell) => {
        if (cell.getAttribute("name") == "spawn") countSpawn++;
    })

    
    if (countSpawn === 1) return true;
    return false;
}

function obterMatrizMapa() {
    const gridContainer = document.getElementById("grid-container");
    const totalRows = parseInt(document.getElementById("input-linhas").value);
    const totalCols = parseInt(document.getElementById("input-colunas").value);

    let MAPA = [];

    for (let y = 0; y < totalRows; y++) {
        let linha = [];
        for (let x = 0; x < totalCols; x++) {
            const idCelula = y * totalCols + x;
            const celula = document.getElementById(`${idCelula}`);

            if (celula && celula.hasAttribute('name')) {
                linha.push(celula.getAttribute('name'));
            } else {
                linha.push('vazio');
            }
        }
        MAPA.push(linha);
    }

    return MAPA;
}

function observer() {
    const divs = document.querySelectorAll(".cell");

    let segurouClick = false;
    let apagando = false;

    divs.forEach((e) => {
        e.addEventListener("mouseenter", () => {
            if (segurouClick) {
                if (e.hasAttribute('name') && apagando) {
                    apagarBlocos(e);
                    return;
                }
                if (!apagando) desenharBlocos(e);
            }
        });

        e.addEventListener("mousedown", (eventoButton) => {
            eventoButton.preventDefault()

            segurouClick = true;
            if (e.hasAttribute('name')) {
                apagando = true;
                apagarBlocos(e);
                return;
            };
            desenharBlocos(e);
        });
        e.addEventListener("mouseup", () => {
            segurouClick = false;
            apagando = false;
        });
    })
}

function apagarBlocos(e) {
    const bloco = document.getElementById(`${e.id}`);

    bloco.style.borderRight = "1px solid rgba(255, 255, 255, 0.3)";
    bloco.style.borderBottom = "1px solid rgba(255, 255, 255, 0.3)";

    bloco.removeAttribute('name');
    bloco.style.background = `none`;
}

function desenharBlocos(e) {
    const bloco = document.getElementById(`${e.id}`);

    const listaSelect = document.getElementById("lista-blocos");
    const objetos = listaSelect.getElementsByTagName("option");

    bloco.style.borderRight = "none";
    bloco.style.borderBottom = "none";

    let nomeBloco;

    Object.entries(objetos).forEach(([chave, valor]) => {
        if (valor.selected) {
            nomeBloco = valor.value;
        }
    });

    bloco.setAttribute('name', nomeBloco);
    bloco.style.background = `${blocos[nomeBloco]}`;
}

function acrecentaNaGrid() {
    rows = document.getElementById("input-linhas").value;
    cols = document.getElementById("input-colunas").value;

    const ultimoIdIndex = Number(document.querySelector('#grid-container div:last-child').id) + 1;

    if (rows > 500 || cols > 500 || rows < 10 || cols < 10) {
        alert("ERRO: Quantia muito alta ou muito baixa!\n\n MAX: 500 | MIN: 10");
        return;
    }

    grid.style.gridTemplateColumns = `repeat(${cols}, 40px)`;
    grid.style.gridTemplateRows = `repeat(${rows}, 40px)`;

    if ((cols * rows) > ultimoIdIndex) {
        for (let i = ultimoIdIndex; i < cols * rows; i++) {
            const cell = document.createElement('div');
            const x = i % cols;
            const y = Math.floor(i / cols);

            if (y === 0) {
                cell.style.borderTop = "1px solid rgba(255,255,255,0.3)";
            }

            if (x === 0) {
                cell.style.borderLeft = "1px solid rgba(255,255,255,0.3)";
            }

            cell.classList.add("cell");
            cell.id = `${i}`;
            grid.appendChild(cell)
        }
    } else if ((cols * rows) < ultimoIdIndex) {
        for (let i = ultimoIdIndex; i > cols * rows; i--) {
            const cell = document.querySelector('#grid-container div:last-child');
            cell.remove();
        }
    }
}

function desenharGrid() {
    grid = document.getElementById("grid-container");

    rows = document.getElementById("input-linhas").value;
    cols = document.getElementById("input-colunas").value;

    if (rows > 500 || cols > 500 || rows <= 10 || cols <= 10) {
        alert("ERRO: Quantia muito alta ou muito baixa!\n\n MAX: 500 | MIN: 10");
        return;
    }

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

        cell.classList.add("cell");
        cell.id = `${i}`;
        grid.appendChild(cell)
    }

    observer()
}