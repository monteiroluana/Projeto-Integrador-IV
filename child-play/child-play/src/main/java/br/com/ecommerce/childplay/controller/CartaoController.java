package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childPlay.model.Cartao;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.CartaoService;
import java.sql.SQLException;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity save(@RequestBody Cartao cartao) throws ClassNotFoundException, SQLException {
        CartaoService service = new CartaoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.save(cartao, cartao.getIdCliente()));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity update(@RequestBody Cartao cartao) throws ClassNotFoundException, SQLException {
        CartaoService service = new CartaoService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.update(cartao));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }
}
