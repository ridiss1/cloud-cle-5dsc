/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author camara
 */
@Entity
@Table(name = "Vm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vm.findAll", query = "SELECT v FROM Vm v"),
    @NamedQuery(name = "Vm.findById", query = "SELECT v FROM Vm v WHERE v.id = :id"),
    @NamedQuery(name = "Vm.findByUser", query = "SELECT v FROM Vm v WHERE v.user = :user"),
    @NamedQuery(name = "Vm.findByProf", query = "SELECT v FROM Vm v WHERE v.prof = :prof"),
    @NamedQuery(name = "Vm.findByGroupe", query = "SELECT v FROM Vm v WHERE v.groupe = :groupe"),
    @NamedQuery(name = "Vm.findByPassword", query = "SELECT v FROM Vm v WHERE v.password = :password")})
public class Vm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private int user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prof")
    private int prof;
    @Basic(optional = false)
    @NotNull
    @Column(name = "groupe")
    private int groupe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;

    public Vm() {
    }

    public Vm(Integer id) {
        this.id = id;
    }

    public Vm(Integer id, int user, int prof, int groupe, String password) {
        this.id = id;
        this.user = user;
        this.prof = prof;
        this.groupe = groupe;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getProf() {
        return prof;
    }

    public void setProf(int prof) {
        this.prof = prof;
    }

    public int getGroupe() {
        return groupe;
    }

    public void setGroupe(int groupe) {
        this.groupe = groupe;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vm)) {
            return false;
        }
        Vm other = (Vm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PAAS.Models.Vm[ id=" + id + " ]";
    }
    
}
