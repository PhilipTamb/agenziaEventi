/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Philip T
 */
public class threadForeground extends Thread {
    gestoreLista gestore;
    private BufferedReader tastiera;
    
    public threadForeground(gestoreLista gestore){
        this.gestore = gestore;
        this.tastiera = new BufferedReader ( new InputStreamReader (System.in));
    }
    
    public int menu(){
        int scelta = -1;
        do{
                    try{
            
                            System.out.println("\n\n" +
                                    "      1  : Inserisci un nuovo evento\n" +
                                    "      2  : Inserisci una nuova prenotazione\n" +
                                    "      3  : Cancella evento, spostandolo e calcolando la perdita\n" +
                                    "      4  : Stampa tutti gli eventi a cui Ã¨ prenotata una persona\n" +
                                    "      5  : Stampa Report\n"  +
                                    "      6  : Stampa tutti gli eventi\n"  +
                                    "      7  : Stampa prenotazioni\n"  +  
                                    "      0  : Esci\n" + 
                                    "INSERISCI LA SCELTA: -> "
                                    );
                            scelta = Integer.parseInt(tastiera.readLine());

                        }catch(IOException e){
                            System.out.println("Errore di I/O");
                        }catch(NumberFormatException f){
                            System.out.println("Devi inerire un valore numerico");
                        }
            
        }while(scelta<0 || scelta>7 );
            return scelta;  
        }
    

    
    @Override
    public void run(){
                
        gestore.caricaEventi();
        gestore.caricaPrenotazioni();
        
        while(true){
            switch(menu()){
                case 0:
                    System.exit(0);
                    break;
                case 1 :
                {
                    try {
                        gestore.inserisciEventoDaTastiera();
                    } catch (eventoEsisteException ex) {
                        System.out.println("Evento giÃ  esistente");
                    }
                }
                    break;

                case 2 :
                {
                    try {
                        gestore.inserisciPrenotazioneDaTastiera();
                    } catch (numeroMassimoPrenotazioniException ex) {
                       System.out.println("L'evento Ã¨ pieno");
                    }
                }
                    break;

                case 3 :
                    gestore.cancellaEvento();
                    break;
                case 4 :
                    gestore.stampaPrenotaziniPersona();
                    break;
                case 5 :
                    gestore.stampaReport();
                    break;
                case 6 :
                    gestore.stampaEventi();
                    break;
                case 7 :
                    gestore.stampaPrenotazioni();
                    break;
                default:
                    System.out.println("Scelta non consentita");
                    break;
                    
            }
        }
    }
    
}
