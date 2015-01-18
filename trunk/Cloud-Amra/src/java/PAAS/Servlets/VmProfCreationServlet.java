/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Duy Duc
 */
public class VmProfCreationServlet extends HttpServlet{
    private static final String VUE_VM_PROF_CREATION = "/WEB-INF/Professor/vmProfCreation.jsp";
    private static final String ATTR_LISTE_TEMPLATE = "ListeTemplate";
    private static final String ATTR_LISTE_GROUPE = "ListeGroupe";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Form form =  new Form ();
        HttpSession session = request.getSession();
                    
        session.setAttribute(ATTR_LISTE_TEMPLATE, form.getListTemplate());
        session.setAttribute(ATTR_LISTE_GROUPE, form.getListGroupe());
       
        this.getServletContext().getRequestDispatcher(VUE_VM_PROF_CREATION).forward(request, response); 

        
    }
}
