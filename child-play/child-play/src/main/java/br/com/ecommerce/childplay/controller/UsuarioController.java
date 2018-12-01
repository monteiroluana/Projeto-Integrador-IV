package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childPlay.service.UsuarioService;
import br.com.ecommerce.childplay.model.ResponseEntity;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("/list-usuario")
    public ResponseEntity litar() throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.listar());
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/auth", method = RequestMethod.POST,consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity authUsuarioByLoginSenha(@RequestBody Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.authUsuarioByLoginSenha(usuario.getLogin(), usuario.getSenha()));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getUsuario/{login}", method = RequestMethod.POST,consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity getUsuario(@PathVariable("login") String login) throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.getUsuario(login));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity save(@RequestBody Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.save(usuario));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update", method = RequestMethod.POST,consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity update(@PathVariable("email") Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.update(usuario));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/enable", method = RequestMethod.POST,consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity enable(@RequestBody Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.enable(usuario));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }


    //POR FAVOR NÃO APAGUEM ISSO
    /* @GetMapping("/get-usuarioById/{id}")
    public ResponseEntity<List<Usuario>> getUsuarioById(@PathVariable("id") int id) throws ClassNotFoundException, SQLException{
        UsuarioService service = new UsuarioService();
        List<Usuario> lista = service.getUsuarioById(id);
        return ResponseEntity.ok(lista);
    }
     */
}
