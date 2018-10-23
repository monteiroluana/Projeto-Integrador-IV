package br.com.ecommerce.childplay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class testeDAO {

    public static int idGerado;
    public void savePedido(Pedido pedido, int usuario, int cliente, List<ItemPedido> itens) throws SQLException {
        String sql = "INSERT INTO PEDIDO (idCliente, idUsuario, enderecoEntrega, tipoPagamento, status) VALUES (?,?,?,?,?)";

        Connection con = null;
        PreparedStatement p = null;
  
        try {
            con = Conexao.getConnection();
            p = con.prepareStatement(sql, p.RETURN_GENERATED_KEYS);
            p.setInt(1, cliente);
            p.setInt(2, usuario);
            p.setString(3, pedido.getEnderecoEntrega());
            p.setString(4, pedido.getTipoPagamento());
            p.setString(5, pedido.getStatus());
            
            Timestamp dtNow = new Timestamp(pedido.getDataPedido().getTime());
            
         //   p.setTimestamp(6, dtNow);
            p.execute();
            
            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
            System.out.println("idGerado: "+idGerado);
            
            saveItemPedido(itens,idGerado);
 
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
    
     public void saveItemPedido(List<ItemPedido>itens, int idPedido) throws SQLException {
        String sql = "INSERT INTO ITEM_PEDIDO (idPedido, idProduto, quantidade, preco)VALUES (?,?,?,?)";

        Connection con = null;
        PreparedStatement p = null;
       
  
       try {
            con = Conexao.getConnection();

            //INSERINDO ITEM-PEDIDO
            p = con.prepareStatement(sql);
            for (ItemPedido iten : itens) {
                System.out.println("dentro do for saveItemPedido: "+idPedido+" | "+ iten.getIdProduto());
                p.setInt(1, idPedido);
                p.setInt(2, iten.getIdProduto());
                p.setInt(3, iten.getQuantidade());
                p.setBigDecimal(4, iten.getPreco());
                
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
