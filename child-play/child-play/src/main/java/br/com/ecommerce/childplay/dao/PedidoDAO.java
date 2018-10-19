package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PedidoDAO {

    int idPedido = 0;

    public void savePedido(Pedido pedido, Cliente cliente, Usuario usuario,List<ItemPedido> itens)throws ClassNotFoundException, SQLException {
       
        System.out.println("ENTROU AQUI");
        
        String sqlInsertPedido = "INSET INTO PEDIDO (idCliente, idUsuario, dataPedido, enderecoEntrega, tipoPagamento, status"
                + "VALUES (?,?,?,?,?,?))";

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            //INSERINDO PEDIDO
            p = connection.prepareStatement(sqlInsertPedido);
            p.setInt(1, cliente.getIdCliente());
            p.setInt(2, usuario.getIdUsuario());
            p.setDate(3, (Date) pedido.getDataPedido());
            p.setString(4, pedido.getEnderecoEntrega());
            p.setString(5, pedido.getTipoPagamento());
            p.setString(6, pedido.getStatus());
            rs = p.executeQuery();
            
           // selectIdPedido();
           // inserindoItens(itens, idPedido);

        } catch (Exception e) {
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
    }

    public void selectIdPedido() throws ClassNotFoundException, SQLException{
        System.out.println("ENTROU NO SELECT LAST_INSERT_ID");
        String sqlReturnId = "SELECT LAST_INSERT_ID();";

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            //PEGANDO O ID QUE FOI GERADO PARA IDPRODUTO
            p = connection.prepareStatement(sqlReturnId);
            rs = p.executeQuery();
            while (rs.next()) {
                idPedido = rs.getInt("LAST_INSERT_ID");
            }
            rs = null;
            p = null;

        } catch (Exception e) {
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
    }

    public void inserindoItens(List<ItemPedido> itens, int pedidoId)throws ClassNotFoundException, SQLException {
        System.out.println("ENTROU NO INSERINDOITENS");
        String sqlInsertItens = "INSERT INTO ITEM_PEDIDO (idPedido, idProduto, quantidade, preco)"
                + "VALUES (?,?,?,?)";

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;
        int idPedido = 0;

        try {
            connection = Conexao.getConnection();

            //INSERINDO ITEM-PEDIDO
            p = connection.prepareStatement(sqlInsertItens);
            for (ItemPedido iten : itens) {
                p.setInt(1, pedidoId);
                p.setInt(2, iten.getIdProduto());
                p.setInt(3, iten.getQuantidade());
                p.setBigDecimal(4, iten.getPreco());
                rs = p.executeQuery();
            }

        } catch (Exception e) {
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
    }

}
