package br.com.ecommerce.childplay.model;

import java.sql.Date;
import java.util.List;
import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childPlay.model.Endereco;

public class PlanZ {

    private int idPedido;
    private Date dataPedido;
    private Cliente cliente;
    private Endereco endereco;
    private String status;
    private String tipoPagamento;
    private String protocolo;
    private List<ItemPedido> itens;
    private double valorTotal;
    private double valorFrete;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String listItens() {
        List<ItemPedido> itensPedido = itens;
        String resultado="";
        for (ItemPedido i : itensPedido) {
            System.out.println("item: "+i.getProduto().getNome()+ " "+ i.getProduto().getPreco()+ "\n");
            resultado = resultado + i.getProduto().getNome();
            System.out.println(resultado);
        }
        return resultado;
    }

}
