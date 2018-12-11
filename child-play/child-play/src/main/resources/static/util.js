/*$('.sidenav').append(
    //'<img src="cuboMagico.png" alt="cubo mágico, logo da empresa" th:href="@{cuboMagico.png}"> Child-Play'+
    // '<hr/>'+
    '<a href="/usuario/listarUsuarios">Usuários</a>'+
    '<a href="/produto/listarProdutos">Produtos</a>'+
    '<a href="/pedido/listarPedidos">Pedidos</a>'+
    '<a href="/relatorio/relatorio">Relatório</a>'+
    '<a href="#">Descontos</a>'
);*/

$('.sidenav').append(
    '<span style="font-size:40px;cursor:pointer" class="closebtn" onclick="closeNav()">&times;</span>'+
    // '<hr/>'+
    '<a href="/usuario/listarUsuarios">Usuários</a>'+
    '<a href="/produto/listarProdutos">Produtos</a>'+
    '<a href="#">Pedidos</a>'+
    '<a href="/relatorio/relatorio">Relatório</a>'+
    '<a href="#">Descontos</a>'
);


function openNav() {
    document.querySelector(".sidenav").style.width = "15%";
    document.querySelector(".conteudo").style.marginLeft="16%";
  
    $('#demo').html('');
}

function closeNav() {
    document.querySelector(".sidenav").style.width = "0";
    document.querySelector(".conteudo").style.marginLeft="5%";
    $('#demo').append( '<center><span style="font-size:30px;cursor:pointer;color:white;" onclick="openNav()">&#9776;</span></center>');
}
