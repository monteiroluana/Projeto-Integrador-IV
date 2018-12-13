let listPedidos = [];

$(document).ready(function () {
    //listarPedidos(filtrarStatus());
});


function listarPedidos(status) {
    document.querySelector(".bodyTable").innerHTML = '';
    $.ajax({
        url: '/pedido/list-pedido-status/' + status, method: 'GET', success: function (data) {
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
                    '            <span>' + listPedidos[i].valorTotal + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '           <span>' + listPedidos[i].status + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '           <button type="button" class="btn btn-success" onclick="aprovarPedido(' + listPedidos[i].idPedido + ')" title="Aprovar pedido">Aprovar</button>' +
                    '           <button type="button" class="btn btn-danger" onclick="cancelarPedido(' + listPedidos[i].idPedido + ')" title="Cancelar pedido">Reprovar</button>' +
                    '        </td>' +
                    '        <td>' +
                    '           <button type="button" class="btn btn-outline-info" onclick="abrirModalDetalhes(' + listPedidos[i].idPedido + ')" title="Ver mais detalhes do pedido">Detalhes</button>' +
                    '        <td>' +

                    '    </tr >'

                );
            }
        }
    });
}

function listarProdutos(i) {

}

function filtrarStatus() {
    let filtroStatus = $("#filtroStatus option:selected").val();
    console.log(filtroStatus);

    listarPedidos(filtroStatus);

}