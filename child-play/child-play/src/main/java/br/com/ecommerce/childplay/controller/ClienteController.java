
package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.ClienteService;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @PostMapping("/save")
    public ResponseEntity saveProduto(@ModelAttribute("cliente")Cliente cliente) throws ClassNotFoundException, SQLException {
       ClienteService service = new ClienteService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.saveCliente(cliente));
        } catch (ClassNotFoundException | SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
      return re;
    }
    
    @GetMapping("/form")
    public ModelAndView abrirForm() {
        ModelAndView retorno = new ModelAndView("telaTesteCliente")
                .addObject("cliente", new Cliente());
        return retorno;
    }
}
