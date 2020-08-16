/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.repositories;

import com.exerciseSpringBoot.crudBootstrap.entities.Employee;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO employees(id, idjob, name, group) VALUES (?1,?2,?3,?4);",nativeQuery = true)
    void saveProfil(String idemployee, String idjob, String name, String group);
    
    @Query(value="SELECT COUNT(*) FROM employees where employees.id =?1", nativeQuery= true)
    int check(String idemployee);
}
