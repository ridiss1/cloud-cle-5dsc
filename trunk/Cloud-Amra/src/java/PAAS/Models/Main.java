/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Models;

import java.util.List;

/**
 *
 * @author camara
 */
public class Main {
    
    public static void main (String [] args) {
        Factory factory = new Factory ();
        factory.open();
        if (factory.getEntityManager().isOpen()) {
            System.out.println ("Ouvert");
//            List <User> listUser = factory.userFindAll();
//            for (User user : listUser) {
//                System.out.println (user.getUsername());
//                
//                
//            }
           User user = factory.userFindByUserNamePassword("Christophe", "Chassot"); 
           System.out.println (user.getType());
            
        }
        
        else {
            System.out.println ("Erreur");
        }
    }
    
}
