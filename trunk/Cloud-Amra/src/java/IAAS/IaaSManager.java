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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alioune BA <luno at mozshit.org>
 */
public class IaaSManager {
    
    private String user = "root";
    private String pwd = "pppppppp";
    private String host = "192.168.100.10";
    private int port = 22;
    
    
    public void createCustomerTemplate (int containerID, int userID, String comments) throws JSchException, IOException {

        JSch jsch = new JSch();
        String command = "/root/scripts/create_template.sh "+containerID+" "+userID+ " "+"\""+comments+"\"";
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
        System.out.println("Unix system connected...");
        byte[] tmp = new byte[1024];
        boolean test=true;
        while (test){
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
                String line = new String(tmp, 0, i);
                System.out.println("Unix system console output: " +line);
            }
            if (channel.isClosed()){
                test=false;
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee){
                //ignore
            }
        }
        channel.disconnect();
        session.disconnect();       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IaaSManager mgt = new IaaSManager();
        try {
            //mgt.createTemplate(101, 5, "commentaire de mister diop");
            mgt.createCustomerTemplate(101, 1, "commentaire de mister diop");
            mgt.createCustomerTemplate(100, 2, "camara le maure");
            mgt.createCustomerTemplate(102, 5, "code ernesto diop");
        } catch (JSchException ex) {
            Logger.getLogger(IaaSManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IaaSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
