<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bem-Vindo Área de gráficos</title>
    <link rel="stylesheet" type="text/css" href="AreaRestrita/CSS/telaGetAll.css">
    <link rel="icon" href="Imagens/logos2.png">
</head>
<body>
<nav>
    <a href="index.html"><img id="backAreaRestrita" src="Imagens/icons8-back-arrow-50.png" alt=""></a>
</nav>
<section class="conteudo">
    <div class="sidebar">
        <a href="Admin">Administrador</a>
        <a href="Respon">Responsável</a>
        <a href="Transp">Transportador</a>
        <a href="Telefone">Telefone</a>
        <a href="Escola">Escola</a>
        <a class="selecionado" id="bi">Gráficos</a>
    </div>

    <iframe title="dashbord_responsaveis" width="90%" height="80%" style="grid-column: 2/4"
            src="https://app.powerbi.com/view?r=eyJrIjoiMjE4YzVlZGUtMDc4Mi00YjNhLWFkYzctZTYxZWI0YWM3NGM2IiwidCI6ImIxNDhmMTRjLTIzOTctNDAyYy1hYjZhLTFiNDcxMTE3N2FjMCJ9"
            frameborder="0"></iframe>
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
