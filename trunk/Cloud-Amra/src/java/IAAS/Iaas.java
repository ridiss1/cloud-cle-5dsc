/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IAAS;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import net.elbandi.pve2api.Pve2Api;
import net.elbandi.pve2api.data.Container;
import net.elbandi.pve2api.data.VncData;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author metbill
 */
public class Iaas {
    
    private Pve2Api pve;
    
    public  Iaas(){
        
        // Connexion au serveur Proxmox
        pve = new Pve2Api("10.201.2.5","root","pam","iaas2014");
        try {
            pve.login();
        } catch (JSONException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void creerContainer ( Container container , int nbrectainer ){
        
        try {
            container.setStorage("local");
            container.setIp_address("192.168.1."+nbrectainer);
            container.setOstemplate("local:vztmpl/"+container.getOstemplate());
            pve.createOpenvz("proxmox", container);
        } catch (JSONException | LoginException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Container getContainer (int vmid){
        
            Container container = null;
            try {
                container =  pve.getOpenvzCT("proxmox", vmid);
            } catch (JSONException | LoginException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        return container;
    }
    
    public String deleteContainer (int vmid){
        
        String result=null;
        try {        
            Container container = this.getContainer(100);
            if ( "running".equals(container.getStatus())){
                pve.shutdownOpenvz("proxmox", vmid);
            }
            result = pve.deleteOpenvz("proxmox", vmid);
        } catch (LoginException | JSONException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void UpdateContainer(Container container){
        
        try {
            pve.updateOpenvz("proxmox", container);
        } catch (JSONException | LoginException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String startContainer (int vmid){
        
        String result=null;
        try {
            result = pve.startOpenvz("proxmox", vmid);
        } catch (LoginException | JSONException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String stopContainer (int vmid){
       
       String result=null;
       try {
           result = pve.shutdownOpenvz("proxmox", vmid);
        } catch (LoginException | JSONException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    
    public String getConsole(int vmid){
        
       String result=null;
       try {
           VncData vnc = pve.consoleOpenvz("proxmox",vmid);
           result =  pve.openConsole("proxmox", vmid, vnc);
        } catch (LoginException | JSONException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    
     public String getStatistics(int vmid,String param){
         
       String result=null;
       try {
           result = pve.getStatistics("proxmox", vmid, param);
        } catch (LoginException | JSONException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
     }
    
    
    
}
