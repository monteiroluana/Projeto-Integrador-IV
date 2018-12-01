package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.model.Pedido;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.PedidoService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity save(@RequestBody Pedido pedido) throws SQLException, ClassNotFoundException {
        PedidoService service = new PedidoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.save(pedido));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @GetMapping("/list-pedido")
    public ResponseEntity listPedido() throws ClassNotFoundException, SQLException {
        PedidoService service = new PedidoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.listPedido());
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @PostMapping("/autorizaPedido/{protocolo}")
    public ResponseEntity AutorizaPedido(@PathVariable("protocolo") String protocolo) throws SQLException, ClassNotFoundException {
        PedidoService service = new PedidoService();
        ResponseEntity re = null;
        re = ResponseEntity.createSuccess();
        boolean teste = service.AutorizaPedido(protocolo);
        re.setData(service.AutorizaPedido(protocolo));
        return re;
    }

    @PostMapping("/{protocolo}")
    public ResponseEntity getPedidosByProtocolo(@PathVariable("protocolo") String protocolo) throws ClassNotFoundException, SQLException {
        PedidoService service = new PedidoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.getPedidosByProtocolo(protocolo));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @PostMapping("/history/{email}")
    public ResponseEntity pedido(@PathVariable("email") String email) throws SQLException, ClassNotFoundException {
        PedidoService service = new PedidoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.listPedidosByCliente(email));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

}
