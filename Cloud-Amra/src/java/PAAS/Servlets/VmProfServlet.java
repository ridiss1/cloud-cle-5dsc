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
public class VmProfServlet extends HttpServlet{
    
    private static final String VUE_VM_PROF = "/WEB-INF/Professor/vmProf.jsp";
    private static final String VUE_VM_PROF_MODIFY = "/WEB-INF/Professor/vmProfModify.jsp";
    private static final String ATTR_LISTE_TEMPLATE="ListeTemplate";  
    private static final String ATTR_LISTE_GROUPE="ListeGroupe";
    private static final String ATTR_LISTE_CONTAINER="ListeContainer";
    private static final String ATTR_INFO_CONTAINER="InfoContainer";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Iaas ias = new Iaas();
        Form form =  new Form ();
        HttpSession session = request.getSession();
                    
        session.setAttribute(ATTR_LISTE_TEMPLATE, form.getListTemplate());
        session.setAttribute(ATTR_LISTE_GROUPE, form.getListGroupe());
        
        User prof = (User) session.getAttribute("sessionUser"); 
        
        //Ajoute des containers
        if (request.getParameter("actionChange") == null){
            List<Container> listContainer = new ArrayList ();        
            

            List<Vm> listVMProf = form.getListVmByProf(prof);
            for(Vm vm : listVMProf){
                Container c = ias.getContainer(vm.getId());
                System.out.println("Container " + c.toString());
                c.setVmid(vm.getId().toString());
                listContainer.add(c);
            }
            session.setAttribute(ATTR_LISTE_CONTAINER, listContainer);

            this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response); 
        }
        
        //Modification des containers (Update/Delete)
        else{
            
            System.out.println("id====="+ request.getParameter("VMid"));
            Container c = ias.getContainer(Integer.parseInt(request.getParameter("VMid")));
            session.setAttribute(ATTR_INFO_CONTAINER, c);
            this.getServletContext().getRequestDispatcher(VUE_VM_PROF_MODIFY).forward(request, response); 
        }


    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Form form = new Form();
        Iaas ias = new Iaas();
        HttpSession session = request.getSession();
        User prof = (User) session.getAttribute("sessionUser");
        
        //Ajouter des containers 
        if (request.getParameter("actionAdd") != null){
                                
            System.out.println("RAM="+request.getParameter("ram"));
            System.out.println("CPUS="+request.getParameter("cpus"));
            System.out.println("DISK="+request.getParameter("disk"));
            System.out.println("TEMPLATE="+request.getParameter("template"));
            System.out.println("GROUPE="+request.getParameter("groupe"));
            System.out.println("HOSTNAME="+request.getParameter("hostname"));
            System.out.println("MDP="+request.getParameter("passwordDefault"));

            //Le groupe choisi n'est pas UNIQUE
            if (!request.getParameter("groupe").equals("Unique")){
                                
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
                        String vmid = form.getVmByStudentAndGroupe(student, groupe).getId().toString();
                        System.out.println("vmid="+vmid);
                        Container container = new Container(request.getParameter("template"),vmid,
                        request.getParameter("cpus"),request.getParameter("disk"),request.getParameter("hostname")+"_"+vmid,
                                request.getParameter("ram"),request.getParameter("passwordDefault")+user.getId());

                        ias.creerContainer(container, Integer.parseInt(vmid));
                        System.out.println("Creation OK pour "+ user.getId());
                    }
                    else{
                        System.out.println("Problem avec l'ajout à la BD");
                    }
                }
            }
            //Le cas le groupe chosi est UNIQUE
            else{
                    System.out.println("Creation le container seuelemtn pour le prof");                
                    //Recuperation du groupe avec sa libelle
                    Groupe groupe = form.getGroupeByLibelle(request.getParameter("groupe"));
                    //Ajout du VM dans la BD
                    boolean result = form.addVM(prof, groupe,request.getParameter("passwordDefault"),prof.getId().intValue());
                    if(result){
                                            
                        String vmid = form.getVmByStudentAndGroupe(prof, groupe).getId().toString();
                        System.out.println("VMID=" + vmid);
                        Container container = new Container(request.getParameter("template"),vmid,
                            request.getParameter("cpus"),request.getParameter("disk"),request.getParameter("hostname")+"_"+vmid,
                                    request.getParameter("ram"),request.getParameter("passwordDefault"));
                        ias.creerContainer(container, Integer.parseInt(vmid));
                    }
                    else{
                        System.out.println("Problem avec l'ajout à la BD");
                    }
            }

            System.out.println("DONE");
        }
        
        //Mettre à jour les paramètres du container
        else if (request.getParameter("actionUpdate") != null){
                        
            System.out.println("RAM="+request.getParameter("ram"));
            System.out.println("CPUS="+request.getParameter("cpus"));
            System.out.println("DISK="+request.getParameter("disk"));
            System.out.println("DISK="+request.getParameter("VMid"));
            Container c = new Container(request.getParameter("VMid"),request.getParameter("cpus"),request.getParameter("disk"),request.getParameter("ram"));
            ias.UpdateContainer(c);
            System.out.println("UPDATE OK pour VM " + request.getParameter("VMid"));
        }
        
        //Supprimer un container
        else{
            ias.deleteContainer(Integer.parseInt(request.getParameter("VMid")));
            boolean result = form.removeVM(Integer.parseInt(request.getParameter("VMid")));
            if (result){
                System.out.println("DELTE OK pour VM " + request.getParameter("VMid"));
            }
            else{
                System.out.println("Problem DELETE pour VM " + request.getParameter("VMid"));
            }
            
        }

       this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);  

    }
    
}
