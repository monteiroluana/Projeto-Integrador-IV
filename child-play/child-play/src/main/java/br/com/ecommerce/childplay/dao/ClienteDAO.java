package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class ClienteDAO {

    public List<Cliente> listClientes() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Cliente WHERE enable = ?";

        List<Cliente> lista = new ArrayList<>();

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
                Cliente cliente = new Cliente();
                Cliente.setIdCliente(rs.getInt("idCliente"));
                Cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNasc(rs.getDate("dataNasc"));
                cliente.setStringGenero(rs.getString("genero"));
                cliente.setString(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                Cliente.setLogin(rs.getString("login"));
                Cliente.setSenha(rs.getString("senha"));
 
                lista.add(Cliente);
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
    
    public Cliente getClienteByLoginSenha(String login, String senha) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Cliente WHERE enable = ? and login = ? and senha = ?";

        Cliente cliente = new Cliente();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, true);
            p.setString(2, cliente.getLogin());
            p.setString(3, cliente.getSenha());
            //Armazenando os resultados
            rs = p.executeQuery();

            //Enquanto tiver linha de resultado execute esse trecho
            if (rs.next()) {
                Cliente.setIdCliente(rs.getInt("idCliente"));
                Cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNasc(rs.getDate("dataNasc"));
                cliente.setStringGenero(rs.getString("genero"));
                cliente.setString(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                Cliente.setLogin(rs.getString("login"));
                Cliente.setSenha(rs.getString("senha")); 
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
        return cliente;
    }
    
    
    public boolean saveCliente(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO cliente (nome, cpf, dataNasc, genero, telefone, email, login, senha, enable) VALUES (?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setString(1, cliente.getNome());
            p.setString(2, cliente.getCpf());
            p.setDate(3, cliente.getDataNasc());
            p.setString(4, cliente.getGenero());
            p.setString(5, cliente.getTelefone());
            p.setString(6, cliente.getEmail());
            p.setString(7, cliente.getLogin());
            p.setString(8, cliente.getSenha());
            p.setBoolean(9, true);
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
