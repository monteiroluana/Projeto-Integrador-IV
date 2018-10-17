package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.service.UsuarioService;
import br.com.ecommerce.childplay.model.ResponseEntity;
import java.sql.SQLException;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    @GetMapping("/list-usuario/{id}")
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

    //POR FAVOR NÃO APAGUEM ISSO
    /* @GetMapping("/get-usuarioById/{id}")
    public ResponseEntity<List<Usuario>> getUsuarioById(@PathVariable("id") int id) throws ClassNotFoundException, SQLException{
        UsuarioService service = new UsuarioService();
        List<Usuario> lista = service.getUsuarioById(id);
        return ResponseEntity.ok(lista);
    }
     */
}
