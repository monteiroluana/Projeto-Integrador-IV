package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Produto;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.ProdutoService;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//https://www.youtube.com/watch?v=1wYNFfgrXTI
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @GetMapping("/list-produto")
    public ResponseEntity list() throws ClassNotFoundException, SQLException {
        ProdutoService service = new ProdutoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.list());
        } catch (ClassNotFoundException | SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @GetMapping("/{produto_id}")
    public ResponseEntity produtoById(@PathVariable("produto_id") int produtoId) throws ClassNotFoundException, SQLException {
        ProdutoService service = new ProdutoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.getProdutoById(produtoId));
        } catch (ClassNotFoundException | SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    @PostMapping("/save")
    public ResponseEntity saveProduto(@ModelAttribute("produto") Produto produto) throws ClassNotFoundException, SQLException {
        ProdutoService service = new ProdutoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.saveProduto(produto));
        } catch (ClassNotFoundException | SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
      return re;
    }
      
    @GetMapping("/form")
    public ModelAndView abrirForm() {
        ModelAndView retorno = new ModelAndView("telaTesteProduto")
                .addObject("produto", new Produto());
        return retorno;
    }
    
    
}
