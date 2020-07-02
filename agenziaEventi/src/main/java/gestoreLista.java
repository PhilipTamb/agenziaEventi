
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class gestoreLista {
    private LinkedList<evento> listaEventi;
    private BufferedReader tastiera;
    private LinkedList<prenotazione> personeCancellate;
    private String ricavoTotalePerEvento;
    private String eventiConPostiDisponibili;
    private int totalePersoneEventi;
    
    
    public gestoreLista(){
        this.listaEventi = new LinkedList<evento>();
        this.tastiera = new BufferedReader( new InputStreamReader(System.in));
    }
    
    public synchronized void stampaReport(){
        System.out.println("Ricavo per ciascun evento: ");
        System.out.println(ricavoTotalePerEvento);
        System.out.println("Eventi con posti disponibili: ");
        System.out.println(eventiConPostiDisponibili);
        System.out.println("Totale delle persone prenotate agli eventi: ");
        System.out.println(totalePersoneEventi);
    }
    
    public synchronized void totalePersoneEventi(){
        totalePersoneEventi = 0;
        for(evento e : listaEventi){
            totalePersoneEventi += e.getPresonePrenotate();
        }     
}
    
    public synchronized void eventiConPostiDisponibili(){
        eventiConPostiDisponibili = " ";
        for(evento e : listaEventi){
            if(e.getPresonePrenotate()<e.getMaxPersone()){
            eventiConPostiDisponibili += e.getId() + "\n";
            }
        }        
    }
    
    public synchronized void ricavoTotalePerEvento(){
        ricavoTotalePerEvento = "";
        for(evento e : listaEventi){
            ricavoTotalePerEvento +=( e.getCosto() * e.getPresonePrenotate()) + "\n";
        } 
    }

    public synchronized void stampaPrenotazioni(){
        for(evento e : listaEventi){
             e.stampaPrenotazioni();
        }
        
    }
    
    public synchronized void stampaPrenotaziniPersona(){
        String codice;
        try{
            stampaPrenotazioni();
            System.out.println("Inserisci il codice fiscale: ");
            codice = tastiera.readLine();
            for(evento e : listaEventi){
                 e.stampaPrenotazionePersona(codice,e);
             }
        }catch(IOException e){
            System.out.println("Errore di I/O");
            System.exit(-1);
        }
    }
    
    public synchronized void cancellaEvento(){
        String id;
        float perdita=0.0f;
        try{
            stampaEventi();
            System.out.println("Inserisci l' ID dell' evento che vuoi eliminare");
            id = tastiera.readLine();
            for(evento e : listaEventi){
                if(e.getId().equalsIgnoreCase(id)){
                        if(e.getPresonePrenotate() != 0){
                                personeCancellate = e.getListaPrenotazioni();
                                System.out.println("Prenotazioni all'evento:");
                                for(prenotazione p : personeCancellate){
                                    System.out.println(p.getNome() + p.getCodice());
                                }
                        }

                        perdita = e.getCosto() * e.getPresonePrenotate();
                        System.out.println("La cancellazione ha portato a una perdita di: " + perdita);
                        evento ev = new evento(id, "","","", 0.0f, 0);
                        listaEventi.remove(ev);
                    }
            }            
        }catch(IOException e){
            System.out.println("Errore di I/O");
            System.exit(-1);
        }
    }
    
    public synchronized void stampaEventi(){
        for(evento e : listaEventi){
            System.out.println("Evento: " + e.getId() + "  descrizione: " + e.getDescrizione() + "  luogo: " + e.getLuogo() + "  costo: " + e.getCosto() + " capienza: " + e.getMaxPersone() );
        }
    }
    
    public synchronized void inserisciPrenotazioneDaTastiera() throws numeroMassimoPrenotazioniException{
        String id,nome, codice; 
 
        try{
            stampaEventi();
            System.out.println("Inserisci l' ID dell' evento al quale aggiungere la prenotazione: ");
            id =tastiera.readLine();
            for(evento e : listaEventi){
                if(e.getId().equalsIgnoreCase(id)){
                     if(e.getPresonePrenotate() >= e.getMaxPersone()){
                         throw new numeroMassimoPrenotazioniException();
                     }else{
                        System.out.println("Inserisci il nome: ");
                        nome = tastiera.readLine();
                        System.out.println("Inserisci il codice fiscale: ");
                        codice = tastiera.readLine();

                        prenotazione p = new prenotazione(nome,codice);
                        inserisciPrenotazione(id, p);

                        System.out.println("Prenotazione inserita correttamente");

                     }
                }
             }
        }catch(numeroMassimoPrenotazioniException f){
            System.out.println("L'evento Ã¨ pieno");
            System.exit(-1);
        }catch(IOException e){
            System.out.println("Errore di I/O");
            System.exit(-1);
        }
        
    }
    
    public synchronized boolean esistenzaEvento(String id){
        for(evento e : listaEventi){
            if(e.getId().equalsIgnoreCase(id)){
                return true;
            }
        }
    return false;
    }
    
    public synchronized void inserisciEventoDaTastiera() throws eventoEsisteException{
        String id, descrizione, data, luogo;
        int maxPersone;
        float costo;
        try{
            System.out.println("Inserisci L'ID dell' evento: ");
            id = tastiera.readLine();
            if(esistenzaEvento(id)){
                throw new eventoEsisteException();
            }
            System.out.println("Inserisci la descrizione: ");
            descrizione = tastiera.readLine();
            System.out.println("Inserisci la data: ");
            data = tastiera.readLine();
            System.out.println("Inserisci il luogo: ");
            luogo = tastiera.readLine();
            System.out.println("Inserisci il costo del biglietto: ");
            costo = Float.parseFloat(tastiera.readLine());
            System.out.println("Inserisci il numero massimo di persone: ");
            maxPersone = Integer.parseInt( tastiera.readLine());

            listaEventi.add( new evento(id, descrizione, data, luogo, costo, maxPersone) );
            
            System.out.println("Evento inserito correttamente!");
             
        }catch(eventoEsisteException ee){
            System.out.println("Evento giÃ  esistente");
            System.exit(-1);
        }catch(IOException e){
            System.out.println("Errore di I/O");
            System.exit(-1);
        }
    }
    
    public synchronized void inserisciPrenotazione(String id, prenotazione p){
        for(evento e : listaEventi){
            if(e.getId().equalsIgnoreCase(id)){
                e.inserisciPrenotazione(p);
            }
        }
    }
    
   
    
    public synchronized void caricaPrenotazioni(){
        BufferedReader fp;
        String id,nome, codice;
 
        try{
            fp = new  BufferedReader (new FileReader ( "prenotazioni.txt"));
            id = fp.readLine();
            while(id!= null){
                nome = fp.readLine();
                codice = fp.readLine();

                prenotazione p = new prenotazione(nome,codice);
                inserisciPrenotazione(id, p);
                
                id = fp.readLine();
            }
            
        }catch(FileNotFoundException f){
            System.out.println("Errore file non trovato");
            System.exit(-1);
        }catch(IOException e){
            System.out.println("Errore di I/O");
            System.exit(-1);
        }
        
    }
    
    
    public synchronized void caricaEventi(){
        BufferedReader fp;
        String id, descrizione, data, luogo;
        int maxPersone;
        float costo;
        try{
            fp = new BufferedReader ( new FileReader ("eventi.txt"));
            id = fp.readLine();
            while(id!= null){
                descrizione = fp.readLine();
                data = fp.readLine();
                luogo = fp.readLine();
                costo = Float.parseFloat(fp.readLine());
                maxPersone = Integer.parseInt( fp.readLine());
                
                listaEventi.add( new evento(id, descrizione, data, luogo, costo, maxPersone) );
                id = fp.readLine();
            }
            
        }catch(FileNotFoundException f){
            System.out.println("Errore file non trovato");
            System.exit(-1);
        }catch(IOException e){
            System.out.println("Errore di I/O");
            System.exit(-1);
        }
    }
    
}
