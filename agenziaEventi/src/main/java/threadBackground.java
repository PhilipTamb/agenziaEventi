/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Philip T
 */
public class threadBackground extends Thread {
    gestoreLista gestore;
    
    public threadBackground(gestoreLista gestore){
        this.gestore = gestore;
    }
    
    @Override
    public void run(){
       
            while(true){
                 try{
                    gestore.ricavoTotalePerEvento();
                    gestore.eventiConPostiDisponibili();
                    gestore.totalePersoneEventi();
                    Thread.sleep(5000);
                }catch(InterruptedException i){
                return;           
                }
            } 
        
    }

    
}
