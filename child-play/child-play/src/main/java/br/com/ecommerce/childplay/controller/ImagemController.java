package br.com.ecommerce.childplay.controller;

import br.com.ecommerce.childplay.model.Imagem;
import br.com.ecommerce.childplay.model.ResponseEntity;
import br.com.ecommerce.childplay.service.ImagemService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagem")
public class ImagemController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public ResponseEntity save(@RequestBody List<Imagem> imagem) throws ClassNotFoundException, SQLException {
        ImagemService service = new ImagemService();
        ResponseEntity re = null;
        try {
            re = ResponseEntity.createSuccess();
            re.setData(service.save(imagem));
        } catch (SQLException e) {
            re = ResponseEntity.createUnknownError();
        }
        return re;

    }
}
