package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childPlay.service.UsuarioService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    @GetMapping("/list-usuario")
    public ResponseEntity<List<Usuario>> litar() throws ClassNotFoundException, SQLException{
        UsuarioService service = new UsuarioService();
        List<Usuario> lista = service.listar();
        return ResponseEntity.ok(lista);
    }
 
}
