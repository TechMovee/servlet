<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-Vindo Área de Telefones</title>
    <%
        if (request.getAttribute("erro") == null) {
    %>
    <link rel="stylesheet" type="text/css" href="../CSS/telaCrud.css">
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
                                                                            <% if (request.getAttribute("erro") == null){%>src="../../Imagens/setaAzul.png"<%}else{%>
                                                                            src="Imagens/setaAzul.png"
                                                                            <%}%>alt=""></a>
    <span class="email_session"></span>
</nav>
<section class="conteudo">

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
        <p><span>Use <strong>keyUp</strong> e <strong>keyDown</strong> para navegar entre áreas</span> <img class="setas" src="../../Imagens/keys.webp" alt=""></p>
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
        <p><span>Use <strong>keyUp</strong> e <strong>keyDown</strong> para navegar entre áreas</span> <img class="setas" src="../../Imagens/keys.webp" alt=""></p>
    </div>
    <%
        }
    %>

    <%--  Conteúdo  --%>
    <div class="content">
        <div class="botoes">
            <%
                if (request.getAttribute("erro") == null) {
            %>
            <a href="areaRestritaTelefoneId.jsp">
                <button>Buscar</button>
            </a>
            <a href="areaRestritaTelefoneInserir.jsp">
                <button>Inserir</button>
            </a>
            <a href="areaRestritaTelefoneAtualizar.jsp">
                <button class="BotãoSelecionado">Atualizar</button>
            </a>
            <a href="areaRestritaTelefoneDeletar.jsp">
                <button >Deletar</button>
            </a>
            <%
            } else {
            %>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneId.jsp">
                <button>Buscar</button>
            </a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneInserir.jsp">
                <button>Inserir</button>
            </a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneAtualizar.jsp">
                <button class="BotãoSelecionado">Atualizar</button>
            </a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneDeletar.jsp">
                <button >Deletar</button>
            </a>
            <%
                }
            %>
        </div>
        <form class="form" action="../../Telefone" method="post">
            <%
                if (request.getAttribute("erro") != null) {
            %>
            <h3 id="erro"><%= request.getAttribute("erro")%>
            </h3>
            <%
                }
            %>
            <input style="display: none;" type="text" name="method" value="put">
            <label>
                <input required placeholder="" type="number" name="idAtualizar" class="input">
                <span>ID</span>
            </label>
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
                <input required placeholder="" type="text" name="cnhOuCpf" class="input">
                <span>CPF ou CNH do usuário</span>
            </label>
            <br>
            <input type="submit" class="submit" value="Atualizar">
        </form>
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
    const email_session = document.getElementsByClassName("email_session")[0]
    window.addEventListener("load", () => {
        email_session.innerText = "Olá, " + sessionStorage.getItem("email") + "!"
    })
    window.addEventListener("keydown", (e) => {
        if (e.key == "ArrowUp") {
            window.location.replace("../../Transp");
        } else if (e.key == "ArrowDown") {
            window.location.replace("../../Escola");
        }
    })
    const bi = document.getElementsByClassName("bi")[0]
    bi.addEventListener('click', () => {
        window.location.replace("BI");
    })
</script>
</body>
</html>
