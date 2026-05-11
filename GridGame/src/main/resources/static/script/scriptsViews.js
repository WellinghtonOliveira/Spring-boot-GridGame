document.addEventListener("DOMContentLoaded", () => {
    const buttoncomecar = document.getElementById("button-comecar");
    const buttonCriarMapa = document.getElementById("button-criar-mapa");

    buttonCriarMapa.addEventListener("click", () => {
        window.location.href = "./pages/criacao/index.html"
    })

    buttoncomecar.addEventListener("click", () => {
        const containerEscolhas = document.getElementById("container-criacao");
        const game = document.getElementById("game");
        containerEscolhas.style.display = "none";
        game.style.display = "grid";
        window.jogadorOn = true;
        console.log(window.jogadorOn)
    })

})