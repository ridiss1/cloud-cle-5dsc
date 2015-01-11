/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Models;

import java.util.ArrayList;
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
    
    public List <Vm> vmfindAllByUser (User user) {
        //ArrayList <Vm> vm= new ArrayList <Vm> ();
        List <Vm> vm= null;
        String requete= "SELECT v FROM Vm v WHERE v.user =:id";
        Query query = em.createQuery(requete);
        query.setParameter("id", user);
        if (query.getResultList()!=null)
            vm= (List <Vm>) query.getResultList(); 
        return vm;
    }
    
     public List<Template> templatefindAll () {
        List <Template> templates=null;
        Query query = em.createNamedQuery("Template.findAll");
        templates= (List <Template>) query.getResultList(); 
        return templates;
    }
    
    public List<Groupe> groupefindAll(){
        List<Groupe> groupes=null;
        Query query = em.createNamedQuery("Groupe.findAll");
        groupes = (List<Groupe>) query.getResultList();
        return groupes;
    }
    
    public Groupe groupefindByLibelle(String groupeName){
        Groupe groupe=null;
        Query query = em.createNamedQuery("Groupe.findByLibelle");
        query.setParameter("libelle", groupeName);
        groupe = (Groupe) query.getSingleResult();
        return groupe;
    }
    
    public List<UserGroupe> studentfindAllByGroupe(Groupe groupe){
        List<UserGroupe> idStudents=null;
        String req = "SELECT u FROM UserGroupe u WHERE u.groupe = :groupe"; 
        Query query = em.createQuery(req);  
        query.setParameter("groupe", groupe);       
        idStudents = (List<UserGroupe>) query.getResultList();
        
        return idStudents;
    }
    
    public User studentfindInGroupe(UserGroupe userGroupe){
        User student = null;
        Query query = em.createNamedQuery("User.findById");
        query.setParameter("id", (Integer)userGroupe.getUser().getId());
        student = (User) query.getSingleResult();
        return student;
    }
    public void vmAddForUser(User user, Groupe groupe, String password){
        Vm vm = new Vm();
        vm.setUser(user);
        vm.setGroupe(groupe);
        vm.setPassword(password);
        em.persist(vm);
        
    }
    
    public Vm vmfindByStudentAndGroupe(User user, Groupe groupe){
        Vm vm = null;
        String req = "SELECT v FROM Vm v WHERE v.user = :user AND v.groupe = :groupe";
        Query query = em.createQuery(req);
        query.setParameter("user", user);
        query.setParameter("groupe", groupe);
        vm = (Vm) query.getSingleResult();
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
