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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.elbandi.pve2api.data.Container;

/**
 *
 * @author camara
 */
public class AuthentificationServlet extends HttpServlet {

    private static final String VUE_ACCUEIL_STUDENT = "/WEB-INF/Student/accueil.jsp";
    private static final String VUE_ACCUEIL_PROFESSEUR = "/WEB-INF/Professor/accueilProf.jsp";
    private static final String VUE_ACCUEIL_ADMIN = "/WEB-INF/Admin/accueilAdmin.jsp";
    private static final String LISTE_CONTAINER = "ListContainer";
    private static final String ATTR_LOGIN = "login";
    private static final String ATTR_PASWWORD = "password";
    private static final String ATTR_ERROR_LOGIN = "login";
    private static final String ATTR_ERROR_PASSWORD = "password";
    private static final String ATTR_MESSAGE = "Message";
    public static final String ATT_SESSION_USER = "sessionUser";
    public static final String ATT_SESSION_LISTVM = "ListVm";
    private String nextPage;
    private List<Container> listContainer = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nextPage = null;
        this.getServletContext().getRequestDispatcher(VUE_ACCUEIL_STUDENT).forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nextPage = null;

        /**
         * ******************ALY NE TOUCHE
         * PAS*******************************************
         */
        HashMap<String, String> message = new HashMap<String, String>();
        String login = request.getParameter(ATTR_LOGIN).trim();
        String password = request.getParameter(ATTR_PASWWORD).trim();
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        Form form = new Form();
        form.validation(login, password);
        message = form.getError();
        User user = form.getUser();

        // session.setAttribute( ATT_SESSION_LISTVM, listVm );
        form.close();

        /**
         * ********************************************************************************
         */
        if (message.get("connexion") != null) {
            nextPage = "/index.jsp";

        } else {
            session.setAttribute(ATT_SESSION_USER, user);
            /**
             * ***********ETUDIANT***************************************************************
             */
            if (form.getType() == 3) {
                /**
                 * ***********Get List Container******************************
                 */

                List<Vm> listVm = form.getListVm(user);

                if (listVm.size() != 0) {
                    listContainer = form.getListContainer(listVm);
                    session.setAttribute(LISTE_CONTAINER, listContainer);
                    form.writeFile(listContainer);

                }
                nextPage = VUE_ACCUEIL_STUDENT;

            }

            /**
             * ***********PROFESSOR***************************************************************
             */
            if (form.getType() == 2) {
                nextPage = VUE_ACCUEIL_PROFESSEUR;
            }

            /**
             * ***********ADMIN***************************************************************
             */
            if (form.getType() == 1) {
                nextPage = VUE_ACCUEIL_ADMIN;
            }
        }

        request.setAttribute(ATTR_MESSAGE, message);

        this.getServletContext().getRequestDispatcher(nextPage).forward(request, response);

    }

}
