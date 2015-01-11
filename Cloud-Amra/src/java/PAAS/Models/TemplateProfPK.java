/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAS.Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author camara
 */
@Embeddable
public class TemplateProfPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "template")
    private int template;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prof")
    private int prof;

    public TemplateProfPK() {
    }

    public TemplateProfPK(int template, int prof) {
        this.template = template;
        this.prof = prof;
    }

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public int getProf() {
        return prof;
    }

    public void setProf(int prof) {
        this.prof = prof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) template;
        hash += (int) prof;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemplateProfPK)) {
            return false;
        }
        TemplateProfPK other = (TemplateProfPK) object;
        if (this.template != other.template) {
            return false;
        }
        if (this.prof != other.prof) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PAAS.Models.TemplateProfPK[ template=" + template + ", prof=" + prof + " ]";
    }
    
}
