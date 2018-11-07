
package br.com.ecommerce.childplay.model;
import br.com.ecommerce.childPlay.model.Produto;

public class ItemPedido {
    private int idPedido;
   // private int idProduto;
    private int quantidade;
    private double preco;
    private Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

//    public int getIdProduto() {
//        return idProduto;
//    }
//
//    public void setIdProduto(int idProduto) {
//        this.idProduto = idProduto;
//    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
