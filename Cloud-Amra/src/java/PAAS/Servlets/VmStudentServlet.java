/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import PAAS.Models.User;
import PAAS.Models.Vm;
import static PAAS.Servlets.AuthentificationServlet.ATT_SESSION_USER;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.elbandi.pve2api.data.Container;

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
      private List<Container> listContainer = null;
      private static final String LISTE_CONTAINER = "ListContainer";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Form form = new Form ();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATT_SESSION_USER);
        
        String requeteStart= request.getParameter("start");
        String requeteStop= request.getParameter("stop");
        request.setAttribute("requete_start", false);
        request.setAttribute("requete_stop", false);
        
        if (requeteStart != null) {
             int vmid= Integer.parseInt(request.getParameter("start"));
            form.startVm(vmid);
            session.setAttribute(CLASS_START, form.getStart());
            request.setAttribute(STATUS_START, form.getStartStatus());
            request.setAttribute("requete_start", true);
            
        }
        
         if (requeteStop != null) {
            int vmid= Integer.parseInt(request.getParameter("stop"));
           form.stopVm(vmid);
           session.setAttribute(CLASS_STOP, form.getStop());
           request.setAttribute(STATUS_STOP, form.getStopStatus());
           request.setAttribute("requete_stop", true);
            
        }
        
        List<Vm> listVm = new ArrayList <Vm> ();
        if (form.getListVm(user).size() ==0)
             listVm = null;
        else
            listVm = form.getListVm(user);
        
        if (listVm != null) {
            listContainer = form.getListContainer(listVm);
            session.setAttribute(LISTE_CONTAINER, listContainer);
            form.writeFile(listContainer);

        }
       
      
        request.setAttribute(CLASS_CONSOLE, form.getConsole());
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
