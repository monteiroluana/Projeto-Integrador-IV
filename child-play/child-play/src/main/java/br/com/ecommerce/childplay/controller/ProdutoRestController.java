package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.ProdutoService;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//https://www.youtube.com/watch?v=1wYNFfgrXTI
@RestController
@RequestMapping("/produto")
public class ProdutoRestController {

    @GetMapping("/list-produto")
    public ResponseEntity list() throws ClassNotFoundException, SQLException {
        ProdutoService service = new ProdutoService();
        ResponseEntity re = null;
        try {
            
            re = ResponseEntity.createSuccess();
            re.setData(service.list());
        } catch (Exception e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }

    
    @GetMapping("/produto/{produto_id}")
    public ResponseEntity produtoById(@PathVariable("produto_id") int produtoId) throws ClassNotFoundException, SQLException {
        ProdutoService service = new ProdutoService();
        ResponseEntity re = null;
        try {
            if(service.getProdutoById(produtoId).isEmpty()) re = ResponseEntity.createUnknownError();
            re = ResponseEntity.createSuccess();
            re.setData(service.getProdutoById(produtoId));
        } catch (Exception e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    @PostMapping("/save")
    public ModelAndView saveProduto(@ModelAttribute("produto") Produto produto){
        ProdutoService service = new ProdutoService();
        service.saveProduto(produto);
    
    }
}
