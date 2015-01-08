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
 * @author camara
 */
public class VmStudentServlet extends HttpServlet{
     private static final String VUE = "/WEB-INF/Student/vm.jsp";
     private static final String CLASS_START = "start";
     private static final String CLASS_STOP = "stop";
     private static final String STATUS_START = "statusStart";
     private static final String STATUS_STOP = "statusStop";
     private static final String CLASS_CONSOLE = "console";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Form form = new Form ();
        HttpSession session = request.getSession();
        
        String requeteStart= request.getParameter("start");
        String requeteStop= request.getParameter("stop");
        
        if (requeteStart != null) {
            form.startVm();
            
        }
        
         if (requeteStop != null) {
           form.stopVm();
            
        }
        
        session.setAttribute(CLASS_START, form.getStart());
        session.setAttribute(CLASS_STOP, form.getStop());
        request.setAttribute(STATUS_START, form.getStartStatus());
        request.setAttribute(STATUS_STOP, form.getStopStatus());
        request.setAttribute(CLASS_CONSOLE, form.getConsole());
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
