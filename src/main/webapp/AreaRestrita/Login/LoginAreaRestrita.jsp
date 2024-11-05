<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%
        if (request.getAttribute("erro") == null){
    %>
        <link rel="stylesheet" href="LoginAreaRestrita.css">
        <link rel="icon" href="../../Imagens/logos2.png">
    <%
        }else{
    %>
        <link rel="stylesheet" href="AreaRestrita/Login/LoginAreaRestrita.css">
        <link rel="icon" href="Imagens/logos2.png">
    <%
        }
    %>
    <title>Área de Login</title>
</head>
<body>

    <%
        if (request.getAttribute("erro") == null){
    %>
        <a href="../../index.html"><img src="../../Imagens/icons8-back-arrow-50.png" class="backLogin" alt="Voltar"></a>
    <%
        }else{
    %>
        <a href="index.html"><img src="Imagens/icons8-back-arrow-50.png" class="backLogin" alt="Voltar"></a>
    <%
        }
    %>

    <form class="formulario" action="../../loginAdm" method="post">
        <p class="tituloFormulario" id="title">Área do Admin</p>
        <p class="mensagem">Faça seu login para acessar a Área do Admin.</p>

        <%
            if (request.getAttribute("erro") != null){
        %>
            <h3 id="messageError"><%= request.getAttribute("erro")%></h3>
        <%
            }
        %>
        <div id="formDiv">
                <label>
                    <input required placeholder="" type="email" name="email" class="input">
                    <span>E-mail</span>
                </label>

                <label>
                    <input required placeholder="" type="password" name="password" class="input">
                    <span>Senha</span>
                </label>
                <input type="submit" class="enviar" value="Entrar">
        </div>
    </form>

    <footer>
        <div class="footer-content">
            <img id="bus" <% if (request.getAttribute("erro") == null){%>src="../../Imagens/ImagemOnibusFooter.png"<%}else{%>src="Imagens/ImagemOnibusFooter.png"<%}%> alt="">
            <%
                if (request.getAttribute("erro") == null){
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
            }else{
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
</body>
</html>
