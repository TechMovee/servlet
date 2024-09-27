var botaoMenu = document.getElementById("ButtonLogin"); // Corrigido para o ID correto do botão

botaoMenu.addEventListener("click", function () {
    window.location.replace("TelaLoginCadeado/telaLoginCadeado.html"); // Corrigido para redirecionar corretamente
});

// Função para adicionar/remover a classe 'active' das divs
// divs.forEach((div) => {
//     div.addEventListener('click', () => {
//         if (div.classList.contains('active')) {
//             div.classList.remove('active');
//         } else {
//             divs.forEach(d => d.classList.remove('active'));
//             div.classList.add('active');
//         }
//     });
// });
