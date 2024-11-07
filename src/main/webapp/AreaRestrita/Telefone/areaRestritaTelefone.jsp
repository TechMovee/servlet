<%@ page import="java.util.List" %>
<%@ page import="org.example.projecttechmovee.ClasseTabelas.Telefone" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bem-Vindo Área de Telefones</title>
    <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaGetAll.css">
    <link rel="icon" href="/Imagens/logos2.png">
</head>
<body>
<nav>
    <a href="AreaRestrita/TelaInicial/areaRestrita.jsp"><img id="backAreaRestrita" src="Imagens/setaAzul.png" alt=""></a>
    <span class="email_session"></span>
</nav>

<section>

    <!--  Sidebar  -->

    <div class="sidebar">
        <a href="Admin">Administrador</a>
        <a href="Respon">Responsável</a>
        <a href="Transp">Transportador</a>
        <a href="Telefone" class="selecionado">Telefone</a>
        <a href="Escola">Escola</a>
        <a class="bi">Gráficos</a>
    </div>

    <!-- Conteúdo -->

    <div class="content">
        <div class="botoes">
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneId.jsp">
                <button>Buscar por ID</button>
            </a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneInserir.jsp">
                <button>Adicionar</button>
            </a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneAtualizar.jsp">
                <button>Atualizar</button>
            </a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneDeletar.jsp">
                <button>Deletar</button>
            </a>
        </div>
        <%
            String erro = (String) request.getAttribute("erro");
            if (null == erro) {
        %>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th class="cabeçalho">ID</th>
                    <th class="cabeçalho">Número</th>
                    <th class="cabeçalho">Tipo de usuário</th>
                    <th class="cabeçalho">CNH ou CPF</th>
                </tr>
                </thead>
                <%
                    List<Telefone> telefones = (List<Telefone>) request.getAttribute("telefones");// Obtém a lista do servlet
                    if (telefones != null && !telefones.isEmpty()) {
                        for (Telefone telefone : telefones) {
                %>
                <!-- Conteúdo dentro da div -->
                <tr class="item">
                    <td><%= telefone.getId() %>
                    </td>
                    <td><%= telefone.getNumero() %>
                    </td>
                    <% if (telefone.getId_resp() == null) {%>
                    <td>Transportador</td>
                    <td><%= telefone.getId_transp() %>
                    </td>
                    <%} else {%>
                    <td>Responsável</td>
                    <td><%= telefone.getId_resp()%>
                    </td>
                    <%}%>
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
            window.location.replace("Transp");
        } else if (e.key == "ArrowDown") {
            window.location.replace("Escola");
        }
    })
    const bi = document.getElementsByClassName("bi")[0]
    bi.addEventListener('click', () => {
        window.location.replace("BI");
    })
</script>
</body>
</html>
