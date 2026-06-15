import { jogador, confJogadorInit } from "./scripts.js";

document.addEventListener("DOMContentLoaded", () => {
    const buttoncomecar = document.getElementById("button-comecar");
    const buttonCriarMapa = document.getElementById("button-criar-mapa");

    buttonCriarMapa.addEventListener("click", () => {
        window.open("./pages/criacao/index.html", "_blank", "noopener,noreferrer");
    })

    buttoncomecar.addEventListener("click", async () => {
        if (configName() && configCor()) {
            await confJogadorInit();

            if (window.jogadorOn) {
                const containerEscolhas = document.getElementById("container-criacao");
                const game = document.getElementById("game");

                containerEscolhas.style.display = "none";
                game.style.display = "grid";
            }
        }
    })

})

function configCor() {
    let cor = document.getElementById("input-color");
    if (cor) {
        jogador.cor = cor.value;
        return true;
    }
    return false;
}

function configName() {
    let nome = document.getElementById("input-text-nome");
    if (nome && nome.value.length <= 15) {
        jogador.nome = nome.value;
        return true;
    } else {
        alert("ERRO! Nome invalido\n MAX: 15 caracteres");
    }
    return false;
}