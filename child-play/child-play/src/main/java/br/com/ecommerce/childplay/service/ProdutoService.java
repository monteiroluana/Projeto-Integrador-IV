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

    public Produto getProdutoById(int id) throws ClassNotFoundException, SQLException {
        ProdutoDAO produtoDao = new ProdutoDAO();
        return produtoDao.getProdutoById(id);
    }

    public List<Produto> getProdutoByNome(String nome) throws ClassNotFoundException, SQLException {
        ProdutoDAO produtoDao = new ProdutoDAO();
        return produtoDao.getProdutoByNome(nome);
    }

    public String save(Produto produto) throws ClassNotFoundException, SQLException {
        ProdutoDAO produtoDao = new ProdutoDAO();
        String msg = null;
        if (produtoDao.save(produto)) {
            msg = "Produto foi inserido com Sucesso!";
        } else {
            msg = "Falha ao inserir o produto!";
        }
        return msg;
    }
}
