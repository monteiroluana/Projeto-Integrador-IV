package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childPlay.service.UsuarioService;
import br.com.ecommerce.childplay.model.ResponseEntity;
import java.sql.SQLException;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity getUsuarioByLoginSenha(@RequestBody Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.getUsuarioByLoginSenha(usuario.getLogin(), usuario.getSenha()));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity saveUsuario(@RequestBody Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.saveUsuario(usuario));
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
