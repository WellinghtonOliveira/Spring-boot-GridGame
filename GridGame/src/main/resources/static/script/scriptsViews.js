import { jogador, confJogadorInit } from "./scripts.js";

document.addEventListener("DOMContentLoaded", () => {
    const buttoncomecar = document.getElementById("button-comecar");
    const buttonCriarMapa = document.getElementById("button-criar-mapa");

    buttonCriarMapa.addEventListener("click", () => {
        window.location.href = "./pages/criacao/index.html"
    })

    buttoncomecar.addEventListener("click", async () => {
        if (configName() && configCor()) {
            await confJogadorInit();

            const containerEscolhas = document.getElementById("container-criacao");
            const game = document.getElementById("game");

            containerEscolhas.style.display = "none";
            game.style.display = "grid";
            window.jogadorOn = true;
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
    if (nome && nome.value < 3) {
        jogador.nome = nome;
        return true;
    }
    return false;
}