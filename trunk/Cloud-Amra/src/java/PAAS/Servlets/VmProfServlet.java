/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import IAAS.Iaas;
import PAAS.Models.Groupe;
import PAAS.Models.User;
import PAAS.Models.UserGroupe;
import PAAS.Models.Vm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.elbandi.pve2api.data.Container;

/**
 *
 * @author metbill
 */

public class VmProfServlet extends HttpServlet {

    private static final String VUE_VM_PROF = "/WEB-INF/Professor/vmProf.jsp";
    private static final String VUE_VM_PROF_CREATION = "/WEB-INF/Professor/vmProfCreation.jsp";
    private static final String VUE_VM_PROF_MODIFY = "/WEB-INF/Professor/vmProfModify.jsp";
    private static final String ATTR_LISTE_TEMPLATE = "ListeTemplate";
    private static final String ATTR_LISTE_GROUPE = "ListeGroupe";
    private static final String ATTR_LISTE_CONTAINER = "ListeContainer";
    private static final String ATTR_INFO_CONTAINER = "InfoContainer";

    /**
     * **********************Listes des attribusts *************
     */
    private static final String ATTR_TEMPLATE = "template";
    private static final String ATTR_GROUPE = "groupe";
    private static final String ATTR_HOSTNAME = "hostname";
    private static final String ATTR_RAM = "ram";
    private static final String ATTR_CPU = "cpus";
    private static final String ATTR_DISK = "disk";
    private static final String ATTR_PASSWORD = "passwordDefault";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Iaas ias = new Iaas();
        Form form =  new Form ();
        HttpSession session = request.getSession();
                    
        session.setAttribute(ATTR_LISTE_TEMPLATE, form.getListTemplate());
        session.setAttribute(ATTR_LISTE_GROUPE, form.getListGroupe());

        
        User prof = (User) session.getAttribute("sessionUser"); 
        session.setAttribute("RequestDelete", false);
        session.setAttribute("RequestUpdate", false);
        
        //Ajoute des containers
        if (request.getParameter("actionChange") == null){
            List<Container> listContainer = new ArrayList ();        
            
            List<Vm> listVMProf = form.getListVmByProf(prof);
            if (listVMProf != null){
                            
                for(Vm vm : listVMProf){
                    Container c = ias.getContainer(vm.getId());
                    System.out.println("Container " + c.toString());
                    c.setVmid(vm.getId().toString());
                    listContainer.add(c);
                }
                session.setAttribute(ATTR_LISTE_CONTAINER, listContainer);
            }

            this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response); 
        }
        
        //Modification des containers (Update/Delete)
        else{
            
            System.out.println("id====="+ request.getParameter("VMid"));
            Container c = ias.getContainer(Integer.parseInt(request.getParameter("VMid")));
            c.setVmid(request.getParameter("VMid"));
            long disk=Long.parseLong(c.getDisk())/(1024*1024*1024);
            c.setDisk(Long.toString(disk));
            long ram=Long.parseLong(c.getMemory())/(1024*1024);
            c.setMemory(Long.toString(ram));
            session.setAttribute(ATTR_INFO_CONTAINER, c);
            System.out.println("test=" + c.toString());
            this.getServletContext().getRequestDispatcher(VUE_VM_PROF_MODIFY).forward(request, response); 
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Form form = new Form();
        Iaas ias = new Iaas();
        HttpSession session = request.getSession();
        User prof = (User) session.getAttribute("sessionUser");
        
        
        
        if ((request.getParameter(ATTR_TEMPLATE) != null)
                && (request.getParameter(ATTR_GROUPE) != null)
                && (request.getParameter(ATTR_HOSTNAME) != null)
                && (request.getParameter(ATTR_RAM) != null)
                && (request.getParameter(ATTR_CPU) != null)
                && (request.getParameter(ATTR_DISK) != null)
                && (request.getParameter(ATTR_PASSWORD) != null)) {
          
            session.setAttribute("RequestCreation", true);    
            //String template = request.getParameter(ATTR_TEMPLATE).trim();
            String template = form.getTemplateByLibelle(request.getParameter("template")).getFile().trim();
            String groupe = request.getParameter(ATTR_GROUPE).trim();
            String cpu = request.getParameter(ATTR_CPU).trim();
            String disk = request.getParameter(ATTR_DISK).trim();
            String hostname = request.getParameter(ATTR_HOSTNAME).trim();
            String ram = request.getParameter(ATTR_RAM).trim();
            String password = request.getParameter(ATTR_PASSWORD).trim();

            boolean resultat = form.createContainer(template, groupe, cpu, disk, hostname, ram, password, prof);
            session.setAttribute("InfoCreation", resultat);
            this.getServletContext().getRequestDispatcher(VUE_VM_PROF_CREATION).forward(request, response);
        }

    //Mettre à jour les paramètres du container
        if (request.getParameter("actionUpdate") != null) {
            session.setAttribute("RequestUpdate", true);
            System.out.println("RAM=" + request.getParameter("ram"));
            System.out.println("CPUS=" + request.getParameter("cpus"));
            System.out.println("DISK=" + request.getParameter("disk"));
            System.out.println("DISK=" + request.getParameter("VMid"));
            Container c = new Container(request.getParameter("VMid"), request.getParameter("cpus"), request.getParameter("disk"), request.getParameter("ram"));
            ias.UpdateContainer(c);
            System.out.println("UPDATE OK pour VM " + request.getParameter("VMid"));
            //session.setAttribute("InfoUpdate", resultat);
            this.getServletContext().getRequestDispatcher(VUE_VM_PROF_MODIFY).forward(request, response);
        } 
   //Supprimer un container
        
        if (request.getParameter("actionDelete") != null) {
            session.setAttribute("RequestDelete", true);
            String test = ias.deleteContainer(Integer.parseInt(request.getParameter("VMid")));
            System.out.println("test==" + test);
            boolean result = form.removeVM(Integer.parseInt(request.getParameter("VMid")));
            if (result) {
                            
                System.out.println("DELETE OK pour VM " + request.getParameter("VMid"));
            } else {
                                

                System.out.println("Problem DELETE pour VM " + request.getParameter("VMid"));
            }
                            
            session.setAttribute("InfoDelete", result);
            session.setAttribute("InfoVM", request.getParameter("VMid"));
            this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
        }

        

    }

}
