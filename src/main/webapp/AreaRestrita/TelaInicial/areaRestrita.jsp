<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-Vindo Área Restrita</title>
    <%
        if (request.getAttribute("passouPorServlet") != null) {
    %>
    <link rel="stylesheet" href="AreaRestrita/TelaInicial/areaRestrita.css">
    <link rel="icon" href="Imagens/logos2.png">
    <%
    } else {
    %>
    <link rel="stylesheet" href="areaRestrita.css">
    <link rel="icon" href="../../Imagens/logos2.png">
    <%
        }
    %>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.5.0/fonts/remixicon.css" rel="stylesheet">
</head>
<body>
<nav>
    <a <%if(request.getAttribute("passouPorServlet") == null){%>href="../../index.html"
       <%}else{%>href="index.html"<%}%>><img id="backAreaRestrita"
                                             <% if (request.getAttribute("passouPorServlet") == null){%>src="../../Imagens/setaAzul.png"<%}else{%>
                                             src="Imagens/setaAzul.png" <%}%>alt="Voltar"></a>
    <span class="email_session"></span>
</nav>

<!-- Sidebar -->
<section>
    <%
        if (request.getAttribute("passouPorServlet") == null) {
    %>
    <div class="sidebar">
        <a href="../../Admin">Administrador</a>
        <a href="../../Respon">Responsável</a>
        <a href="../../Transp">Transportador</a>
        <a href="../../Telefone">Telefone</a>
        <a href="../../Escola">Escola</a>
        <a class="bi">Gráficos</a>
    </div>
    <%
    } else {
    %>
    <div class="sidebar">
        <a href="Admin">Administrador</a>
        <a href="Respon">Responsável</a>
        <a href="Transp">Transportador</a>
        <a href="Telefone">Telefone</a>
        <a href="Escola">Escola</a>
        <a class="bi">Gráficos</a>
    </div>
    <%
        }
    %>

    <div class="content">
        <h1>Bem Vindo a Área Restrita!</h1>
        <p>Selecione qual área você quer acessar!</p>
    </div>
</section>

<footer>
    <div class="footer-content">
        <img id="bus"
             <% if (request.getAttribute("passouPorServlet") == null){%>src="../../Imagens/ImagemOnibusFooter.png"
             <%}else{%>src="Imagens/ImagemOnibusFooter.png"<%}%> alt="">
        <%
            if (request.getAttribute("passouPorServlet") == null) {
        %>
        <div class="estrada">
            <img class="listra" src="../../Imagens/listra.png" alt="">
            <img class="listra" src="../../Imagens/listra.png" alt="">
            <img class="listra" src="../../Imagens/listra.png" alt="">
            <img class="listra" src="../../Imagens/listra.png" alt="">
            <img class="listra" src="../../Imagens/listra.png" alt="">
            <img class="listra" src="../../Imagens/listra.png" alt="">
            <img class="listra" src="../../Imagens/listra.png" alt="">
            <img class="listra" src="../../Imagens/listra.png" alt="">
        </div>
        <%
        } else {
        %>
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
        <%
            }
        %>
    </div>
</footer>

<script>
    const bi = document.getElementsByClassName("bi")[0]
    bi.addEventListener('click', () => {
        window.location.replace("BI");
    })
    const email_session = document.getElementsByClassName("email_session")[0]
    window.addEventListener("load", () => {
        email_session.innerText = "Olá, " + sessionStorage.getItem("email") + "!"
    })
</script>

</body>
</html>

