/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "emp_condition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpCondition.findAll", query = "SELECT e FROM EmpCondition e")
    , @NamedQuery(name = "EmpCondition.findByIdempcondition", query = "SELECT e FROM EmpCondition e WHERE e.idempcondition = :idempcondition")
    , @NamedQuery(name = "EmpCondition.findByDate", query = "SELECT e FROM EmpCondition e WHERE e.date = :date")
    , @NamedQuery(name = "EmpCondition.findByTemperature", query = "SELECT e FROM EmpCondition e WHERE e.temperature = :temperature")})
public class EmpCondition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempcondition")
    private Integer idempcondition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "temperature")
    private float temperature;
    @JoinColumn(name = "idemployee", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee idemployee;
    @JoinColumn(name = "idhealty", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private HealtyStatus idhealty;

    public EmpCondition() {
    }

    public EmpCondition(Integer idempcondition) {
        this.idempcondition = idempcondition;
    }

    public EmpCondition(Integer idempcondition, Date date, float temperature) {
        this.idempcondition = idempcondition;
        this.date = date;
        this.temperature = temperature;
    }

    public Integer getIdempcondition() {
        return idempcondition;
    }

    public void setIdempcondition(Integer idempcondition) {
        this.idempcondition = idempcondition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Employee getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(Employee idemployee) {
        this.idemployee = idemployee;
    }

    public HealtyStatus getIdhealty() {
        return idhealty;
    }

    public void setIdhealty(HealtyStatus idhealty) {
        this.idhealty = idhealty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempcondition != null ? idempcondition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpCondition)) {
            return false;
        }
        EmpCondition other = (EmpCondition) object;
        if ((this.idempcondition == null && other.idempcondition != null) || (this.idempcondition != null && !this.idempcondition.equals(other.idempcondition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.exerciseSpringBoot.crudBootstrap.entities.EmpCondition[ idempcondition=" + idempcondition + " ]";
    }
    
}
