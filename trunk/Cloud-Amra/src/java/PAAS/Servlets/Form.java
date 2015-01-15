/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import IAAS.Iaas;
import PAAS.Models.*;
import com.jcraft.jsch.JSchException;
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
    private boolean template= false;
    private boolean deletetemplate= false;

    public boolean isDeletetemplate() {
        return deletetemplate;
    }

    public void setDeletetemplate(boolean deletetemplate) {
        this.deletetemplate = deletetemplate;
    }

    public boolean getTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }
    private boolean startStatus = false;
    private boolean stopStatus = false;

    public boolean getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(boolean startStatus) {
        this.startStatus = startStatus;
    }

    public boolean getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(boolean stopStatus) {
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
    
        
    /**
     * Ajouter un VM pour un etudiant dans un groupe.
     */
    public boolean addVM(User user, Groupe groupe, String password, int idProf){
        Factory factory = new Factory();
        factory.open();
        factory.vmAddForUser(user, groupe, password, idProf);
        factory.close();
        return true;
    }

    /**
     * Supprimer un VM
     */
    public boolean removeVM(int idVM){
        Factory factory = new Factory();
        factory.open();
        factory.vmDelete(idVM);
        factory.close();
        return true;
    }
    
    /**
     * Recuperer la liste des VMs.
     */
    public List <Vm> getListVm(User user) {
        List <Vm> listVm = null;
        Factory factory = new Factory();
        factory.open();
        if (factory.vmfindAllByUser(user)!=null){
            listVm = (List<Vm>) factory.vmfindAllByUser(user);
        }
        factory.close();
        return listVm;

    }

    /**
     * Recuperer la liste des VMs.
     */
    public List<Vm> getListVmByProf(User user) {
        List<Vm> listVm = null;
        Factory factory = new Factory();
        factory.open();
        if (factory.vmfindAllByProf(user)!=null){
            listVm = (List<Vm>) factory.vmfindAllByProf(user);  
        }
        factory.close();
        return listVm;

    }    
    /**
     * Recuperer la liste des groupes.
     */
    public List<Groupe> getListGroupe(){
        List<Groupe> listGroupe = null;
        Factory factory = new Factory();
        factory.open();
        if (factory.groupefindAll() != null){
            listGroupe = (List<Groupe>)factory.groupefindAll();
        }
        factory.close();
        return listGroupe;
    }
    
        
    /**
     * Recuperer le groupe avec sa libelle.
     */
    public Groupe getGroupeByLibelle(String groupeName){
        Factory factory = new Factory();
        Groupe groupe = null;
        factory.open();
        if (factory.groupefindByLibelle(groupeName) != null){
            groupe = factory.groupefindByLibelle(groupeName);
        }
        factory.close();
        return groupe;
    }

        
    /**
     * Recuperer la liste des etudiants du groupe.
     */
    public List<UserGroupe> getListStudentByGroupe(Groupe groupe){
        List<UserGroupe> listIdStudent = null;
        Factory factory = new Factory();
        factory.open();
        if (factory.studentfindAllByGroupe(groupe) != null){
            listIdStudent = (List<UserGroupe>) factory.studentfindAllByGroupe(groupe);
        }
        factory.close();
        
        return listIdStudent;
    }
    
    /*********************
     * 
     * Add template prof
     */
    
    public void addTemplateProf (TemplateProf templateProf) {
        Factory factory = new Factory();
        factory.open();
        factory.addTemplateProf(templateProf);
        
        factory.close();;
        
    }
    
        
    /**
     * Recuperer l'etudiant du groupe.
     */
    public User getStudentInGroupe(UserGroupe userGroupe){
        User student = null;
        Factory factory = new Factory();
        factory.open();
        if (factory.studentfindInGroupe(userGroupe) != null){
            student = factory.studentfindInGroupe(userGroupe);
        }
        factory.close();
        return student;
    }
    
        
    /**
     * Recuperer le VM avec l'idEtudiant et l'idGroupe.
     */
    public Vm getVmByStudentAndGroupe(User user,Groupe groupe){
        Vm vm=null;
        Factory factory = new Factory();
        factory.open();
        if (factory.vmfindByStudentAndGroupe(user, groupe) != null){
            vm=factory.vmfindByStudentAndGroupe(user, groupe);
        }
        factory.close();
        return vm;
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
        
    /**
     * Valide la connexion.
     */
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
    
    /**
     * Recuperer la liste des templates.
     */
    public List<Template> getListTemplate() {
        List<Template> listTemplate = null;
        Factory factory = new Factory();
        factory.open();
        listTemplate = factory.templatefindAll();
//        factory.close();
        return listTemplate;

    }
    
    public Template getTemplateByLibelle(String libelle) {
        Template template = null;
        Factory factory = new Factory();
        factory.open();
        template = factory.templatefindByLibelle(libelle);
        factory.close();
        return template;

    }
    
    public List<Template> getListTemplateByProf(User user) {
        List<Template> listTemplate = null;
        Factory factory = new Factory();
        factory.open();
        listTemplate = factory.templatefindByProf(user);
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
        String nomFichier = "C:/Users/metbill/Documents/NetBeansProjects/Cloud-Amra/web/js/graphe.js";

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

        List<Container> listCont = new ArrayList ();
        Iaas iaas = new Iaas ();
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
        for (Vm vm : listVm) {
          container= iaas.getContainer(vm.getId());
          container.setVmid(vm.getId().toString());
         /* long disk=Long.parseLong(container.getDisk())/(1024*1024*1024);
          long disk_usage=Long.parseLong(container.getDisk_usage())/(1024*1024*1024);
          long ram2=Long.parseLong(container.getMemory())/(1024*1024);
          long ram_usage=Long.parseLong(container.getMem_usage())/(1024*1024);
          container.setDisk(Long.toString(disk));
          container.setDisk_usage(Long.toString(disk_usage));
          container.setMemory(Long.toString(ram2));
          container.setMem_usage(Long.toString(ram_usage));*/
          System.out.println(container.toString());
       // container = new Container(id[0], hostname[0], ram[0], ramUsage[0], cpu[0], cpuUsage[0], mem[0], memUsage[0]);
        listCont.add(container);
        }
          //  i++;
        //  }

        return listCont;

    }

    public void startVm(int id) {
        Iaas iaas = new Iaas ();
        String status=iaas.getContainer(id).getStatus();
       
        if (status.equals("running")){
            this.startStatus=true;
        }else{
            this.startStatus=false;
            iaas.startContainer(id);
        }
         
    }

    public void stopVm(int id) {
        Iaas iaas = new Iaas ();
        String status=iaas.getContainer(id).getStatus();
        if (status.equals("stopped")){
            this.stopStatus=true;
        }else{
            this.stopStatus=false;
            iaas.stopContainer(id);
        }
        System.out.println("STOP="+iaas.getContainer(id).getStatus()); 
    }
    
    public void createTemplate (int vmid, String libelle, int prof) {
        boolean result =false;
        try {
            Iaas iaas = new Iaas();
            result = iaas.createCustomerTemplate(vmid, libelle);
        } catch (JSchException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result) {
            Template template = getTemplateByLibelle(libelle); 
            int id = template.getId();
            TemplateProf templateProf = new TemplateProf(id, prof);
            addTemplateProf (templateProf);
            
            
        }

        System.out.println ("+++++++++++++++++++++++++++++++++++++Vmid : "+result);
    }
    
    public void deleteTemplate (int idTemplate, String file, int idProf) {
        boolean result = false;
        try {
            Iaas iaas = new Iaas();
            iaas.deleteTemplate(file);
        } catch (JSchException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

}
