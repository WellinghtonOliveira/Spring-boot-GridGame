document.addEventListener("DOMContentLoaded", () => {
    const buttoncomecar = document.getElementById("button-comecar");
    const buttonCriarMapa = document.getElementById("button-criar-mapa");

    buttonCriarMapa.addEventListener("click", () => {
        window.location.href = "./pages/criacao/index.html"
    })

    buttoncomecar.addEventListener("click", () => {
        const containerEscolhas = document.getElementById("container-criacao");
        const camera = document.getElementById("camera");
        const game = document.getElementById("game");
        containerEscolhas.style.display = "none";
        game.style.display = "grid";
        window.jogadorOn = true;


        camera.style.width = "100vw";
        camera.style.height = "100vh";
        camera.style.overflow = "hidden";
        camera.style.position = "relative";
    })

})