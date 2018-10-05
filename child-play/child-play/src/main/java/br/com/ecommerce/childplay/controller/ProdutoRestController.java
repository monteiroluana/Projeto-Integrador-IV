
package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Produto;
import br.com.ecommerce.childplay.service.ProdutoService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//https://www.youtube.com/watch?v=1wYNFfgrXTI

@RestController
@RequestMapping("/produto")
public class ProdutoRestController {
    @GetMapping("/list-produto")
    public ResponseEntity<List<Produto>> list() throws ClassNotFoundException, SQLException{
        ProdutoService service = new ProdutoService();
        List<Produto> lista = service.list();
        return ResponseEntity.ok(lista);
    }
    
     @GetMapping("/get-produtoById/{id}")
    public ResponseEntity<List<Produto>> listById(@PathVariable("id") int id) throws ClassNotFoundException, SQLException{
        ProdutoService service = new ProdutoService();
        List<Produto> lista = service.getProdutoById(id);
        return ResponseEntity.ok(lista);
    }
}
