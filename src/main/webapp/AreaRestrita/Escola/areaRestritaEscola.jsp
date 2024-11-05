<%@ page import="java.util.List" %>
<%@ page import="org.example.projecttechmovee.ClasseTabelas.Escolas" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Bem-Vindo Área Restrita</title>
        <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaGetAll.css">
        <link rel="icon" href="Imagens/logos2.png">
    </head>
    <body>
        <nav>
            <a href="index.html"><img id="backAreaRestrita" src="Imagens/icons8-back-arrow-50.png" alt=""></a>
        </nav>
        <section>
                <!-- Sidebar -->
            <div class="sidebar">
                <a href="Admin">Administrador</a>
                <a href="Respon">Responsável</a>
                <a href="Transp">Transportador</a>
                <a href="Telefone">Telefone</a>
                <a class="selecionado" href="Escola">Escola</a>
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
                        </tr>
                    </thead>
                    <%
                        List<Escolas> escolas = (List<Escolas>) request.getAttribute("escolas");// Obtém a lista do servlet
                        if (escolas != null && !escolas.isEmpty()) {
                            for (Escolas escola : escolas) {
                    %>
                    <!-- Conteúdo dentro da div -->
                    <tr class="item">
                        <td><%= escola.getId() %></td>
                        <td><%= escola.getNome() %></td>
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
                <a href="AreaRestrita/Escola/areaRestritaEscolaId.jsp"><button>Buscar</button></a>
                <a href="AreaRestrita/Escola/areaRestritaEscolaInserir.jsp"><button>Adicionar</button></a>
                <a href="AreaRestrita/Escola/areaRestritaEscolaAtualizar.jsp"><button>Atualizar</button></a>
                <a href="AreaRestrita/Escola/areaRestritaEscolaDeletar.jsp"><button>Deletar</button></a>
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
