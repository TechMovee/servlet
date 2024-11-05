<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
    <style>
        .form {
            column-gap: 5px;
            margin-left: -40px;
            max-width: 80%;
        }

        .form label {
            width: 45%;
        }

        .submit {
            width: 45%;
        }
    </style>
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
        <a href="../../Transp" class="selecionado">Transportador</a>
        <a href="../../Telefone">Telefone</a>
        <a href="../../Escola">Escola</a>
        <a href="../../BI">Gráficos</a>
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
    </div>
    <%
        }
    %>

    <!-- Conteúdo -->
    <form class="form" action="../../Transp" method="post">
        <%
            if (request.getAttribute("erro") != null) {
        %>
        <h3 id="erro"><%= request.getAttribute("erro")%>
        </h3>
        <%
            }
        %>
        <label>
            <input required placeholder="" type="text" name="cnhInserir" class="input">
            <span>CNH</span>
        </label>
        <label>
            <input required placeholder="" type="text" name="nome" class="input">
            <span>Nome</span>
        </label>
        <label>
            <input required placeholder="" type="text" name="cep" class="input">
            <span>CEP</span>
        </label>
        <label>
            <input required placeholder="" type="email" name="email" class="input">
            <span>Email</span>
        </label>
        <label>
            <input required placeholder="" type="date" name="dtNascimento" class="input">
            <span>Data de Nascimento</span>
        </label>
        <label>
            <input required placeholder="" type="password" name="senha" class="input">
            <span>Senha</span>
        </label>
        <label>
            <input required placeholder="" type="text" name="foto" class="input">
            <span>Foto</span>
        </label>
        <input type="submit" class="submit" value="Cadastrar">
    </form>
    <!-- Opções para o CRUD -->
    <div class="botoes">
        <a href="../../Transp">
            <button>Mostrar todos Transportadores</button>
        </a>
        <%
            if (request.getAttribute("erro") == null) {
        %>
        <a href="areaRestritaTranspId.jsp">
            <button>Buscar</button>
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
            <button>Buscar</button>
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
