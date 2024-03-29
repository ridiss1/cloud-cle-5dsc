/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import PAAS.Models.Template;
import PAAS.Models.User;
import static PAAS.Servlets.TemplateCreateServlet.ATT_SESSION_USER;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author camara
 */
public class TemplateDeleteServlet extends HttpServlet{
     private static final String VUE_VM_PROF = "/WEB-INF/Professor/templateShow.jsp";
     private static final String ATTR_LIBELLE = "template";
     public static final String ATT_SESSION_USER = "sessionUser";
     
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession();
         Form form = new Form ();
         User prof=null; 
         String libelle=null;
          if (request.getParameter(ATTR_LIBELLE)!=null)
             libelle=request.getParameter(ATTR_LIBELLE).trim();
          
         prof = (User) session.getAttribute(ATT_SESSION_USER);
         
         if ( (libelle!=null) &&  (prof!=null)) {           
             form.deleteTemplate(libelle, prof.getId());           
             request.setAttribute("Libelle", libelle);
             request.setAttribute("deleteTemplate", form.isDeletetemplate());
             request.setAttribute("requete", true);
          }
         else {
             request.setAttribute("requete", false);
         }
         
         
         this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
     }
     
     
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
     }
    
}
