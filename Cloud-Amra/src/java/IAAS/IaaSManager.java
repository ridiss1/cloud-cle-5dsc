/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IAAS;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.login.LoginException;
import net.elbandi.pve2api.Pve2Api;
import net.elbandi.pve2api.data.Container;
import net.elbandi.pve2api.data.VmOpenvz;
import org.json.JSONException;

/**
 *
 * @author Alioune BA <luno at mozshit.org>
 */
public class IaaSManager {

    private String user = "root";
    private String pwd = "pppppppp";
    private String host = "192.168.100.10";
    private int port = 22;

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
        Session session = jsch.getSession(this.user, this.host, this.port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(this.pwd);
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

    public void registerTemplateProf(int userID, int templateID) {

    }

    public boolean deleteTemplate(String fileName) throws JSchException, IOException {
        JSch jsch = new JSch();
        String command = "/root/scripts/delete_template.sh " + fileName;
        Session session = jsch.getSession(this.user, this.host, this.port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(this.pwd);
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
    
        public boolean updateTemplate(int container, int ram , int cpu) throws JSchException, IOException {
        JSch jsch = new JSch();
        String command;
        if (cpu==0)
             command = "/root/scripts/update_container.sh " + container + " "+ ram;
        else
            command = "/root/scripts/update_container.sh " + container + " "+ ram + " "+ cpu;
        
        Session session = jsch.getSession(this.user, this.host, this.port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(this.pwd);
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
    
    /*public void run(){
        Pve2Api api = new Pve2Api("192.168.100.10","root","pam","pppppppp");
        //Iaas iaas = new Iaas();
        float percent;
        int var;
        List<Container> list = null;
            try {
                list = api.getOpenvzCTs("proxmox");
            } catch (JSONException | LoginException | IOException ex) {
                Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
            }
        Iterator<Container> it = list.iterator();
        while(it.hasNext()){
            Container ct = it.next();
            percent = (Float.parseFloat(ct.getMem_usage())*100)/ct.getMemory();
            System.out.println("vmID " +ct.getVmid());
            System.out.println("Memory Usage " +ct.getMem_usage());
            System.out.println("Memory " +ct.getMemory());
            System.out.println("percentage   " +percent);
            if (percent > 4){  try {
                //reale value 70
                var  = ct.getMemory();
                System.out.println("var   " +var);
                System.out.println("var +1  " +(++var));
                //updateTemplate(int container, int ram , int cpu)
                updateTemplate(Integer.parseInt(ct.getVmid()), ct.getMemory()+50, Integer.parseInt(ct.getCpus()) );
                //ct.setMemory(var);
                //iaas.UpdateContainer(ct);
                } catch (JSchException | IOException ex) {
                    Logger.getLogger(IaaSManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException, LoginException, IOException {
        //IaaSManager mgt = new IaaSManager();
        //mgt.run();
        Monitoring moni = new Monitoring();
        moni.run();
        /**
        Pve2Api api = new Pve2Api("192.168.100.10","root","pam","pppppppp");
        float percent;
        List<Container> list = api.getOpenvzCTs("proxmox");
        Iterator<Container> it = list.iterator();
        while(it.hasNext()){
            Container ct = it.next();
            System.out.println("cpu " +ct.getCpu_usage());
            percent = (Float.parseFloat(ct.getMem_usage())/Float.parseFloat(ct.getMemory()))*100;
            System.out.println("pourcentage " +percent);
        }
        * **/
        /**
        try {
            //mgt.createTemplate(101, 5, "commentaire de mister diop");
            //System.out.println(mgt.createCustomerTemplate(105, "version after SOA presentation"));
            System.out.println(mgt.deleteTemplate("vzdump-openvz-105-2015_01_15-16_41_32.tar.gz"));
            //mgt.createCustomerTemplate(102, 1, "tocar version 3.0");
            //mgt.createCustomerTemplate(100, 2, "camara le maure");
            //mgt.createCustomerTemplate(102, 5, "code ernesto diop");
        } catch (JSchException ex) {
            Logger.getLogger(IaaSManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IaaSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        **/
    }
}
