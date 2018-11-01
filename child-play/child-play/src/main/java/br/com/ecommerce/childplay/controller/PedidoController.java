package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childplay.dao.PedidoDAO;
import br.com.ecommerce.childplay.dao.PedidoDAO;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.PedidoService;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @GetMapping("/teste")
    public ResponseEntity haha() throws SQLException, ClassNotFoundException {
        PedidoService service = new PedidoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.savePedido());
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public void pedido(@RequestBody Pedido pedido, ItemPedido itens) throws SQLException, ClassNotFoundException {

        System.out.println("pedido: "+pedido.getEnderecoEntrega());
        System.out.println("itens: "+itens.getQuantidade());
    }
}
