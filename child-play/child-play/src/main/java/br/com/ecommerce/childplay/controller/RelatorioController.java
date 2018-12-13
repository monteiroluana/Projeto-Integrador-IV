
package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.RelatorioService;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {
    
    @GetMapping("/ver")
    public ResponseEntity relatorio() throws ClassNotFoundException, SQLException {
        RelatorioService service = new RelatorioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.relatorio());
        } catch (ClassNotFoundException | SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    
    @GetMapping("/pedido")
    public ResponseEntity relatorioPedidos() throws ClassNotFoundException, SQLException {
        RelatorioService service = new RelatorioService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.relatorioPedidos());
        } catch (ClassNotFoundException | SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;
    }
    
    @GetMapping("/relatorio")
    public ModelAndView listarProdutos() throws ClassNotFoundException, SQLException {
        return new ModelAndView("Relatorio");
    }
}
