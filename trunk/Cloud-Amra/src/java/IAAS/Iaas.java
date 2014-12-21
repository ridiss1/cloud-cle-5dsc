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
        
        /**
         * Connexion au serveur Proxmox
         * param1 = adresse IP Proxmox
         * param2 = login
         * param3 = "pam"
         * param4 = mot de passe
         */
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
    
    /**
     * 
     * @param container : objet container 
     * @param nbrectainer : utilisé pour numéroter la machine de façcon unique
     * via son IP = 192.168.1.nbreContainer
     */
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
    
    /**
     * 
     * @param vmid = id du container 
     * @return ( Utilisé la methode container.toString pour afficher les résultats retournés )
     */
    public Container getContainer (int vmid){
        
            Container container = null;
            try {
                container =  pve.getOpenvzCT("proxmox", vmid);
            } catch (JSONException | LoginException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        return container;
    }
    
    /**
     * 
     * @param vmid = id du container 
     * @return 
     */
    public String deleteContainer (int vmid){
        
        String result=null;
        try {        
            Container container = this.getContainer(vmid);
            if ( "running".equals(container.getStatus())){
                pve.shutdownOpenvz("proxmox", vmid);
            }
            result = pve.deleteOpenvz("proxmox", vmid);
        } catch (LoginException | JSONException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Mettre à jour les caractériqtiques d'un container en l'occurence : 
     * cpus , diskspace , memory(RAM)
     * @param container 
     */
    public void UpdateContainer(Container container){
        
        try {
            pve.updateOpenvz("proxmox", container);
        } catch (JSONException | LoginException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param vmid=id du container 
     * @return 
     */
    public String startContainer (int vmid){
        
        String result=null;
        try {
            result = pve.startOpenvz("proxmox", vmid);
        } catch (LoginException | JSONException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * 
     * @param vmid = id du container 
     * @return 
     */
    public String stopContainer (int vmid){
       
       String result=null;
       try {
           result = pve.shutdownOpenvz("proxmox", vmid);
        } catch (LoginException | JSONException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    
    /**
     * 
     * @param vmid = id du container 
     * @return lien https vers la console du container  
     */
    public String getConsole(int vmid){
        
       // retourne url de la console 
       Container container = this.getContainer(vmid);
       String url ="https://"+pve.getPve_hostname()+":8006/?console=openvz&novnc=1&vmid="+vmid+"&vmname="+container.getHostname()+"&node=proxmox";
       return url;
    }
    
    /**
     * 
     * @param vmid = id du container 
     * @param param = "hour" ,"day","week","month","year"
     * @return un tableau de json contenant les caractéristiques par 
     * heure de la machine depuis son allumage ( cas "hour") : RRD data
     */
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
