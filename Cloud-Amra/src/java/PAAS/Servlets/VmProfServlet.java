/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PAAS.Servlets;

import IAAS.Iaas;
import PAAS.Models.User;
import java.io.IOException;
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
    // gggggg
     private static final String VUE_VM_PROF = "/WEB-INF/Professor/vmProf.jsp";
      private static final String ATTR_LISTE_TEMPLATE="ListeTemplate";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         Form form =  new Form ();
          HttpSession session = request.getSession();
         
         session.setAttribute(ATTR_LISTE_TEMPLATE, form.getListTemplate());
        this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);

    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        System.out.println("RAM="+request.getParameter("ram"));
        System.out.println("CPUS="+request.getParameter("cpus"));
        System.out.println("DISK="+request.getParameter("disk"));
        System.out.println("TEMPLATE="+request.getParameter("template"));
        
        User utilisateur = (User)session.getAttribute("sessionUser");
        Iaas ias = new Iaas();
        Container cont = new Container(request.getParameter("template"),
        "101",request.getParameter("cpus"),request.getParameter("disk")
                ,"test44",request.getParameter("ram"),"test");
        ias.creerContainer(cont, 18);
        
       
        
        

    }
    
}
