/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Adam Augusto
 */
public class Comprador implements Serializable {

    /**
     * @return the formaPagamento
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }
    private String cpf;
    private String nomeComprador;
    private String formaPagamento;
    
    public Comprador(String nomeComprador, String cpf){
        this.nomeComprador=nomeComprador;
        this.cpf=cpf;
    }
    public Comprador(String nomeComprador, String cpf, String formaPagamento){
        this.nomeComprador=nomeComprador;
        this.cpf=cpf;
        this.formaPagamento=formaPagamento;
    }
    public Comprador(){
    }
    @Override
    public String toString(){
        return cpf+"-"+nomeComprador+"-"+formaPagamento;
    }
}
