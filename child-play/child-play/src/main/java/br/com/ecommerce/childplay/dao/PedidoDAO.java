package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class PedidoDAO {

    public static int idGerado;

    public String savePedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO PEDIDO (idCliente, enderecoEntrega, tipoPagamento, status, protocolo, dataPedido) VALUES (?,?,?,?,?,?)";

        Connection con = null;
        PreparedStatement p = null;

        try {
            con = Conexao.getConnection();
            p = con.prepareStatement(sql, p.RETURN_GENERATED_KEYS);
            p.setInt(1, pedido.getIdCliente());
            p.setString(2, pedido.getEnderecoEntrega());
            p.setString(3, pedido.getTipoPagamento());
            p.setString(4, pedido.getStatus());
            p.setString(5, pedido.getProtocolo());

            Timestamp times = new Timestamp(System.currentTimeMillis());
            Date date = new Date(times.getTime());

            p.setDate(6, date);
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
        String sql = "INSERT INTO ITEM_PEDIDO (idPedido, idProduto, quantidade, preco)VALUES (?,?,?,?)";

        Connection con = null;
        PreparedStatement p = null;

        try {
            con = Conexao.getConnection();

            //INSERINDO ITEM-PEDIDO
            p = con.prepareStatement(sql);
            for (ItemPedido iten : itens) {
                p.setInt(1, idPedido);
                p.setInt(2, iten.getIdProduto());
                p.setInt(3, iten.getQuantidade());
                p.setDouble(4, iten.getPreco());

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
}
