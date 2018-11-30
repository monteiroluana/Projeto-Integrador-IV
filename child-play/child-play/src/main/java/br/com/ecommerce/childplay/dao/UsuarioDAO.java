package br.com.ecommerce.childPlay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> listUsuarios() throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM usuario WHERE enable = ?";

        List<Usuario> lista = new ArrayList<>();

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
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFuncao(rs.getString("funcao"));
                
                lista.add(usuario);
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
    
    
    public boolean authUsuarioByLoginSenha(String login, String senha) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM usuario WHERE enable = ?  and login = ? and senha = ?";
        
        Usuario usuario = new Usuario();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, true);
            p.setString(2, login);
            p.setString(3, senha);
            //Armazenando os resultados
            rs = p.executeQuery();

            //Enquanto tiver linha de resultado execute esse trecho
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFuncao(rs.getString("funcao"));
                return true;
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
        return false;
    }
    
    
    public boolean save (Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (nome, login, senha, funcao, enable) VALUES (?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setString(1, usuario.getNome());
            p.setString(2, usuario.getLogin());
            p.setString(3, usuario.getSenha());
            p.setString(4, usuario.getFuncao());
            p.setBoolean(5, true);

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
    
     public boolean update (Usuario usuario) throws SQLException {

        String sql = "UPDATE USUARIO SET nome = ?, senha = ?, funcao = ? WHERE login = ?";
        
        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            
            p.setString(1, usuario.getNome());
            p.setString(2, usuario.getSenha());
            p.setString(3, usuario.getFuncao());
            p.setString(4, usuario.getLogin());

            return true;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        
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
