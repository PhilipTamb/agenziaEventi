
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Philip T
 */
public class evento {
    private String id;
    private String descrizione;
    private String data;
    private String luogo;
    private float costo;
    private int maxPersone;
    private int personePrenotate;
    private LinkedList<prenotazione> listaPrenotazioni;
    
    public evento(String id, String descrizione, String data, String luogo, float costo, int maxPersone ){
        this.id = id;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.costo = costo;
        this.maxPersone = maxPersone;
        this.personePrenotate = 0;
        listaPrenotazioni = new LinkedList<prenotazione>();
    }
    
    public String getId(){
        return id;
    }
    
    public String getDescrizione(){
        return descrizione;
    }
    
    public String getData(){
        return data;
    }
    
    public String getLuogo(){
        return luogo;
    }
    
    public float getCosto(){
        return costo;
    }
    
    public int getMaxPersone(){
        return maxPersone;
    }

    public int getPresonePrenotate(){
        return personePrenotate;
    }
    
    public boolean numeroMassimoPrenotazioni(){
        if(maxPersone<personePrenotate){
            return true;
        }
        return false;
    }
           
    public void inserisciPrenotazione(prenotazione p){
        listaPrenotazioni.add(p);
        personePrenotate ++;
    }
    
    public void stampaPrenotazioni(){
        for(prenotazione p: listaPrenotazioni){
            System.out.println("Nome: " + p.getNome() + "   codice fiscale: " + p.getCodice());
        }
    }
    
    public void stampaPrenotazionePersona( String codice, evento e){
        for(prenotazione p: listaPrenotazioni){
            if(p.getCodice().equalsIgnoreCase(codice)){
                System.out.println("Evento: " + e.id + "  nome: " + p.getNome() + "  codice: " + p.getCodice());
            }
        }
    }
    
    public LinkedList<prenotazione> getListaPrenotazioni(){
        return listaPrenotazioni;
    }
    
    public boolean equals(Object o){
            return (id.equals(((evento)o).id));
    }

    
    
}
