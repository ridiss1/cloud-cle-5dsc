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
public class ContactStudentServlet extends HttpServlet{
     private static final String VUE = "/WEB-INF/Student/contact.jsp";
     private static final String ATTR_MAIL="email";
     private static final String ATTR_ERROR="error";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email=request.getParameter(ATTR_MAIL);
        Form form = new Form ();
        form.validationEmail(email);
        
        String error= form.getErrorMail();
        request.setAttribute(ATTR_ERROR, error);
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
