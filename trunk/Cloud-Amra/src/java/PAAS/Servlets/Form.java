/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Servlets;

import PAAS.Models.*;
import java.util.HashMap;

/**
 *
 * @author camara
 */
public class Form {

    private HashMap<String, String> error = new HashMap<String, String>();
    private int type;

    public Form() {

    }
    
        public void validation(String login, String password) {
            validationLogin(login);
            validationPassword(password);
            validationConnexion(login, password);
            
        }


    public void validationLogin(String login) {
        try {
            Factory factory = new Factory();
            factory.open();
            User userLogin = factory.userFindByLogin(login);
            if (userLogin == null) {
                error.put("login", "Merci de saisir un login valide");
            }
            factory.close();
            // errorLogin=null;
        } catch (Exception e) {
            error.put("login", "Merci de saisir un login valide");
        }

    }

    public void validationPassword(String password) {
        try {
            Factory factory = new Factory();
            factory.open();
            User userPassword = factory.userFindByPassword(password);
            if (userPassword == null) {
                error.put("password", "Merci de saisir un mot de passe valide");
            }
            factory.close();
            // errorLogin=null;
        } catch (Exception e) {
            error.put("password", "Merci de saisir un mot de passe valide");
        }

    }

    public void validationConnexion(String login, String password) {
        try {
            Factory factory = new Factory();
            factory.open();
            User user = factory.userFindByLoginPassword(login, password);
            if (user == null) {
                error.put("connexion", "Connexion impossible");
            }
            else {
                type=user.getType();
            }
            factory.close();
            // errorLogin=null;
        } catch (Exception e) {
            error.put("connexion", "Connexion impossible");
        }

    }

    public HashMap<String, String> getError() {
        return this.error;
    }
    public int getType() {
        return this.type;
    }

}
