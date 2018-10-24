package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

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
