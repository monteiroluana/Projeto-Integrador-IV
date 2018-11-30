package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Cartao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartaoDAO {

    public boolean save(Cartao cartao, int idCliente) throws SQLException {

        String sql = "INSERT INTO cartao (idCliente, nomeTitular, tipoCartao, numCartao, codSeguranca, validade, enable) VALUES (?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setInt(1, idCliente);
            p.setString(2, cartao.getNomeTitular());
            p.setString(3, cartao.getTipoCartao());
            p.setString(4, cartao.getNumCartao());
            p.setString(5, cartao.getCodSeguranca());
            p.setDate(6, cartao.getValidade());
            p.setBoolean(7, true);
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

    public Cartao getCartaoByClienteId(int idCliente) throws SQLException {
        String sql = "select * from cartao where idCliente = ?";

        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        Cartao cartao = new Cartao();

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setInt(1, idCliente);
            rs = p.executeQuery();

            if (rs.next()) {

                cartao.setIdCliente(rs.getInt("idCliente"));
                cartao.setNomeTitular(rs.getString("nomeTitular"));
                cartao.setTipoCartao(rs.getString("tipoCartao"));
                cartao.setNumCartao(rs.getString("numCartao"));
                cartao.setCodSeguranca(rs.getString("codSeguranca"));
                cartao.setValidade(rs.getDate("validade"));
                cartao.setEnable(rs.getBoolean("enable"));
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
        return cartao;

    }

    public boolean update(Cartao cartao) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE cartao SET nomeTitular =?, tipoCartao =?, numCartao =?, codSeguranca =?, validade =? WHERE idCliente=?";

        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);

            p.setString(1, cartao.getNomeTitular());
            p.setString(2, cartao.getTipoCartao());
            p.setString(3, cartao.getNumCartao());
            p.setString(4, cartao.getCodSeguranca());
            p.setDate(5, cartao.getValidade());
            p.setInt(5, cartao.getIdCliente());
            
            p.execute();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;

        } finally {
            //Fechando todas as conexões que foram abertas
            try {
                if (connection != null) {
                    connection.close();
                }
                if (p != null) {
                    p.close();
                }
            } catch (SQLException e) {

            }
        }

    }

}
