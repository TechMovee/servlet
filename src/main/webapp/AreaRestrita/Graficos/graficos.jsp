<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bem-Vindo Área de gráficos</title>
    <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaGetAll.css">
    <link rel="icon" href="Imagens/logos2.png">
</head>
<body>
<nav>
    <a <%if(request.getAttribute("erro") == null){%>href="AreaRestrita/TelaInicial/areaRestrita.jsp"
       <%}else{%>href="AreaRestrita/TelaInicial/areaRestrita.jsp"<%}%>><img id="backAreaRestrita"
                                                                            <% if (request.getAttribute("erro") == null){%>src="Imagens/setaAzul.png"<%}else{%>
                                                                            src="Imagens/setaAzul.png"
                                                                            <%}%>alt=""></a>
    <span class="email_session"></span>
</nav>
<section class="conteudo">
    <div class="sidebar">
        <a href="Admin">Administrador</a>
        <a href="Respon">Responsável</a>
        <a href="Transp">Transportador</a>
        <a href="Telefone">Telefone</a>
        <a href="Escola">Escola</a>
        <a class="selecionado" id="bi">Gráficos</a>
        <p><span>Use <strong>keyUp</strong> e <strong>keyDown</strong> para navegar entre áreas</span> <img class="setas" src="Imagens/keys.webp" alt=""></p>
    </div>

    <iframe title="dashbord_responsaveis" width="90%" height="80%" style="grid-column: 2/4"
            src="https://app.powerbi.com/view?r=eyJrIjoiMjE4YzVlZGUtMDc4Mi00YjNhLWFkYzctZTYxZWI0YWM3NGM2IiwidCI6ImIxNDhmMTRjLTIzOTctNDAyYy1hYjZhLTFiNDcxMTE3N2FjMCJ9"
            frameborder="0"></iframe>
</section>
<footer>
    <div class="footer-content">
        <img id="bus" src="Imagens/ImagemOnibusFooter.png" alt="">
        <div class="estrada">
            <img class="listra" src="Imagens/listra.png" alt="">
            <img class="listra" src="Imagens/listra.png" alt="">
            <img class="listra" src="Imagens/listra.png" alt="">
            <img class="listra" src="Imagens/listra.png" alt="">
            <img class="listra" src="Imagens/listra.png" alt="">
            <img class="listra" src="Imagens/listra.png" alt="">
            <img class="listra" src="Imagens/listra.png" alt="">
            <img class="listra" src="Imagens/listra.png" alt="">
        </div>
    </div>
</footer>
<script>
    const email_session = document.getElementsByClassName("email_session")[0]
    window.addEventListener("load", () => {
        email_session.innerText = "Olá, " + sessionStorage.getItem("email") + "!"
    })
    window.addEventListener("keydown", (e) => {
        if (e.key == "ArrowUp") {
            window.location.replace("Escola");
        } else if (e.key == "ArrowDown") {
            window.location.replace("Admin");
        }
    })
</script>
</body>
</html>
