<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-Vindo Área de Admins</title>
    <%
        if (request.getAttribute("erro") == null) {
    %>
    <link rel="stylesheet" type="text/css" href="../CSS/telaCrud.css">
    <link rel="stylesheet" type="text/css" href="../CSS/telaGetAll.css">
    <link rel="icon" href="../../Imagens/logos2.png">
    <%
    } else {
    %>
    <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaCrud.css">
    <link rel="icon" href="Imagens/logos2.png">
    <%
        }
    %>
</head>
<body>
<nav>
    <a <%if(request.getAttribute("erro") == null){%>href="../TelaInicial/areaRestrita.jsp"
       <%}else{%>href="AreaRestrita/TelaInicial/areaRestrita.jsp"<%}%>><img id="backAreaRestrita"
                                                                            <% if (request.getAttribute("erro") == null){%>src="../../Imagens/icons8-back-arrow-50.png"<%}else{%>
                                                                            src="Imagens/icons8-back-arrow-50.png"
                                                                            <%}%>alt=""></a>
</nav>
<section>

    <%
        if (request.getAttribute("erro") == null) {
    %>
    <div class="sidebar">
        <a class="selecionado" href="../../Admin">Administrador</a>
        <a href="../../Respon">Responsável</a>
        <a href="../../Transp">Transportador</a>
        <a href="../../Telefone">Telefone</a>
        <a href="../../Escola">Escola</a>
        <a href="../../BI">Gráficos</a>
    </div>
    <%
    } else {
    %>
    <div class="sidebar">
        <a class="selecionado" href="Admin">Administrador</a>
        <a href="Respon">Responsável</a>
        <a href="Transp">Transportador</a>
        <a href="Telefone">Telefone</a>
        <a href="Escola">Escola</a>
        <a class="bi">Gráficos</a>
    </div>
    <%
        }
    %>

    <!-- Forms -->
    <form class="form" action="../../Admin" method="post">
        <%
            if (request.getAttribute("erro") != null) {
        %>
        <h3 id="erro"><%= request.getAttribute("erro")%>
        </h3>
        <%
            }
        %>
        <input style="display: none;" type="text" name="method" value="delete">
        <label>
            <input placeholder="" type="text" name="idOuEmail" class="input">
            <span>ID ou Email</span>
        </label>
        <br>
        <input type="submit" class="submit" value="Deletar">
    </form>
    <!-- Opções para o CRUD -->
    <div class="botoes">
        <a href="../../Admin">
            <button>Mostras todos Admins</button>
        </a>
        <%
            if (request.getAttribute("erro") == null) {
        %>
        <a href="areaRestritaAdminId.jsp">
            <button>Buscar</button>
        </a>
        <a href="areaRestritaAdminInserir.jsp">
            <button>Inserir</button>
        </a>
        <a href="areaRestritaAdminAtualizar.jsp">
            <button>Atualizar</button>
        </a>
        <%
        } else {
        %>
        <a href="AreaRestrita/Administrador/areaRestritaAdminId.jsp">
            <button>Buscar</button>
        </a>
        <a href="AreaRestrita/Administrador/areaRestritaAdminInserir.jsp">
            <button>Inserir</button>
        </a>
        <a href="AreaRestrita/Administrador/areaRestritaAdminAtualizar.jsp">
            <button>Atualizar</button>
        </a>
        <%
            }
        %>
    </div>
</section>

<footer>
    <div class="footer-content">
        <img id="bus" <% if (request.getAttribute("erro") == null){%>src="../../Imagens/ImagemOnibusFooter.png"
             <%}else{%>src="Imagens/ImagemOnibusFooter.png"<%}%> alt="">
        <%
            if (request.getAttribute("erro") == null) {
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
</script>

</body>
</html>  
