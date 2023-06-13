/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Adam Augusto
 */
public class Evento implements Serializable, Comparable<Evento> {

    Evento(){};

    /**
     * @return the datafim
     */
    public Date getDatafim() {
        return datafim;
    }

    /**
     * @param datafim the datafim to set
     */
    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }


    public String getNome() {
        return nome;
    }

  
    public void setNome(String Nome) {
        this.nome = Nome;
    }

   
    public Date getDatainicio() {
        return datainicio;
    }

  
    public String getLocal() {
        return local;
    }


    public void setLocal(String local) {
        this.local = local;
    }


    public int getInscricoes_disponiveis() {
        return inscricoes_disponiveis;
    }

    public void setInscricoes_disponiveis(int inscricoes_disponiveis) {
        this.inscricoes_disponiveis = inscricoes_disponiveis;
    }
    String nome;
    private Date datainicio;
    private Date datafim;
    private String local;
    private int inscricoes_disponiveis;
    double preco;
    
    public Evento(Date datainicio,String local, int inscricoes_disponiveis, String Nome, double preco){
    this.datainicio=datainicio;
    this.local=local;
    this.inscricoes_disponiveis=inscricoes_disponiveis;
    this.nome=Nome;
    this.preco=preco;
    }
    public Evento(String local, String Nome, double preco,int inscricoes_disponiveis){
    this.datainicio=datainicio;
    this.local=local;
    this.inscricoes_disponiveis=inscricoes_disponiveis;
    this.nome=Nome;
    this.preco=preco;
    }
   
    public void alterarData(Date data){
        this.datainicio=data;
    }
    public void alterarLocal(String local){
        this.local=local;
    }
    public void lancarNovoLote(int inscricoes_disponiveis){
        this.inscricoes_disponiveis=inscricoes_disponiveis;
    }
    public String toString(){
        return this.getNome()+" - "+getLocal();
    }

    @Override
    public int compareTo(Evento t) {
       return (this.nome.compareTo(t.getNome()));
    }
}
