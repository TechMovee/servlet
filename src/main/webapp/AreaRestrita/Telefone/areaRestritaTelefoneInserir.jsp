<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-Vindo Área dos Telefones</title>
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
        <a href="../../Admin">Administrador</a>
        <a href="../../Respon">Responsável</a>
        <a href="../../Transp">Transportador</a>
        <a class="selecionado" href="../../Telefone">Telefone</a>
        <a href="../../Escola">Escola</a>
        <a href="../../BI">Gráficos</a>
    </div>
    <%
    } else {
    %>
    <div class="sidebar">
        <a href="Admin">Administrador</a>
        <a href="Respon">Responsável</a>
        <a href="Transp">Transportador</a>
        <a class="selecionado" href="Telefone">Telefone</a>
        <a href="Escola">Escola</a>
        <a class="bi">Gráficos</a>
    </div>
    <%
        }
    %>

    <!-- Conteúdo -->
    <form class="form" action="../../Telefone" method="post">
        <%
            if (request.getAttribute("erro") != null) {
        %>
        <h3 id="erro"><%= request.getAttribute("erro")%>
        </h3>
        <%
            }
        %>
        <label>
            <input required placeholder="" type="tel" name="telefone" class="input">
            <span>Número</span>
        </label>
        <select id="tipo" name="tipo" required>
            <option value="">Selecione o tipo de usuário</option>
            <option value="responsavel">Responsável</option>
            <option value="transportador">Transportador</option>
        </select>
        <label>
            <input required placeholder="" type="number" name="cnhOuCpf" class="input">
            <span>ID do Usuário</span>
        </label>
        <br>
        <input type="submit" class="submit" value="Cadastrar">
    </form>
    <!-- Opções para o CRUD -->
    <div class="botoes">
        <a href="../../Telefone">
            <button>Mostrar todos Telefones</button>
        </a>
        <%
            if (request.getAttribute("erro") == null) {
        %>
        <a href="areaRestritaTelefoneId.jsp">
            <button>Buscar</button>
        </a>
        <a href="areaRestritaTelefoneAtualizar.jsp">
            <button>Atualizar</button>
        </a>
        <a href="areaRestritaTelefoneDeletar.jsp">
            <button>Deletar</button>
        </a>
        <%
        } else {
        %>
        <a href="AreaRestrita/Telefone/areaRestritaTelefoneId.jsp">
            <button>Buscar</button>
        </a>
        <a href="AreaRestrita/Telefone/areaRestritaTelefoneAtualizar.jsp">
            <button>Atualizar</button>
        </a>
        <a href="AreaRestrita/Telefone/areaRestritaTelefoneDeletar.jsp">
            <button>Deletar</button>
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
