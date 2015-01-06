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

    public Form() {

    }

    public List<Vm> getListVm(User user) {
        List<Vm> listVm = null;
        Factory factory = new Factory();
        factory.open();
        listVm = factory.vmfindAllByUser(user);
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
    
     public List <Template> getListTemplate () {
        List <Template> listTemplate= null;
        Factory factory= new Factory ();
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
            for (Container container : listContainer) {
                String data=container.getHostname();
                String ramUsage=container.getDisk_usage();
                String ramTotal=container.getDisk();
                String concav="";
                String cpu=container.getCpus();
                String cpuUsage=container.getCpu_usage();
                /**************RAM******************************/
                ecrire.println("var "+data+" = [");
                ecrire.println("{");
                ecrire.println("value: "+ramUsage+",");
                ecrire.println("color:\"#F7464A\",");
                ecrire.println("highlight: \"#FF5A5E\",");
                ecrire.println("label: \"RAM Usage\"");
                ecrire.println("},");

                ecrire.println("{");
                ecrire.println("value: "+ramTotal+",");
                ecrire.println("color: \"#4D5360\",");
                ecrire.println("highlight: \"#616774\",");
                ecrire.println("label: \"RAM Total\"");
                ecrire.println("}");
                ecrire.println("];");

                ecrire.println("window.onload = function(){");
                ecrire.println("var ctx = document.getElementById(\"ram"+data+"\").getContext(\"2d\");");
                ecrire.println("window.myPie = new Chart(ctx).Pie("+data+");");
                ecrire.println("};");
                
                /**************CPU******************************/
                
                ecrire.println("var "+data+" = [");
                ecrire.println("{");
                ecrire.println("value: "+cpuUsage+",");
                ecrire.println("color:\"#F7464A\",");
                ecrire.println("highlight: \"#FF5A5E\",");
                ecrire.println("label: \"RAM Usage\"");
                ecrire.println("},");

                ecrire.println("{");
                ecrire.println("value: "+cpu+",");
                ecrire.println("color: \"#4D5360\",");
                ecrire.println("highlight: \"#616774\",");
                ecrire.println("label: \"RAM Total\"");
                ecrire.println("}");
                ecrire.println("];");

                ecrire.println("window.onload = function(){");
                ecrire.println("var ctx = document.getElementById(\"cpu"+data+"\").getContext(\"2d\");");
                ecrire.println("window.myPie = new Chart(ctx).Pie("+data+");");
                ecrire.println("};");
            }

            ecrire.close();

        } catch (IOException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Container> getListContainer(List<Vm> listVm) {

        ArrayList<Container> listCont = new ArrayList<Container>();
        for (Vm vm : listVm) {
            int idVm = vm.getId();
            int i= 0;
            String vmid = Integer.toString(idVm);
            String password = vm.getPassword();
            Container container = null;
            container = new Container("Linux", vmid, "pus", "300", "Vm :"+i, "String memory", password);
            listCont.add(container);
            i++;
        }

        return listCont;

    }

}
