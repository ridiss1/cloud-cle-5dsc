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

    public List<User> userFindAll() {
        List<User> listUser = null;
        Query query = em.createNamedQuery("User.findAll");
        listUser = (List<User>) query.getResultList();
        return listUser;

    }

    public User userFindByLogin(String login) {
        User user = null;
        Query query = em.createNamedQuery("User.findByLogin");
        query.setParameter("login", login);
        user = (User) query.getSingleResult();
        return user;

    }

    public User userFindByPassword(String password) {
        User user = null;
        Query query = em.createNamedQuery("User.findByPassword");
        query.setParameter("password", password);
        user = (User) query.getSingleResult();
        return user;

    }

    public User userFindByLoginPassword(String login, String password) {
        User user = null;
        String requete = "SELECT u FROM User u WHERE u.login = :login AND u.password = :password";
        Query query = em.createQuery(requete);
        query.setParameter("login", login);
        query.setParameter("password", password);
        user = (User) query.getSingleResult();
        return user;

    }

    public List<Vm> vmfindAllByUser(User user) {
        //ArrayList <Vm> vm= new ArrayList <Vm> ();
        List<Vm> vm = null;
        String requete = "SELECT v FROM Vm v WHERE v.user =:id";
        Query query = em.createQuery(requete);
        query.setParameter("id", user);
        if (query.getResultList() != null) {
            vm = (List<Vm>) query.getResultList();
        }

        return vm;
    }

    public List<Vm> vmfindAllByProf(User user) {
        List<Vm> vm = null;
        Query query = em.createNamedQuery("Vm.findByProf");
        query.setParameter("prof", user.getId());
        if (query.getResultList() != null) {
            vm = (List<Vm>) query.getResultList();
        }

        return vm;
    }

    public List<Template> templatefindAll() {
        List<Template> templates = null;
        Query query = em.createNamedQuery("Template.findAll");
        templates = (List<Template>) query.getResultList();
        return templates;
    }

    public Template templatefindByLibelle(String libelle) {
        Template templates = null;
        Query query = em.createNamedQuery("Template.findByLibelle");
        query.setParameter("libelle", libelle);
        templates = (Template) query.getSingleResult();
        return templates;
    }

    public List<Template> templatefindByProf(User user) {
        List<Template> templates = new ArrayList();
        List<TemplateProf> templateProf = null;
        // System.out.println ("=============================================== :");
        Query query = em.createNamedQuery("TemplateProf.findByProf");
        query.setParameter("prof", user.getId());
        templateProf = (List<TemplateProf>) query.getResultList();
        for (TemplateProf temp : templateProf) {
            //System.out.println ("+++++++++++++++++++++++++++ : "+temp.getTemplateProfPK().getTemplate());
            Template template = templateFindById(temp.getTemplateProfPK().getTemplate());
            templates.add(template);
            //System.out.println ("+++++++++++++++++++++++++++ : "+template.getLibelle());
        }
        //templates= (List <Template>) query.getResultList(); 
        return templates;
    }

    public List<Groupe> groupefindAll() {
        List<Groupe> groupes = null;
        Query query = em.createNamedQuery("Groupe.findAll");
        groupes = (List<Groupe>) query.getResultList();
        return groupes;
    }

    public Groupe groupefindByLibelle(String groupeName) {
        Groupe groupe = null;
        Query query = em.createNamedQuery("Groupe.findByLibelle");
        query.setParameter("libelle", groupeName);
        groupe = (Groupe) query.getSingleResult();
        return groupe;
    }

    public Template templateFindById(int id) {
        Template template = null;
        Query query = em.createNamedQuery("Template.findById");
        query.setParameter("id", id);
        template = (Template) query.getSingleResult();
        return template;
    }

    public List<UserGroupe> studentfindAllByGroupe(Groupe groupe) {
        List<UserGroupe> idStudents = null;
        String req = "SELECT u FROM UserGroupe u WHERE u.groupe = :groupe";
        Query query = em.createQuery(req);
        query.setParameter("groupe", groupe);
        idStudents = (List<UserGroupe>) query.getResultList();

        return idStudents;
    }

    public User studentfindInGroupe(UserGroupe userGroupe) {
        User student = null;
        Query query = em.createNamedQuery("User.findById");
        query.setParameter("id", (Integer) userGroupe.getUser().getId());
        student = (User) query.getSingleResult();
        return student;
    }

    public void vmAddForUser(Integer id, int user, int prof, int groupe, String password) {

        Vm vm = new Vm(id, user, prof, groupe, password);

        em.persist(vm);

    }

    public Vm vmfindById(int id) {
        Vm vm = new Vm();
        Query query = em.createNamedQuery("Vm.findById");
        query.setParameter("id", id);
        vm = (Vm) query.getSingleResult();
        return vm;
    }

    public boolean vmDelete(int idVM) {
        Vm vm = new Vm();
        String req = "DELETE v FROM Vm v WHERE v.id = :id";
        Query query = em.createQuery(req);
        query.setParameter("id", idVM);
        return true;
    }

    public Vm vmfindByStudentAndGroupe(User user, Groupe groupe) {
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

    public int lastIdVm() {
        int nb = 0;
        List<Vm> listVm = null;
        Query query = em.createNamedQuery("Vm.findAll");

        if (query.getResultList() != null) {
            listVm = (List<Vm>) query.getResultList();
            nb = listVm.size();

        }

        return nb;
    }

}
