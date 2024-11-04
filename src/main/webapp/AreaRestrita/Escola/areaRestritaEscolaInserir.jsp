<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-Vindo Área de Admins</title>
    <%
        if (request.getAttribute("erro") == null){
    %>
        <link rel="stylesheet" type="text/css" href="../CSS/telaCrud.css">
        <link rel="icon" href="../../Imagens/logos2.png">
    <%
    }else{
    %>
        <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaCrud.css">
        <link rel="icon" href="Imagens/logos2.png">
    <%
        }
    %>
</head>
<body>
    <nav>
        <a <%if(request.getAttribute("erro") == null){%>href="../TelaInicial/areaRestrita.jsp"<%}else{%>href="AreaRestrita/TelaInicial/areaRestrita.jsp"<%}%>><img id="backAreaRestrita" <% if (request.getAttribute("erro") == null){%>src="../../Imagens/icons8-back-arrow-50.png"<%}else{%> src="Imagens/icons8-back-arrow-50.png"<%}%>alt=""></a>
    </nav>
    <section>

        <%
            if (request.getAttribute("erro") == null){
        %>
            <div class="sidebar">
                <a href="../../Admin">Administrador</a>
                <a href="../../Respon">Responsável</a>
                <a href="../../Transp">Transportador</a>
                <a href="../../Telefone">Telefone</a>
                <a class="selecionado" href="../../Escola">Escola</a>
            </div>
        <%
        }else{
        %>
            <div class="sidebar">
                <a href="Admin">Administrador</a>
                <a href="Respon">Responsável</a>
                <a href="Transp">Transportador</a>
                <a href="Telefone">Telefone</a>
                <a class="selecionado" href="Escola">Escola</a>
            </div>
        <%
            }
        %>

        <!-- Conteúdo -->

        <form class="form" action="/ProjectTechMovee_war_exploded/Escola" method="post">
            <%
                if (request.getAttribute("erro") != null){
            %>
            <h3 id="erro"><%= request.getAttribute("erro")%></h3>
            <%
                }
            %>
            <label>
                <input required placeholder="" type="text" name="nome" class="input">
                <span>Nome</span>
            </label>
            <br>
            <input type="submit" class="submit" value="Cadastrar">
        </form>
            <!-- Opções para o CRUD -->
        <div class="botoes">
            <a href="/ProjectTechMovee_war_exploded/Escola"><button>Mostrar todos Admins</button></a>
            <%
                if (request.getAttribute("erro") == null){
            %>
            <a href="areaRestritaEscolaId.jsp"><button>Buscar</button></a>
            <a href="areaRestritaEscolaAtualizar.jsp"><button>Atualizar</button></a>
            <a href="areaRestritaEscolaDeletar.jsp"><button>Deletar</button></a>
            <%
            }else{
            %>
            <a href="AreaRestrita/Escola/areaRestritaEscolaId.jsp"><button>Buscar</button></a>
            <a href="AreaRestrita/Escola/areaRestritaEscolaAtualizar.jsp"><button>Atualizar</button></a>
            <a href="AreaRestrita/Escola/areaRestritaEscolaDeletar.jsp"><button>Deletar</button></a>
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

</body>
</html>  
