let listPedidos = [];

$(document).ready(function () {
    listarPedidos("aguardando pagamento");
});


function listarPedidos(status) {
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
                    '            <form><select class="form-control"><option>' + listPedidos[i].status + '</option></select></form>' +
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

function filtrarStatus(){
    let filtroStatus = $("#filtroStatus option:selected").val();
    console.log(filtroStatus);

    $.ajax({
        url: '/pedido/list-pedido-status/{status}',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (data) {

            console.log("savar",data);
            swal("Success!", data.data, "success");

            $('#adicionar').modal('hide');
            listarProdutos();
        }
    });

}