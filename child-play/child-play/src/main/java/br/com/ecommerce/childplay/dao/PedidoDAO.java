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

    public static int id_Pedido = 0;

    public void savePedido(Pedido pedido, int cliente, int usuario) throws ClassNotFoundException, SQLException {

        System.out.println("ENTROU AQUI NO PEDIDO DAO");
        System.out.println("objeto dentro do DAO: " + pedido.getEnderecoEntrega() + "|" + cliente + "|" + usuario);

        String sql = "INSERT INTO PEDIDO (idCliente, idUsuario, enderecoEntrega, tipoPagamento, status)"
                + "VALUES (?,?,?,?,?,?)";

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            //INSERINDO PEDIDO
            p = connection.prepareStatement(sql);
            p.setInt(1, cliente);
            p.setInt(2, usuario);
            // p.setDate(3, (Date) pedido.getDataPedido());
            p.setString(3, pedido.getEnderecoEntrega());
            p.setString(4, pedido.getTipoPagamento());
            p.setString(5, pedido.getStatus());
            p.execute();

//            rs = p.getGeneratedKeys();
//            if(rs.next()){
//                id_Pedido = rs.getInt(1);
//            }
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

    public void inserindoItens(List<ItemPedido> itens) throws ClassNotFoundException, SQLException {
        System.out.println("ENTROU NO INSERINDO ITENS");
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
                p.setInt(1, id_Pedido);
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
