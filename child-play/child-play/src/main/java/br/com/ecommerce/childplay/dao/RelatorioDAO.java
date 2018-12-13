package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.PlanZ;
import br.com.ecommerce.childplay.model.Relatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
    
    public List<Relatorio> relatorio() throws SQLException, ClassNotFoundException {
        
        String sql = "SELECT sum(pedido.valorTotal) as somaMes,"
                + "DATE_FORMAT(pedido.dataPedido, \"%M\") as mes,"
                + "DATE_FORMAT(pedido.dataPedido, \"%Y\") as ano "
                + "from pedido "
                + "group by mes,ano "
                + "order by ano,mes;";
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        List<Relatorio> list = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            
            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setSomaMes(rs.getDouble("somaMes"));
                relatorio.setMes(rs.getString("mes"));
                relatorio.setAno(rs.getString("ano"));
                list.add(relatorio);
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
    
    public List<Relatorio> relatorioPedidos() throws SQLException, ClassNotFoundException {
        
        String sql = "SELECT idPedido, protocolo, idCliente, idUsuario, dataPedido, tipoPagamento, status, "
                + "valorTotal, valorFrete, cep, logradouro, numero, bairro, cidade, uf, complemento,"
                + "DATE_FORMAT(pedido.dataPedido, \"%m\") as mes, "
                + "DATE_FORMAT(pedido.dataPedido, \"%Y\") as ano  FROM pedido";
        
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        List<Relatorio> list = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            
            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
  
                relatorio.setMes(rs.getString("mes"));
                relatorio.setAno(rs.getString("ano"));
                
                PlanZ pedido = new PlanZ();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setDataPedido(rs.getDate("dataPedido"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                pedido.setTipoPagamento(rs.getString("tipoPagamento"));
                pedido.setStatus(rs.getString("status"));
                pedido.setProtocolo(rs.getString("protocolo"));
                pedido.setValorFrete(rs.getDouble("valorFrete"));
                
                PedidoDAO pedidoDao = new PedidoDAO();
                List<ItemPedido> itens = pedidoDao.getItensPedido(pedido.getIdPedido());
                pedido.setItens(itens);
                
                ClienteDAO clienteDao = new ClienteDAO();
                Cliente cliente = clienteDao.getClienteById(rs.getInt("idCliente"));
                pedido.setCliente(cliente);
                
                relatorio.setPedido(pedido);
                
                list.add(relatorio);
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
