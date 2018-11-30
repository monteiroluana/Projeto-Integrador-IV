
package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childPlay.model.Cartao;
import br.com.ecommerce.childplay.dao.CartaoDAO;
import java.sql.SQLException;

public class CartaoService {
     public String save(Cartao cartao, int idCliente) throws ClassNotFoundException, SQLException {
         CartaoDAO cartaoDao = new CartaoDAO();
        String msg = null;
        if (cartaoDao.save(cartao, idCliente)) {
            msg = "Cartão cadastrado com Sucesso!";
        } else {
            msg = "Falha ao cadastrar cartão!";
        }
        return msg;
    }
     
      public String update(Cartao cartao, int idCliente) throws ClassNotFoundException, SQLException {
         CartaoDAO cartaoDao = new CartaoDAO();
        String msg = null;
        if (cartaoDao.save(cartao, idCliente)) {
            msg = "Cartao atualizado com Sucesso!";
        } else {
            msg = "Falha ao atualizar cartao!";
        }
        return msg;
    }

   
}
