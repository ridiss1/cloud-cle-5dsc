/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import PAAS.Models.User;
import PAAS.Models.Vm;
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
 * @author Duy Duc
 */
public class VmProfCreationServlet extends HttpServlet {

    private static final String VUE_VM_PROF_CREATION = "/WEB-INF/Professor/vmProfCreation.jsp";
    private static final String ATTR_LISTE_TEMPLATE = "ListeTemplate";
    private static final String ATTR_LISTE_GROUPE = "ListeGroupe";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Form form = new Form();
        HttpSession session = request.getSession();
        User prof = null;
        prof = (User) session.getAttribute(ATT_SESSION_USER);

        List<Vm> listVmByProf = new ArrayList<Vm>();
        List<Vm> listVmRunByProf = new ArrayList<Vm>();
        if (form.getListVmByProf(prof).size() == 0) {
            listVmByProf = null;
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

        if (listVmRunByProf != null) {
            request.setAttribute("ListVmRun", listVmRunByProf);
        }

        request.setAttribute(ATTR_LISTE_TEMPLATE, form.getListTemplate());
        request.setAttribute(ATTR_LISTE_GROUPE, form.getListGroupe());

        this.getServletContext().getRequestDispatcher(VUE_VM_PROF_CREATION).forward(request, response);

    }
}
