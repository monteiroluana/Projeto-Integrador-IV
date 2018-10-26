package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childplay.dao.PedidoDAO;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoService {

    public String savePedido() throws SQLException {
        List<ItemPedido> list = new ArrayList<>();
        ItemPedido item = new ItemPedido();
        item.setIdProduto(1);
        item.setQuantidade(1);
        //item.setPreco(84.6);
        list.add(item);

//        for (ItemPedido itemPedido : list) {
//            System.out.println("itemPedido: "+ itemPedido.getIdProduto());
//        }
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2);

        Pedido pedido = new Pedido();

        Date date = new Date(System.currentTimeMillis());
        pedido.setDataPedido(date);
        System.out.println(pedido.getDataPedido());
        pedido.setEnderecoEntrega("haha 5");
        pedido.setTipoPagamento("cartão");
        pedido.setStatus("d");
        pedido.setProtocolo(gerarProtocolo());

        PedidoDAO pedidoDao = new PedidoDAO();
        return pedidoDao.savePedido(pedido, cliente.getIdCliente(), list);
    }

    public static String gerarProtocolo() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss"); //formato da data
        Date date = new Date(); //Pegando a data do momento
        String nDt = (dateFormat.format(date).replaceAll("[^0-9]", "")); //retidando todas os caracteres que não estiveres no intervalo 0 a 9
        int random = (int) Math.ceil(Math.random() * (999 - 100 + 1)) - 1 + 100; //gerando um random que vai de 100 a 999
        nDt = nDt + (Integer.toString(random)); //concatenando data com o valor gerado pelo randomm

        return nDt;
    }
}
