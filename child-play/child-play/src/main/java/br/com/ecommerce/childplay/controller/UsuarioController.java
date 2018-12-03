package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childPlay.service.UsuarioService;
import br.com.ecommerce.childplay.model.ResponseEntity;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@RestController
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("/list-usuario")
    public ResponseEntity listUsuarios() throws ClassNotFoundException, SQLException {
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
    @RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
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
    @RequestMapping(value = "/getUsuario/{login}", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
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
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
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
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
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
    @RequestMapping(value = "/enable", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
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

    //métodos p/ thymeleaf
//    @GetMapping("/listarUsuarios")
//    public ModelAndView listarUsuarios() throws SQLException, ClassNotFoundException {
//        UsuarioService service = new UsuarioService();
//        List<Usuario> lista = service.listar();
//        return new ModelAndView("telaUsuario").addObject("usuarios", lista);
//    }

    @GetMapping("/listarUsuarios")
    public ModelAndView exemplo() throws ClassNotFoundException, SQLException {
        UsuarioService service = new UsuarioService();
        List<Usuario> lista = service.listar();
        ModelAndView resposta = new ModelAndView("telaUsuario");
        resposta.addObject("usuarios", lista);
        return resposta;
    }

    @PostMapping("/cadastrarUsuario")
    public ModelAndView salvar(@ModelAttribute("usuario") Usuario usuario,
            RedirectAttributes redirectAttributes) throws SQLException, ClassNotFoundException {

        System.out.println(usuario.getNome());
        redirectAttributes.addFlashAttribute("usuario", usuario);

        UsuarioService service = new UsuarioService();
        service.save(usuario);

        ModelAndView retorno = new ModelAndView("redirect:/usuario/listarUsuarios");
        return retorno;
    }

    @RequestMapping(value = "/getUsuario", method = RequestMethod.POST, params = "action=edit")
    public ModelAndView editar(@ModelAttribute("usuario") Usuario usuario) throws SQLException, ClassNotFoundException {
        UsuarioService service = new UsuarioService();
        System.out.println("controller: " + usuario.getNome());
        usuario = service.getUsuarioId(usuario.getIdUsuario());
        return new ModelAndView("telaUsuario").addObject("usuario", usuario);
    }

    /* @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") int id) throws SQLException {
        
        UsuarioService service = new UsuarioService();
        Usuario usuario = service.(id);
        service.deletarProduto(usuario);
        return new ModelAndView("telaUsuario");       
    }*/
    //POR FAVOR NÃO APAGUEM ISSO
    /* @GetMapping("/get-usuarioById/{id}")
    public ResponseEntity<List<Usuario>> getUsuarioById(@PathVariable("id") int id) throws ClassNotFoundException, SQLException{
        UsuarioService service = new UsuarioService();
        List<Usuario> lista = service.getUsuarioById(id);
        return ResponseEntity.ok(lista);
    }
     */
}
