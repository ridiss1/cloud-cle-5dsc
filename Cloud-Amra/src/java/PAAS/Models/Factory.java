/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Models;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author camara
 */
public class Factory {

    private EntityManager em;
    private EntityManagerFactory emf;
    private static final String UNIT = "CLOUDAMRAPU";

    public void open() {
        emf = Persistence.createEntityManagerFactory(UNIT);
        em = emf.createEntityManager();
        em.getTransaction().begin();

    }
    
    public List <User> userFindAll () {
        List <User> listUser = null;
        Query query = em.createNamedQuery("User.findAll");
        listUser = (List <User>) query.getResultList();
        return listUser;
        
    }
    
    
    public User userFindByUsername (String userName) {
        User user = null;
        Query query = em.createNamedQuery("User.findByUsername");
        query.setParameter("username", userName);
        user = (User) query.getSingleResult();
        return user;
        
    }
    
    public User userFindByPassword (String password) {
        User user = null;
        Query query = em.createNamedQuery("User.findByPassword");
        query.setParameter("username", password);
        user = (User) query.getSingleResult();
        return user;
        
    }
    
    public User userFindByUserNamePassword (String username, String password) {
        User user = null;
        String requete= "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
        Query query = em.createQuery(requete);
        query.setParameter("username", username);
        query.setParameter("password", password);
        user = (User) query.getSingleResult();
        return user;
        
    }
    
    
    
    

    public void close() {
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    public EntityManager getEntityManager() {
        return this.em;
    }

}
