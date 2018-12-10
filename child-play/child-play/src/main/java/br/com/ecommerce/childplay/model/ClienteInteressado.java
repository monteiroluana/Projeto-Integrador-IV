/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.childplay.model;

/**
 *
 * @author jonas.aribeiro
 */
public class ClienteInteressado {
    
    private String email;
    private String Nome;
    int idProduto;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int produto) {
        this.idProduto = produto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    
}
