<%@ page import="java.util.List" %>
<%@ page import="org.example.projecttechmovee.ClasseTabelas.Transportador" %>
<%@ page import="org.example.projecttechmovee.ClasseTabelas.Telefone" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bem-Vindo Área Restrita</title>
    <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaGetAll.css">
    <link rel="icon" href="/Imagens/logos2.png">
</head>
<body>
    <nav>
        <a href="index.html"><img id="backAreaRestrita" src="Imagens/icons8-back-arrow-50.png" alt=""></a>
    </nav>

    <section>

        <!--  Sidebar  -->

        <div class="sidebar">
            <a href="Admin">Administrador</a>
            <a href="Respon">Responsável</a>
            <a href="Transp">Transportador</a>
            <a class="selecionado" href="Telefone">Telefone</a>
            <a href="Escola">Escola</a>
        </div>

        <!-- Conteúdo -->
        <%
            String erro = (String) request.getAttribute("erro");
            if (null == erro){
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
                    <td><%= telefone.getId() %></td>
                    <td><%= telefone.getNumero() %></td>
                    <% if (telefone.getId_resp() == null) {%>
                        <td>Transportador</td>
                        <td><%= telefone.getId_transp() %></td>
                    <%}else{%>
                        <td>Responsável</td>
                        <td><%= telefone.getId_resp()%></td>
                    <%}%>
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <%
            }else{
            %>
            <div>
                <h1><%=erro%></h1>
            </div>
            <%
                }
            %>
        </div>

        <!-- Opções para o CRUD -->

        <div class="botoes">
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneId.jsp"><button>Buscar por ID</button></a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneInserir.jsp"><button>Adicionar</button></a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneAtualizar.jsp"><button>Atualizar</button></a>
            <a href="AreaRestrita/Telefone/areaRestritaTelefoneDeletar.jsp"><button>Deletar</button></a>
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
</body>
</html>
