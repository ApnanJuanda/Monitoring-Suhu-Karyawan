/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.services;

import com.exerciseSpringBoot.crudBootstrap.entities.Employee;
import com.exerciseSpringBoot.crudBootstrap.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employee;
    
    public List<Employee> getAll() {
        return employee.findAll();
    }
    
    public Employee getbyusername(String username){
        return employee.findById(username).get();
    }
    
     public boolean check(String idemployee) {
        if (employee.check(idemployee) == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public void saveProfil(String idemployee, String idjob, String name, String group){
       employee.saveProfil(idemployee, idjob, name, group);
    }
    
    public void deleteEmployee(String username){
        employee.deleteEmployee(username);
    }
    
    public Employee get(String id){
        return employee.findById(id).get();
    }
    
     public void UpdateProfil(String id, String idjob, String name, String group){
        employee.UpdateProfil(id, idjob, name, group);
    }
}
