package br.com.ecommerce.childplay.model;

public class Imagem {
    private int idImagem;
    private int idProduto;
    private String imagem;
    private String alt;

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdIMagem(int idImagem) {
        this.idImagem = idImagem;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
