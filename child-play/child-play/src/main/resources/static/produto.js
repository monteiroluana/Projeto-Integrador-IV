let listProdutos = []

$(document).ready(function () {
    $.ajax({
        url: '/produto/list-produto', method: 'GET', success: function (data) {
            listProdutos = data.data;
            console.log(listProdutos);
            for (var i = 0; i < listProdutos.length; i++) {
                $('#table-produtos').append(
                    '    <tr>' +
                    '        <td>' +
                    '            <span>' + listProdutos[i].idProduto + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listProdutos[i].nome + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listProdutos[i].preco + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listProdutos[i].marca + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listProdutos[i].estoque + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listProdutos[i].desconto + '</span>' +
                    '        </td >' +

                    '<td>' +
                    '    <button type="button" class="btn btn-outline-success" onclick="abrirModal(' + listProdutos[i].idProduto + ')">Detalhes</button>' +
                    '    <button type="button" class="btn btn-outline-info">Editar</button>' +
                    '    <button type="button" class="btn btn-outline-danger">Excluir</button>' +
                    '</td>' +

                    '    </tr >'

                );
            }
        }
    });
});

function abrirModal(idProduto) {
    let i = idProduto - 1;
    let imgs = listProdutos[i].imagem.length;

    // imagens
    document.querySelector(".carousel-inner").innerHTML='';
    document.querySelector(".modal-title").innerHTML = listProdutos[i].nome;
    $('.carousel-inner').append(
        '<div class="carousel-item active">'+
        '    <img class="d-block w-100" src="' + listProdutos[i].imagem[0].imagem + '" alt="First slide" width=100px>'+
        '</div>'
    );
    for(var j=1; j < imgs; j++){
        $('.carousel-inner').append(
            '<div class="carousel-item">'+
            '    <img class="d-block w-100" src="' + listProdutos[i].imagem[j].imagem + '" alt="First slide" width=100px>'+
            '</div>'
        );
    }

    $('#detalhes').modal();
}