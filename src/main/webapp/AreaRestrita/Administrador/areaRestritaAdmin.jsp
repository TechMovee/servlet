<%@ page import="java.util.List" %>
<%@ page import="org.example.projecttechmovee.ClasseTabelas.Admin" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bem-Vindo Área de Admins</title>
        <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaGetAll.css">
        <link rel="icon" href="Imagens/logos2.png">
    </head>
    <body>
        <nav>
            <a href="index.html"><img id="backAreaRestrita" src="Imagens/icons8-back-arrow-50.png" alt=""></a>
        </nav>
        <section class="conteudo">

                <!-- Sidebar -->

            <div class="sidebar">
                <a class="selecionado" href="Admin">Administrador</a>
                <a href="Respon">Responsável</a>
                <a href="Transp">Transportador</a>
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
                        <th class="cabeçalho">ID</th>
                        <th class="cabeçalho">Nome Completo</th>
                        <th class="cabeçalho">Email</th>
                    </tr>
                    </thead>
                    <%
                        List<Admin> admins = (List<Admin>) request.getAttribute("admins");// Obtém a lista do servlet
                        if (admins != null && !admins.isEmpty()) {
                            for (Admin admin : admins) {
                        %>
                    <!-- Conteúdo dentro da div -->
                    <tr class="item">
                        <td><%= admin.getId() %></td>
                        <td><%= admin.getName() %></td>
                        <td><%= admin.getEmail() %></td>
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
                <a href="AreaRestrita/Administrador/areaRestritaAdminId.jsp"><button>Buscar por ID</button></a>
                <a href="AreaRestrita/Administrador/areaRestritaAdminInserir.jsp"><button>Adicionar</button></a>
                <a href="AreaRestrita/Administrador/areaRestritaAdminAtualizar.jsp"><button>Atualizar por ID</button></a>
                <a href="AreaRestrita/Administrador/areaRestritaAdminDeletar.jsp"><button>Deletar Admin</button></a>
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
    </body>'
</html>  
