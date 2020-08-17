/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "healty_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HealtyStatus.findAll", query = "SELECT h FROM HealtyStatus h")
    , @NamedQuery(name = "HealtyStatus.findById", query = "SELECT h FROM HealtyStatus h WHERE h.id = :id")
    , @NamedQuery(name = "HealtyStatus.findByInformation", query = "SELECT h FROM HealtyStatus h WHERE h.information = :information")})
public class HealtyStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "information")
    private String information;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idhealty", fetch = FetchType.LAZY)
    private List<EmpCondition> empConditionList;

    public HealtyStatus() {
    }

    public HealtyStatus(String id) {
        this.id = id;
    }

    public HealtyStatus(String id, String information) {
        this.id = id;
        this.information = information;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @XmlTransient
    public List<EmpCondition> getEmpConditionList() {
        return empConditionList;
    }

    public void setEmpConditionList(List<EmpCondition> empConditionList) {
        this.empConditionList = empConditionList;
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
        if (!(object instanceof HealtyStatus)) {
            return false;
        }
        HealtyStatus other = (HealtyStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id;
    }
    
}
