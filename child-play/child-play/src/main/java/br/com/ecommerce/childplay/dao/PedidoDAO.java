package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import br.com.ecommerce.childplay.model.PlanZ;
import br.com.ecommerce.childPlay.model.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    
    public static int idGerado;
    
    public String savePedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO PEDIDO (idCliente, tipoPagamento, status, protocolo, dataPedido, valorTotal, valorFrete,"
                + "logradouro, numero, cep, complemento, bairro, cidade, uf) "
                + "VALUES (?,?,?,?,?,?,?, ?,?,?,?,?,?,?)";
        
        Timestamp times = new Timestamp(System.currentTimeMillis());
        Date date = new Date(times.getTime());
        Connection con = null;
        PreparedStatement p = null;
        
        try {
            con = Conexao.getConnection();
            p = con.prepareStatement(sql, p.RETURN_GENERATED_KEYS);
            p.setInt(1, pedido.getIdCliente());
            p.setString(2, pedido.getTipoPagamento());
            p.setString(3, pedido.getStatus());
            p.setString(4, pedido.getProtocolo());
            p.setDate(5, date);
            p.setDouble(6, pedido.getValorTotal());
            p.setDouble(7, pedido.getValorFrete());
            p.setString(8, pedido.getLogradouro());
            p.setString(9, pedido.getNumero());
            p.setString(10, pedido.getCep());
            p.setString(11, pedido.getComplemento());
            p.setString(12, pedido.getBairro());
            p.setString(13, pedido.getCidade());
            p.setString(14, pedido.getUf());
            
            p.execute();
            
            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
            
            saveItemPedido(pedido.getItens(), idGerado);
            
            return pedido.getProtocolo();
        } catch (SQLException e) {
            
        } finally {
            if (con != null) {
                con.close();
            }
            if (p != null) {
                p.close();
            }
        }
        return null;
        
    }
    
    public void saveItemPedido(List<ItemPedido> itens, int idPedido) throws SQLException {
        String sql = "INSERT INTO itemPedido (idPedido, idProduto, quantidade, preco)VALUES (?,?,?,?)";
        
        Connection con = null;
        PreparedStatement p = null;
        
        try {
            con = Conexao.getConnection();

            //INSERINDO ITEM-PEDIDO
            p = con.prepareStatement(sql);
            for (ItemPedido item : itens) {
                p.setInt(1, idPedido);
                p.setInt(2, item.getProduto().getIdProduto());                
                p.setInt(3, item.getQuantidade());
                p.setDouble(4, item.getPreco());
                
                p.execute();
            }
            
        } catch (SQLException e) {
            
        } finally {
            if (con != null) {
                con.close();
            }
            if (p != null) {
                p.close();
            }
        }
    }
    
    public Pedido getPedidosByProtocolo(String protocolo) throws SQLException {
        
        String sql = "SELECT * FROM pedido \n"
                + "INNER JOIN itemPedido ON itemPedido.idPedido = pedido.idPedido \n"
                + "INNER JOIN produto ON produto.idProduto = itemPedido.idProduto\n"
                + "INNER JOIN cliente ON cliente.idCliente = pedido.idCliente \n"
                + "WHERE pedido.protocolo = ?";
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        Pedido pedido = new Pedido();
        
        try {
            con = Conexao.getConnection();
            p = con.prepareStatement(sql);
            p.setString(1, protocolo);
            rs = p.executeQuery();
            
            if (rs.next()) {
                
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdCliente(rs.getInt("idCliente"));
                pedido.setDataPedido(rs.getDate("dataPedido"));
                // pedido.setEnderecoEntrega(rs.getString("enderecoEntrega"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                pedido.setTipoPagamento(rs.getString("tipoPagamento"));
                pedido.setStatus(rs.getString("status"));
                pedido.setProtocolo(rs.getString("protocolo"));
                pedido.setValorFrete(rs.getDouble("valorFrete"));
                
                List<ItemPedido> itens = getItensPedido(pedido.getIdPedido());
                pedido.setItens(itens);
            }
            
        } catch (SQLException e) {
            
        } finally {
            if (con != null) {
                con.close();
            }
            if (p != null) {
                p.close();
            }
        }
        return pedido;
    }
    
    public List<ItemPedido> getItensPedido(int idPedido) throws SQLException {
        String sql = "select idItem, idPedido, idProduto, quantidade, preco from itemPedido where idPedido = ?;";
        
        List<ItemPedido> lista = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        
        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setInt(1, idPedido);
            rs = p.executeQuery();
            
            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setIdPedido(rs.getInt("idPedido"));
                item.setIdItem(rs.getInt("idItem"));
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                item.setProduto(produto);
                
                item.setPreco(rs.getDouble("preco"));
                item.setQuantidade(rs.getInt("quantidade"));
                lista.add(item);
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
    
    public List<PlanZ> listPedido() throws SQLException {
        String sql = "select p.idPedido, p.dataPedido, p.protocolo, p.status, p.tipoPagamento, p.valorTotal, p.valorFrete, "
                + "c.idCliente, c.nome, c.cpf, c.dataNasc, c.email, c.genero, c.senha, c.telefone "
                + "from pedido as p "
                + "inner join cliente as c on p.idCliente = c.idCliente";
        
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        
        List<PlanZ> list = new ArrayList<>();
        try {
            con = Conexao.getConnection();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            
            while (rs.next()) {
                PlanZ pedido = new PlanZ();
                pedido.setIdPedido(rs.getInt("idPedido"));
                
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNasc(rs.getDate("dataNasc"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
               // cliente.setLogin(rs.getString("login"));
                cliente.setSenha(rs.getString("senha"));
                pedido.setCliente(cliente);
                pedido.setDataPedido(rs.getDate("dataPedido"));
                
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                pedido.setTipoPagamento(rs.getString("tipoPagamento"));
                pedido.setStatus(rs.getString("status"));
                pedido.setProtocolo(rs.getString("protocolo"));
                pedido.setValorFrete(rs.getDouble("valorFrete"));
                
                List<ItemPedido> itens = getItensPedido(pedido.getIdPedido());
                pedido.setItens(itens);
                
                list.add(pedido);
            }
            
        } catch (SQLException e) {
            
        } finally {
            if (con != null) {
                con.close();
            }
            if (p != null) {
                p.close();
            }
        }
        return list;
    }
    
    public List<Pedido> listPedidosByCliente(Cliente cliente) throws SQLException {
        
        String sql = "SELECT * FROM pedido \n"
                + "INNER JOIN itemPedido ON itemPedido.idPedido = pedido.idPedido \n"
                + "INNER JOIN produto ON produto.idProduto = itemPedido.idProduto\n"
                + "INNER JOIN cliente ON cliente.idCliente = pedido.idCliente \n"
                + "WHERE cliente.email = ?";
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        List<Pedido> list = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            p = con.prepareStatement(sql);
            p.setString(1, cliente.getCpf());
            rs = p.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdCliente(rs.getInt("idCliente"));
                pedido.setDataPedido(rs.getDate("dataPedido"));
                // pedido.setEnderecoEntrega(rs.getString("enderecoEntrega"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                pedido.setTipoPagamento(rs.getString("tipoPagamento"));
                pedido.setStatus(rs.getString("status"));
                pedido.setProtocolo(rs.getString("protocolo"));
                pedido.setValorFrete(rs.getDouble("valorFrete"));
                
                List<ItemPedido> itens = getItensPedido(pedido.getIdPedido());
                pedido.setItens(itens);
                
                list.add(pedido);
            }
            
        } catch (SQLException e) {
            
        } finally {
            if (con != null) {
                con.close();
            }
            if (p != null) {
                p.close();
            }
        }
        return list;
    }
    
}
