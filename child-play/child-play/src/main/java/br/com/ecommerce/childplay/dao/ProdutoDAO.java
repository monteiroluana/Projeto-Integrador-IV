
package br.com.ecommerce.childPlay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
     public List<Produto> listProduto() throws ClassNotFoundException, SQLException {

        String sql = "SELECT p.idProduto, p.nome, p.marca ,p.descricao ,p.caracteristicas ,p.idade ,"
                + "p.categoria ,p.preco ,p.estoque ,p.desconto, i.imagem, i.alt \n"
                + "FROM produto as p INNER JOIN imagem as i ON i.idProduto = p.idProduto WHERE enable = ?;";

        List<Produto> lista = new ArrayList<>();

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, true);
            //Armazenando os resultados
            rs = p.executeQuery();

            //Enquanto tiver linha de resultado execute esse trecho
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setNome(rs.getString("nome"));
                produto.setMarca(rs.getString("marca"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setCaracteristicas(rs.getString("caracteristicas"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setIdade(rs.getInt("idade"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setEstoque(rs.getInt("estoque"));
                produto.setDesconto(rs.getInt("desconto"));
                produto.setImagem(rs.getString("imagem"));
                produto.setAlt(rs.getString("alt"));
                         
                lista.add(produto);
            }
        } catch (SQLException e) {

        } finally {
            //Fechando todas as conexões que foram abertas
            try {
                if (connection != null) {
                    connection.close();
                }
                if (p != null) {
                    p.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {

            }
        }
        return lista;
    }
     
     
     public List<Produto> getProdutoById(int id) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM produto WHERE enable = ? and idProduto = ?";

        List<Produto> lista = new ArrayList<>();

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, true);
            p.setInt(2, id);
            //Armazenando os resultados
            rs = p.executeQuery();

            //Enquanto tiver linha de resultado execute esse trecho
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setNome(rs.getString("nome"));
                produto.setMarca(rs.getString("marca"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setCaracteristicas(rs.getString("caracteristicas"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setIdade(rs.getInt("idade"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setEstoque(rs.getInt("estoque"));
                produto.setDesconto(rs.getInt("desconto"));
         
                lista.add(produto);
            }
        } catch (SQLException e) {

        } finally {
            //Fechando todas as conexões que foram abertas
            try {
                if (connection != null) {
                    connection.close();
                }
                if (p != null) {
                    p.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {

            }
        }
        return lista;
    }
}
