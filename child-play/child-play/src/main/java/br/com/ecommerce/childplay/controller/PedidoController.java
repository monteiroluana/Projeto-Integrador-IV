package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childplay.dao.PedidoDAO;
import br.com.ecommerce.childplay.dao.PedidoDAO;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import br.com.ecommerce.childplay.service.PedidoService;
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
        PedidoService pedidoService = new PedidoService();
        return new ModelAndView("pedido").addObject("pedido", pedidoService.savePedido());
    }
}
