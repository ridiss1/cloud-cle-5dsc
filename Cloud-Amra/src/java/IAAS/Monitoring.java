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
public class Monitoring extends Thread{
    
    private String user = "root";
    private String pwd = "pppppppp";
    private String host = "192.168.100.10";
    private int port = 22;
    
    public boolean updateContainer(int container, int ram , int cpu) throws JSchException, IOException {
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
    
    public void run(){
        Pve2Api api = new Pve2Api("192.168.100.10","root","pam","pppppppp");
        //Iaas iaas = new Iaas();
        float percent;
        int var;
        List<Container> list = null;
        
        
        while(true){
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
                if (percent > 5){  
                    try {
                    //reale value 70
                    var  = ct.getMemory()/1000000;
                    System.out.println("var   " +var);
                    System.out.println("var +1  " +(++var));
                    //updateTemplate(int container, int ram , int cpu)
                    updateContainer(Integer.parseInt(ct.getVmid()), var+50, 0 );
                    //ct.setMemory(var);
                    //iaas.UpdateContainer(ct);
                    } catch (JSchException | IOException ex) {
                        Logger.getLogger(IaaSManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        
        }

    }
}
