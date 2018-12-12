
let resultado = [];

$(document).ready(function () {
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
});


/*SELECT idPedido, protocolo, idCliente, idUsuario, dataPedido, tipoPagamento, status, valorTotal, valorFrete, cep, logradouro, numero, bairro, cidade, uf, complemento,DATE_FORMAT(pedido.dataPedido, "%M") as mes,
DATE_FORMAT(pedido.dataPedido, "%Y") as ano  FROM `pedido` */