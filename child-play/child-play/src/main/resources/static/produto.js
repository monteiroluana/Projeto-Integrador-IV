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

                    '        <td>' +
                    '           <button type="button" class="btn btn-outline-success" onclick="abrirModal(' + listProdutos[i].idProduto + ')" title="Ver mais detalhes do produto ' + listProdutos[i].nome + '">Detalhes</button>' +
                    '           <button type="button" class="btn btn-outline-info" title="Editar o produto ' + listProdutos[i].nome + '">Editar</button>' +
                    '           <button type="button" class="btn btn-outline-danger" title="Excluir o produto ' + listProdutos[i].nome + '">Excluir</button>' +
                    '        </td>' +

                    '    </tr >'

                );
            }
        }
    });
});


//------Modal - ver mais detalhes do produto ----------------------------

function abrirModal(idProduto) {
    let i = idProduto - 1;
    let imgs = listProdutos[i].imagem.length;

    // imagens
    document.querySelector(".carousel-inner").innerHTML = '';
    document.querySelector(".modal-title").innerHTML = listProdutos[i].nome;
    $('.carousel-inner').append(
        '<div class="carousel-item active">' +
        '    <img class="d-block w-100" src="' + listProdutos[i].imagem[0].imagem + '" alt="First slide">' +
        '</div>'
    );
    for (var j = 1; j < imgs; j++) {
        $('.carousel-inner').append(
            '<div class="carousel-item">' +
            '    <img class="d-block w-100" src="' + listProdutos[i].imagem[j].imagem + '" alt="First slide">' +
            '</div>'
        );
    }

    document.querySelector(".nome").value = listProdutos[i].nome;
    document.querySelector(".nome").disabled = true;

    document.querySelector(".marca").value = listProdutos[i].marca;
    document.querySelector(".marca").disabled = true;
    $('#detalhes').modal();
}

$('#tab a').on('click', function (e) {
    e.preventDefault();
    $(this).tab('show');
});

$('#tab a[href="#profile"]').tab('show') // Selecione a tab pelo nome
$('#tab a:first').tab('show') // Selecione a primeira tab
$('#tab a:last').tab('show') // Selecione a última tab
$('#tab li:eq(2) a').tab('show') // Selecione a terceira tab (0-indexed)