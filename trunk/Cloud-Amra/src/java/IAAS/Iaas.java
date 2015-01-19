/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IAAS;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
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
    /**
     * SSH Attributes
     */
   
   
    private int port = 22;
    
    /**
    * Attrites for ssh connection
    */
    
     //Server dist distant
    final  private String address="192.168.100.10"; 
    private String user="root";
    private String password="pppppppp";
   
    /*
  //Server Local R1
    final  private String address="10.201.2.218"; 
    private String user="root";
    private String password="admin";
  */
    /*
     //Server Local INSA
    final  private String address="192.168.100.10"; 
    private String user="root";
    private String password="admin";*/

    public  Iaas(){
    
        /**
         * Connexion au serveur Proxmox
         * param1 = adresse IP Proxmox
         * param2 = login
         * param3 = "pam"
         * param4 = mot de passe
         */
          pve = new Pve2Api(address,user,"pam",password);
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
    public boolean creerContainer ( Container container , int nbrectainer ) {
        boolean result =false;
        try {
            container.setStorage("local");
            container.setIp_address("192.168.1."+nbrectainer);
            container.setOstemplate("local:vztmpl/"+container.getOstemplate());
            pve.createOpenvz("proxmox", container);
            result=true;
        } catch (JSONException | LoginException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        }
        return result;
        
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
    
    /**
     * ALIOUNE WORK
     */
        /**
     *
     * @param containerID
     * @param userID : the ID of user in User table will be used as foreign key
     * @param comments : user's comments to describe the aim of the template
     * @throws JSchException
     * @throws IOException
     */
    public boolean createCustomerTemplate(int containerID, String comments , int idUser) throws JSchException, IOException {

        //boolean result=false;
        String text = "";
        String pattern = ".*Backup job finished successfully.*";
        JSch jsch = new JSch();
        String command = "/root/scripts/createtemplateprof.sh " + containerID + " " + "\"" + comments + "\""+" "+idUser;
        Session session = jsch.getSession(this.user, this.address, this.port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(this.password);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        channel.connect();
        boolean result = channel.isConnected();
        System.out.println("Unix system connected...");
        byte[] tmp = new byte[1024];
        boolean test = true;
        while (test) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
                String line = new String(tmp, 0, i);
                //matcher = pattern.matcher(line);
                text = line;

                System.out.println("Unix system console output: ");
                System.out.println("Here is a line " + line);
            }
            if (channel.isClosed()) {
                test = false;
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
                //ignore
            }
        }
        channel.disconnect();
        session.disconnect();

        //return Pattern.matches(pattern, text);
        return result;
    }
    /**
     * ALIOUNE WORK
     */
        /**
     *
     * @param containerID
     * @param userID : the ID of user in User table will be used as foreign key
     * @param comments : user's comments to describe the aim of the template
     * @throws JSchException
     * @throws IOException
     */
    public boolean createCustomerTemplate(int containerID, String comments) throws JSchException, IOException {

        //boolean result=false;
        String text = "";
        String pattern = ".*Backup job finished successfully.*";
        JSch jsch = new JSch();
        String command = "/root/scripts/createtemplateprof.sh " + containerID + " " + "\"" + comments + "\"";
        Session session = jsch.getSession(this.user, this.address, this.port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(this.password);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        channel.connect();
        boolean result = channel.isConnected();
        System.out.println("Unix system connected...");
        byte[] tmp = new byte[1024];
        boolean test = true;
        while (test) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
                String line = new String(tmp, 0, i);
                //matcher = pattern.matcher(line);
                text = line;

                System.out.println("Unix system console output: ");
                System.out.println("Here is a line " + line);
            }
            if (channel.isClosed()) {
                test = false;
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
                //ignore
            }
        }
        channel.disconnect();
        session.disconnect();

        //return Pattern.matches(pattern, text);
        return result;
    }
    
    
    /**
     * 
     * @param fileName
     * @return
     * @throws JSchException
     * @throws IOException 
     */
    public boolean deleteTemplate(String fileName) throws JSchException, IOException {
        JSch jsch = new JSch();
        String command = "/root/scripts//delete_template.sh " + fileName;
        Session session = jsch.getSession(this.user, this.address, this.port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(this.password);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        channel.connect();
        boolean result = channel.isConnected();
        System.out.println("Unix system connected...");
        byte[] tmp = new byte[1024];
        boolean test = true;
        while (test) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
                String line = new String(tmp, 0, i);

                System.out.println("Unix system console output: ");
                System.out.println("Here is a line " + line);
            }
            if (channel.isClosed()) {
                test = false;
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
                //ignore
            }
        }
        channel.disconnect();
        session.disconnect();

        //return Pattern.matches(pattern, text);
        return result;
    }
}
