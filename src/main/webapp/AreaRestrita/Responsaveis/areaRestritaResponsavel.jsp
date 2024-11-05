<%@ page import="java.util.List" %>
<%@ page import="org.example.projecttechmovee.ClasseTabelas.Responsaveis" %>
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
        <div class="sidebar">
            <a href="Admin">Administrador</a>
            <a class="selecionado"  href="Respon">Responsável</a>
            <a href="Transp">Transportador</a>
            <a href="Telefone">Telefone</a>
            <a href="Escola">Escola</a>
        </div>

        <!-- Conteúdo -->

        <%
            String erro = (String) request.getAttribute("erro");
            if (erro == null){
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
                    <td><%= responsavel.getNome() %></td>
                    <td><%= responsavel.getEmail() %></td>
                    <td><%= responsavel.getDtNascimento() %></td>
                    <td><%= responsavel.getFoto() %></td>
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
            <a href="AreaRestrita/Responsaveis/areaRestritaResponsavelId.jsp"><button>Buscar</button></a>
            <a href="AreaRestrita/Responsaveis/areaRestritaResponsavelInserir.jsp"><button>Adicionar</button></a>
            <a href="AreaRestrita/Responsaveis/areaRestritaResponsavelAtualizar.jsp"><button>Atualizar</button></a>
            <a href="AreaRestrita/Responsaveis/areaRestritaResponsavelDeletar.jsp"><button>Deletar</button></a>
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
