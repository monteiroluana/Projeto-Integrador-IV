let listPedidos= [];

$(document).ready(function () {
    listarPedidos("aguardando pagamento");
});




function  listarPedidos(status){
    $.ajax({
        url: '/pedido/list-pedido-status/'+status, method: 'GET', success: function (data) {
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
                    '            <span> produtos ....</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].tipoPagamento + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].valorTotal + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listPedidos[i].status + '</span>' +
                    '        </td >' +

                    '        <td>' +
                    '           <button type="button" class="btn btn-outline-success" onclick="abrirModalDetalhes(' + listPedidos[i].idProduto + ')" title="Ver mais detalhes do pedido">Detalhes</button>' +
                    '        </td>' +

                    '    </tr >'

                );
            }
        }
    });


}