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
    
    
    public User userFindByLogin (String login) {
        User user = null;
        Query query = em.createNamedQuery("User.findByLogin");
        query.setParameter("login", login);
        user = (User) query.getSingleResult();
        return user;
        
    }
    
    public User userFindByPassword (String password) {
        User user = null;
        Query query = em.createNamedQuery("User.findByPassword");
        query.setParameter("password", password);
        user = (User) query.getSingleResult();
        return user;
        
    }
    
    public User userFindByLoginPassword (String login, String password) {
        User user = null;
        String requete= "SELECT u FROM User u WHERE u.login = :login AND u.password = :password";
        Query query = em.createQuery(requete);
        query.setParameter("login", login);
        query.setParameter("password", password);
        user = (User) query.getSingleResult();
        return user;
        
    }
    
    public List<Vm> vmfindAllByUser (User user) {
        List <Vm> vm=null;
        String requete= "SELECT v FROM Vm v WHERE v.user =:id";
        Query query = em.createQuery(requete);
        query.setParameter("id", user);
        vm= (List <Vm>) query.getResultList(); 
        return vm;
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
