
let resultado = [];
let listPedidos = [];


$(document).ready(function () {
  grafico();

});


function grafico(){
  $.ajax({
    url: '/relatorio/ver', method: 'GET', success: function (data) {

      resultado = data.data;
      console.log(resultado);

      var chart = new Taucharts.Chart({
        data: resultado,
        type: 'line',
        x: 'mes',
        y: 'somaMes',
        text: 'mes',
        color: 'ano',
        guide: {
          showAnchors: 'always'
        },
        plugins: [
          Taucharts.api.plugins.get('tooltip')()
        ]
      });
      chart.renderTo('#line');
    }
  });
}


function listPedido(){

  let mes = document.getElementById("mesSelecionado").value;
  $.ajax({
    url: '/relatorio/pedido', method: 'GET', success: function (data) {

      listPedidos = data.data;
     console.log(listPedidos);
      for (var i = 0; i < listPedidos.length; i++) {

        if(listPedidos[i].mes == mes){
          $('#bodyTable').append(
            '    <tr>' +
            '        <td>' +
            '            <span>' + listPedidos[i].pedido.idPedido + '</span>' +
            '        </td >' +
            '        <td>' +
            '            <span>' + listPedidos[i].pedido.protocolo + '</span>' +
            '        </td >' +
            '        <td>' +
            '            <span>' + listPedidos[i].pedido.cliente.nome + '</span>' +
            '        </td >' +
            '        <td>' +
            '            <span>' + listPedidos[i].pedido.tipoPagamento + '</span>' +
            '        </td >' +
            '        <td>' +
            '            <span>' + listPedidos[i].pedido.valorTotal + '</span>' +
            '        </td >' +
            '        <td>' +
            '            <span>' + listPedidos[i].pedido.status + '</span>' +
            '        </td >' +

            '    </tr >'

        );

        }

       
    }

    }
  });

}

/*SELECT idPedido, protocolo, idCliente, idUsuario, dataPedido, tipoPagamento, status, valorTotal, valorFrete, cep, logradouro, numero, bairro, cidade, uf, complemento,DATE_FORMAT(pedido.dataPedido, "%M") as mes,
DATE_FORMAT(pedido.dataPedido, "%Y") as ano  FROM `pedido` */