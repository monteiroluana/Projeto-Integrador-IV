package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.ClienteService;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping("/list-cliente")
    public ResponseEntity listClientes() throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.listClientes());
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity authClienteByLoginSenha(@RequestBody Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.authClienteByLoginSenha(cliente.getLogin(), cliente.getSenha()));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity saveProduto(@RequestBody Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.saveCliente(cliente));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity update(@RequestBody Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.update(cliente));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/cliente/{email}", method = RequestMethod.GET, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity getCliente(@PathVariable("email") String email) throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.getCliente(email));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }
}
