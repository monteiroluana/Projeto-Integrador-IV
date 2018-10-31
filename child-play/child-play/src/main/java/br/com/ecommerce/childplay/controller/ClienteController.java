package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.ClienteService;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity saveProduto(@RequestBody Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteService service = new ClienteService();
        ResponseEntity re = null;
        System.out.println("\n --------");
         System.out.println("resposta URL:cliente/save: " + cliente.getNome());
         System.out.println("resposta URL:cliente/save: " + cliente.getLogin());
         System.out.println("resposta URL:cliente/save: " + cliente.getSenha());
         System.out.println("resposta URL:cliente/save: " + cliente.getCpf());
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
