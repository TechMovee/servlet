<%@ page import="java.util.List" %>
<%@ page import="org.example.projecttechmovee.ClasseTabelas.Transportador" %>
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
        <a href="index.html"><img id="backAreaRestrita" src="../../Imagens/icons8-back-arrow-50.png" alt=""></a>
    </nav>

    <section>

            <!--  Sidebar  -->

        <div class="sidebar">
            <a href="Admin">Administrador</a>
            <a href="Respon">Responsável</a>
            <a class="selecionado" href="Transp">Transportador</a>
            <a href="Telefone">Telefone</a>
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
                        <th class="cabeçalho">Nome Completo</th>
                        <th class="cabeçalho">Email</th>
                        <th class="cabeçalho">Data de Nascimento</th>
                        <th class="cabeçalho">Foto</th>
                    </tr>
                </thead>
                <%
                    List<Transportador> transportadores = (List<Transportador>) request.getAttribute("transp");// Obtém a lista do servlet
                    if (transportadores != null && !transportadores.isEmpty()) {
                        for (Transportador transportador : transportadores) {
                %>
                <!-- Conteúdo dentro da div -->
                <tr class="item">
                    <td><%= transportador.getNome() %></td>
                    <td><%= transportador.getEmail() %></td>
                    <td><%= transportador.getDtNascimento() %></td>
                    <td><%= transportador.getFoto() %></td>
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
            <a href="AreaRestrita/Transportador/areaRestritaTranspId.jsp"><button>Buscar por ID</button></a>
            <a href="AreaRestrita/Transportador/areaRestritaTranspInserir.jsp"><button>Adicionar</button></a>
            <a href="AreaRestrita/Transportador/areaRestritaTranspAtualizar.jsp"><button>Mostras todos Admins</button></a>
            <a href="AreaRestrita/Transportador/areaRestritaTranspDeletar.jsp"><button>Deletar Admin</button></a>
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
