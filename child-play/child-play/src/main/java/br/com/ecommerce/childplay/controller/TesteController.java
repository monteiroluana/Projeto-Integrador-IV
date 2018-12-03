package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childPlay.service.UsuarioService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teste")
public class TesteController {

    @GetMapping("/listar")
    public ModelAndView mostrarResultado() throws ClassNotFoundException, SQLException {
//         ProdutoService service = new ProdutoService();
        UsuarioService service = new UsuarioService();
        List<Usuario> lista = service.listar();
        return new ModelAndView("teste").addObject("usuarios", lista);
    }
}
