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
import java.util.ArrayList;
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
public class TemplateShowServlet extends HttpServlet{
     private static final String VUE_VM_PROF = "/WEB-INF/Professor/templateShow.jsp";
     
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Form form = new Form ();
        User prof =null;
        prof = (User) session.getAttribute(ATT_SESSION_USER);
        List <Template> listTemplateByProf= new ArrayList <Template> ();
        
        if (form.getListTemplateByProf(prof).size()==0)
              listTemplateByProf=null;
         else
            listTemplateByProf= form.getListTemplateByProf(prof);
        
        if (listTemplateByProf!=null)
            request.setAttribute("ListTemplate", listTemplateByProf);
        

         this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
     }
     
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
     }
    
}