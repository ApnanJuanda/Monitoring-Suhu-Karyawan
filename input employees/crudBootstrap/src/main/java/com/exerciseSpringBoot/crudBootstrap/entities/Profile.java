/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ASUS
 */
@Entity
public class Profile implements Serializable{
    @Id
    private String idemployee;
    private String idjob;
    private String name;
    private String groupp;
    private String rolee;
    
    public Profile(){
        
    }
    
    public Profile(String idemployee, String idjob, String name, String groupp, String rolee){
        this.idemployee = idemployee;
        this.idjob = idjob;
        this.name = name;
        this.groupp = groupp;
        this.rolee = rolee;
    }

    /**
     * @return the idemployee
     */
    public String getIdemployee() {
        return idemployee;
    }

    /**
     * @param idemployee the idemployee to set
     */
    public void setIdemployee(String idemployee) {
        this.idemployee = idemployee;
    }

    /**
     * @return the idjob
     */
    public String getIdjob() {
        return idjob;
    }

    /**
     * @param idjob the idjob to set
     */
    public void setIdjob(String idjob) {
        this.idjob = idjob;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return groupp;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String groupp) {
        this.groupp = groupp;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return rolee;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String rolee) {
        this.rolee = rolee;
    }
    
    
}
