var botaoPlay = document.getElementById("ButtonPlayStore");
var botaoApp = document.getElementById("ButtonAppStore");
var botaoMenu = document.getElementById("buttonAdm");

botaoPlay.addEventListener("click", function () {
    window.open("https://play.google.com", "_blank");
});

botaoApp.addEventListener("click", function () {
    window.open("https://apple.com/app-store/", "_blank");
});

botaoMenu.addEventListener("click", function () {
    window.location.replace("AreaRestrita/Login/LoginAreaRestrita.jsp");
});

