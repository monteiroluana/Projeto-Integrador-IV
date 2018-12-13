package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childPlay.dao.ProdutoDAO;
import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childPlay.model.Produto;
import br.com.ecommerce.childPlay.model.Usuario;
import br.com.ecommerce.childplay.dao.PedidoDAO;
import br.com.ecommerce.childplay.model.ItemPedido;
import br.com.ecommerce.childplay.model.Pedido;
import br.com.ecommerce.childplay.model.PlanZ;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PedidoService {

    public String save(Pedido pedido) throws SQLException, ClassNotFoundException {
        pedido.setProtocolo(gerarProtocolo());
        List<ItemPedido> itens = pedido.getItens();
        ProdutoDAO produtodao = new ProdutoDAO();
        

        double total = 0; //Somando o valor total dos itens
        for (ItemPedido iten : itens) {
            Produto produto = produtodao.getProdutoById(iten.getProduto().getIdProduto());
            iten.setDesconto(produto.getDesconto());
            
            double valDesconto = iten.getPreco() * iten.getDesconto()/100;                        
            total += (iten.getPreco() - valDesconto) * iten.getQuantidade();
            
        }

        pedido.setValorTotal(total + pedido.getValorFrete());
        PedidoDAO pedidoDao = new PedidoDAO();
        System.out.println(pedido);
        
        boolean qtdsValida = RetiraEstoqueItemPedido(itens);
        return pedidoDao.save(pedido);
    }

    public List<PlanZ> listPedido() throws SQLException, ClassNotFoundException {
        PedidoDAO pedidoDao = new PedidoDAO();
        return pedidoDao.listPedido();
    }

    public PlanZ getPedidosByProtocolo(String protocolo) throws SQLException, ClassNotFoundException {
        PedidoDAO pedidoDao = new PedidoDAO();
        return pedidoDao.getPedidosByProtocolo(protocolo);
    }
    
    public boolean AutorizaPagamento(String protocolo) throws SQLException, ClassNotFoundException {
        PedidoDAO pedidoDao = new PedidoDAO();
        return pedidoDao.AutorizaPedido(protocolo, "Pagamento Aprovado");
    }

    public boolean AutorizaPedido(String protocolo) throws SQLException, ClassNotFoundException {
        PedidoDAO pedidoDao = new PedidoDAO();
        return pedidoDao.AutorizaPedido(protocolo, "Aprovado");
    }
    
    public boolean CancelaPedido(String protocolo) throws SQLException, ClassNotFoundException {
        PedidoDAO pedidoDAO = new PedidoDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        PlanZ pedido = pedidoDAO.getPedidosByProtocolo(protocolo);
        
        List<ItemPedido> itens = pedidoDAO.getItensPedido(pedido.getIdPedido());                
        produtoDAO.RetornaEstoqueProduto(itens);
        
        
        return pedidoDAO.AutorizaPedido(protocolo, "Cancelado");
    }
    

    public List<PlanZ> listPedidosByCliente(String email) throws SQLException, ClassNotFoundException {
        PedidoDAO pedidoDao = new PedidoDAO();
        return pedidoDao.listPedidosByCliente(email);
    }

    public static String gerarProtocolo() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss"); //formato da data
        Date date = new Date(); //Pegando a data do momento
        String nDt = (dateFormat.format(date).replaceAll("[^0-9]", "")); //retidando todas os caracteres que não estiveres no intervalo 0 a 9
        int random = (int) Math.ceil(Math.random() * (999 - 100 + 1)) - 1 + 100; //gerando um random que vai de 100 a 999
        nDt = nDt + (Integer.toString(random)); //concatenando data com o valor gerado pelo randomm

        return nDt;
    }
    
    public boolean RetiraEstoqueItemPedido(List<ItemPedido> itens) throws ClassNotFoundException, SQLException {
        
        
        for (ItemPedido item : itens) {
            Produto produto = new Produto();
            produto.setIdProduto(item.getProduto().getIdProduto());            
            
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produto = produtoDAO.getProdutoById(produto.getIdProduto());
            
            int qtdProduto = produto.getEstoque();
            int qtdItemPedido = item.getQuantidade();
            int novoEstoqueProduto = qtdProduto - qtdItemPedido;
            
            if(novoEstoqueProduto > 0){
            produtoDAO.setEstoqueProduto(produto.getIdProduto(), novoEstoqueProduto);
            } 
            else{return false;}
            
        }

        return true;
    }

    public List<PlanZ> listPedidosByStatus(String status) throws SQLException, ClassNotFoundException {
        PedidoDAO pedidoDao = new PedidoDAO();
        return pedidoDao.listPedidosByStatus(status);
    }
}
