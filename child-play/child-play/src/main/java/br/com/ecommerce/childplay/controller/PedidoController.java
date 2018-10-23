package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childplay.dao.PedidoDAO;
import br.com.ecommerce.childplay.dao.testeDAO;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @GetMapping("/teste")
    public ModelAndView haha() throws SQLException, ClassNotFoundException {
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

        //PedidoDAO pedidoDao = new PedidoDAO();
        testeDAO teste = new testeDAO();

        teste.savePedido(pedido, usuario.getIdUsuario(), cliente.getIdCliente(),list);
        
        
       // pedidoDao.savePedido(pedido, cliente.getIdCliente(), usuario.getIdUsuario());
        
         return new ModelAndView("pedido").addObject("pedido", teste);

    }
}
