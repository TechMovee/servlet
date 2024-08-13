
const divs = document.querySelectorAll('.features .card');
var botaoMenu = document.getElementById("botaoLogin")
    
botaoMenu.addEventListener("click", function(){
window.location.replace("TelaCadastro/index.html");
    
    
})
divs.forEach((div) => {
  div.addEventListener('click', () => {
    if (div.classList.contains('active')) {
      div.classList.remove('active');
    } else {
      divs.forEach(d => d.classList.remove('active'));
      div.classList.add('active');
    }
  });
});

