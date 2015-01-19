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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String text = "";
        String pattern = ".*Backup job finished successfully.*";
        JSch jsch = new JSch();
        String command = "/root/scripts//delete_template.sh " + fileName;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IaaSManager mgt = new IaaSManager();
        try {
            //mgt.createTemplate(101, 5, "commentaire de mister diop");
            System.out.println(mgt.createCustomerTemplate(102, "aly 17h30 tocar version 3.0"));
            //mgt.createCustomerTemplate(102, 1, "tocar version 3.0");
            //mgt.createCustomerTemplate(100, 2, "camara le maure");
            //mgt.createCustomerTemplate(102, 5, "code ernesto diop");
        } catch (JSchException ex) {
            Logger.getLogger(IaaSManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IaaSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
