package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.model.ClienteInteressado;
import br.com.ecommerce.childplay.model.ContatoCliente;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.ClienteService;
import java.sql.SQLException;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity authClienteByEmailSenha(@RequestBody Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.authClienteByEmailSenha(cliente.getEmail(), cliente.getSenha()));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity save(@RequestBody Cliente cliente) throws ClassNotFoundException, SQLException, MessagingException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.save(cliente));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }
    
        @CrossOrigin(origins = "*")
    @RequestMapping(value = "/enviaEmail", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity enviaEmailAviseMe(@RequestBody ClienteInteressado cliInteressado) throws ClassNotFoundException, SQLException, MessagingException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.enviaEmailAviseMe(cliInteressado));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }
    
            @CrossOrigin(origins = "*")
    @RequestMapping(value = "/EmailContatoCliente", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity EmailContatoCliente(@RequestBody ContatoCliente contatoCliente) throws ClassNotFoundException, SQLException, MessagingException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.EmailContatoCliente(contatoCliente));
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
    public ResponseEntity getClientePorEmail(@PathVariable("email") String email) throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.getClientePorEmail(email));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/enable", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity enable(@RequestBody Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.enable(cliente));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
}
