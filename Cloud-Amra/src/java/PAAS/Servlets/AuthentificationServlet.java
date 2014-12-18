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

/**
 *
 * @author camara
 */
public class AuthentificationServlet extends HttpServlet{
    private static final String VUE_ACCUEIL_STUDENT = "/WEB-INF/Student/accueil.jsp";
    private static final String VUE_ACCUEIL_PROFESSEUR = "/WEB-INF/Professor/accueil.jsp";
    private static final String VUE_ACCUEIL_ADMIN = "/WEB-INF/Admin/accueil.jsp";
    private String ATTR_LOGIN="login";
    private String ATTR_PASWWORD="password";
    private String nextPage;
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nextPage=null;
        this.getServletContext().getRequestDispatcher(VUE_ACCUEIL_STUDENT).forward(request, response);

    }
    
    
}
