/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Philip T
 */
public class agenziaEventi {
        public static void main(String args[]){
        gestoreLista gestore = new gestoreLista();

        threadForeground tf = new threadForeground(gestore);
        threadBackground tb = new threadBackground(gestore);
        
        tf.start();
        tb.start();
              
        
    }
    
}
