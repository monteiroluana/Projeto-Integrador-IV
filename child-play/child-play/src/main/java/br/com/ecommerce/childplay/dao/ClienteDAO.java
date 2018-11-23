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

    public static int idGerado;

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
                //cliente.setLogin(rs.getString("login"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setCep(rs.getString("cep"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));

                cliente.setCartao(listCartaoByClienteId(cliente.getIdCliente()));
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

    public boolean authClienteByLoginSenha(String email, String senha) throws ClassNotFoundException, SQLException {
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
                // cliente.setLogin(rs.getString("login"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setCartao(listCartaoByClienteId(cliente.getIdCliente()));
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

    public boolean saveCliente(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO cliente (nome, cpf, dataNasc, genero, telefone, email, senha, enable,"
                + "logradouro, numero, cep, complemento, bairro, cidade, uf, token) VALUES (?,?,?,?,?,?,?,?,? , ?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement p = null;

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

           // if (!cliente.getCartao().get(1).getNumCartao().isEmpty()) {
//                ResultSet rs = p.getGeneratedKeys();
//                if (rs.next()) {
//                    idGerado = rs.getInt(1);
//                }
//                saveCartao(cliente.getCartao().get(0));
                System.out.println("não está vazio");
           // }
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

    public boolean saveCartao(Cartao cartao) throws SQLException {

        String sql = "INSERT INTO cartao (idCliente, nomeTitular, tipoCartao, numCartao, codSeguranca, validade, enable) VALUES (?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setInt(1, cartao.getIdCliente());
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

    public List<Cartao> listCartaoByClienteId(int idCliente) throws SQLException {
        String sql = "select * from cartao where idCliente = ?";

        List<Cartao> lista = new ArrayList<>();

        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setInt(1, idCliente);
            rs = p.executeQuery();

            while (rs.next()) {
                Cartao cartao = new Cartao();
                cartao.setIdCliente(rs.getInt("idCliente"));
                cartao.setNomeTitular(rs.getString("nomeTitular"));
                cartao.setTipoCartao(rs.getString("tipoCartao"));
                cartao.setNumCartao(rs.getString("numCartao"));
                cartao.setCodSeguranca(rs.getString("codSeguranca"));
                cartao.setValidade(rs.getDate("validade"));
                cartao.setEnable(rs.getBoolean("enable"));

                lista.add(cartao);
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

    public boolean update(Cliente cliente) throws ClassNotFoundException, SQLException {
        String sql = "uptade cliente set genero = ?,telefone = ?, senha = ?, cep = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, uf = ?, complemento = ?, token = ? where email = ?";
  
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setString(1, cliente.getGenero());
            p.setString(2, cliente.getTelefone());
            p.setString(3, cliente.getSenha());
            p.setString(4, cliente.getCep());
            p.setString(5, cliente.getLogradouro());
            p.setString(6, cliente.getNumero());
            p.setString(7, cliente.getBairro());
            p.setString(8, cliente.getCidade());
            p.setString(9, cliente.getUf());
            p.setString(10, cliente.getComplemento());
            p.setString(11, cliente.getToken());
            
           p.execute();

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
}
