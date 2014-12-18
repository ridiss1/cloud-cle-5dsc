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
import PAAS.Models.*;
import java.util.HashMap;

/**
 *
 * @author camara
 */
public class AuthentificationServlet extends HttpServlet{
    private static final String VUE_ACCUEIL_STUDENT = "/WEB-INF/Student/accueil.jsp";
    private static final String VUE_ACCUEIL_PROFESSEUR = "/WEB-INF/Professor/accueil.jsp";
    private static final String VUE_ACCUEIL_ADMIN = "/WEB-INF/Admin/accueil.jsp";
    private static final String  ATTR_LOGIN="login";
    private static final String ATTR_PASWWORD="password";
    private static final String ATTR_ERROR_LOGIN="login";
    private static final String ATTR_ERROR_PASSWORD="password";
     private static final String ATTR_MESSAGE="Message";
    private String nextPage;
    
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nextPage=null;
        this.getServletContext().getRequestDispatcher(VUE_ACCUEIL_STUDENT).forward(request, response);

    }
    
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nextPage=null;
       
        HashMap <String,String> message = new HashMap <String,String> (); 
        String login=request.getParameter(ATTR_LOGIN).trim();
        String password=request.getParameter(ATTR_PASWWORD).trim();
        Form form = new Form ();
        form.validation(login, password);
        message= form.getError();
        
        
        
       
        
        if (message.get("connexion")!=null) {
            nextPage= "/index.jsp";
            
        }
        else {
            if (form.getType()==3) {
                nextPage=VUE_ACCUEIL_STUDENT;
            }
        }
        
        request.setAttribute(ATTR_MESSAGE, message);
        
        this.getServletContext().getRequestDispatcher(nextPage).forward(request, response);

    }
    
    
}
