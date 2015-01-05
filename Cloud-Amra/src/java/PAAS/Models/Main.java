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
        //form.validation("atcamara", "passer");
       form.validation("atcamara", "passer");
       
        User user = form.getUser();
       // form.close();
        System.out.println ("Login : "+user.getNom());
        List <Vm> listVm = form.getListVm(user);
        for (Vm vm : listVm) {
            System.out.println ("Login : "+vm.getPassword());
            
        }
        
    }
    
}
