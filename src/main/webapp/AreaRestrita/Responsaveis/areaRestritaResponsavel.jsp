<%@ page import="java.util.List" %>
<%@ page import="org.example.projecttechmovee.ClasseTabelas.Responsaveis" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bem-Vindo Área de Responsáveis</title>
    <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaGetAll.css">
    <link rel="icon" href="Imagens/logos2.png">
</head>
<body>
<nav>
    <a href="AreaRestrita/TelaInicial/areaRestrita.jsp"><img id="backAreaRestrita" src="Imagens/setaAzul.png" alt=""></a>
    <span class="email_session"></span>
</nav>

<section>
    <div class="sidebar">
        <a href="Admin">Administrador</a>
        <a class="selecionado" href="Respon">Responsável</a>
        <a href="Transp">Transportador</a>
        <a href="Telefone">Telefone</a>
        <a href="Escola">Escola</a>
        <a class="bi">Gráficos</a>
        <p><span>Use <strong>keyUp</strong> e <strong>keyDown</strong> para navegar entre áreas</span> <img class="setas" src="Imagens/keys.webp" alt=""></p>
    </div>

    <!-- Conteúdo -->

    <div class="content">
        <div class="botoes">
            <a href="AreaRestrita/Responsaveis/areaRestritaResponsavelId.jsp">
                <button>Buscar</button>
            </a>
            <a href="AreaRestrita/Responsaveis/areaRestritaResponsavelInserir.jsp">
                <button>Inserir</button>
            </a>
            <a href="AreaRestrita/Responsaveis/areaRestritaResponsavelAtualizar.jsp">
                <button class="BotãoSelecionado">Atualizar</button>
            </a>
            <a href="AreaRestrita/Responsaveis/areaRestritaResponsavelDeletar.jsp">
                <button >Deletar</button>
            </a>
        </div>
        <%
            String erro = (String) request.getAttribute("erro");
            if (erro == null) {
        %>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th class="cabeçalho">Nome Completo</th>
                    <th class="cabeçalho">Email</th>
                    <th class="cabeçalho">Data de Nascimento</th>
                    <th class="cabeçalho">Foto</th>
                </tr>
                </thead>
                <%
                    List<Responsaveis> responsaveis = (List<Responsaveis>) request.getAttribute("resps");// Obtém a lista do servlet
                    if (responsaveis != null && !responsaveis.isEmpty()) {
                        for (Responsaveis responsavel : responsaveis) {
                %>
                <!-- Conteúdo dentro da div -->
                <tr class="item">
                    <td><%= responsavel.getNome() %>
                    </td>
                    <td><%= responsavel.getEmail() %>
                    </td>
                    <td><%= responsavel.getDtNascimento() %>
                    </td>
                    <td><%= responsavel.getFoto() %>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <%
            } else {
            %>
            <div>
                <h1><%=erro%>
                </h1>
            </div>
            <%
                }
            %>
        </div>
    </div>
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
            window.location.replace("Admin");
        } else if (e.key == "ArrowDown") {
            window.location.replace("Transp");
        }
    })
    const bi = document.getElementsByClassName("bi")[0]
    bi.addEventListener('click', () => {
        window.location.replace("BI");
    })
</script>
</body>
</html>
