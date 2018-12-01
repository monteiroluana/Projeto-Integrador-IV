package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childplay.dao.ImagemDAO;
import br.com.ecommerce.childplay.model.Imagem;
import java.sql.SQLException;
import java.util.List;

public class ImagemService {

    public String save(List<Imagem> imagem) throws ClassNotFoundException, SQLException {
        ImagemDAO imagemDao = new ImagemDAO();
        String msg = null;

        for (Imagem img : imagem) {
            imagemDao.save(img);
        }
        return msg;
    }
}
