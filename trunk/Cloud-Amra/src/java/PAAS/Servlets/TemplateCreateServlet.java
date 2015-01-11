/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import PAAS.Models.User;
import PAAS.Models.Vm;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author camara
 */
public class TemplateCreateServlet extends HttpServlet{
     private static final String VUE_VM_PROF = "/WEB-INF/Professor/templateCreate.jsp";
     private static final String ATTR_LIBELLE = "libelle";
      private static final String ATTR_TEMPLATE = "template";
     private static final String ATTR_VM = "vm";
      public static final String ATT_SESSION_USER = "sessionUser";
     
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Form form = new Form ();
         User prof=null;
         HttpSession session = request.getSession();        
         request.setAttribute("get", true);
         request.setAttribute(ATTR_TEMPLATE, form.getTemplate());
         this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
     }
     
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession();
          String libelle=null;
          Form form = new Form ();
          int vmid=Integer.MAX_VALUE;
          User prof=null;
          
          request.setAttribute("get", false);
               
          if (request.getParameter(ATTR_LIBELLE)!=null)
             libelle=request.getParameter(ATTR_LIBELLE).trim();
          if (request.getParameter(ATTR_VM)!=null)
            vmid= Integer.parseInt(request.getParameter(ATTR_VM).trim());
          prof = (User) session.getAttribute(ATT_SESSION_USER);
          
          if ( (libelle!=null) && (vmid!=Integer.MAX_VALUE) &&  (prof!=null)) {
              form.createTemplate(vmid, libelle, prof.getId());
              request.setAttribute("Libelle", libelle);
              request.setAttribute(ATTR_TEMPLATE, form.getTemplate());
              
          }
         this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
     }
    
}
