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
public class TemplateCreateServlet extends HttpServlet{
     private static final String VUE_VM_PROF = "/WEB-INF/Professor/templateCreate.jsp";
     
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
     }
     
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
     }
    
}
