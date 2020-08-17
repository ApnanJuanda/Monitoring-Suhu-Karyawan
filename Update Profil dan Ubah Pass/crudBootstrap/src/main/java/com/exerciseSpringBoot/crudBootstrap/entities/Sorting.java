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
public class Sorting implements Serializable {
    @Id
    private String idemployee;
    private int month;
    private float temp1;
    private float temp2;
    
    public Sorting(){
        
    }
     public Sorting(String idemployee){
         this.idemployee = idemployee;
     }
    
     public Sorting(String idemployee, int month){
         this.idemployee = idemployee;
         this.month = month;
     }
     
     public Sorting(String idemployee, int month, float temp1, float temp2){
         this.idemployee = idemployee;
         this.month = month;
         this.temp1 = temp1;
         this.temp2 = temp2;
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
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the temp1
     */
    public float getTemp1() {
        return temp1;
    }

    /**
     * @param temp1 the temp1 to set
     */
    public void setTemp1(float temp1) {
        this.temp1 = temp1;
    }

    /**
     * @return the temp2
     */
    public float getTemp2() {
        return temp2;
    }

    /**
     * @param temp2 the temp2 to set
     */
    public void setTemp2(float temp2) {
        this.temp2 = temp2;
    }
    
}
