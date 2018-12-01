package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childplay.model.Imagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImagemDAO {
    
    public List<Imagem> getImagemByProdutoId(int idProduto) throws SQLException {
        String sql = "select i.idImagem, i.idProduto, i.imagem, i.alt from "
                + "imagem as i inner join produto on i.idProduto = produto.idProduto "
                + "Where produto.idProduto = ?;";
        
        List<Imagem> lista = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        
        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setInt(1, idProduto);
            rs = p.executeQuery();
            
            while (rs.next()) {
                Imagem img = new Imagem();
                img.setIdIMagem(rs.getInt("idImagem"));
                img.setIdProduto(rs.getInt("idProduto"));
                img.setImagem(rs.getString("imagem"));
                img.setAlt(rs.getString("alt"));
                
                lista.add(img);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        } finally {
            
            if (connection != null) {
                connection.close();
            }
            if (p != null) {
                p.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return lista;
        
    }
    
    public boolean save(Imagem imagem) throws SQLException {
        String sql = "INSERT INTO imagem (idProduto, imagem, alt) "
                + "VALUES (?,?,?)";
        
        Connection connection = null;
        PreparedStatement p = null;
        
        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setInt(1, imagem.getIdProduto());
            p.setString(2, imagem.getImagem());
            p.setString(3, imagem.getImagem());
            
            p.execute();
            return (true);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return (false);
            
        } finally {
            
            if (connection != null) {
                connection.close();
            }
            if (p != null) {
                p.close();
            }
            
        }
    }
    
}
