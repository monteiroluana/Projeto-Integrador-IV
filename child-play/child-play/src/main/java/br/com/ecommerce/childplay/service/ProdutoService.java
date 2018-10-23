
package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childPlay.dao.ProdutoDAO;
import br.com.ecommerce.childPlay.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
     public List<Produto> list() throws ClassNotFoundException, SQLException {
      ProdutoDAO produtoDao = new ProdutoDAO();
        return new ArrayList<>(produtoDao.listProduto());
    }
     
      public List<Produto> getProdutoById(int id) throws ClassNotFoundException, SQLException {
      ProdutoDAO produtoDao = new ProdutoDAO();
        return new ArrayList<>(produtoDao.getProdutoById(id));
    }
     
     public boolean saveProduto(Produto produto) throws ClassNotFoundException, SQLException{
           ProdutoDAO produtoDao = new ProdutoDAO();
        return (produtoDao.saveProduto(produto));
     }
}
