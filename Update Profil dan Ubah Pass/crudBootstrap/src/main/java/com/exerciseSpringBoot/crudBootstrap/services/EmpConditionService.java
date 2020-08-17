/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.services;

import com.exerciseSpringBoot.crudBootstrap.entities.EmpCondition;
import com.exerciseSpringBoot.crudBootstrap.repositories.EmpconditionRepository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class EmpConditionService {
    
    @Autowired
    private EmpconditionRepository empcondition;
    
    public List<EmpCondition> getAll(){
        return empcondition.findAllConditions(); ////memanggil findAll biasa
    }
     
    public List<EmpCondition> getByUsername(String username){
        return empcondition.findbyUSERNAME(username);
    }
    
    public List<EmpCondition> getByIlness(){
        return empcondition.findByIlness();
    }
    
    public List<EmpCondition> getBySorting(String id, int month, float temp1, float temp2){
        return empcondition.findBySorting(id, month, temp1, temp2);
    }
    
//    public void savedata(String idemployee, Date date, float temperature){
//        
//        if(temperature<36.5){
//            String idhealty="S01";
//            empcondition.savedata(idemployee, date, temperature, idhealty);
//        }
//        else if(temperature>36.5 && temperature<37.5){
//            String idhealty="S02";
//            empcondition.savedata(idemployee, date, temperature, idhealty);
//        }
//        else{
//            String idhealty="S03";
//            empcondition.savedata(idemployee, date, temperature, idhealty);
//        }
//    }
//    
    public boolean checktanggal(Date tanggal, String username) {
        if (empcondition.count(tanggal, username) == 0) {
            return false;
        } else {
            return true;
        }
    }
    
     public boolean checktanggal2(LocalDate tanggal, String username) {
        if (empcondition.hitung(tanggal, username) == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    //insert sudah bisa tapi tanggal masih bisaberulang
//    public void savedata(EmpCondition empCondition){
//       empcondition.save(empCondition);
//    }
     public void savedata(String idemployee, Date date, float temperature, String idhealty){
       empcondition.savedata(idemployee, date, temperature, idhealty);
    }
     
   public void saveinput(String idemployee, LocalDate date, float temperature, String idhealty){
       empcondition.saveinput(idemployee, date, temperature, idhealty);
    }
     
//    public void deletedata(int idempcondition){
//        empcondition.deleteById(idempcondition);
//    }
    
     public void deletedata(int idempcondition){
        empcondition.deletedata(idempcondition);
    }
    
//    public List<EmpCondition> getByData(int idempcondition){
//        return empcondition.findbyData(idempcondition);
//    }
    
    public EmpCondition get(int id){
        return empcondition.findById(id).get();
    }
    
    public void update(String idemployee, Date date, float temperature, String idhealty, int idempcondition){
        empcondition.update(idemployee, date, temperature, idhealty, idempcondition);
    }
    
    
}
