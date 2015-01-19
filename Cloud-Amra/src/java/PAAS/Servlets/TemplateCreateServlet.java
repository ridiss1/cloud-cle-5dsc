/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

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

/**
 *
 * @author camara
 */
public class TemplateCreateServlet extends HttpServlet {

    private static final String VUE_VM_PROF = "/WEB-INF/Professor/templateCreate.jsp";
    private static final String ATTR_LIBELLE = "libelle";
    private static final String ATTR_TEMPLATE = "template";
    private static final String ATTR_VM = "vm";
    public static final String ATT_SESSION_USER = "sessionUser";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Form form = new Form();
        User prof = null;
        HttpSession session = request.getSession();
        prof = (User) session.getAttribute(ATT_SESSION_USER);

        List<Vm> listVmByProf = new ArrayList<Vm>();
        List<Vm> listVmRunByProf = new ArrayList<Vm>();

        if (form.getListVmByProf(prof).size() == 0) {
            listVmByProf = null;
            System.out.println("******************Nombre : " + listVmByProf.size());
        } else {
            listVmByProf = form.getListVmByProf(prof);

        }

        if (listVmByProf != null) {
            listVmRunByProf = form.getListListVmRunning(listVmByProf);
        } else {
            listVmRunByProf = null;
        }

        if (listVmByProf != null) {
            request.setAttribute("ListVm", listVmByProf);
        }
        System.out.println ("*******************Nombre : "+listVmByProf.size());
        if (listVmRunByProf != null) {
            request.setAttribute("ListVmRun", listVmRunByProf);
        }
        request.setAttribute("get", true);
        request.setAttribute(ATTR_TEMPLATE, form.getTemplate());
        this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String libelle = null;
        Form form = new Form();
        int vmid = Integer.MAX_VALUE;
        User prof = null;

        request.setAttribute("get", false);

        if (request.getParameter(ATTR_LIBELLE) != null) {
            libelle = request.getParameter(ATTR_LIBELLE).trim();
        }
        if (request.getParameter(ATTR_VM) != null) {
            vmid = Integer.parseInt(request.getParameter(ATTR_VM).trim());
        }
        prof = (User) session.getAttribute(ATT_SESSION_USER);

        if ((libelle != null) && (vmid != Integer.MAX_VALUE) && (prof != null)) {
            form.createTemplate(vmid, libelle, prof.getId());
            request.setAttribute("Libelle", libelle);
            request.setAttribute(ATTR_TEMPLATE, form.getTemplate());

        }
        this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
    }

}
