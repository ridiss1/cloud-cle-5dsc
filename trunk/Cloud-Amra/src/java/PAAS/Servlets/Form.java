/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import PAAS.Models.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.elbandi.pve2api.data.Container;

/**
 *
 * @author camara
 */
public class Form {

    private HashMap<String, String> error = new HashMap<String, String>();
    private String errorMail;
    private User user;
    private int type;
    private String start = "btn-lg active";
    private String stop = "disabled";
    private String console = "disabled";

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }
    private String startStatus = "NOK";
    private String stopStatus = "NOK";

    public String getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(String startStatus) {
        this.startStatus = startStatus;
    }

    public String getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(String stopStatus) {
        this.stopStatus = stopStatus;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public Form() {

    }

    public List <Vm> getListVm(User user) {
        List <Vm> listVm = null;
        Factory factory = new Factory();
        factory.open();
        if (factory.vmfindAllByUser(user)!=null)
            listVm = (List<Vm>) factory.vmfindAllByUser(user);
        factory.close();
        return listVm;

    }

    public void validation(String login, String password) {
        validationLogin(login);
        validationPassword(password);
        validationConnexion(login, password);

    }

    public void validationLogin(String login) {
        try {
            Factory factory = new Factory();
            factory.open();

            User userLogin = factory.userFindByLogin(login);
            if (userLogin == null) {
                error.put("login", "Merci de saisir un login valide");
            }
            factory.close();
            // errorLogin=null;
        } catch (Exception e) {
            error.put("login", "Merci de saisir un login valide");
        }

    }

    public void validationPassword(String password) {
        try {
            Factory factory = new Factory();
            factory.open();
            User userPassword = factory.userFindByPassword(password);
            if (userPassword == null) {
                error.put("password", "Merci de saisir un mot de passe valide");
            }
            factory.close();
            // errorLogin=null;
        } catch (Exception e) {
            error.put("password", "Merci de saisir un mot de passe valide");
        }

    }

    public void validationConnexion(String login, String password) {
        try {

            Factory factory = new Factory();
            factory.open();
            User userConnexion = factory.userFindByLoginPassword(login, password);
            if (userConnexion == null) {
                error.put("connexion", "Connexion impossible");
                user = null;
            } else {
                type = userConnexion.getType();
                user = userConnexion;
            }
            factory.close();
            // errorLogin=null;
        } catch (Exception e) {
            error.put("connexion", "Connexion impossible");
            user = null;
        }

    }

    public List<Template> getListTemplate() {
        List<Template> listTemplate = null;
        Factory factory = new Factory();
        factory.open();
        listTemplate = factory.templatefindAll();
        factory.close();
        return listTemplate;

    }

    /**
     * Valide l'adresse mail saisie.
     */
    public void validationEmail(String email) {
        errorMail = "SUCCESS";
        if (email != null && email.trim().length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                errorMail = "Merci de saisir une adresse mail valide.";
            }
        } else {
            errorMail = "Merci de saisir une adresse mail valide.";
        }
    }

    public HashMap<String, String> getError() {
        return this.error;
    }

    public int getType() {
        return this.type;
    }

    public User getUser() {
        return this.user;
    }

    public String getErrorMail() {
        return this.errorMail;
    }

    public void close() {
        //factory.close();
    }

    public void writeFile(List<Container> listContainer) {
        PrintWriter ecrire;
        String nomFichier = "C:/Users/camara/Documents/NetBeansProjects/Cloud/trunk/Cloud-Amra/web/js/graphe.js";

        try {
            ecrire = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));

            ecrire.println("window.onload = function(){");
            for (Container container : listContainer) {
                String data = "ramdata" + container.getVmid();
                String ramUsage = container.getMem_usage();
                String ramTotal = container.getMemory();
                String memUsage = container.getDisk_usage();
                String memTotal = container.getDisk();
                String concav = "";
                String cpu = container.getCpus();
                String cpuUsage = container.getCpu_usage();
                /**
                 * ************RAM*****************************
                 */
                ecrire.println("var " + data + " = [");
                ecrire.println("{");
                ecrire.println("value: " + ramUsage + ",");
                ecrire.println("color:'#F7464A',");
                ecrire.println("highlight: '#FF5A5E',");
                ecrire.println("label: 'RAM Usage'");
                ecrire.println("},");

                ecrire.println("{");
                ecrire.println("value: " + ramTotal + ",");
                ecrire.println("color: '#4D5360',");
                ecrire.println("highlight: '#616774',");
                ecrire.println("label: 'RAM Total'");
                ecrire.println("}");
                ecrire.println("];");

                /**
                 * ************CPU*****************************
                 */
                String data1 = "cpudata" + container.getVmid();

                ecrire.println("var " + data1 + " = [");
                ecrire.println("{");
                ecrire.println("value: " + cpuUsage + ",");
                ecrire.println("color:'#F7464A',");
                ecrire.println("highlight: '#FF5A5E',");
                ecrire.println("label: 'CPU Usage'");
                ecrire.println("},");

                ecrire.println("{");
                ecrire.println("value: " + cpu + ",");
                ecrire.println("color: '#4D5360',");
                ecrire.println("highlight: '#616774',");
                ecrire.println("label: 'CPU Total'");
                ecrire.println("}");
                ecrire.println("];");

                /**
                 * ************DISK*****************************
                 */
                String data2 = "memdata" + container.getVmid();
                ecrire.println("var " + data2 + " = [");
                ecrire.println("{");
                ecrire.println("value: " + memUsage + ",");
                ecrire.println("color:'#F7464A',");
                ecrire.println("highlight: '#FF5A5E',");
                ecrire.println("label: 'Disk Usage'");
                ecrire.println("},");

                ecrire.println("{");
                ecrire.println("value: " + memTotal + ",");
                ecrire.println("color: '#4D5360',");
                ecrire.println("highlight: '#616774',");
                ecrire.println("label: 'Disk Total'");
                ecrire.println("}");
                ecrire.println("];");

                ecrire.println("var ctx = document.getElementById('" + data + "').getContext('2d');");
                ecrire.println("window.myPie" + data + " = new Chart(ctx).Pie(" + data + ");");

                ecrire.println("var ctx = document.getElementById('" + data1 + "').getContext('2d');");
                ecrire.println("window.myPie" + data1 + " = new Chart(ctx).Pie(" + data1 + ");");

                ecrire.println("var ctx = document.getElementById('" + data2 + "').getContext('2d');");
                ecrire.println("window.myPie" + data2 + " = new Chart(ctx).Pie(" + data2 + ");");

            }
            ecrire.println("};");

            ecrire.close();

        } catch (IOException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Container> getListContainer(List<Vm> listVm) {

        List<Container> listCont = null;
        String[] hostname = {"vm Cloud", "vm M2M"};
        String[] ram = {"400", "460"};
        String[] ramUsage = {"210", "150"};
        String[] cpu = {"540", "430"};
        String[] cpuUsage = {"70", "180"};
        String[] mem = {"600", "510"};
        String[] memUsage = {"10", "100"};
        String[] id = {"110", "150"};

        int i = 0;
        //for (Vm vm : listVm) {
        //int idVm = vm.getId();
        
        int idVm = listVm.get(0).getId();

        String vmid = Integer.toString(idVm);
        //String password = vm.getPassword();
        String password = listVm.get(0).getPassword();
        Container container = null;
        //container = new Container(id[i],hostname[i],ram[i],ramUsage[i],cpu[i],cpuUsage[i]);
        container = new Container(id[0], hostname[0], ram[0], ramUsage[0], cpu[0], cpuUsage[0], mem[0], memUsage[0]);
        listCont.add(container);
          //  i++;
        //  }

        return listCont;

    }

    public void startVm() {
        String status = this.startStatus;
        if (!status.equals("NOK")) {
            this.start = "disabled";
            this.stop = "btn-lg active";
            this.console = "btn-lg active";
        }
        
    }

    public void stopVm() {
        String status = this.stopStatus;
        if (!status.equals("NOK")) {
            this.stop = "disabled";
            this.start = "btn-lg active";
            this.console = "disabled";
        }
        
    }

}
