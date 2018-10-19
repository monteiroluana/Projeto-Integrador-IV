package br.com.ecommerce.childplay;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childplay.dao.PedidoDAO;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChildPlayApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(ChildPlayApplication.class, args); 
                
                System.out.println("ESTOU NA CLASSE DE EXECUÇÃO");
                
                  List<ItemPedido> list = null;
                ItemPedido item = new ItemPedido();
                item.setIdProduto(1);
                item.setQuantidade(1);
                
                list.add(item);
                
                Cliente cliente = new Cliente();
                cliente.setIdCliente(1);
                
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(3);
                
                Pedido pedido = new Pedido();
                
                Date date = new Date(System.currentTimeMillis());
                pedido.setDataPedido(date);
                pedido.setEnderecoEntrega("rua do senac");
                pedido.setTipoPagamento("boleto");
                pedido.setStatus("indo para aprovação");
                
                PedidoDAO pedidoDao = new PedidoDAO();
                
                pedidoDao.savePedido(pedido, cliente, usuario,list);
	}
}
