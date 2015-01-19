/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;

import net.elbandi.pve2api.Pve2Api;
import net.elbandi.pve2api.data.Container;
import org.json.JSONException;

/**
 *
 * @author Alioune BA <luno at mozshit.org>
 */
public class Monitoring {
    
         public boolean updateContainer(int container, int ram , int cpu, int disk){
             try {
                 Process execute ;
                 String command;
                 if (cpu==0){
                     command = "/root/scripts/update_container.sh " + container + " "+ ram;
                     
                 }else{
                     command = "/root/scripts/update_container.sh " + container + " "+ ram + " "+ cpu;
                 }
                 
                 execute = Runtime.getRuntime().exec(command);
                 
             } catch (IOException ex) {
                 Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             return true;
         }
        /**
         * 
         */
        public void run(){
        Pve2Api api = new Pve2Api("192.168.100.10","root","pam","pppppppp");
        //Iaas iaas = new Iaas();
        float percent;
        int var;
        List<Container> list = null;
        
        
        while(true){
                    
            try {
                list = api.getOpenvzCTs("proxmox");
                //list = api.getOpenvzCTs(null);
                Iterator<Container> it = list.iterator();
                while(it.hasNext()){
                    Container ct = it.next();
                    percent = (Float.parseFloat(ct.getMem_usage())*100)/Float.parseFloat(ct.getMemory());
                    System.out.println("vmID " +ct.getVmid());
                    System.out.println("Memory Usage " +ct.getMem_usage());
                    System.out.println("Memory " +ct.getMemory());
                    System.out.println("percentage   " +percent);
                    if (percent > 5){
                        
                        //reale value 70
                        var  = Integer.parseInt(ct.getMemory())/1000000;
                        System.out.println("var   " +var);
                        System.out.println("var +1  " +(++var));
                        //updateTemplate(int container, int ram , int cpu)
                        updateContainer(Integer.parseInt(ct.getVmid()), var+50, 0,0);
                        //ct.setMemory(var);
                        //iaas.UpdateContainer(ct);
                        
                    }
                    
                }
            } catch (JSONException | LoginException | IOException ex) {
                Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Monitoring monitoring = new Monitoring();
        monitoring.run();
    }
    
}
