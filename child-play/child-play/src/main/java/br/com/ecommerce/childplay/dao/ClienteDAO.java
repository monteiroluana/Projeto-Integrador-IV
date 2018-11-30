package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Cartao;
import br.com.ecommerce.childPlay.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public Cliente getClientePorEmail(String email) throws ClassNotFoundException, SQLException {
        String sql = "select * from cliente where email = ?";

        Cliente cliente = new Cliente();

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setString(1, email);
            //Armazenando os resultados
            rs = p.executeQuery();

            //Enquanto tiver linha de resultado execute esse trecho
            while (rs.next()) {

                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNasc(rs.getDate("dataNasc"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setCep(rs.getString("cep"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));

                CartaoDAO cartaoDao = new CartaoDAO();
                Cartao cartao = cartaoDao.getCartaoByClienteId(cliente.getIdCliente());
                cliente.setCartao(cartao);

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

    public List<Cliente> listClientes() throws ClassNotFoundException, SQLException {
        String sql = "select * from cliente where enable = ?";

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
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNasc(rs.getDate("dataNasc"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setCep(rs.getString("cep"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));

                CartaoDAO cartaoDao = new CartaoDAO();
                Cartao cartao = cartaoDao.getCartaoByClienteId(cliente.getIdCliente());
                cliente.setCartao(cartao);

                lista.add(cliente);
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

    public Cliente authClienteByEmailSenha(String email, String senha) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Cliente WHERE enable = ? and email = ? and senha = ?";

        Cliente cliente = new Cliente();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, true);
            p.setString(2, email);
            p.setString(3, senha);
            //Armazenando os resultados
            rs = p.executeQuery();

            //Enquanto tiver linha de resultado execute esse trecho
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNasc(rs.getDate("dataNasc"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setCep(rs.getString("cep"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));

                CartaoDAO cartaoDao = new CartaoDAO();
                Cartao cartao = cartaoDao.getCartaoByClienteId(cliente.getIdCliente());
                cliente.setCartao(cartao);

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

    public boolean save(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO cliente (nome, cpf, dataNasc, genero, telefone, email, senha, enable,"
                + "logradouro, numero, cep, complemento, bairro, cidade, uf, token) VALUES (?,?,?,?,?,?,?,?,? , ?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement p = null;
        int idGerado = -1;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql, p.RETURN_GENERATED_KEYS);
            p.setString(1, cliente.getNome());
            p.setString(2, cliente.getCpf());
            p.setDate(3, cliente.getDataNasc());
            p.setString(4, cliente.getGenero());
            p.setString(5, cliente.getTelefone());
            p.setString(6, cliente.getEmail());

            p.setString(7, cliente.getSenha());
            p.setBoolean(8, true);
            p.setString(9, cliente.getLogradouro());
            p.setString(10, cliente.getNumero());
            p.setString(11, cliente.getCep());
            p.setString(12, cliente.getComplemento());
            p.setString(13, cliente.getBairro());
            p.setString(14, cliente.getCidade());
            p.setString(15, cliente.getUf());
            p.setString(16, cliente.getToken());

            p.execute();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
                System.out.println("idGerado: " + idGerado);
            }

            CartaoDAO cartaoDao = new CartaoDAO();
            cartaoDao.save(cliente.getCartao(), idGerado);

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

    public boolean update(Cliente cliente) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE cliente SET  senha = ? ,  nome = ?, cpf  = ?, dataNasc  = ?, telefone = ?, genero  = ? , token = ? ,"
                + "cep = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, uf = ?, complemento = ? "
                + "WHERE email = ? and enable = ?";

        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);

            p.setString(1, cliente.getSenha());
            p.setString(2, cliente.getNome());
            p.setString(3, cliente.getCpf());
            p.setDate(4, cliente.getDataNasc());
            p.setString(5, cliente.getTelefone());
            p.setString(6, cliente.getGenero());
            p.setString(7, cliente.getToken());
            p.setString(8, cliente.getCep());
            p.setString(9, cliente.getLogradouro());
            p.setString(10, cliente.getNumero());
            p.setString(11, cliente.getBairro());
            p.setString(12, cliente.getCidade());
            p.setString(13, cliente.getUf());
            p.setString(14, cliente.getComplemento());
            p.setString(15, cliente.getEmail());
            p.setBoolean(16, true);

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
    
    
    public boolean enable(Cliente cliente) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE cliente SET  enable = ? "
                + "WHERE (email = ?)";

        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);

            p.setBoolean(1,false);
            p.setString(2, cliente.getEmail());

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
