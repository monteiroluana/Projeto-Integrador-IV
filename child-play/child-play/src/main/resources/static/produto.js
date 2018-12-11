let listProdutos = []

$(document).ready(function () {
    listarProdutos();
});


//abre modal com detalhes do produto
function abrirModal(idProd) {

    for (var i = 0; i <= listProdutos.length; i++) {
        if (idProd == listProdutos[i].idProduto) {

            let imgs = listProdutos[i].imagem.length;

            document.querySelector(".idProduto").value = idProd;
            document.querySelector(".idProduto").disabled = true;

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

            document.querySelector(".idade").value = listProdutos[i].idade;
            document.querySelector(".idade").disabled = true;

            document.querySelector(".preco").value = listProdutos[i].preco;
            document.querySelector(".preco").disabled = true;

            document.querySelector(".estoque").value = listProdutos[i].estoque;
            document.querySelector(".estoque").disabled = true;

            document.querySelector(".desconto").value = listProdutos[i].desconto;
            document.querySelector(".desconto").disabled = true;

            document.querySelector(".descricao").value = listProdutos[i].descricao;
            document.querySelector(".descricao").disabled = true;

            document.querySelector(".caracteristicas").value = listProdutos[i].caracteristicas;
            document.querySelector(".caracteristicas").disabled = true;

            document.querySelector(".btnEditar").disabled = false;
            document.querySelector(".btnAtualizar").disabled = true;

            $('#detalhes').modal();

        }
    }
}

function editar() {
    document.querySelector(".nome").disabled = false;
    document.querySelector(".marca").disabled = false;
    document.querySelector(".idade").disabled = false;
    document.querySelector(".preco").disabled = false;
    document.querySelector(".estoque").disabled = false;
    document.querySelector(".desconto").disabled = false;
    document.querySelector(".descricao").disabled = false;
    document.querySelector(".caracteristicas").disabled = false;

   /* $('.modal-footer').append(
        '<button type="button" class="btn btn-primary btnAtualizar" onclick="atualizar()">Atualizar</button>'
    );*/
    document.querySelector(".btnEditar").disabled = true;
    document.querySelector(".btnAtualizar").disabled = false;
}

function atualizar() {
    let idProduto = document.querySelector(".idProduto").value;
    let nome = document.querySelector(".nome").value;
    let marca = document.querySelector(".marca").value;
    let idade = document.querySelector(".idade").value;
    let preco = document.querySelector(".preco").value;
    let estoque = document.querySelector(".estoque").value;
    let desconto = document.querySelector(".desconto").value;
    let descricao = document.querySelector(".descricao").value;
    let caracteristicas = document.querySelector(".caracteristicas").value;

    let obj = {
        "idProduto": idProduto,
        "nome": nome,
        "marca": marca,
        "descricao": descricao,
        "caracteristicas": caracteristicas,
        "idade": idade,
        /*"categoria":"categoria teste post",*/
        "preco": preco,
        "estoque": estoque,
        "desconto": desconto
    }

    $.ajax({
        url: '/produto/update',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (data) {
            swal("Success!", data.data, "success");

            $('#detalhes').modal('hide');
            listarProdutos();
        }
    });
}

function excluir(idProduto) {
    swal({
        title: "Tem certeza?",
        text: "O produto (" + listProdutos[idProduto - 1].nome + ") será apagado!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                let obj = {
                    "idProduto": idProduto,
                }

                $.ajax({
                    url: '/produto/enable',
                    type: 'post',
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (data) {
                        swal("Success!", data.data, "success");
                        listarProdutos();
                    }
                });
            }
        });
}


function listarProdutos() {
    document.querySelector(".table-produtos").innerHTML = '';

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
                    '           <button type="button" class="btn btn-outline-danger" onclick="excluir(' + listProdutos[i].idProduto + ')" title="Excluir o produto ' + listProdutos[i].nome + '">Excluir</button>' +
                    '        </td>' +

                    '    </tr >'

                );
            }
        }
    });
}