/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IAAS;

import net.elbandi.pve2api.data.Container;

/**
 *
 * @author metbill
 */
public class bill {
    
    public static void main(String[] args) {
        // POUR LES TESTS
        Iaas ias = new Iaas();
        
        /*Container container = new Container("ubuntu-10.04-standard_10.04-4_i386.tar.gz",
        "105","1","1","test2","512","test");
        ias.creerContainer(container, 19);*/
                                
        Container container = new Container("11","1","1","1024");
        ias.UpdateContainer(container);
       System.out.println(ias.getContainer(10).toString());
        
        //System.outprintln(ias.deleteContainer(100));
        
        //System.out.println(ias.getConsole(10;
        
        //System.out.println(ias.getStatistics(101,"hour"));
        
        //System.out.println(System.getProperty("java.home"));
       
        
    }
    
    
}
