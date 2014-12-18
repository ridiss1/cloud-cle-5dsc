/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Models;

import java.util.List;
import PAAS.Servlets.*;

/**
 *
 * @author camara
 */
public class Main {
    
    public static void main (String [] args) {
        
        Form form = new Form ();
        form.validation("atcamara", "passer");
        //System.out.println (form.getError().get("login"));
        System.out.println (form.getError().get("password"));
       // System.out.println (form.getError().get("connexion"));
        
       // Factory factory = new Factory ();
       // factory.open();
       // if (factory.getEntityManager().isOpen()) {
         //   System.out.println ("Ouvert");
//            List <User> listUser = factory.userFindAll();
//            for (User user : listUser) {
//                System.out.println (user.getNom());
//                
//                
//            }
//           User user = factory.userFindByUserNamePassword("atcamara", "passer"); 
//           System.out.println (user.getPrenom());
            
       // }
        
       // else {
         //   System.out.println ("Erreur");
       // }
    }
    
}
