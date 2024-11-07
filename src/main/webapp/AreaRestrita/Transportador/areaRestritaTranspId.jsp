<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-Vindo Área de Transportadores</title>
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
<section>

    <%
        if (request.getAttribute("erro") == null) {
    %>
    <div class="sidebar">
        <a href="../../Admin">Administrador</a>
        <a href="../../Respon">Responsável</a>
        <a class="selecionado" href="../../Transp">Transportador</a>
        <a href="../../Telefone">Telefone</a>
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
        <a class="selecionado" href="Transp">Transportador</a>
        <a href="Telefone">Telefone</a>
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
            <a href="areaRestritaTranspId.jsp">
                <button class="BotãoSelecionado">Buscar</button>
            </a>
            <a href="areaRestritaTranspInserir.jsp">
                <button>Inserir</button>
            </a>
            <a href="areaRestritaTranspAtualizar.jsp">
                <button>Atualizar</button>
            </a>
            <a href="areaRestritaTranspDeletar.jsp">
                <button>Deletar</button>
            </a>
            <%
            } else {
            %>
            <a href="AreaRestrita/Transportador/areaRestritaTranspId.jsp">
                <button class="BotãoSelecionado">Buscar</button>
            </a>
            <a href="AreaRestrita/Transportador/areaRestritaTranspInserir.jsp">
                <button>Inserir</button>
            </a>
            <a href="AreaRestrita/Transportador/areaRestritaTranspAtualizar.jsp">
                <button>Atualizar</button>
            </a>
            <a href="AreaRestrita/Transportador/areaRestritaTranspDeletar.jsp">
                <button>Deletar</button>
            </a>
            <%
                }
            %>
        </div>
        <form class="form" action="../../Transp" method="get">
            <%
                if (request.getAttribute("erro") != null) {
            %>
            <h3 id="erro"><%= request.getAttribute("erro")%>
            </h3>
            <%
                }
            %>
            <label>
                <input required placeholder="" type="number" name="cnh" class="input" min="1">
                <span>CNH</span>
            </label>
            <input type="submit" class="submit" value="Procurar">
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

        <!-- Forms -->
        <form class="form" action="/ProjectTechMovee_war_exploded/Transp" method="get">
            <%
                if (request.getAttribute("erro") != null){
            %>
            <h3 id="erro"><%= request.getAttribute("erro")%></h3>
            <%
                }
            %>
            <label>
                <input required placeholder="" type="number" name="cnh" class="input">
                <span>CNH</span>
            </label>
            <input type="submit" class="submit" value="Procurar">
        </form>
        <!-- Opções para o CRUD -->
        <div class="botoes">
            <a href="/ProjectTechMovee_war_exploded/Transp"><button>Mostrar todos Transportadores</button></a>
            <%
                if (request.getAttribute("erro") == null){
            %>
                <a href="areaRestritaTranspInserir.jsp"><button>Inserir</button></a>
                <a href="areaRestritaTranspAtualizar.jsp"><button>Atualizar</button></a>
                <a href="areaRestritaTranspDeletar.jsp"><button>Deletar</button></a>
            <%
            }else{
            %>
                <a href="AreaRestrita/Transportador/areaRestritaTranspInserir.jsp"><button>Inserir</button></a>
                <a href="AreaRestrita/Transportador/areaRestritaTranspAtualizar.jsp"><button>Atualizar</button></a>
                <a href="AreaRestrita/Transportador/areaRestritaTranspDeletar.jsp"><button>Deletar</button></a>
            <%
                }
            %>
        </div>
    </section>

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
    </div>
</footer>
<script>
    const email_session = document.getElementsByClassName("email_session")[0]
    window.addEventListener("load", () => {
        email_session.innerText = "Olá, " + sessionStorage.getItem("email") + "!"
    })
    window.addEventListener("keydown", (e) => {
        if (e.key == "ArrowUp") {
            window.location.replace("../../Respon");
        } else if (e.key == "ArrowDown") {
            window.location.replace("../../Telefone");
        }
    })
    const bi = document.getElementsByClassName("bi")[0]
    bi.addEventListener('click', () => {
        window.location.replace("BI");
    })
</script>
</body>
</html>  
