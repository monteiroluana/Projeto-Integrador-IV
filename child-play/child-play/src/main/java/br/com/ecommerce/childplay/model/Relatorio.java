
package br.com.ecommerce.childplay.model;

public class Relatorio {
    private double somaMes;
    private String mes;
    private String ano;
    private PlanZ pedido;

    public PlanZ getPedido() {
        return pedido;
    }

    public void setPedido(PlanZ pedido) {
        this.pedido = pedido;
    }

    public double getSomaMes() {
        return somaMes;
    }

    public void setSomaMes(double somaMes) {
        this.somaMes = somaMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
