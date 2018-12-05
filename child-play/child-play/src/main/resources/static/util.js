$('.sidenav').append(

    '<a href="/usuario/listarUsuarios"> Usuários</a>'+
    '<a href="/produto/listarProdutos">Produtos</a>'+
    '<a href="#">Pedidos</a>'+
    '<a href="#">Relatório</a>'+
    '<a href="#">Descontos</a>'

);
var message = [[${produtos}]];
console.log(message);


/*$("#verModal").click(function(){
    $("#ver").modal();

    console.log(document.getElementById("#verModal").value);
});*/