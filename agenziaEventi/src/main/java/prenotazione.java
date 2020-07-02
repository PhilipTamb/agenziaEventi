/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Philip T
 */
public class prenotazione {
    private String nome;
    private String codice;
    
    public prenotazione(String nome, String codice){
        this.nome = nome;
        this.codice = codice;
    }
    
    public String getNome(){
        return nome;
    }
        
    public String getCodice(){
        return codice;
    }
    
}
