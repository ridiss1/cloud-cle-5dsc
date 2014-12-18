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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author camara
 */
@Entity
@Table(name = "TemplateProf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TemplateProf.findAll", query = "SELECT t FROM TemplateProf t"),
    @NamedQuery(name = "TemplateProf.findById", query = "SELECT t FROM TemplateProf t WHERE t.id = :id")})
public class TemplateProf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "prof", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User prof;
    @JoinColumn(name = "template", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Template template;

    public TemplateProf() {
    }

    public TemplateProf(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getProf() {
        return prof;
    }

    public void setProf(User prof) {
        this.prof = prof;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
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
        if (!(object instanceof TemplateProf)) {
            return false;
        }
        TemplateProf other = (TemplateProf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PAAS.Models.TemplateProf[ id=" + id + " ]";
    }
    
}
