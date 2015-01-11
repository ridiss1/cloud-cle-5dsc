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
public class VmProfServlet extends HttpServlet{
    
    private static final String VUE_VM_PROF = "/WEB-INF/Professor/vmProf.jsp";
    private static final String VUE_VM_PROF_MODIFY = "/WEB-INF/Professor/vmProfModify.jsp";
    private static final String ATTR_LISTE_TEMPLATE="ListeTemplate";  
    private static final String ATTR_LISTE_GROUPE="ListeGroupe";
    private static final String ATTR_LISTE_VM="ListeVM";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Form form =  new Form ();
        HttpSession session = request.getSession();
        User prof = (User) session.getAttribute("sessionUser"); 
        if (request.getParameter("actionChange") == null){
                   
            session.setAttribute(ATTR_LISTE_TEMPLATE, form.getListTemplate());
            session.setAttribute(ATTR_LISTE_GROUPE, form.getListGroupe());
            session.setAttribute(ATTR_LISTE_VM, form.getListVmByProf(prof));

            this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response); 
        }
        else{
            this.getServletContext().getRequestDispatcher(VUE_VM_PROF_MODIFY).forward(request, response); 
        }


    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("actionAdd").isEmpty()){
                    
            HttpSession session = request.getSession();
            System.out.println("RAM="+request.getParameter("ram"));
            System.out.println("CPUS="+request.getParameter("cpus"));
            System.out.println("DISK="+request.getParameter("disk"));
            System.out.println("TEMPLATE="+request.getParameter("template"));
            System.out.println("GROUPE="+request.getParameter("groupe"));
            System.out.println("MDP="+request.getParameter("passwordDefault"));

            Iaas ias = new Iaas();

            //un container - un etudiant
            /*Container cont = new Container(request.getParameter("template"),
            "101",request.getParameter("cpus"),request.getParameter("disk")
                    ,"test44",request.getParameter("ram"),"test");*/
            //ias.creerContainer(cont, 18);
            User prof = (User) session.getAttribute("sessionUser");

            Form form = new Form();
            //Recuperation du groupe avec sa libelle
            Groupe groupe = form.getGroupeByLibelle(request.getParameter("groupe"));
            //Recuperation la liste des etudiants concernant le groupe
            List<UserGroupe> students = form.getListStudentByGroupe(groupe);
            System.out.println("Creation des containers");
            boolean result=false;

            for(UserGroupe user : students){
                //Recuperation l'etudiant du groupe
                User student = form.getStudentInGroupe(user);
                System.out.println("Nom=" + student.getNom());

                //Ajout du VM dans la BD
                result = form.addVM(student, groupe,request.getParameter("passwordDefault")+student.getNom(),prof.getId().intValue());
                System.out.println("Add VM OK for " + student.getNom());

                //Si l'ajout dans la BD est OK, on cree un container pour cette etudiant
                if (result){
                    System.out.println("Creation CONTAINER");
                    //Creation du VM pour un etudiant
                    Container container = new Container(request.getParameter("template"),form.getVmByStudentAndGroupe(student, groupe).getId().toString(),
                    request.getParameter("cpus"),request.getParameter("disk"),"test",request.getParameter("cpus"),request.getParameter("disk"),
                    request.getParameter("passwordDefault")+user.getId());

                    //Ajout du container
                    ias.creerContainer(container, 1);
                    System.out.println("Creation OK pour "+ user.getId());
                }
                else{
                    System.out.println("Problem avec l'ajout à la BD");
                }
            }
            System.out.println("DONE");
        }
        else if (!request.getParameter("actionUpdate").isEmpty()){
            
        }
        else{
            
        }

        
       this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
        
        

    }
    
}
