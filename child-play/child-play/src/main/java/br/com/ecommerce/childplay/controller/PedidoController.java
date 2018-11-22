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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity pedido(@RequestBody Pedido pedido) throws SQLException, ClassNotFoundException {
        PedidoService service = new PedidoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.savePedido(pedido));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

//    @GetMapping("/list-pedido")
//    public ResponseEntity listClientes() throws ClassNotFoundException, SQLException {
//        PedidoService service = new PedidoService();
//        ResponseEntity re = null;
//        try {
//            re = ResponseEntity.createSuccess();
//            re.setData(service.listPedidos());
//        } catch (SQLException e) {
//            re = ResponseEntity.createUnknownError();
//        }
//        return re;
//    }
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

    @GetMapping("/{protocolo}")
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
    
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/historico", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity pedido(@RequestBody Cliente cliente) throws SQLException, ClassNotFoundException {
        PedidoService service = new PedidoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.listPedidosByCliente(cliente));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }


//    @CrossOrigin(origins = "*")
//    @RequestMapping(value = "/teste", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
//    public void teste(@RequestBody Pedido pedido) throws SQLException, ClassNotFoundException {
//        List<ItemPedido> lista = pedido.getItens();
//        
//        for (ItemPedido itemPedido : lista) {
//            System.out.println(itemPedido.getIdProduto());
//        }
//    }
}
