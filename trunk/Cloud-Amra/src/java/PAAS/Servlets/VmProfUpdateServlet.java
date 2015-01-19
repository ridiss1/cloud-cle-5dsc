/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import IAAS.Iaas;
import PAAS.Models.User;
import PAAS.Models.Vm;
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
 * @author Duy Duc
 */
public class VmProfUpdateServlet extends HttpServlet{
        
    private static final String VUE_VM_PROF = "/WEB-INF/Professor/vmProf.jsp";
    private static final String VUE_VM_PROF_CREATION = "/WEB-INF/Professor/vmProfCreation.jsp";
    private static final String VUE_VM_PROF_MODIFY = "/WEB-INF/Professor/vmProfModify.jsp";
    private static final String ATTR_LISTE_TEMPLATE = "ListeTemplate";
    private static final String ATTR_LISTE_GROUPE = "ListeGroupe";
    private static final String ATTR_LISTE_CONTAINER = "ListeContainer";
    private static final String ATTR_INFO_CONTAINER = "InfoContainer";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Iaas ias = new Iaas();
            HttpSession session = request.getSession();

            request.setAttribute("RequestUpdate", false); 
            Container c = ias.getContainer(Integer.parseInt(request.getParameter("actionChange")));  
            c.setVmid(request.getParameter("actionChange"));
            //System.out.println("id====="+ request.getParameter("VMid"));
            
            
            long disk=Long.parseLong(c.getDisk())/(1024*1024*1024);
            c.setDisk(Long.toString(disk));
            long ram=Long.parseLong(c.getMemory())/(1024*1024);
            c.setMemory(Long.toString(ram));
            //session.setAttribute(ATTR_INFO_CONTAINER, c);
            request.setAttribute(ATTR_INFO_CONTAINER, c);
            System.out.println("test=" + c.toString());
            this.getServletContext().getRequestDispatcher(VUE_VM_PROF_MODIFY).forward(request, response); 
    }
}
