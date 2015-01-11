/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    @NamedQuery(name = "TemplateProf.findByTemplate", query = "SELECT t FROM TemplateProf t WHERE t.templateProfPK.template = :template"),
    @NamedQuery(name = "TemplateProf.findByProf", query = "SELECT t FROM TemplateProf t WHERE t.templateProfPK.prof = :prof")})
public class TemplateProf implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TemplateProfPK templateProfPK;

    public TemplateProf() {
    }

    public TemplateProf(TemplateProfPK templateProfPK) {
        this.templateProfPK = templateProfPK;
    }

    public TemplateProf(int template, int prof) {
        this.templateProfPK = new TemplateProfPK(template, prof);
    }

    public TemplateProfPK getTemplateProfPK() {
        return templateProfPK;
    }

    public void setTemplateProfPK(TemplateProfPK templateProfPK) {
        this.templateProfPK = templateProfPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (templateProfPK != null ? templateProfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemplateProf)) {
            return false;
        }
        TemplateProf other = (TemplateProf) object;
        if ((this.templateProfPK == null && other.templateProfPK != null) || (this.templateProfPK != null && !this.templateProfPK.equals(other.templateProfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PAAS.Models.TemplateProf[ templateProfPK=" + templateProfPK + " ]";
    }
    
}
