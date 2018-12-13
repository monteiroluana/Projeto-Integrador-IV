let listPedidos = [];
let filtroStatus;

$(document).ready(function () {
    listarTodosPedidos();
});

function listarPedidos(status) {
    document.querySelector(".bodyTable").innerHTML = '';

    $.ajax({
        url: '/pedido/list-pedido-status/' + status,
        method: 'GET',
        success: function (data) {
            listPedidos = data.data;
            console.log(listPedidos);
            for (var i = 0; i < listPedidos.length; i++) {
                $('#bodyTable').append(
                    '    <tr>' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].idPedido + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].protocolo + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].cliente.nome + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].valorTotal + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '           <span>' + listPedidos[i].status + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '           <button type="button" class="btn btn-success" onclick="aprovarPedido(' + i + ')" title="Aprovar pedido">Aprovar</button>' +
                    '           <button type="button" class="btn btn-danger" onclick="cancelarPedido(' + i + ')" title="Cancelar pedido">Cancelar</button>' +
                    '        </td>' +
                    //     '        <td>' +
                    //     '           <button type="button" class="btn btn-outline-info" onclick="abrirModalDetalhes(' + listPedidos[i].idPedido + ')" title="Ver mais detalhes do pedido">Detalhes</button>' +
                    //     '        <td>' +

                    '    </tr >'

                );
            }
        }
    });
}

function listarTodosPedidos() {
    document.querySelector(".bodyTable").innerHTML = '';

    $.ajax({
        url: '/pedido/list-pedido/', method: 'GET', success: function (data) {
            listPedidos = data.data;
            console.log(listPedidos);
            for (var i = 0; i < listPedidos.length; i++) {
                $('#bodyTable').append(
                    '    <tr>' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].idPedido + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].protocolo + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].cliente.nome + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].valorTotal + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '           <span>' + listPedidos[i].status + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '           <button type="button" class="btn btn-success" onclick="aprovarPedido(' + i + ')" title="Aprovar pedido">Aprovar</button>' +
                    '           <button type="button" class="btn btn-danger" onclick="cancelarPedido(' + i + ')" title="Cancelar pedido">Cancelar</button>' +
                    '        </td>' +
                    '    </tr >'

                );

                console.log("protocolo ", listPedidos[i].protocolo)
            }
        }
    });
}

function filtrarStatus() {
    filtroStatus = $("#filtroStatus option:selected").val();

    console.log(filtroStatus);

    listarPedidos(filtroStatus);

}

function aprovarPedido(aux) {
    console.log(listPedidos[aux].protocolo);

    if (listPedidos[aux].status.localeCompare("Pagamento Aprovado") == 0) {
        $.ajax({
            url: '/pedido/autorizaPedido/' + listPedidos[aux].protocolo,
            type: 'post',
            success: function (data) {
                swal("Success!", "Aprovado!", "success");

                listarTodosPedidos();
            }
        });

    } else {
        alert("Só é possível liberar o pedido com pagamento aprovado!");
    }
}

function cancelarPedido(aux) {
    console.log(listPedidos[aux].protocolo);
    $.ajax({
        url: '/pedido/cancelaPedido/' + listPedidos[aux].protocolo,
        type: 'post',
        success: function (data) {
            swal("Success!", "Cancelado!", "success");

            listarTodosPedidos();
        }
    });
}